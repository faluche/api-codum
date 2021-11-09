package fr.faluche.codum.service;

import fr.faluche.codum.controller.MessageController;
import fr.faluche.codum.exception.MessageNotFoundException;
import fr.faluche.codum.exception.SubjectNotFoundException;
import fr.faluche.codum.model.Message;
import fr.faluche.codum.model.MessageModelAssembler;
import fr.faluche.codum.repository.MessageRepository;
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
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageModelAssembler messageAssembler;




    public Message newMessage(Message message) {
        return messageRepository.save(message);
    }


    public void subjectExists(Long idSubject) throws SubjectNotFoundException{
        List<Message> messageList = messageRepository.findAll();

        boolean exist = false;
        for(Message M: messageList){
            if(M.getSubject().getId() == idSubject) exist = true;
        }
        if(!exist) throw new SubjectNotFoundException(idSubject);
    }

    public EntityModel<Message> one(Long idMessage,Long idSubject) {
        Message message = messageRepository.findBySubjectIdAndId(idSubject,idMessage);
        if (Objects.isNull(message)) throw new  MessageNotFoundException(idMessage,idSubject);

        return messageAssembler.toModel(message);
    }

    public CollectionModel<EntityModel<Message>> all(Long idSubject) {
        List<EntityModel<Message>> messages = messageRepository.findBySubjectId(idSubject).stream()
                .map(messageAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(messages, linkTo(methodOn(MessageController.class).all(idSubject)).withSelfRel());
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
