package fr.faluche.codum.controller;

import fr.faluche.codum.model.Topic;
import fr.faluche.codum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public Topic newTopic(@RequestBody Topic topic){
        return topicService.newTopic(topic);
    }

    @GetMapping("/{idTopic}")
    public EntityModel<Topic> one(@PathVariable Long idTopic){
        return topicService.one(idTopic);
    }

    @GetMapping
    public CollectionModel<EntityModel<Topic>> all(){
        return topicService.all();
    }

    @PutMapping("/{idTopic}")
    public Topic editTopic( @RequestBody Topic newTopic,@PathVariable Long idTopic){
        return topicService.editTopic(newTopic,idTopic);
    }

    @DeleteMapping("/{idTopic}")
    public void deleteTopic(@PathVariable Long idTopic){
        topicService.deleteTopic(idTopic);
    }

}
