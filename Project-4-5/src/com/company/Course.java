package com.company;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Quiz> quizList;
    public Course(String name) {
        courseName = name;
    }
    public void addQuiz(Quiz q) {
        quizList.add(q);
    }
    public void removeQuiz(Quiz q) {
        quizList.remove(q);
    }
    public Quiz getQuiz(int index) {
        return quizList.get(index);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }
}
