/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author HA GIA KHANH
 */
public class ExerciseDTO {

    private int exerciseID;
    private String title, media, description, status;
    private Date createdDate;
    private ClassesDTO classes;
    private LectureDTO lecturer;
//    private Date startDate;
//    private Date endDate;
    private Timestamp startDate;
    private Timestamp endDate;

    public ExerciseDTO() {
    }

    public ExerciseDTO(int exerciseID, String title, String media, String description, String status, Date createdDate, ClassesDTO classes, LectureDTO lecturer) {
        this.exerciseID = exerciseID;
        this.title = title;
        this.media = media;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.classes = classes;
        this.lecturer = lecturer;
    }

    public ExerciseDTO(int exerciseID, String title, String media, String description, String status, Date createdDate, ClassesDTO classes, LectureDTO lecturer, Timestamp startDate, Timestamp endDate) {
        this.exerciseID = exerciseID;
        this.title = title;
        this.media = media;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.classes = classes;
        this.lecturer = lecturer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    
    
}
