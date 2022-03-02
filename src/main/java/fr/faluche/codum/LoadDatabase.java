package fr.faluche.codum;

import fr.faluche.codum.model.Message;
import fr.faluche.codum.model.Subject;
import fr.faluche.codum.model.Topic;
import fr.faluche.codum.service.MessageService;
import fr.faluche.codum.service.SubjectService;
import fr.faluche.codum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initData(TopicService topic, SubjectService subject, MessageService message){
        return args -> {
            log.info("Start populating Database");
            List<Topic> topicList =new ArrayList<>();
            topicList.add(new Topic(1,"Présentation"));
            topicList.add(new Topic(2,"Villes"));
            topicList.add(new Topic(3,"Techniques"));
            topicList.add(new Topic(4,"Partie Croix"));
            topicList.add(new Topic(5,"Archiviste"));
            topicList.add(new Topic(6,"Gestion/Admin"));


            List<Subject> subjectList =new ArrayList<>();
            subjectList.add(new Subject(1,"Consignes",topicList.get(0)));
            subjectList.add(new Subject(2,"Présentation",topicList.get(0)));
            subjectList.add(new Subject(3,"Annonces",topicList.get(2)));
            subjectList.add(new Subject(4,"Sujet 1",topicList.get(2)));
            subjectList.add(new Subject(5,"Sujet 2",topicList.get(2)));
            subjectList.add(new Subject(6,"sujet N",topicList.get(2)));
            subjectList.add(new Subject(7,"sujet 1",topicList.get(4)));
            subjectList.add(new Subject(8,"sujet N",topicList.get(4)));
            subjectList.add(new Subject(9,"Conversation",topicList.get(5)));

            List<Message> messageList =new ArrayList<>();
            messageList.add(new Message(1,1,"Règles: toto",subjectList.get(0)));
            messageList.add(new Message(2,4,"Je m'appelle Mario",subjectList.get(1)));
            messageList.add(new Message(3,6,"et moi Luigi",subjectList.get(1)));
            messageList.add(new Message(4,4,"On est bro alors",subjectList.get(1)));
            messageList.add(new Message(5,1,"event qui arrive",subjectList.get(2)));
            messageList.add(new Message(6,67,"je suis dans le sujet 1 ",subjectList.get(3)));
            messageList.add(new Message(7,62,"je suis dans le sujet 2 ",subjectList.get(3)));
            messageList.add(new Message(8,64,"Ben moi je suis dans le sujet N",subjectList.get(3)));
            messageList.add(new Message(9,67,"Ohhh de nouveau dans le sujet 1 ",subjectList.get(4)));
            messageList.add(new Message(10,67,"Encore un sujet N",subjectList.get(5)));
            messageList.add(new Message(11,23,"Eh ouiiiiii!!!!",subjectList.get(5)));
            messageList.add(new Message(12,55,"je suis admin pcq je suis là ou je suis là pcq je suis admin ...",subjectList.get(6)));


            for(Topic T:topicList){
                topic.newTopic(T);

            }
            for (Subject S:subjectList){
                subject.newSubject(S);

            }
            for (Message M:messageList){
                message.newMessage(M);
            }


            log.info("finish to init Database");

        };
    }
}
