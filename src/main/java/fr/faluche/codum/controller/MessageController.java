package fr.faluche.codum.controller;

import fr.faluche.codum.exception.MessageNotFoundException;
import fr.faluche.codum.model.Message;
import fr.faluche.codum.model.MessageModelAssembler;
import fr.faluche.codum.repository.MessageRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageRepository repository;

    private final MessageModelAssembler assembler;

    public MessageController(MessageRepository repository, MessageModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping("/messages")
    public CollectionModel<EntityModel<Message>> all() {
        List<EntityModel<Message>> messages = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(messages, linkTo(methodOn(MessageController.class).all()).withSelfRel());
    }

    @PostMapping("/messages")
    public Message newMessage(@RequestBody Message newMessage){
        return repository.save(newMessage);
    }

    @GetMapping("/messages/{IdMessage}")
    public EntityModel<Message> one(@PathVariable Long IdMessage){

        Message message = repository.findById(IdMessage) //
                .orElseThrow(() -> new MessageNotFoundException(IdMessage));

        return assembler.toModel(message);
    }


    @PutMapping("/messages/{IdMessage}")
    public Message replaceMessage(@RequestBody Message newMessage, @PathVariable Long IdMessage) {

        return repository.findById(IdMessage)
                .map(message-> {
                    message.setContents(newMessage.getContents());
                    message.setIdPerson(newMessage.getIdPerson());
                    return repository.save(message);
                })
                .orElseGet(() -> {
                    newMessage.setIdMessage(IdMessage);
                    return repository.save(newMessage);
                });
    }

    @DeleteMapping("/messages/{IdMessage}")
    public void deleteMessage(@PathVariable Long IdMessage) {
        repository.deleteById(IdMessage);
    }
}


