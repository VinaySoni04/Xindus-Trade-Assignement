package XindusTrade.Assignment.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int numberOfUnits;
    private boolean isAvailable; // This attributes indicates that whether the item is in stock or out of stock.

    // (mappedBy = "wishlist") indicates that the relationship is mapped by the wishlist field in the User entity.
    @ManyToMany(mappedBy = "wishlist",cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
}
