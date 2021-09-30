package fr.faluche.codum.controller;

import fr.faluche.codum.model.Message;
import fr.faluche.codum.model.Subject;
import fr.faluche.codum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/subject/{IdSubject}/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;



    @PostMapping
    public Message newMessage(@RequestBody Message message,@PathVariable Long IdSubject){
        message.setSubject(new Subject(IdSubject,""));
        return messageService.newMessage(message);
    }

    @GetMapping("/{IdMessage}")
    public EntityModel<Message> one(@PathVariable Long IdMessage,@PathVariable Long IdSubject){
        return messageService.one(IdMessage);
    }

    @GetMapping
    public CollectionModel<EntityModel<Message>> all(@PathVariable Long IdSubject){
        return messageService.all(IdSubject);
    }

    @PutMapping("/{IdMessage}")
    public Message editMessage(@RequestBody Message newMessage, @PathVariable Long IdMessage,@PathVariable Long IdSubject) {
        newMessage.setSubject(new Subject(IdSubject,""));
        return messageService.editMessage(newMessage, IdMessage);
    }

    @DeleteMapping("/{IdMessage}")
    public  void deleteMessage(@PathVariable Long IdMessage){
        messageService.deleteMessage(IdMessage);
    }


}



