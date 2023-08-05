package com.company;

import java.util.ArrayList;

public class Quiz {
    private String quizName;
    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> answers;
    private ArrayList<String> correctAnswer;

    public Quiz(String name, ArrayList<String> questions, ArrayList<ArrayList<String>> answers, ArrayList<String> correctAnswer) {
        this.questions = questions;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.quizName = name;
    }

    public Quiz() {
        questions = new ArrayList<String>();
        ArrayList<String> a = new ArrayList<String>();
        answers = new ArrayList<ArrayList<String>>();
        correctAnswer = new ArrayList<String>();
    }

    public void addQuestion(String question) {
        questions.add(question);

    }

    public void addAnswer(ArrayList<String> answers, String correctAnswer) {
        this.correctAnswer.add(correctAnswer);
        this.answers.add(answers);
    }

    public void deleteQuestion(int index) {
        if (index < 0 || index > questions.size()) {
            System.out.println("There is no question at index " + index + ".");
            return;
        }
        String deletedQ = questions.remove(index);
        ArrayList<String> deleteA = answers.remove(index);
        String correctA = correctAnswer.remove(index);

    }


    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public ArrayList<ArrayList<String>> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<ArrayList<String>> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(ArrayList<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}
