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

    @Builder
    public ChatRoomDto(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public ChatRoom toEntity() {
        return ChatRoom.builder().roomId(roomId).roomName(roomName).build();
    }

}
