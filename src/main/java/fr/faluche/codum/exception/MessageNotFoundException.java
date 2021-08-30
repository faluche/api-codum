package fr.faluche.codum.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long IdMessage){
        super("Could not find message -->" + IdMessage);
    }

}
