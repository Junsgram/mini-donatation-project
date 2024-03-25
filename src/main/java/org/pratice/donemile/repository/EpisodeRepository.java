package org.pratice.donemile.repository;

import org.pratice.donemile.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
