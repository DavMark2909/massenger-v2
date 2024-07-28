package application.service;

import application.dto.message.MessageConverter;
import application.dto.message.MessageDto;
import application.dto.message.MessagePayload;
import application.dto.system.SystemMessageDto;
import application.entity.ChatRoom;
import application.entity.Message;
import application.exception.type.NoContentException;
import application.exception.type.NotFoundException;
import application.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final ChatRoomService chatRoomService;
    private final MessageRepository messageRepository;

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
        message.setSenderUsername(payload.getSender());
//        message.setSenderUsername("regdji");
        message.setRequestBased(payload.isRequestBased());
        chatRoom.getMessages().add(message);
        message.setChat(chatRoom);
        LocalDateTime moment = LocalDateTime.now();
        message.setTime(moment);
        Message savedMessage = messageRepository.save(message);
        chatRoom.setLastTime(moment);
        chatRoomService.saveChatRoom(chatRoom);
        return MessageConverter.convertToMessageDto(savedMessage, chatRoom.getName());
    }

    @Transactional
    public MessageDto saveSystemMessage(SystemMessageDto dto) throws NotFoundException {
        ChatRoom chatRoom;
        try{
            chatRoom = chatRoomService.findChatRoomByTwoNames(dto.getReceiverUsername(), "system");
        } catch (NoContentException e){
            chatRoom = chatRoomService.createChatRoom(dto.getReceiverUsername(), "system");
        }
        Message msg = new Message();
        msg.setRequestBased(true);
        msg.setContent(dto.getContent());
        msg.setSenderUsername("system");
        chatRoom.getMessages().add(msg);
        msg.setChat(chatRoom);
        LocalDateTime moment = LocalDateTime.now();
        msg.setTime(moment);
        Message savedMessage = messageRepository.save(msg);
        chatRoom.setLastTime(moment);
        chatRoomService.saveChatRoom(chatRoom);
        return MessageConverter.convertToMessageDto(savedMessage, chatRoom.getName());
    }
}
