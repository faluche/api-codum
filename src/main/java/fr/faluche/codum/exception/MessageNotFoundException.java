package fr.faluche.codum.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long idMessage){
        super("Could not find message --> " + idMessage);
    }

}
