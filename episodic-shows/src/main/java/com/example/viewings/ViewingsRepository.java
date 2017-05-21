package com.example.viewings;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public interface ViewingsRepository extends CrudRepository<Viewing, Long> {
  @Modifying
  @Query("update viewings v set v.updatedAt = ?1, v.timecode = ?2 where v.id = ?3")
  void setViewingInfoById(LocalDateTime updatedAt, int timecode, Long viewingId);

  @Query("select v from viewings v where v.userId = ?1")
  List<Viewing> getViewingsByUserId(Long userId);

  List<Viewing> findAll();
}
