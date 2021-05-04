package lux.grcc.keycloak_java_demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDTO {

    long catId;

    String name;

    int age;

    String ownerId;

}
