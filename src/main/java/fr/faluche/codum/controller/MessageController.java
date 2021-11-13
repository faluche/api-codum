package fr.faluche.codum.controller;

import fr.faluche.codum.model.Message;
import fr.faluche.codum.model.Subject;
import fr.faluche.codum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value={"/api/v1/topic/{idTopic}/subject/{idSubject}/messages"})
public class MessageController {

    @Autowired
    private MessageService messageService;



    @PostMapping
    public Message newMessage(@RequestBody Message message,@PathVariable Long idSubject,@PathVariable Long idTopic){
        messageService.subjectExists(idSubject,idTopic);
        message.setSubject(new Subject(idSubject, "",null));
        return messageService.newMessage(message);

    }

    @GetMapping("/{idMessage}")
    public EntityModel<Message> one(@PathVariable Long idMessage,@PathVariable Long idSubject,@PathVariable Long idTopic){
        return messageService.one(idMessage,idSubject,idTopic);
    }

    @GetMapping
    public CollectionModel<EntityModel<Message>> all(@PathVariable Long idSubject,@PathVariable Long idTopic){
        return messageService.all(idSubject,idTopic);
    }

    @PutMapping("/{idMessage}")
    public Message editMessage(@RequestBody Message newMessage, @PathVariable Long idMessage,@PathVariable Long idSubject) {
        newMessage.setSubject(new Subject(idMessage,"",null));
        return messageService.editMessage(newMessage, idMessage);
    }

    @DeleteMapping("/{idMessage}")
    public  void deleteMessage(@PathVariable Long idMessage){
        messageService.deleteMessage(idMessage);
    }


}



