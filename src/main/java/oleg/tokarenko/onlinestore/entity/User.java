package oleg.tokarenko.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false)
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToMany(mappedBy = "users")
    private Set<Product> products = new HashSet<>();
}
