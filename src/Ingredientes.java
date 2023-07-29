/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Juan Pablo
 */
public class Ingredientes {
   
    private String pan;
    private String carne;
    private String queso;
    private String lechuga;

    public Ingredientes(String pan, String carne) {
        this.pan = pan;
        this.carne = carne;
    }

    public Ingredientes(String pan, String carne, String queso) {
        this.pan = pan;
        this.carne = carne;
        this.queso = queso;
    }

    public Ingredientes(String pan, String carne, String queso, String lechuga) {
        this.pan = pan;
        this.carne = carne;
        this.queso = queso;
        this.lechuga = lechuga;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getQueso() {
        return queso;
    }

    public void setQueso(String queso) {
        this.queso = queso;
    }

    public String getLechuga() {
        return lechuga;
    }

    public void setLechuga(String lechuga) {
        this.lechuga = lechuga;
    }
    
    
    
}
