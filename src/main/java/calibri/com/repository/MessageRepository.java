package calibri.com.repository;

import calibri.com.entity.GptMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<GptMessages, Long> {

    List<GptMessages> findAllByTelegramId(String telegramId);
}
