package fr.faluche.codum.repository;

import fr.faluche.codum.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Subject findByTopicIdAndId(long idTopic,Long idSubject);

    List<Subject> findByTopicId(Long idTopic);
}
