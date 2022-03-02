package fr.faluche.codum.service;

import fr.faluche.codum.controller.SubjectController;
import fr.faluche.codum.exception.SubjectNotFoundException;
import fr.faluche.codum.exception.TopicNotFoundException;
import fr.faluche.codum.model.Subject;
import fr.faluche.codum.model.assembler.SubjectModelAssembler;
import fr.faluche.codum.repository.SubjectRepository;
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
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectModelAssembler subjectAssembler;



    public Subject newSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public void topicExists(Long idTopic){

        //TODO find a solution to replace the URL to function/variable

        try {
            boolean exist;
            URL local = new URL("http://localhost:8080/api/v1/topic/"+ idTopic);
            exist =checkIfResourceExists(local);
            if(!exist) throw new TopicNotFoundException(idTopic);

        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public EntityModel<Subject> one(Long idSubject,Long idTopic){
        Subject subject = subjectRepository.findByTopicIdAndId(idTopic,idSubject);

        if(Objects.isNull(subject)) throw new SubjectNotFoundException(idSubject,idTopic);

        return subjectAssembler.toModel(subject);
    }

    public CollectionModel<EntityModel<Subject>> all(Long idTopic) {
        List<EntityModel<Subject>> subject = subjectRepository.findByTopicId(idTopic).stream()
                .map(subjectAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(subject, linkTo(methodOn(SubjectController.class).all(idTopic)).withSelfRel());
    }

    public Subject editSubject(Subject newSubject,Long idSubject) {

        return subjectRepository.findById(idSubject)
                .map(subject-> {
                    subject.setName(newSubject.getName());
                    return subjectRepository.save(subject);
                })
                .orElseGet(() -> {
                    newSubject.setId(idSubject);
                    return subjectRepository.save(newSubject);
                });
    }


    public void deleteSubject(Long idSubject) {
        subjectRepository.deleteById(idSubject);
    }


}
