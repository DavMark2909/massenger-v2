package application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

    @Id
    private int id;

    private String content;
    private String senderUsername;
    private boolean requestBased;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatRoom chat;
}
