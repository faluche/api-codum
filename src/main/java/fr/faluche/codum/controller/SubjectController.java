package fr.faluche.codum.controller;

import fr.faluche.codum.model.Subject;
import fr.faluche.codum.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public Subject newSubject(@RequestBody Subject subject){
        return subjectService.newSubject(subject);
    }

    @GetMapping("/{IdSubject}")
    public EntityModel<Subject> one(@PathVariable Long IdSubject){
        return subjectService.one(IdSubject);
    }

    @GetMapping
    public CollectionModel<EntityModel<Subject>> all(){
        return subjectService.all();
    }

    @PutMapping("/{IdSubject}")
    public Subject editSubject(@RequestBody Subject newSubject, @PathVariable Long IdSubject) {
        return subjectService.editSubject(newSubject, IdSubject);
    }

    @DeleteMapping("/{IdSubject}")
    public  void deleteSubject(@PathVariable Long IdSubject){
        subjectService.deleteSubject(IdSubject);
    }

}