package best.solution.ever.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateRequestModel {

    @JsonProperty("inn")
    @JsonAlias("inn")
    private String inn;

    @JsonProperty("capital")
    @JsonAlias("capital")
    private Long capital;
}
