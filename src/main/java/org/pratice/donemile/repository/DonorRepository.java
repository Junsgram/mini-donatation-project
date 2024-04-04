package org.pratice.donemile.repository;

import org.pratice.donemile.domain.Donor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Donor> findByEmail(String email);


    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Donor m where m.fromSocial = :social and m.email =:email")
    Optional<Donor> findByEmail(@Param("email") String email, @Param("social")boolean social);


}
