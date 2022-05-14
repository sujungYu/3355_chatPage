// package com.example.chatserver.controller;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

// import javax.annotation.PostConstruct;

// // import com.example.chatserver.repository.ChatRepository;

// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Service
// @Slf4j
// @RequiredArgsConstructor
// public class ChatService {

//     private Map<String, ChatRoom> chatRooms;
//     // private final ChatRepository chatRepository;

//     @PostConstruct
//     // 의존관게 주입완료되면 실행되는 코드
//     private void init() {
//         chatRooms = new LinkedHashMap<>();
//     }

//     // 채팅방 불러오기
//     public List<ChatRoom> findAllRoom() {
//         // 채팅방 최근 생성 순으로 반환
//         List<ChatRoom> result = new ArrayList<>(chatRooms.values());
//         Collections.reverse(result);

//         return result;
//     }

//     // 채팅방 하나 불러오기
//     public ChatRoom findById(String roomId) {
//         return chatRooms.get(roomId);
//     }

//     // 채팅방 생성
//     public ChatRoom createRoom(String name) {
//         ChatRoom chatRoom = ChatRoom.create(name);
//         chatRooms.put(chatRoom.getRoomId(), chatRoom);
//         return chatRoom;
//     }

// }
package com.example.chatserver.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.model.ChatMessage;
import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.repository.ChatRepository;
import com.example.chatserver.repository.MessageRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    @Autowired
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    // 의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        // 채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = chatRepository.findAll();
        // Collections.reverse(result);
        // List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        // Collections.reverse(result);

        // return result;
        log.info("result: {}", result);
        return result;
    }

    // 채팅방 하나 불러오기
    public List<ChatRoom> findByRoomId(String roomId) {
        return chatRepository.findByRoomId(roomId);
    }

    @Transactional
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);

        return chatRepository.save(chatRoom);

    }

    @Transactional
    public ChatRoom create(ChatRoom chatRoom) {
        return chatRepository.save(chatRoom);
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        // ChatMessage chatMessage = new ChatMessage();
        // chatRooms.put(chatRoom.getRoomId(), chatMessage);
        return messageRepository.save(chatMessage);
    }

    public List<ChatMessage> findAllMsgs() {
        List<ChatMessage> result = messageRepository.findAll();
        return result;
    }
}