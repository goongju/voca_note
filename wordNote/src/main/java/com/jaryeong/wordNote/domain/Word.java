package com.jaryeong.wordNote.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;

    private String english;
    private String korean;

    public Word() {
    }

    @Builder
    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }
}