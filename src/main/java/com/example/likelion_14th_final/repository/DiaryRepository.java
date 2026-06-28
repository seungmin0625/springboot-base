package com.example.likelion_14th_final.repository;

import com.example.likelion_14th_final.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
