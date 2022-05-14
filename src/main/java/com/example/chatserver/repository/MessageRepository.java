package com.example.chatserver.repository;

import java.util.List;

import com.example.chatserver.model.ChatMessage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(String roomId);
}