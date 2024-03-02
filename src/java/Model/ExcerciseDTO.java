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
public class ExcerciseDTO {
    private int excercise_id;
    private String title, media, description;
    private boolean status;
    private Date created_date;
    private ClassesDTO classes;
    private LectureDTO lecturer;

    public ExcerciseDTO() {
    }

    public ExcerciseDTO(int excercise_id, String title, String media, String description, boolean status, Date created_date, ClassesDTO classes, LectureDTO lecturer) {
        this.excercise_id = excercise_id;
        this.title = title;
        this.media = media;
        this.description = description;
        this.status = status;
        this.created_date = created_date;
        this.classes = classes;
        this.lecturer = lecturer;
    }

    public int getExcercise_id() {
        return excercise_id;
    }

    public void setExcercise_id(int excercise_id) {
        this.excercise_id = excercise_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public ClassesDTO getClasses() {
        return classes;
    }

    public void setClasses(ClassesDTO classes) {
        this.classes = classes;
    }

    public LectureDTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LectureDTO lecturer) {
        this.lecturer = lecturer;
    }
    
}
