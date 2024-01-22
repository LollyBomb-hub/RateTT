package best.solution.ever.repositories.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Document(createIndex = false, indexName = "rate")
public class ESRate {
    @Field(value = "id", type = FieldType.Keyword)
    private UUID id;
    @Field(value = "inn", type = FieldType.Keyword)
    private String inn;
    @Field(value = "capital", type = FieldType.Long)
    private long capital;
    @Field(value = "rate", type = FieldType.Boolean)
    private Boolean rate;

}
