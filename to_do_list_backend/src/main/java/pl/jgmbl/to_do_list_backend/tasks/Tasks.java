package pl.jgmbl.to_do_list_backend.tasks;

import jakarta.persistence.*;
import pl.jgmbl.to_do_list_backend.names.Names;

@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "name_id")
    private Names names;

    private String content;

    public Tasks() {
    }

    public Integer getId() {
        return id;
    }

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
