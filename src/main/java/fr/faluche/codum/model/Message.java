package fr.faluche.codum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private long idPerson;
    private String contents;

    @ManyToOne
    private Subject subject;





}
