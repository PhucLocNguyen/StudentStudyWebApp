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
public class LectureDTO {

    private int id;
    private String name, image, email, password;

    public LectureDTO() {
    }

    public LectureDTO(int id, String name, String image, String email, String password) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LectureDTO{" + "id=" + id + ", name=" + name + ", image=" + image + ", email=" + email + ", password=" + password + '}';
    }

}
