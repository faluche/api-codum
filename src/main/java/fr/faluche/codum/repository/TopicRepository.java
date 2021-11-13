package fr.faluche.codum.repository;

import fr.faluche.codum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository  extends JpaRepository<Topic,Long> {

}
