/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

/**
 *
 * @author raja
 */
public class Entreprise extends Offre {
    
    private int nbr_etage;
    private double surfaceCouverte;

    public Entreprise(int nbr_etage, double surfaceCouverte, int id, String description, String date, String region, double prix, double surface, String engagement,String type,Gerant gerant) 
    {
        super(id, description, date, region, prix, surface, engagement,type,gerant);
        this.nbr_etage = nbr_etage;
        this.surfaceCouverte = surfaceCouverte;
    }

    public Entreprise() {
        
    }

    public int getNbr_etage()
    {
        return nbr_etage;
    }

    public double getSurfaceCouverte() 
    {
        return surfaceCouverte;
    }

    public void setNbr_etage(int nbr_etage)
    {
        this.nbr_etage = nbr_etage;
    }

    public void setSurfaceCouverte(double surfaceCouverte) 
    {
        this.surfaceCouverte = surfaceCouverte;
    }

   
    public String toString() {
        return super.getEngagement()+ " Entreprise :\n" +super.toString()+ " a " + nbr_etage + " étages sa surfaceCouverte est " + surfaceCouverte + "\n";
    }
        
}
