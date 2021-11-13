package fr.faluche.codum.model;


import fr.faluche.codum.controller.MessageController;
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
                linkTo(methodOn(SubjectController.class).one(subject.getId(),subject.getTopic().getId())).withSelfRel(),
                linkTo(methodOn(MessageController.class).all(subject.getId(),subject.getTopic().getId())).withRel("messages"),
                linkTo(methodOn(SubjectController.class).all(subject.getTopic().getId())).withRel("subject"));
    }
}
