package application.controller;


import application.dto.ChatRoomDto;
import application.entity.User;
import application.exception.type.NoContentException;
import application.exception.type.NotFoundException;
import application.service.ChatRoomService;
import application.service.UserService;
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
    private final UserService userService;

    @GetMapping("/chats/{username}")
    public ResponseEntity<List<ChatRoomDto>> getChatRooms(@PathVariable String username) throws NoContentException {
        return ResponseEntity.ok(chatRoomService.getUserChatRooms(username));
    }

    @GetMapping("/chats/find/{name}")
    public ResponseEntity<?> findChat(@PathVariable String name, String username) throws NotFoundException, NoContentException {
        User user = userService.findUserByFullname(name);
        return ResponseEntity.ok(chatRoomService.findChatRoomByTwoNames(user.getUsername(), username));
    }

//    find method to create a new group chat
}
