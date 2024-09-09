package com.fiap.greentracefood.infrastructure.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    @Bean
    public SqsAsyncClient sqsAsyncClient() throws JsonProcessingException {
        return SqsAsyncClient.builder()
                //.endpointOverride(URI.create("http://localhost:4566"))
                .region(Region.of("us-east-1"))
                .credentialsProvider(StaticCredentialsProvider.create(getAwsCredentialsFromSecretsManager()))
                .build();
    }

    private AwsBasicCredentials getAwsCredentialsFromSecretsManager() throws JsonProcessingException {
        SecretsManagerClient secretsClient = SecretsManagerClient.create();
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId("key")
                .build();

        GetSecretValueResponse getSecretValueResponse = secretsClient.getSecretValue(getSecretValueRequest);
        String secret = getSecretValueResponse.secretString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nameNode = mapper.readTree(secret);

        String accessKeyId = nameNode.get("aws_access_key_id").toString();
        String secretAccessKey = nameNode.get("aws_secret_access_key").toString();

        return AwsBasicCredentials.create(accessKeyId, secretAccessKey);
    }
}
