package fr.faluche.codum.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long IdSubject){
        super("Couldn't find subject -->" + IdSubject);
    }
}
