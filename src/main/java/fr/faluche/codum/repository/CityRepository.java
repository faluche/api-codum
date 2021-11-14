package fr.faluche.codum.repository;

import fr.faluche.codum.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {


    City findByTopicIdAndId(Long idTopic, Long idCity);

    List<City> findByTopicId(Long idTopic);
}
