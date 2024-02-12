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
    // Here we are implementing OneToMany mapping because a user can have multiple items in their wishlist
    // Multiple users can add one same item to their wishlist.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Item> wishlist=new ArrayList<>();
}
