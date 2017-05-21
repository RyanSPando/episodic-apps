package com.example.shows;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.GeneratedValue;
import java.util.List;

public interface EpisodesRepository extends CrudRepository<Episode, Long>{

  List<Episode> findByShowId(Long id);
}
