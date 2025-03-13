import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

@SpringBootApplication
public class AwsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSpringBootApplication.class, args);

        // Configuração do cliente S3
        S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_1) // Altere para a região desejada
                .credentialsProvider(ProfileCredentialsProvider.create())
                .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                .build();

        ListBucketsResponse bucketsResponse = s3Client.listBuckets();
        bucketsResponse.buckets().forEach(bucket -> System.out.println("Bucket: " + bucket.name()));

        System.out.println("Conexão com AWS S3 configurada com sucesso!");
    }

}
