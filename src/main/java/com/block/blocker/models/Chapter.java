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

    public Long getId() {
        return id;
    }

    public Chapter() {

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