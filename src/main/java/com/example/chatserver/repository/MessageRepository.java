package com.example.chatserver.repository;

import com.example.chatserver.model.ChatMessage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

}
