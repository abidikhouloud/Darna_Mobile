package Entity;
public class reclamation {

    private int id;  
    private String question;
    private String reponse;
    

    public reclamation (int id, String question, String reponse) {
        this.id = id;
        this.question = question;
        this.reponse = reponse;
    }

    
    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question = question;
    }

    public String getreponse() {
        return reponse;
    }

    public void setreponse(String reponse) {
        this.reponse = reponse;
    }

    public String toString() {
        return "reclamation{" + "id=" + id + ", question=" + question + ", reponse=" + reponse + '}';
    }

    public reclamation() {
    }
    


    reclamation getreclamation() {
        return null;
    }
}
