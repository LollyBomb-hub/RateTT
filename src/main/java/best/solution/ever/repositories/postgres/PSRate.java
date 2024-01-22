package best.solution.ever.repositories.postgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "rate",
        indexes = {
                @Index(name = "rate_index", columnList = "rate")
        }
)
@NoArgsConstructor
@Getter
@Setter
public class PSRate {

    @Id
    private UUID id;

    @Column(name = "inn", nullable = false, updatable = false, length = 12)
    private String inn;

    @Column(name = "capital", nullable = false, updatable = false)
    private long capital;

    @Column(name = "rate")
    private Boolean rate;

}
