package oleg.tokarenko.onlinestore.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class ProductForOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Product product;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private _Order order;
}
