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
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.REMOVE)
    private Set<Product> products = new HashSet<>();
}
