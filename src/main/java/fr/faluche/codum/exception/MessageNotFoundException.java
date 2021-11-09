package fr.faluche.codum.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long idMessage){
        super("Could not find message --> " + idMessage);
    }

    public MessageNotFoundException(Long idMessage, Long idSubject){
        super("Could not find the message "+ idMessage + "in subject "+idSubject );
    }

}
