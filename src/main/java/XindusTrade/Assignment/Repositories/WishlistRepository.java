package XindusTrade.Assignment.Repositories;

import XindusTrade.Assignment.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishlistRepository extends JpaRepository<Item,Integer> {
}
