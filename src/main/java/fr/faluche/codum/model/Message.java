package fr.faluche.codum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Message {
    private @Id @GeneratedValue long IdMessage;
    private long IdPerson;
    private String Contents;

    public Message(){};

    public Message(long idMessage, long idPerson, String contents) {
        IdMessage = idMessage;
        IdPerson = idPerson;
        Contents = contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public void setIdMessage(long idMessage) {
        IdMessage = idMessage;
    }

    public void setIdPerson(long idPerson) {
        IdPerson = idPerson;
    }

    public long getIdMessage() {
        return IdMessage;
    }

    public long getIdPerson() {
        return IdPerson;
    }

    public String getContents() {
        return Contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return IdMessage == message.IdMessage && IdPerson == message.IdPerson
                && Objects.equals(Contents, message.Contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdMessage, IdPerson, Contents);
    }

    @Override
    public String toString() {
        return "Message{" +
                "IdMessage=" + IdMessage +
                ", IdPerson=" + IdPerson +
                ", Contents='" + Contents + '\'' +
                '}';
    }
}
