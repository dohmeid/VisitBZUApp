package com.example.visitbzu.features.faq;

import android.widget.ImageView;

public class sararom_Question {
    private String question,description;
    private boolean expandable;
    private ImageView drop_arrow;

    public sararom_Question(String question, String description) {
        this.question = question;
        this.description = description;
        this.expandable = false;

    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
