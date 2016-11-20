/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sprintMobile;

/**
 *
 * @author Raja
 */
public class Agence {
    private int id;
     private String adresse;
    private String tel;
    private String nom;
    
    
    

    public Agence() {
    }

    public Agence( String adresse, String tel, String nom) {
        this.adresse = adresse;
        this.tel = tel;
        this.nom = nom;
        
    }

    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public String toString() {
        return "Agence{" + "adress=" + adresse+ ", tel=" + tel+", nom="+nom+ "}";
    }

    void setId(String id) {
        this.id = Integer.parseInt(id);      
    }
    
    
}
