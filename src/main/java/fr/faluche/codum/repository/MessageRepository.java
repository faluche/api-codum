package fr.faluche.codum.repository;

import fr.faluche.codum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public List<Message> findBySubjectId(Long id);
    public Message findBySubjectIdAndId(Long idSubject,Long idMessage);
}
