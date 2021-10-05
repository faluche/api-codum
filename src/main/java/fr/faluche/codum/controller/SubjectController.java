package fr.faluche.codum.controller;

import fr.faluche.codum.model.Subject;
import fr.faluche.codum.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @PostMapping
    public Subject newSubject(@RequestBody Subject subject) {
        return subjectService.newSubject(subject);
    }

    @GetMapping("/{idSubject}")
    public EntityModel<Subject> one(@PathVariable Long idSubject) {
        return subjectService.one(idSubject);
    }

    @GetMapping
    public CollectionModel<EntityModel<Subject>> all() {
        return subjectService.all();
    }

    @PutMapping("/{idSubject}")
    public Subject editSubject(@RequestBody Subject newSubject, @PathVariable Long idSubject) {
        return subjectService.editSubject(newSubject, idSubject);
    }

    @DeleteMapping("/{idSubject}")
    public void deleteSubject(@PathVariable Long idSubject) {
        subjectService.deleteSubject(idSubject);
    }

}