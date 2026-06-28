package com.example.likelion_14th_final.dto;

import com.example.likelion_14th_final.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
public class DiaryResponse {
    private Long id;
    private Long createdDate; // timestamp
    private int emotionId;
    private String content;

    public static DiaryResponse fromEntity(Diary diary) {
        long timestamp = diary.getCreatedDate()
                .atZone(ZoneId.of("Asia/Seoul"))
                .toInstant()
                .toEpochMilli();

        return new DiaryResponse(
                diary.getId(),
                timestamp,
                diary.getEmotionId(),
                diary.getContent()
        );
    }
}
