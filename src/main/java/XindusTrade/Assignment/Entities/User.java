package XindusTrade.Assignment.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // Bi-Directional Mapping between user and item
    // Here we are implementing ManyToMany mapping because a user can have multiple items in their wishlist
    // Multiple users can have same item in their wishlist.
    // @ManyToMany annotation indicates that there is a Many-to-Many relationship between user and item entity.
    // cascade = CascadeType.ALL, it is called as cascading effect, allows operations performed on one entity to propagate to associated entities.
    // @JoinTable is used to specify the details of the join table that will manage the Many-to-Many relationship.
    // name = "user_item" specifies the name of the join table in the database.
    // joinColumns = @JoinColumn(name = "user_id") indicates the foreign key column in the join table that references the primary key of the owning side.
    // inverseJoinColumns = @JoinColumn(name = "item_id") indicates the foreign key column in the join table that references the primary key of the inverse side.
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_item",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> wishlist=new ArrayList<>();
}
