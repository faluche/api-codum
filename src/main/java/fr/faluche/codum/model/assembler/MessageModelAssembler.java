package fr.faluche.codum.model.assembler;

import fr.faluche.codum.controller.MessageController;
import fr.faluche.codum.model.Message;
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
                linkTo(methodOn(MessageController.class).one(message.getId(),message.getSubject().getId(),message.getSubject().getTopic().getId())).withSelfRel(),
                linkTo(methodOn(MessageController.class).all(message.getSubject().getId(),message.getSubject().getTopic().getId())).withRel("messages"));
    }
}
