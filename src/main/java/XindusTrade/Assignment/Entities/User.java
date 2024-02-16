package XindusTrade.Assignment.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
//@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Item> wishlist=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
