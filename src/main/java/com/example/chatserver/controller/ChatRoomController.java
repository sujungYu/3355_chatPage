package com.example.chatserver.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.model.ChatMessage;
import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.service.ChatService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    // 의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        // return "/";
        // return "/room";
        return "/vue/index";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    // sql 저장 시, html 주소 - post 주소 맞춰주기
    /*
     * /room/enter/{roomId} 에서 save 로직을 실행시켜서 SQL 저장 X
     * 화면이 구성된 주소와 동일한 /room에서 해당 로직을 실행시켜 주어야 저장 가능
     * 
     * --- > ID는 부여됐지만 지정한 roomName이 할당되지 않는 문제 발생
     */

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);

        // return "roomdetail";
        return "/vue/index";
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

}