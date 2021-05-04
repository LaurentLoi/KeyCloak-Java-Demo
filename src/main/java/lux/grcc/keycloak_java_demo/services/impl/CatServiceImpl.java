package lux.grcc.keycloak_java_demo.services.impl;

import com.querydsl.core.BooleanBuilder;
import lux.grcc.keycloak_java_demo.mappers.WebApiMapper;
import lux.grcc.keycloak_java_demo.models.dtos.CatDTO;
import lux.grcc.keycloak_java_demo.models.entities.Cat;
import lux.grcc.keycloak_java_demo.models.entities.QCat;
import lux.grcc.keycloak_java_demo.models.forms.CatForm;
import lux.grcc.keycloak_java_demo.repositories.CatRepository;
import lux.grcc.keycloak_java_demo.services.CatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CatServiceImpl implements CatService {

    private final WebApiMapper webApiMapper;
    private final CatRepository catRepository;

    public CatServiceImpl(WebApiMapper webApiMapper, CatRepository catRepository) {
        this.webApiMapper = webApiMapper;
        this.catRepository = catRepository;
    }

    @Override
    public List<CatDTO> getAll() {
        return this.catRepository.findAll()
                .stream()
                .map(this.webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CatDTO getOne(long id) {
        return this.webApiMapper.entityToDTO(this.catRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Cat not found !")));
    }

    @Override
    public CatDTO insertWithReturnValue(CatForm catForm) throws Exception {
        return this.webApiMapper.entityToDTO(this.catRepository.save(this.webApiMapper.fromFormToEntity(catForm)));
    }

    @Override
    public void insert(CatForm catForm) {
        this.catRepository.save(this.webApiMapper.fromFormToEntity(catForm));
    }

    @Override
    public String delete(long id) {
        Cat catToDelete = this.webApiMapper.DtoToEntity(getOne(id));
        this.catRepository.delete(catToDelete);
        return "Deleted cat: " + catToDelete.getCatId() + " - " + catToDelete.getName();
    }

    @Override
    public CatDTO update(long id, CatForm catForm) {
        Cat catToUpdate = this.webApiMapper.DtoToEntity(getOne(id));

        if (catForm.getName() != null) {
            catToUpdate.setName(catForm.getName());
        }

        if (catForm.getAge() > 0) {
            catToUpdate.setAge(catToUpdate.getAge());
        }

        if (catForm.getOwnerId() != null) {
            catToUpdate.setOwnerId(catForm.getOwnerId());
        }

        this.catRepository.save(catToUpdate);

        return this.webApiMapper.entityToDTO(catToUpdate);
    }

    @Override
    public List<CatDTO> search(CatForm catForm) {
        BooleanBuilder predicate = new BooleanBuilder();

        QCat qCat = QCat.cat;

        if (catForm.getName() != null) {
            predicate.and(qCat.name.eq(catForm.getName()));
        }

        if (catForm.getAge() > 0) {
            predicate.and(qCat.age.eq(catForm.getAge()));
        }

        if (catForm.getOwnerId() != null) {
            predicate.and(qCat.ownerId.eq(catForm.getOwnerId()));
        }

        return StreamSupport.stream(this.catRepository.findAll(predicate).spliterator(), false)
                .map(webApiMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
