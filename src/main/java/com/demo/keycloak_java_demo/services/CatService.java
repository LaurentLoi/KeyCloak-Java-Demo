package com.demo.keycloak_java_demo.services;

import com.demo.keycloak_java_demo.models.dtos.CatDTO;
import com.demo.keycloak_java_demo.models.forms.CatForm;

import java.util.List;

public interface CatService {

    List<CatDTO> getAll();

    CatDTO getOne(long id);

    CatDTO insertWithReturnValue(CatForm catForm) throws Exception;

    void insert(CatForm catForm) throws Exception;

    String delete(long id);

    CatDTO update(long id, CatForm catForm);

    List<CatDTO> search(CatForm catForm);

}
