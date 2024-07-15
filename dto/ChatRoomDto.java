package application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomDto {
    private int id;
    private String time;
    private String name;
    private boolean personal;
}
