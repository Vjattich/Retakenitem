package toggle.spring.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "disc")
public @Data
class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
}
