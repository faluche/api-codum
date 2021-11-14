package fr.faluche.codum.controller;

import fr.faluche.codum.model.City;
import fr.faluche.codum.model.Topic;
import fr.faluche.codum.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/topic/{idTopic}/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public City newCity(@RequestBody City city,@PathVariable Long idTopic) {
        city.setTopic(new Topic(idTopic,""));
        return cityService.newCity(city);
    }

    @GetMapping("/{idCity}")
    public EntityModel<City>one(@PathVariable Long idCity,@PathVariable Long idTopic) {
        return cityService.one(idCity,idTopic);
    }


    @GetMapping
    public CollectionModel<EntityModel<City>> all(@PathVariable Long idTopic){
        return cityService.all(idTopic);
    }

    @PutMapping("/{idCity}")
    public City editCity(@RequestBody City newCity, @PathVariable Long idCity, @PathVariable Long idTopic){
        newCity.setTopic(new Topic(idTopic,""));
        return cityService.editCity(newCity,idCity);
    }

    @DeleteMapping("/{idCity}")
    public void deleteCity(@PathVariable Long idCity){
        cityService.deleteCity(idCity);
    }



}
