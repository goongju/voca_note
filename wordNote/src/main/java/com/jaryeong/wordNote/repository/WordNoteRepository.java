package com.jaryeong.wordNote.repository;

import com.jaryeong.wordNote.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordNoteRepository extends JpaRepository<Word,Long>{
    Word findByEnglish(final String english);
    Word findByKorean(final String korean);
}