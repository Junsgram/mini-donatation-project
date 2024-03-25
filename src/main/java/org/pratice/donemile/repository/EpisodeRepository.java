package org.pratice.donemile.repository;

import org.pratice.donemile.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    @Query("select e from Episode e where e.title like %:title%")
    List<Episode> findByTitle(@Param("title") String title);
    @Query("select e from Episode e where e.content like %:content%")
    List<Episode> findByContent(@Param("content") String content);

}
