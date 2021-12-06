package pl.com.k1313.goal4goal.domain.player;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

}



