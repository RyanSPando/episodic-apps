package com.example.shows;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EpisodesRepository extends CrudRepository<Episode, Long>{

  @Query("SELECT e FROM episodes e WHERE e.show_id = :id")
  List<Episode> findByShowId(@Param("id") long id);
}
