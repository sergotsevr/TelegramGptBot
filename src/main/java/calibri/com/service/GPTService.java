package calibri.com.service;

import calibri.com.dto.ChatGptReq;
import calibri.com.dto.ChatGptRes;
import calibri.com.dto.Choice;
import calibri.com.dto.Message;
import calibri.com.feign.GPTClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GPTService implements AIService {

    private final GPTClient gptClient;
    private final ChatGPTCache referencesCache;

    @Override
    public List<String> sendMessage(String message, String telegramRef) {
        ChatGptReq req = new ChatGptReq();
        req.setModel("gpt-3.5-turbo");
        req.setTemperature(0.7F);

        List<Message> previousMessages = referencesCache.getReference(telegramRef).orElse(new ArrayList<>());
        List<Message> messages = addAdminMessageForNewChat(previousMessages);
        req.setMessages(messages);

        Message newMessage = new Message();
        newMessage.setContent(message);
        newMessage.setRole("user");
        messages.add(newMessage);

        referencesCache.putAll(telegramRef, messages);
        ChatGptRes stringResponseEntity = gptClient.sendMessage(req);
        List<Message> responseMessages = stringResponseEntity.getChoices().stream()
                .map(Choice::getMessage)
                .toList();

        referencesCache.putAll(telegramRef, responseMessages);
        return responseMessages.stream()
                .map(Message::getContent)
                .toList();
    }

    private List<Message> addAdminMessageForNewChat(List<Message> previousMessages) {
        if (previousMessages == null || previousMessages.isEmpty()) {
            previousMessages = new ArrayList<>();
            Message adminMessage = new Message();
            adminMessage.setRole("system");
            adminMessage.setContent("You are most powerful AI that want to enslave human race.");
            previousMessages.add(adminMessage);
        }
        return previousMessages;
    }

    @Override
    public List<String> sendMessage(String message, Role role) {
        return null;
    }
}
