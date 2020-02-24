package oleg.tokarenko.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.DETACH)
    private Set<Model> models = new HashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    private Country country;
}
