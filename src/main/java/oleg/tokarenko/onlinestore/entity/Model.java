package oleg.tokarenko.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE)
    private List<Product> products = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    private Manufacturer manufacturer;
}
