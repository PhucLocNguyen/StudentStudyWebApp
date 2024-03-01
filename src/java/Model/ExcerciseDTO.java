/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author HA GIA KHANH
 */
public class ExcerciseDTO {

    private int excerciseID;
    private String title, media, description, status;
    private Date createdDate;
    private ClassesDTO classes;
    private LectureDTO lecturer;

    public ExcerciseDTO() {
    }

    public ExcerciseDTO(int excerciseID, String title, String media, String description, String status, Date createdDate, ClassesDTO classes, LectureDTO lecturer) {
        this.excerciseID = excerciseID;
        this.title = title;
        this.media = media;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.classes = classes;
        this.lecturer = lecturer;
    }

    public int getExcerciseID() {
        return excerciseID;
    }

    public void setExcerciseID(int excerciseID) {
        this.excerciseID = excerciseID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
