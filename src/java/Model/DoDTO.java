/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author User
 */
public class DoDTO {
    private StudentDTO student;
    private ExerciseDTO exercise;
    private String solution;
    private float score;
    private boolean isGrade;
    private Date createDate;

    public DoDTO() {
    }

    public DoDTO(StudentDTO student, ExerciseDTO exercise, String solution, float score, boolean isGrade, Date createDate) {
        this.student = student;
        this.exercise = exercise;
        this.solution = solution;
        this.score = score;
        this.isGrade = isGrade;
        this.createDate = createDate;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public ExerciseDTO getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseDTO exercise) {
        this.exercise = exercise;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isIsGrade() {
        return isGrade;
    }

    public void setIsGrade(boolean isGrade) {
        this.isGrade = isGrade;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
