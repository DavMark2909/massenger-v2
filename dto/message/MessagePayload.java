package application.dto.message;

import lombok.Data;

@Data
public class MessagePayload {
    private String content;
    private String sender;
    private String receiver;
    private boolean requestBased;
}
