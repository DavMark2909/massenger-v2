package application.dto.message;

import application.dto.system.SystemMessageDto;
import application.entity.Message;

import java.time.format.DateTimeFormatter;

public class MessageConverter {

    public static MessageDto convertToMessageDto(Message message, String chatName){
        return MessageDto.builder()
                .content(message.getContent())
                .sender(message.getUsername())
                .date(message.getDate().format(DateTimeFormatter.ofPattern("MMMM d, h:mm a")))
                .chatName(chatName)
                .build();
    }

    public static MessageDto convertToMessageDtoFrom(SystemMessageDto dto){
        return MessageDto.builder()
                .content(dto.getContent())
                .sender(dto.getSender())
                .date(dto.getDate())
                .chatName(dto.getChatName())
                .build();
    }
}
