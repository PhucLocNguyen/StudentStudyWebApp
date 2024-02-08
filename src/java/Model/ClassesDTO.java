/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ACER
 */
public class ClassesDTO {

    private int id;
    private String name, thumbnail, password, description;
    private LectureDTO lecturer;

    public ClassesDTO() {
    }

    public ClassesDTO(int id, String name, String thumbnail, String password, String description, LectureDTO lecturer) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.password = password;
        this.description = description;
        this.lecturer = lecturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LectureDTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LectureDTO lecturer) {
        this.lecturer = lecturer;
    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
