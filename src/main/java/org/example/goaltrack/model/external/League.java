package org.example.goaltrack.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class League {
    @JsonProperty
    Integer id;
    @JsonProperty
    String name;
    @JsonProperty
    String localizedName;
    @JsonProperty
    String logo;
}
