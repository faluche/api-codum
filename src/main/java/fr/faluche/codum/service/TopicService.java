package fr.faluche.codum.service;

import fr.faluche.codum.controller.TopicController;
import fr.faluche.codum.exception.TopicNotFoundException;
import fr.faluche.codum.model.Topic;
import fr.faluche.codum.model.TopicModelAssembler;
import fr.faluche.codum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicModelAssembler topicAssembler;


    public Topic newTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public EntityModel<Topic> one(long idTopic){
        Topic topic = topicRepository.findById(idTopic).orElseThrow(()-> new TopicNotFoundException(idTopic));


        return topicAssembler.toModel(topic);
    }
    
    public CollectionModel<EntityModel<Topic>> all() {
        List<EntityModel<Topic>> topic = topicRepository.findAll().stream()
                .map(topicAssembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(topic,linkTo(methodOn(TopicController.class).all()).withSelfRel());
        
    }
    public Topic editTopic(Topic newTopic, Long idTopic) {

        return topicRepository.findById(idTopic)
                .map(topic-> {
                    topic.setName(newTopic.getName());
                    return topicRepository.save(topic);
                })
                .orElseGet(() -> {
                    newTopic.setId(idTopic);
                    return topicRepository.save(newTopic);
                });
    }

    public void deleteTopic(Long idTopic) {
        topicRepository.deleteById(idTopic);
    }
    

}
