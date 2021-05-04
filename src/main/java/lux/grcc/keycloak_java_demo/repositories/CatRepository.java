package lux.grcc.keycloak_java_demo.repositories;

import lux.grcc.keycloak_java_demo.models.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CatRepository extends JpaRepository<Cat, Long>, QuerydslPredicateExecutor<Cat> {
}
