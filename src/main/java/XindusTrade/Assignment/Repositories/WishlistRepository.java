package XindusTrade.Assignment.Repositories;

import XindusTrade.Assignment.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Item,Integer> {
    List<Item> findByUsername(String username);
}
