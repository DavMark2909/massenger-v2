package application.dto.message;

import application.entity.Message;

import java.time.format.DateTimeFormatter;

public class MessageConverter {

    public static MessageDto convertToMessageDto(Message message, String chatName){
        return MessageDto.builder()
                .content(message.getContent())
                .sender(message.getSenderUsername())
                .date(message.getTime().format(DateTimeFormatter.ofPattern("MMMM d, h:mm a")))
                .chatName(chatName)
                .build();
    }
}
