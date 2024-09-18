package dev.sparfinder.fal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class BucketService {

    private final S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public BucketService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void putObjectIntoBucket(MultipartFile file, String key) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
    }
}
