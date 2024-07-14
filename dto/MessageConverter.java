package application.dto;

import application.entity.Message;

import java.time.format.DateTimeFormatter;

public class MessageConverter {

    public static MessageDto convertToMessageDto(Message message){
        return MessageDto.builder()
                .content(message.getContent())
                .sender(message.getSenderUsername())
                .date(message.getTime().format(DateTimeFormatter.ofPattern("MMMM d, h:mm a")))
                .build();
    }
}
