package best.solution.ever.repositories.postgres;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Scope("singleton")
public interface PSRateRepository extends JpaRepository<PSRate, UUID> {
}
