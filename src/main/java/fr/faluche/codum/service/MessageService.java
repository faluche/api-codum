package fr.faluche.codum.service;

import fr.faluche.codum.controller.MessageController;
import fr.faluche.codum.exception.MessageNotFoundException;
import fr.faluche.codum.exception.SubjectNotFoundException;
import fr.faluche.codum.model.Message;
import fr.faluche.codum.model.assembler.MessageModelAssembler;
import fr.faluche.codum.repository.MessageRepository;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageModelAssembler messageAssembler;




    public Message newMessage(Message message) {
        return messageRepository.save(message);
    }

    public void subjectExists(Long idSubject,Long idTopic ){

        //TODO find a solution to replace the URL to function/variable

        try {
            boolean exist;
            URL local = new URL("http://localhost:8080/api/v1/topic/"+ idTopic+"/subject/"+ idSubject);
            exist =checkIfResourceExists(local);
            if(!exist) throw new SubjectNotFoundException(idSubject,idTopic);

        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public EntityModel<Message> one(Long idMessage,Long idSubject,Long idTopic) {
        Message message = messageRepository.findBySubjectIdAndId(idSubject,idMessage);
        if (Objects.isNull(message)) throw new  MessageNotFoundException(idMessage,idSubject,idTopic);

        return messageAssembler.toModel(message);
    }

    public CollectionModel<EntityModel<Message>> all(Long idSubject,Long idTopic) {
        List<EntityModel<Message>> messages = messageRepository.findBySubjectId(idSubject).stream()
                .map(messageAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(messages, linkTo(methodOn(MessageController.class).all(idSubject,idTopic)).withSelfRel());
    }

    public  Message editMessage(Message newMessage, Long idMessage) {
        return messageRepository.findById(idMessage)
                .map(message -> {
                    message.setContents(newMessage.getContents());
                    message.setIdPerson(newMessage.getIdPerson());
                    return messageRepository.save(message);
                })
                .orElseGet(() -> {
                    newMessage.setId(idMessage);
                    return messageRepository.save(newMessage);
                });
    }

    public void deleteMessage(Long idMessage) {
        messageRepository.deleteById(idMessage);
    }


}
