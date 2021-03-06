// package com.example.chatserver.controller;

// import java.util.UUID;

// import javax.persistence.Entity;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// // @Entity
// public class ChatRoom {

//     private String roomId;
//     private String roomName;

//     public static ChatRoom create(String name) {
//         ChatRoom room = new ChatRoom();
//         room.roomId = UUID.randomUUID().toString();
//         room.roomName = name;
//         return room;
//     }
// }

package com.example.chatserver.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
// @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "room_name")
    private String roomName;

    // 게시글 작성자
    @Column(name = "host")
    private String host;

    // 채팅 참여자
    @Column(name = "guest")
    private String guest;

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }

    @Builder
    public ChatRoom(String roomId, String roomName, String host, String guest) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.host = host;
        this.guest = guest;
    }
}