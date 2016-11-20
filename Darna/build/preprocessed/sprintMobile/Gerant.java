/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

/**
 *
 * @author ASUS
 */
public class Gerant extends Personne{

    private Agence idAgence;
    public Gerant(String password, String email, String nom, String prenom, String date, int tel,Agence idAgence) {
        super(password, email, nom, prenom, date, tel);
   this.idAgence=idAgence;
    }

    public Gerant() {
    }

    public Agence getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(Agence idAgence) {
        this.idAgence = idAgence;
    }
    
    
    
}
