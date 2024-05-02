package com.calibri.service;

import com.calibri.dto.Message;
import com.calibri.entity.GptMessages;
import com.calibri.mapper.MessageMapper;
import com.calibri.repository.MessageRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGPTCache {
    private final MessageRepository messageRepository;
    private final MessageMapper mapper;
    private static final HashMap<String, List<Message>> CHATS = new HashMap<>();

    public Optional<List<Message>> getReference(String telegramChatId) {
        return Optional.ofNullable(CHATS.get(telegramChatId));
    }

    public void put(String telegramChatId, Message newMessage) {

        getReference(telegramChatId).ifPresentOrElse(messages -> messages.add(newMessage),
                () -> {
                    List<Message> messages = new ArrayList<>();
                    messages.add(newMessage);
                    CHATS.put(telegramChatId, messages);
                });
    }

    public void putAll(String telegramChatId, List<Message> newMessages) {

        getReference(telegramChatId).ifPresentOrElse(messages -> messages.addAll(newMessages),
                () -> {
                    List<Message> messages = new ArrayList<>(newMessages);
                    CHATS.put(telegramChatId, messages);
                });
    }

    @PostConstruct
    void initCache() {
        List<GptMessages> allMessages = messageRepository.findAll();
        CHATS.putAll(allMessages.stream()
                .collect(Collectors.toMap(entity -> String.valueOf(entity.getTelegramId()), entity -> {
                    Message message = mapper.entityToMessage(entity);
                    List<Message> messages = new ArrayList<>();
                    messages.add(message);
                    return messages;
                }, (first, second) -> {
                    first.addAll(second);
                    return first;
                })));
    }

    @PreDestroy
    void saveChanges() {
        CHATS.forEach((key, value) -> value.stream()
                .filter(message -> message.getId() == null)
                .map(mapper::dataToEntity)
                .peek(message -> message.setTelegramId(Long.valueOf(key)))
                .forEach(messageRepository::save));
    }
}
