package fr.faluche.codum.model;


import fr.faluche.codum.controller.SubjectController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubjectModelAssembler implements RepresentationModelAssembler<Subject, EntityModel<Subject>> {

    @Override
    public EntityModel<Subject> toModel(Subject subject){
        return EntityModel.of(subject,
                linkTo(methodOn(SubjectController.class).one(subject.getIdSubject())).withSelfRel(),
                linkTo(methodOn(SubjectController.class).all()).withRel("subject"));
    }
}
