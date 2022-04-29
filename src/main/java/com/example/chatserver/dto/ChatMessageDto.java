package com.example.chatserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatMessageDto {
    public enum MessageType {
        ENTER, TALK, JOIN
    }

    private MessageType type;
    // 채팅방 ID
    private String roomId;
    // 보내는 사람
    private String sender;
    // 내용
    private String message;

    @Builder
    public ChatMessageDto(String roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
