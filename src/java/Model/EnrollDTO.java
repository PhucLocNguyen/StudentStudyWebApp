/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author ACER
 */
public class EnrollDTO {

    private StudentDTO student;
    private ClassesDTO classes;
    private Date enroll_date;

    public EnrollDTO() {
    }

    public EnrollDTO(StudentDTO student, ClassesDTO classes, Date enroll_date) {
        this.student = student;
        this.classes = classes;
        this.enroll_date = enroll_date;
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

    public Date getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        this.enroll_date = enroll_date;
    }

}
