package application.repository;

import application.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    @Query("SELECT c FROM ChatRoom c JOIN c.participants p WHERE p.username = :userId")
    Optional<Set<ChatRoom>> findChatRoomsByUsername(@Param("userId") String username);

    @Query("SELECT c FROM ChatRoom c Join c.participants p WHERE p.username = :param AND c.name = :chatName")
    Optional<ChatRoom> findChatRoomByName(@Param("chatName") String name, @Param("param") String username);

}
