package lux.grcc.keycloak_java_demo.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatForm {

    String name;

    int age;

    String ownerId;

}
