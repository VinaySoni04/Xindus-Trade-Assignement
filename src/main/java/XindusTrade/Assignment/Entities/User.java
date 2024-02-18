package XindusTrade.Assignment.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
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
    // And multiple users can have one or more same item
    // @ManyToMany annotation indicates that there is a Many-to-Many relationship between user and item entity.
    // cascade = CascadeType.ALL, it is called as cascading effect, allows operations performed on one entity to propagate to associated entities.
    @ManyToMany
    @JoinTable(
            name = "user_wishlist", // name attribute specifies the name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // It specifies the column that represent the user entity
            inverseJoinColumns = @JoinColumn(name = "item_id") // It specifies the column that represent the item entity
    )
    private List<Item> wishlist = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
