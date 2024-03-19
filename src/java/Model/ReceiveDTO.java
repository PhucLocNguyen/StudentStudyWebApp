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
public class ReceiveDTO {

    private NotificationDTO notification;
    private StudentDTO student;
    private boolean isRead;

    public ReceiveDTO() {
    }

    public ReceiveDTO(NotificationDTO notification, StudentDTO student, boolean isRead) {
        this.notification = notification;
        this.student = student;
        this.isRead = isRead;
    }

    public NotificationDTO getNotification() {
        return notification;
    }

    public void setNotification(NotificationDTO notification) {
        this.notification = notification;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

}
