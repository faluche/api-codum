package fr.faluche.codum.service;

import fr.faluche.codum.controller.CityController;
import fr.faluche.codum.exception.CityNotFoundException;
import fr.faluche.codum.model.City;
import fr.faluche.codum.model.assembler.CityModelAssembler;
import fr.faluche.codum.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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



}
