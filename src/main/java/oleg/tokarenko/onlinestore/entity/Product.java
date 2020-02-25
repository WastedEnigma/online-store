package oleg.tokarenko.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double price;

    private Double rating;

    private String image;

    @Column(columnDefinition = "text")
    private String description;

    private Boolean available;

    @ManyToOne(cascade = CascadeType.DETACH)
    private SubCategory subCategory;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Model model;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductForCart> productForCarts = new ArrayList<>();

    @ManyToMany
    private Set<User> users = new HashSet<>();
}
