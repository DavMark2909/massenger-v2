package application.dto.system;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemMessageDto {
    private String username;
    private String content;
    private String sender;
    private String date;
    private String chatName;
}
