/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ACER
 */
public class UserGoogleDto {

    private String id;
    private String name;
    private String email;
    private String given_name;
    private boolean verified_email;
    private String picture;

    public UserGoogleDto() {
    }

    public UserGoogleDto(String id, String name, String email, String given_name, boolean verified_email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.given_name = given_name;
        this.verified_email = verified_email;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
