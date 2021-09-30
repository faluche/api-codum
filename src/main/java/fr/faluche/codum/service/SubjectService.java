package fr.faluche.codum.service;

import fr.faluche.codum.controller.SubjectController;
import fr.faluche.codum.exception.MessageNotFoundException;
import fr.faluche.codum.model.Subject;
import fr.faluche.codum.model.SubjectModelAssembler;
import fr.faluche.codum.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public EntityModel<Subject> one(Long idSubject){
        Subject subject = subjectRepository.findById(idSubject)
                .orElseThrow(() -> new MessageNotFoundException(idSubject));

        return subjectAssembler.toModel(subject);
    }

    public CollectionModel<EntityModel<Subject>> all() {
        List<EntityModel<Subject>> subject = subjectRepository.findAll().stream()
                .map(subjectAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(subject, linkTo(methodOn(SubjectController.class).all()).withSelfRel());
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
