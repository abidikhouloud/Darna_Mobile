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
public class Offre {
    
    private int id;
    private String description;
    private String date;
    private String region;
    private double prix;
    private double surface;
    private String engagement;
    private String type;
    private Gerant g;
 
    

   
    
    public Offre()
    {}
    public Offre(int id,String description,String date, String region, double prix, double surface, String type,String engagement,Gerant g)
    {
      this.id=id;
      this.description=description;
      this.date=date;
      this.region=region;
      this.prix=prix;
      this.surface=surface;
      this.engagement=engagement;
      this.type=type;
      this.g=g;
    }

  
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getRegion() {
        return region;
    }

    public double getPrix() {
        return prix;
    }

    public double getSurface() {
        return surface;
    }

    public String getEngagement() {
        return engagement;
    }

   
    

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
    }

    public Gerant getG() {
        return g;
    }

    public void setG(Gerant g) {
        this.g = g;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "Offre{" + "id=" + id + ", description=" + description + ", date=" + date + ", region=" + region + ", prix=" + prix + ", surface=" + surface + ", engagement=" + engagement + ", type=" + type + ", g=" + g + '}';
    }

  
    

 
   

    
    
    
}
