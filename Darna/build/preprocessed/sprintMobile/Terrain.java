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
public class Terrain  extends Offre{
    
    private String vocation;
    


    public Terrain(String vocation, int id, String description, String date, String region, double prix, double surface,String type ,String engagement,Gerant gerant) 
    {
        super(id, description, date, region, prix, surface,type, engagement,gerant);
        this.vocation = vocation;
    }

    public Terrain() {
      
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    
    }

    public String toString() {
        return super.getEngagement()+" Terrain : \n" +super.toString()+ " de vocation " + vocation + '\n';
    }
    

}
