package application.dto;

import application.entity.ChatRoom;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ChatRoomConverter {

    public static ChatRoomDto convertToChatRoomDto(ChatRoom chat, String username){

        String name;

        if (chat.isPersonal())
            name = Arrays.stream(chat.getName().split("_")).filter(word -> !word.equals(username)).findFirst().get();
        else
            name = chat.getName();

        return ChatRoomDto.builder()
                .id(chat.getId())
                .name(name)
                .time(chat.getLastTime().format(DateTimeFormatter.ofPattern("MMMM d, h:mm a")).toString())
                .build();
    }
}
