package com.example.chatserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chatmessage")
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, JOIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "type")
    private MessageType type;
    // 채팅방 ID
    @Column(nullable = false, name = "room_id")
    private String roomId;
    // 보내는 사람
    @Column(nullable = false, name = "sender")
    private String sender;
    // 내용
    @Column(nullable = false, name = "message")
    private String message;

    @Builder
    public ChatMessage(String roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}