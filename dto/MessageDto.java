package application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private String content;
    private String sender;
    private String date;
}
