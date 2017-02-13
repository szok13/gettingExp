package com.example.android.myquiz;

import java.util.List;

/**
 * Created by Piotr on 2017-02-08.
 */

public class Question {
    private String questionContent;
    private List<String> answers;
    private int correctAnswer;
    private String questionImageURI;
    private boolean hasImage;

    public Question(String questionContent, List<String> answers, int correctAnswer, String questionImageURI, boolean hasImage) {
        this.questionContent = questionContent;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.questionImageURI = questionImageURI;
        this.hasImage = hasImage;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionImageURI() {
        return questionImageURI;
    }

    public void setQuestionImageURI(String questionImageURI) {
        this.questionImageURI = questionImageURI;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }
}

