package fr.faluche.codum.controller;

import fr.faluche.codum.model.Message;
import fr.faluche.codum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message newMessage(@RequestBody Message message){
        return messageService.newMessage(message);
    }

    @GetMapping("/{IdMessage}")
    public EntityModel<Message> one(@PathVariable Long IdMessage){
        return messageService.one(IdMessage);
    }


    @GetMapping
    public CollectionModel<EntityModel<Message>> all(){
        return messageService.all();
    }

    @PutMapping("/{IdMessage}")
    public Message editMessage(@RequestBody Message newMessage, @PathVariable Long IdMessage) {
        return messageService.editMessage(newMessage, IdMessage);
    }

    @DeleteMapping("/{IdMessage}")
    public  void deleteMessage(@PathVariable Long IdMessage){
        messageService.deleteMessage(IdMessage);
    }


}



