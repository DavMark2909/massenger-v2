package application.controller;


import application.dto.ChatRoomDto;
import application.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;

    @GetMapping("/chats/{username}")
    public ResponseEntity<List<ChatRoomDto>> getChatRooms(@PathVariable String username){
        return ResponseEntity.ok(chatRoomService.getUserChatRooms(username));
    }
}
