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
public class Logement extends Offre{
    
    private int nbrChambre;
    private double surfaceCouverte;

    public Logement(int nbrChambre, double surfaceCouverte, int id, String description, String date, String region, double prix, double surface, String engagement,String type,Gerant gerant )
    {
        super(id, description, date, region, prix, surface, engagement,type,gerant);
        this.nbrChambre = nbrChambre;
        this.surfaceCouverte = surfaceCouverte;
    }

    public Logement() {
       
    }

    public int getNbrChambre()
    {
        return nbrChambre;
    }

    public double getSurfaceCouverte()
    {
        return surfaceCouverte;
    }

    public void setSurfaceCouverte(double surfaceCouverte) {
        this.surfaceCouverte = surfaceCouverte;
    }

    public void setNbrChambre(int nbrChambre)
    {
        this.nbrChambre = nbrChambre;
    }

    
    public String toString() {
        return super.getEngagement()+" Logement : \n" +super.toString()+ " contient " + nbrChambre + " chambre sa surface Couverte est " + surfaceCouverte + '\n';
    }

    
    
    

    
}
