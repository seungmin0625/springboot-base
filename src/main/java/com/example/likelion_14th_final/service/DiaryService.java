package com.example.likelion_14th_final.service;


import com.example.likelion_14th_final.dto.DiaryRequest;
import com.example.likelion_14th_final.dto.DiaryResponse;
import com.example.likelion_14th_final.entity.Diary;
import com.example.likelion_14th_final.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryResponse save(DiaryRequest dto){
        Instant instant = Instant.ofEpochMilli(dto.getCreatedDate());
        LocalDateTime createdDate = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Seoul"));

        Diary diary = new Diary();
        diary.setCreatedDate(createdDate);
        diary.setEmotionId(dto.getEmotionId());
        diary.setContent(dto.getContent());

        Diary saved = diaryRepository.save(diary);
        return DiaryResponse.fromEntity(saved);
    }

    public DiaryResponse findById(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID " + id + " not found"));
        return DiaryResponse.fromEntity(diary);
    }

    public DiaryResponse update(Long id, DiaryRequest dto) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID " + id + " not found"));

        Instant instant = Instant.ofEpochMilli(dto.getCreatedDate());
        LocalDateTime createdDate = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Seoul"));

        diary.setCreatedDate(createdDate);
        diary.setEmotionId(dto.getEmotionId());
        diary.setContent(dto.getContent());

        Diary updated = diaryRepository.save(diary);
        return DiaryResponse.fromEntity(updated);
    }

    public List<DiaryResponse> getDiariesByMonth(YearMonth yearMonth){
        LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime end = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Diary> diaries = diaryRepository.findByCreatedDateBetween(start, end);

        List<DiaryResponse> result = new ArrayList<>();
        for (Diary diary : diaries) {
            result.add(DiaryResponse.fromEntity(diary));
        }

        return result;
    }

    public Map<String, String> deleteDiary(Long id){
        if (diaryRepository.existsById(id)) {
            diaryRepository.deleteById(id);
            return Map.of("message", "일기 삭제 완료");
        } else {
            throw new RuntimeException("ID " + id + "는 등록되어 있지 않습니다.");
        }
    }
}
