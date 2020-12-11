/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Notification {
     private int id_notif;
    private String type;
    private String message;
    private int vu;

    public Notification() {
    }

    public Notification(int id_notif, String type, String message, int vu) {
        this.id_notif = id_notif;
        this.type = type;
        this.message = message;
        this.vu = vu;
    }
    public Notification( String type, String message, int vu) {
        this.type = type;
        this.message = message;
        this.vu = vu;
    }

    public int getId_notif() {
        return id_notif;
    }

    public void setId_notif(int id_notif) {
        this.id_notif = id_notif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getVu() {
        return vu;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }

    @Override
    public String toString() {
        return "Notification{" + "id_notif=" + id_notif + ", type=" + type + ", message=" + message + ", vu=" + vu + '}';
    }
    
    
}
