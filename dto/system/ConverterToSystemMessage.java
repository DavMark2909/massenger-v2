package application.dto.system;

import application.entity.Message;

import java.time.format.DateTimeFormatter;

public class ConverterToSystemMessage {

    public static SystemMessageDto convertToSystemMessageDto(Message message, String chatName, String username){
        return SystemMessageDto.builder()
                .content(message.getContent())
                .sender(message.getUsername())
                .date(message.getDate().format(DateTimeFormatter.ofPattern("MMMM d, h:mm a")))
                .chatName(chatName)
                .username(username)
                .build();
    }
}
