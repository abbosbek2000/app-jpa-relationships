package uz.spring.appjparelationships.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FacultyDTO {
    private String name;
    @JsonProperty(value = "university_id")
    private Integer univeristyId;
}
