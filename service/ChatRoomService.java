package application.service;

import application.dto.ChatRoomConverter;
import application.dto.ChatRoomDto;
import application.dto.message.MessageConverter;
import application.dto.message.MessageDto;
import application.entity.ChatRoom;
import application.entity.User;
import application.exception.type.NoContentException;
import application.exception.type.NotFoundException;
import application.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public List<ChatRoomDto> getUserChatRooms(String username) throws NoContentException {
        Set<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsByUsername(username)
                .orElseThrow(() -> new NoContentException("You don't have any chats yet"));
        return chatRooms
                .stream().map(chat -> ChatRoomConverter.convertToChatRoomDto(chat, username)).toList();
    }

    public String getRoomName(String username, String username1){
        String[] names = {username, username1};
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        return String.format("%s_%s", names[0], names[1]);
    }

    public ChatRoom findChatRoomByTwoNames(String username, String sender) throws NoContentException, NotFoundException {
        if (Objects.equals(sender, "system")){
            User userByUsername = userService.findUserByUsername(username);
            return chatRoomRepository.findChatRoomById(userByUsername.getChatId()).orElseThrow(() -> new NoContentException("Could not find a system message"));
        } else {
            String name = getRoomName(username, sender);
            return chatRoomRepository.findRealChatRoomByName(name, sender).orElseThrow(() -> new NoContentException("Here are no messages yet"));
        }
    }



    public List<MessageDto> findChatRoomContentByTwoNames(String username, String searcher) throws NoContentException, NotFoundException {
        ChatRoom chatRoom = findChatRoomByTwoNames(username, searcher);
        return chatRoom.getMessages().stream().map(message -> MessageConverter.convertToMessageDto(message, chatRoom.getName())).toList();
    }

    public ChatRoom createChatRoom(String receiver, String sender) throws NotFoundException {
        ChatRoom room = new ChatRoom();
        User first = userService.findUserByUsername(receiver);
        User second = userService.findUserByUsername(sender);
        room.setName(getRoomName(receiver, sender));
        room.setParticipants(Set.of(first, second));
        room.setPersonal(true);
        ChatRoom savedRoom = chatRoomRepository.save(room);
        first.getChats().add(savedRoom);
        second.getChats().add(savedRoom);
        userService.saveUser(first);
        userService.saveUser(second);
        return savedRoom;
    }

    public ChatRoom findChatRoomById(int id) throws NotFoundException {
        return chatRoomRepository.findChatRoomById(id).orElseThrow(() -> new NotFoundException("Didnt find a system chat"));
    }

    public void saveChatRoom(ChatRoom chatRoom){
        chatRoomRepository.save(chatRoom);
    }
}
