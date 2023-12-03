package com.example.demo.controller;

import com.example.demo.dto.DocumentListDto;
import com.example.demo.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/docs")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/buckets")
    public List<String> getBuckets() {
        return documentService.getAllBuckets();
    }


    @GetMapping
    public DocumentListDto getALlDocuments() {
        return DocumentListDto.builder()
                .documents(documentService.getAllDocuments())
                .build();
    }

    @PostMapping
    public void upload(MultipartFile file) {
        documentService.upload(file);
    }

}
