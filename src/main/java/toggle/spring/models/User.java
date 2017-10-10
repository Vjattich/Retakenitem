package toggle.spring.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "itemholder",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "disc_id"))
    private Set<Disc> myDisc = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "takenitem",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "disc_id"))
    private Set<Disc> discs = new HashSet<>();


}
