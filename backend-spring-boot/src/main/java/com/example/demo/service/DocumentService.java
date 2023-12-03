package com.example.demo.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.demo.dto.DocumentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentService {

    private final AmazonS3 amazonS3;
    private final String BUCKET_NAME = "smaity-demo-app-files";


    public List<String> getAllBuckets() {
        return amazonS3.listBuckets()
                .stream()
                .map(Bucket::getName)
                .toList();

    }

    private DocumentDto map(Bucket bucket) {
        return DocumentDto.builder()
                .name(bucket.getName())
                .build();
    }

    public void upload(MultipartFile file) {

    }

    public List<DocumentDto> getAllDocuments() {
        List<DocumentDto> result = new ArrayList<>();
        try {
            /* Get first batch of objects in a given bucket */
            ObjectListing objects = amazonS3.listObjects(BUCKET_NAME);

            /* Recursively get all the objects inside given bucket */
            if (objects != null && objects.getObjectSummaries() != null) {
                while (true) {
                    for (S3ObjectSummary summary : objects.getObjectSummaries()) {
                        System.out.println("Object Id :" + summary.getKey());
                        var doc = DocumentDto.builder()
                                .name(summary.getKey())
                                .size(summary.getSize())
                                .build();
                        result.add(doc);
                    }

                    if (objects.isTruncated()) {
                        objects = amazonS3.listNextBatchOfObjects(objects);
                    } else {
                        break;
                    }
                }
            }

        } catch (AmazonServiceException e) {

            System.out.println(e.getErrorMessage());

        }
//        finally {
//
//            if (amazonS3 != null) {
//                amazonS3.shutdown();
//            }
//        }
        return result;
    }
}
