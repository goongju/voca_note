package com.jaryeong.wordNote.controller;

import com.jaryeong.wordNote.domain.Word;
import com.jaryeong.wordNote.repository.WordNoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WordNoteController {
    private final WordNoteRepository wordNoteRepository;

    public WordNoteController(WordNoteRepository wordNoteRepository) {
        this.wordNoteRepository = wordNoteRepository;
    }
    @GetMapping("/wordNote/new")
    public String showWordNoteForm(Model model){
        model.addAttribute("wordNoteForm",new WordNoteForm());
        return "wordNote/wordNoteForm";
    }
    @PostMapping("/wordNote/new")
    public String createWordNote(@Valid WordNoteForm wordNoteForm, BindingResult result){
        if (result.hasErrors()){
            return "wordNote/wordNoteForm";
        }
        Word word=new Word();
        word.setEnglish(wordNoteForm.getEnglish());
        word.setKorean(wordNoteForm.getKorean());
        wordNoteRepository.save(word);
        return "redirect:/wordNote";
    }
    @GetMapping(value = "wordNote/{id}")
    public String updateShowWordNote(Model model, @PathVariable("id") Long id){
        Word word=wordNoteRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        WordNoteForm wordNoteForm=new WordNoteForm();
        wordNoteForm.setEnglish(word.getEnglish());
        wordNoteForm.setKorean(word.getKorean());
        model.addAttribute("wordNoteForm",wordNoteForm);
        return "wordNote/wordNoteForm";
    }
    @PostMapping(value = "/wordNote/{id}")
    public String updateWordNote(WordNoteForm form,@PathVariable ("id") Long id){
        Word word=wordNoteRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        word.setEnglish(form.getEnglish());
        word.setKorean(form.getKorean());
        wordNoteRepository.save(word);
        return "redirect:/wordNote";
    }
    @GetMapping(value = "/wordNote/delete/{id}")
    public String deleteWordNote(@PathVariable("id") long id){
        Word word=wordNoteRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        wordNoteRepository.delete(word);
        return "redirect:/wordNote";
    }
    @GetMapping("/wordNote")
    public String list(Model model){
        List<Word> word=wordNoteRepository.findAll();
        model.addAttribute("wordNote",word);
        return "wordNote/wordNoteList";
    }
    //test
    @GetMapping("/testEnglish")
    public String testEnglish(Model model){
        List<Word> word=wordNoteRepository.findAll();
        model.addAttribute("wordNote",word);
        return "wordNote/testEnglish";
    }
    @GetMapping("/testKorean")
    public String testKorean(Model model){
        List<Word> word=wordNoteRepository.findAll();
        model.addAttribute("wordNote",word);
        return "wordNote/testKorean";
    }
}
