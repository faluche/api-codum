package fr.faluche.codum.model.assembler;

import fr.faluche.codum.controller.CityController;
import fr.faluche.codum.controller.SubjectController;
import fr.faluche.codum.controller.TopicController;
import fr.faluche.codum.model.Topic;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static fr.faluche.codum.util.Utils.isCityTopic;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TopicModelAssembler implements RepresentationModelAssembler<Topic, EntityModel<Topic>> {

    @Override
    public EntityModel<Topic> toModel(Topic topic) {
        if(isCityTopic(topic.getName())){
            return EntityModel.of(topic,
                    linkTo(methodOn(TopicController.class).one(topic.getId())).withSelfRel(),
                    linkTo(methodOn(CityController.class).all(topic.getId())).withRel("city"),
                    linkTo(methodOn(SubjectController.class).all(topic.getId())).withRel("subject"),
                    linkTo(methodOn(TopicController.class).all()).withRel("topic"));
        }
        else {

            return EntityModel.of(topic,
                    linkTo(methodOn(TopicController.class).one(topic.getId())).withSelfRel(),
                    linkTo(methodOn(SubjectController.class).all(topic.getId())).withRel("subject"),
                    linkTo(methodOn(TopicController.class).all()).withRel("topic"));
        }
    }
}
