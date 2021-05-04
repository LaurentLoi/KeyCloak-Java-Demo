package lux.grcc.keycloak_java_demo.init;

import lux.grcc.keycloak_java_demo.models.forms.CatForm;
import lux.grcc.keycloak_java_demo.services.CatService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInit implements InitializingBean {

    private final CatService catService;

    public DatabaseInit(CatService catService) {
        this.catService = catService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<CatForm> catsList = Arrays.asList(
                CatForm.builder()
                        .name("Neko")
                        .age(8)
                        .ownerId("e7dcd7e5-640d-4fd3-a99c-dc60fcd9222a") // admin1
                        .build(),
                CatForm.builder()
                        .name("Moomy")
                        .age(5)
                        .ownerId("31e52a31-bc46-4beb-aedf-7a363f880862") // user1
                        .build(),
                CatForm.builder()
                        .name("Sting")
                        .age(4)
                        .ownerId("ff01095c-6278-4baf-91a1-9865970b443a") // manager1
                        .build()
        );

        catsList.forEach(catForm -> {
            try {
                this.catService.insert(catForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}
