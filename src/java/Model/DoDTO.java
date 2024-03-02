/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class DoDTO {

    private ExcerciseDTO excercise;
    private StudentDTO student;
    private String solution;
    private float score;
    private boolean is_grade;
    private Date created_date;

    public DoDTO() {
    }

    public DoDTO(ExcerciseDTO excercise, StudentDTO student, String solution, float score, boolean is_grade, Date created_date) {
        this.excercise = excercise;
        this.student = student;
        this.solution = solution;
        this.score = score;
        this.is_grade = is_grade;
        this.created_date = created_date;
    }

    public ExcerciseDTO getExcercise() {
        return excercise;
    }

    public void setExcercise(ExcerciseDTO excercise) {
        this.excercise = excercise;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
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

    public boolean isIs_grade() {
        return is_grade;
    }

    public void setIs_grade(boolean is_grade) {
        this.is_grade = is_grade;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    
}
