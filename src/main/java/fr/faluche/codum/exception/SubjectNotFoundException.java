package fr.faluche.codum.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long idSubject){
        super("Couldn't find subject --> " + idSubject);
    }
}
