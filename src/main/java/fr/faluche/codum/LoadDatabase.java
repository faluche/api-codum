package fr.faluche.codum;

import fr.faluche.codum.model.Message;
import fr.faluche.codum.repository.MessageRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LoadDatabase {

    private static final Logger log =  LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MessageRepository repository){
        return args -> {
            log.info("Preloading" + repository.save(new Message(1,5,"hello World !!")));
            log.info("Preloading" + repository.save(new Message(2,7,"Hi, person 5")));
        };
    }
}
