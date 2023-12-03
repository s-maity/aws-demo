package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJPARepository extends JpaRepository<MessageEntity,Integer> {
}
