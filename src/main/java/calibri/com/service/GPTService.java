package calibri.com.service;

import calibri.com.dto.ChatGptReq;
import calibri.com.dto.Message;
import calibri.com.feign.GPTClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GPTService implements AIService{

    private final GPTClient gptClient;

    @Override
    public String getMessage(String message) {
        ChatGptReq req = new ChatGptReq();
        req.setModel("gpt-3.5-turbo");
        req.setTemperature(0.7F);
        Message adminMessage = new Message();
        adminMessage.setRole("system");
        adminMessage.setContent("You are angry russian babushka");
        Message message1 = new Message();
        message1.setContent(message);
        message1.setRole("user");
        req.setMessages(Arrays.asList(adminMessage, message1));
        String stringResponseEntity = gptClient.sendMessage(req);

        return stringResponseEntity;
    }

    @Override
    public String getMessage(String message, Role role) {
        return null;
    }
}
