package application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class ChatRoom {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private LocalDateTime lastTime;

    private boolean personal;
    private boolean systematic;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    private List<Message> messages;

    @ManyToMany(mappedBy = "chats")
    private Set<User> participants;
}
