package application.controller;

import application.dto.message.MessageDto;
import application.dto.message.MessagePayload;
import application.exception.type.NotFoundException;
import application.service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MessageController {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    public void getMessage(@Payload MessagePayload payload) throws NotFoundException {
        log.info("In the socket mapping method");
        MessageDto dto = messageService.saveMessage(payload);
        template.convertAndSend(String.format("/topic/messages/%s", payload.getReceiver()), dto);
    }
}
