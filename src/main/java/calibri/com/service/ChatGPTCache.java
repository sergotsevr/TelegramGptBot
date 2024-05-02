package calibri.com.service;

import calibri.com.dto.Message;
import calibri.com.entity.GptMessages;
import calibri.com.repository.MessageRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatGPTCache {
    private final MessageRepository messageRepository;
    private static final HashMap<String, List<Message>> CHATS = new HashMap<>();

    public Optional<List<Message>> getReference(String telegramChatId){
        return Optional.ofNullable(CHATS.get(telegramChatId));
    }

    public void put(String telegramChatId, Message newMessage){

        getReference(telegramChatId).ifPresentOrElse(messages -> messages.add(newMessage),
                ()-> {
                    List<Message> messages = new ArrayList<>();
                    messages.add(newMessage);
                    CHATS.put(telegramChatId, messages);
                });
    }

    public void putAll(String telegramChatId, List<Message> newMessages){

        getReference(telegramChatId).ifPresentOrElse(messages -> messages.addAll(newMessages),
                ()-> {
                    List<Message> messages = new ArrayList<>(newMessages);
                    CHATS.put(telegramChatId, messages);
                });
    }

    @PostConstruct
    void initCache(){
        List<GptMessages> allMessages = messageRepository.findAll();
    }
}
