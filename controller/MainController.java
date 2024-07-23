package application.controller;


import application.dto.ChatRoomDto;
import application.dto.message.MessageDto;
import application.dto.message.MessagePayload;
import application.entity.User;
import application.exception.type.NoContentException;
import application.exception.type.NotFoundException;
import application.service.ChatRoomService;
import application.service.MessageService;
import application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final UserService userService;

    @MessageMapping("/chat")
    public void processMessage(@Payload MessagePayload message) throws NotFoundException {
        MessageDto messageDto = messageService.saveMessage(message);
//        template.convertAndSendToUser(message.getReceiver(), "/queue/messages", messageDto);
        template.convertAndSendToUser("Mark2909", "/queue/messages", messageDto);
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatRoomDto>> getChatRooms(@RequestParam("username") String username) throws NoContentException {
        return ResponseEntity.ok(chatRoomService.getUserChatRooms(username));
    }

    @GetMapping("/chats/{sender}/{receiver}")
    public ResponseEntity<?> getChat(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver) throws NoContentException {
        return ResponseEntity.ok(chatRoomService.findChatRoomContentByTwoNames(sender, receiver));
    }

//    returns NotFoundException if a searched user was not found
//    returns NoContentException if a searched user exists but there was no chat before
    @GetMapping("/chats/find/{name}")
    public ResponseEntity<?> findChat(@PathVariable String name, String username) throws NotFoundException, NoContentException {
        User user = userService.findUserByFullname(name);
        return ResponseEntity.ok(chatRoomService.findChatRoomContentByTwoNames(user.getUsername(), username));
    }

//    find method to create a new group chat
}
