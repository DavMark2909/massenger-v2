package application.init;

//import application.entity.ChatRoom;
//import application.entity.Message;
//import application.repository.MessageRepository;
//import application.repository.UserRepository;
//import application.service.ChatRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;

//@Component
public class InitClass /*implements CommandLineRunner*/ {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//    @Autowired
//    private ChatRoomService chatRoomService;
//
//    @Override
//    public void run(String... args) throws Exception {


//        -------------------- initializing block of code -------------------------
//        User user1 = new User();
//        user1.setUsername("Mark2909");
//        user1.setFullname("Mark Davletov");
//        userRepository.save(user1);
//
//        User user2 = new User();
//        user2.setUsername("regdji");
//        user2.setFullname("Lev Davletov");
//        userRepository.save(user2);
//
//        User user3 = new User();
//        user3.setUsername("ivan");
//        user3.setFullname("Ivan Ivanov");
//        userRepository.save(user3);
//
//
//        ChatRoom chatRoom1 = chatRoomService.createChatRoom("Mark2909", "ivan");
//        ChatRoom chatRoom2 = chatRoomService.createChatRoom("Mark2909", "regdji");
//        ChatRoom chatRoom3 = chatRoomService.createChatRoom("ivan", "regdji");


//        ChatRoom chatRoom1 = chatRoomService.findChatRoomByTwoNames("Mark2909", "ivan");
//        ChatRoom chatRoom2 = chatRoomService.findChatRoomByTwoNames("Mark2909", "regdji");
//        ChatRoom chatRoom3 = chatRoomService.findChatRoomByTwoNames("ivan", "regdji");
//
//        createMessage(chatRoom1, "Message one for Ivan from Mark", "Mark2909");
//        createMessage(chatRoom1, "Another interesting message to Ivan from Mark", "Mark2909");
//        createMessage(chatRoom1, "Response to Mark from Ivan", "ivan");
//
//        createMessage(chatRoom2, "Message one for Mark from Lev", "regdji");
//        createMessage(chatRoom2, "Another interesting message to Mark from Lev", "regdji");
//        createMessage(chatRoom2, "Response to Lev from Mark", "Mark2909");
//
//        createMessage(chatRoom3, "Message one for Ivan from Lev", "regdji");
//        createMessage(chatRoom3, "Another interesting message to Ivan from Lev", "regdji");
//        createMessage(chatRoom3, "Response to Lev from Ivan", "ivan");
//    }
//
//    private void createMessage(ChatRoom room, String content, String sender){
//        Message message = new Message();
//        message.setContent(content);
//        message.setSenderUsername(sender);
//        message.setRequestBased(false);
//        room.getMessages().add(message);
//        message.setChat(room);
//        LocalDateTime moment = LocalDateTime.now();
//        message.setTime(moment);
//        Message savedMessage = messageRepository.save(message);
//        room.setLastTime(moment);
//        chatRoomService.saveChatRoom(room);
//    }
}
