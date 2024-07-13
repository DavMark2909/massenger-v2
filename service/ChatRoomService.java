package application.service;

import application.dto.ChatRoomConverter;
import application.dto.ChatRoomDto;
import application.entity.ChatRoom;
import application.exception.type.NoContentException;
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

    public List<ChatRoomDto> getUserChatRooms(String username) throws NoContentException {
        Set<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsByUsername(username)
                .orElseThrow(() -> new NoContentException("You don't have any chats yet"));
        return chatRooms
                .stream().map(chat -> ChatRoomConverter.convertToChatRoomDto(chat, username)).toList();
    }
}
