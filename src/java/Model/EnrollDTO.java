/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;
/**
 *
 * @author ACER
 */
public class EnrollDTO {
    private StudentDTO student;
    private ClassesDTO classes;

    public EnrollDTO() {
    }

    public EnrollDTO(StudentDTO student, ClassesDTO classes) {
        this.student = student;
        this.classes = classes;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public ClassesDTO getClasses() {
        return classes;
    }

    public void setClasses(ClassesDTO classes) {
        this.classes = classes;
    }
    
}
