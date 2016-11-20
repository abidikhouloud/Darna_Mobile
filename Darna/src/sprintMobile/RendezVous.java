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
public class RendezVous {
    
     private int id;
    private String lieu;
    private String date;
    private Personne personne;
    private Offre offre;
    
    public RendezVous() {
    }

    public RendezVous( String lieu, String date,Personne personne, Offre offre) {
        this.id=id;
        this.lieu = lieu;
        this.date = date;
        this.personne = personne;
        this.offre = offre;
    }

    public Personne getPersonne() {
        return personne;
    }

    

    public Offre getOffre() {
        return offre;
    }

    public void setPersonne(Personne personne) {
        this.personne=personne;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

   
   

    public int getId() {
        return id;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(String date) {
        this.date = date;
    }

   
    public String toString() {
        return "RendezVous{" + "id=" + id + ", lieu=" + lieu + ", date=" + date + ", personne=" + personne + ", offre=" + offre + '}';
    }

    void setPersonne(String personne) {
        // this.personne = Personne.(personne); 
    }

    void setId(String id) {
        
  this.id = Integer.parseInt(id);      

        
    }

   
}
