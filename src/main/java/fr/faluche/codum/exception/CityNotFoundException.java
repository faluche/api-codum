package fr.faluche.codum.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(Long idVille){
        super("Couldn't find city --> "+ idVille);
    }

    public CityNotFoundException(Long idVille,Long idTopic){
        super("Couldn't find city "+ idVille+" in Topic city "+ idTopic);
    }
}
