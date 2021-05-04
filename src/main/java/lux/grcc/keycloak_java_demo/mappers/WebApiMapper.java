package lux.grcc.keycloak_java_demo.mappers;

import lux.grcc.keycloak_java_demo.models.dtos.CatDTO;
import lux.grcc.keycloak_java_demo.models.entities.Cat;
import lux.grcc.keycloak_java_demo.models.forms.CatForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface WebApiMapper {

    @Mappings({
            @Mapping(target = "catId")
    })
    Cat DtoToEntity(CatDTO catDTO);

    @Mappings({
            @Mapping(target = "catId", ignore = true)
    })
    Cat fromFormToEntity(CatForm catForm);

    @Mappings({
            @Mapping(target = "catId")
    })
    CatDTO entityToDTO(Cat catEntity);

}
