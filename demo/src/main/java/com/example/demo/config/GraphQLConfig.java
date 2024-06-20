package com.example.demo.config;

import com.example.demo.graphql.QueryResolver;
import com.example.demo.graphql.MutationResolver;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

@Configuration
public class GraphQLConfig {

    private final ResourceLoader resourceLoader;

    public GraphQLConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public GraphQL graphQL(QueryResolver queryResolver, MutationResolver mutationResolver) throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();

        Resource resource = resourceLoader.getResource("classpath:schema.graphqls");
        File schemaFile = ResourceUtils.getFile(resource.getURI());
        typeRegistry.merge(schemaParser.parse(new InputStreamReader(resource.getInputStream())));

        RuntimeWiring runtimeWiring = buildRuntimeWiring(queryResolver, mutationResolver);

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        return GraphQL.newGraphQL(graphQLSchema)
                .instrumentation(new ChainedInstrumentation(
                        Collections.singletonList(new TracingInstrumentation())
                ))
                .queryExecutionStrategy(new AsyncExecutionStrategy())
                .build();
    }

    private RuntimeWiring buildRuntimeWiring(QueryResolver queryResolver, MutationResolver mutationResolver) {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("getAllBooks", environment -> queryResolver.getAllBooks())
                        .dataFetcher("getBookById", environment -> {
                            String id = environment.getArgument("id");
                            return queryResolver.getBookById(id);
                        })
                )
                .type("Mutation", builder -> builder
                        .dataFetcher("addBook", environment -> {
                            String title = environment.getArgument("title");
                            String author = environment.getArgument("author");
                            String description = environment.getArgument("description");
                            return mutationResolver.addBook(title, author, description);
                        })
                        .dataFetcher("updateBook", environment -> {
                            String id = environment.getArgument("id");
                            String title = environment.getArgument("title");
                            String author = environment.getArgument("author");
                            String description = environment.getArgument("description");
                            return mutationResolver.updateBook(id, title, author, description);
                        })
                        .dataFetcher("deleteBook", environment -> {
                            String id = environment.getArgument("id");
                            return mutationResolver.deleteBook(id);
                        })
                )
                .scalar(ExtendedScalars.DateTime)
                .build();
    }
}
