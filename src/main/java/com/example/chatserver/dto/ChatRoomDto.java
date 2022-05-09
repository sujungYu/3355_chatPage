package com.example.chatserver.dto;

import java.util.UUID;

import com.example.chatserver.model.ChatRoom;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatRoomDto {
    private Long id;
    private String roomId;
    private String roomName;
    private String host;
    private String guest;

    @Builder
    public ChatRoomDto(String roomId, String roomName, String host, String guest) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.host = host;
        this.guest = guest;
    }

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .roomId(roomId).roomName(roomName)
                .host(host).guest(guest)
                .build();
    }

}