package com.example.likelion_14th_final.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryRequest {
    private Long createdDate; // timestamp
    private int emotionId;
    private String content;
}
