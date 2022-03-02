package fr.faluche.codum.service;

import com.google.gson.Gson;
import fr.faluche.codum.controller.CityController;
import fr.faluche.codum.exception.CityNotFoundException;
import fr.faluche.codum.exception.TopicNotFoundException;
import fr.faluche.codum.model.City;
import fr.faluche.codum.model.Topic;
import fr.faluche.codum.model.assembler.CityModelAssembler;
import fr.faluche.codum.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static fr.faluche.codum.util.Utils.checkIfResourceExists;
import static jdk.nashorn.internal.objects.Global.getJSON;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityModelAssembler cityAssembler;



    public City newCity(City city) {
        return cityRepository.save(city);
    }

    public void topicExists(Long idTopic){

        //TODO find a solution to replace the URL to function/variable

        try {
            boolean exist;
            URL local = new URL("http://localhost:8080/api/v1/topic/"+ idTopic);
            exist =checkIfResourceExists(local);
            if(!exist) throw new TopicNotFoundException(idTopic);

        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public EntityModel<City> one(Long idCity, Long idTopic) {
        City city = cityRepository.findByTopicIdAndId(idTopic,idCity);

        if(Objects.isNull(city)) throw  new CityNotFoundException(idCity,idTopic);

        return cityAssembler.toModel(city);
    }

    public CollectionModel<EntityModel<City>> all(Long idTopic){
        List<EntityModel<City>> city = cityRepository.findByTopicId(idTopic).stream()
                .map(cityAssembler::toModel)
                .collect(Collectors.toList());


        return CollectionModel.of(city, linkTo(methodOn(CityController.class).all(idTopic)).withSelfRel());
    }

    public City editCity(City newCity,Long idCity){
        return cityRepository.findById(idCity)
                .map(city-> {
                    city.setName(newCity.getName());
                    city.setNumberOfPerson(newCity.getNumberOfPerson());
                    return cityRepository.save(city);
                })
                .orElseGet(() -> {
                    newCity.setId(idCity);
                    return cityRepository.save(newCity);
                });
    }

    public void deleteCity(Long idCity){
        cityRepository.deleteById(idCity);
    }


    public void isTopicCity(Long idTopic) {

        //TODO find a solution to replace the URL to function/variable
        String url = "http://localhost:8080/api/v1/topic/"+ idTopic;
        String data = (String) getJSON(url);
        Topic reponseTopic = new Gson().fromJson(data,Topic.class);

        System.out.println(reponseTopic.getName());


    }
}
