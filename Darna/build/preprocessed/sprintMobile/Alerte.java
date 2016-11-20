package sprintMobile;

public class Alerte {
    
     private int id;
    private double prixMin;
    private double prixMax;
    private String region;
    private Client client;
    private Offre offre;
    private String engagement;
    private String typedeBien;
    private double surface;
    public Alerte() {
    }
   

    public Alerte(double prixMin, double prixMax, String region,String engagement,String type,double s,Client c,Offre o) 
    {
        
        this.prixMin = prixMin;
        this.prixMax = prixMax;
        this.region = region;
        this.engagement=engagement;
        this.typedeBien=type;
        this.surface=s;
        this.client=c;
        this.offre=o;
    }

    public Alerte(int id, double prixMin, double prixMax, String region, Client client, String engagement, String typedeBien, double surface) {
        this.id = id;
        this.prixMin = prixMin;
        this.prixMax = prixMax;
        this.region = region;
        this.client = client;
        this.engagement = engagement;
        this.typedeBien = typedeBien;
        this.surface = surface;
    }
    

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
    }

    public String getTypedeBien() {
        return typedeBien;
    }

    public void setTypedeBien(String typedeBien) {
        this.typedeBien = typedeBien;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }
    

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }

    public double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   
    
    

   
    

    
       
    
}