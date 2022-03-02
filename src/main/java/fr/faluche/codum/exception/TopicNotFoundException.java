package fr.faluche.codum.exception;

public class TopicNotFoundException extends RuntimeException{

    public TopicNotFoundException(long idTopic){
        super("Could not find topic --> " + idTopic);
    }
}
