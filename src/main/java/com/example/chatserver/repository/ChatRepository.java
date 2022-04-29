package com.example.chatserver.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.chatserver.model.ChatMessage;
import com.example.chatserver.model.ChatRoom;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    // private Map<String, ChatRoom> chatRoomMap;
    // List<ChatMessage> findBySendIdAndReceivedId(int s, int r);
    // List<ChatRoom> findById(String id);
    // Optional<ChatRoom> findByid(String id);

    @Override
    List<ChatRoom> findAll();

}
