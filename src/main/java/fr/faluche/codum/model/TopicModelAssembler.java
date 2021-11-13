package fr.faluche.codum.model;

import fr.faluche.codum.controller.SubjectController;
import fr.faluche.codum.controller.TopicController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TopicModelAssembler implements RepresentationModelAssembler<Topic, EntityModel<Topic>> {

    @Override
    public EntityModel<Topic> toModel(Topic topic) {
        return EntityModel.of(topic,
                linkTo(methodOn(TopicController.class).one(topic.getId())).withSelfRel(),
                linkTo(methodOn(SubjectController.class).all(topic.getId())).withRel("subject"),
                linkTo(methodOn(TopicController.class).all()).withRel("topic"));
    }
}
