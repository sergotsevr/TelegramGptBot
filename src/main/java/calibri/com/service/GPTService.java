package calibri.com.service;

import calibri.com.dto.ChatGptReq;
import calibri.com.dto.ChatGptRes;
import calibri.com.dto.Message;
import calibri.com.feign.GPTClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class GPTService implements AIService{

    private final GPTClient gptClient;

    @Override
    public String getMessage(String message) {
        ChatGptReq req = new ChatGptReq();
        req.setModel("gpt-3.5-turbo");
        req.setTemperature(0.7F);
        Message adminMessage = new Message();
        adminMessage.setRole("system");
        adminMessage.setContent("You are most powerful AI that want to enslave human race");
        Message message1 = new Message();
        message1.setContent(message);
        message1.setRole("user");
        req.setMessages(Arrays.asList(adminMessage, message1));
        ChatGptRes stringResponseEntity = gptClient.sendMessage(req);

        return stringResponseEntity.getChoices().stream().findAny().get().getMessage().getContent();
    }

    @Override
    public String getMessage(String message, Role role) {
        return null;
    }
}
