package calibri.com.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptReq {

    public String model;
    public List<Message> messages;
    public Float temperature;

}
