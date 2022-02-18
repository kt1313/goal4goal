package pl.com.k1313.goal4goal.domain.star;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.k1313.goal4goal.domain.player.Player;


@Repository
public interface StarRepository extends JpaRepository<Player,Long> {

}

