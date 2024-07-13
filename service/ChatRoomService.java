package application.service;

import application.dto.ChatRoomConverter;
import application.dto.ChatRoomDto;
import application.entity.ChatRoom;
import application.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> getUserChatRooms(String username){
        Optional<Set<ChatRoom>> chats = chatRoomRepository.findChatRoomsByUsername(username);
        if (chats.isPresent())
            return chats.get().stream().map(chat -> ChatRoomConverter.convertToChatRoomDto(chat, username)).toList();
        else
            return null;
    }
}
