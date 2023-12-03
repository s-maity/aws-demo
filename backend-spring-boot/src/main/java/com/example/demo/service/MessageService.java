package com.example.demo.service;

import com.example.demo.entity.MessageEntity;
import com.example.demo.entity.MessageJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageJPARepository messageJPARepository;

    public List<String> getMessages() {
        return messageJPARepository.findAll()
                .stream()
                .map(MessageEntity::getMessage)
                .toList();
    }
}
