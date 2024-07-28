package application.dto.system;

import lombok.Data;

@Data
public class SystemMessageDto {
    private String receiverUsername;
    private String content;
}
