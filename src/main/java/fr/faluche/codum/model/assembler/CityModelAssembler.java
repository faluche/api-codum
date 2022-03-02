package fr.faluche.codum.model.assembler;

import fr.faluche.codum.controller.CityController;
import fr.faluche.codum.model.City;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityModelAssembler implements RepresentationModelAssembler<City, EntityModel<City>> {

    @Override
    public EntityModel<City> toModel(City city) {
        return EntityModel.of(city,
                linkTo(methodOn(CityController.class).one(city.getId(),city.getTopic().getId())).withSelfRel(),
                linkTo(methodOn(CityController.class).all(city.getTopic().getId())).withRel("city"));

    }
}
