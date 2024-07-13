package application.controller;


import application.dto.ChatRoomDto;
import application.exception.type.NoContentException;
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
    public ResponseEntity<List<ChatRoomDto>> getChatRooms(@PathVariable String username) throws NoContentException {
        return ResponseEntity.ok(chatRoomService.getUserChatRooms(username));
    }
}
