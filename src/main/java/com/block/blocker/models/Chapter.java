package com.block.blocker.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table( name = "chap")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String chapterTitle;



    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "composition_id")
    private Composition composition;

    public Long getId() {
        return id;
    }

    public Chapter() {

    }

    public Chapter(String chapterTitle, String text, Composition composition) {
        this.chapterTitle = chapterTitle;
        this.text = text;
        this.composition = composition;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}