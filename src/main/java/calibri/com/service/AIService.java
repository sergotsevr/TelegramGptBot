package calibri.com.service;

import java.util.List;

public interface AIService {

    List<String> sendMessage(String message, String telegramRef);
    List<String> sendMessage(String message, Role role);
}
