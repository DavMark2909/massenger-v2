package application.service;

import application.dto.message.MessageConverter;
import application.dto.message.MessageDto;
import application.dto.message.MessagePayload;
import application.dto.system.SystemMessageDto;
import application.entity.ChatRoom;
import application.entity.Message;
import application.entity.User;
import application.exception.type.NoContentException;
import application.exception.type.NotFoundException;
import application.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final ChatRoomService chatRoomService;
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Transactional
    public MessageDto saveMessage(MessagePayload payload) throws NotFoundException {
        ChatRoom chatRoom;
        try {
            chatRoom = chatRoomService.findChatRoomByTwoNames(payload.getReceiver(), payload.getSender());
//            chatRoom = chatRoomService.findChatRoomByTwoNames("Mark2909", "regdji");
        } catch (NoContentException e) {
            chatRoom = chatRoomService.createChatRoom(payload.getReceiver(), payload.getSender());
        }
        Message message = new Message();
        message.setContent(payload.getContent());
        message.setUsername(payload.getSender());
//        message.setSenderUsername("regdji");
        message.setRequestBased(payload.isRequestBased());
        chatRoom.getMessages().add(message);
        message.setChat(chatRoom);
        LocalDateTime moment = LocalDateTime.now();
        message.setDate(moment);
        Message savedMessage = messageRepository.save(message);
        chatRoom.setLastTime(moment);
        chatRoomService.saveChatRoom(chatRoom);
        return MessageConverter.convertToMessageDto(savedMessage, chatRoom.getName());
    }

    @Transactional
    public MessageDto saveSystemMessage(SystemMessageDto dto) throws NotFoundException {
        User userByFullname = userService.findUserByFullname(dto.getReceiverUsername());
        System.out.println(userByFullname.getFullname());
        ChatRoom chat = chatRoomService.findChatRoomById(userByFullname.getChatId());

        Message msg = new Message();
        msg.setRequestBased(true);
        msg.setContent(dto.getContent());
        msg.setUsername("system");
        msg.setChat(chat);
        LocalDateTime now = LocalDateTime.now();
        msg.setDate(now);
        Message savedMessage = messageRepository.save(msg);

        chat.setLastTime(now);
        chatRoomService.saveChatRoom(chat);
        return MessageConverter.convertToMessageDto(savedMessage, chat.getName());
    }
}
