package application.service;

import application.dto.ChatRoomConverter;
import application.dto.ChatRoomDto;
import application.dto.MessageConverter;
import application.dto.MessageDto;
import application.entity.ChatRoom;
import application.exception.type.NoContentException;
import application.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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

    private String getRoomName(String username, String username1){
        String[] names = {username, username1};
        Arrays.sort(names);
        return String.format("%s_%s", names[0], names[1]);
    }

    public List<MessageDto> findChatRoomByTwoNames(String username, String searcher) throws NoContentException {
        String name = getRoomName(username, searcher);
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByName(name, searcher).orElseThrow(() -> new NoContentException("Here are no messages yet"));
        return chatRoom.getMessages().stream().map(MessageConverter::convertToMessageDto).toList();
    }

}
