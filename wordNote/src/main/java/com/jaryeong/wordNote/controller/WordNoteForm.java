package com.jaryeong.wordNote.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class WordNoteForm {
    @NotEmpty(message = "영어를 입력해주세요.")
    private String english;
    @NotEmpty(message = "뜻을 입력해주세요.")
    private String korean;
}
