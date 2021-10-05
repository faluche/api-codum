package fr.faluche.codum.model;

import fr.faluche.codum.controller.MessageController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MessageModelAssembler  implements RepresentationModelAssembler<Message, EntityModel<Message>> {

    @Override
    public EntityModel<Message> toModel(Message message) {
        return EntityModel.of(message,
                linkTo(methodOn(MessageController.class).one(message.getId(),message.getSubject().getId())).withSelfRel(),
                linkTo(methodOn(MessageController.class).all(message.getSubject().getId())).withRel("messages"));
    }
}
