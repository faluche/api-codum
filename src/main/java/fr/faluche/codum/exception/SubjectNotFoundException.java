package fr.faluche.codum.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long idSubject){
        super("Couldn't find subject --> " + idSubject);
    }

    public SubjectNotFoundException(Long idSubject,Long idTopic){
        super("Could not find the subject "+ idSubject + " in topic "+idTopic );
    }
}
