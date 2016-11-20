package Entity;
public class transporteur {

    private int id;  
    private int tel;
    private String adresse;
    

    public transporteur (int id, int tel, String adresse) {
        this.id = id;
        this.tel = tel;
        this.adresse = adresse;
    }

    
    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public int getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = Integer.parseInt(tel);
    }

    public String getadresse() {
        return adresse;
    }

    public void setadresse(String adresse) {
        this.adresse = adresse;
    }

    public String toString() {
        return "transporteur{" + "id=" + id + ", tel=" + tel + ", adresse=" + adresse + '}';
    }

    
    

   

    public transporteur() {
    }
    


    

   
}
