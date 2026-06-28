package com.example.likelion_14th_final.controller;

import com.example.likelion_14th_final.dto.DiaryRequest;
import com.example.likelion_14th_final.dto.DiaryResponse;
import com.example.likelion_14th_final.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    public DiaryResponse create(@RequestBody DiaryRequest diaryRequest){
        return diaryService.save(diaryRequest);
    }

    @GetMapping
    public List<DiaryResponse> getDiariesByMonth(@RequestParam("month") YearMonth yearMonth){
        return diaryService.getDiariesByMonth(yearMonth);
    }

    @GetMapping("/{id}")
    public DiaryResponse findById(@PathVariable Long id) {
        return diaryService.findById(id);
    }

    @PutMapping("/{id}")
    public DiaryResponse update(@PathVariable Long id, @RequestBody DiaryRequest diaryRequest) {
        return diaryService.update(id, diaryRequest);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteDiary(@PathVariable Long id){
        return diaryService.deleteDiary(id);
    }
}
