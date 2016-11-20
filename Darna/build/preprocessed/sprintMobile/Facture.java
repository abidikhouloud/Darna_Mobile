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
public class Facture {
    private int id;
    private String date;
    private Gerant gerant;
    private Client client;
    private Offre offre;

    public Facture() {
    }

    
    
    
    public Facture(String date, Gerant idGerant, Client idClient, Offre idOffre) {
        this.date = date;
        this.gerant = idGerant;
        this.client = idClient;
        this.offre = idOffre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public Client getClient() {
        return client;
    }

    public Offre getOffre() {
        return offre;
    }


    
    

  

   

    
    
    
    
    
    
    
}
