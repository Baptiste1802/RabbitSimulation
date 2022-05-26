public class Vegetal{
    
    private String espece;
    private Territoire territoire;

    public Vegetal(String espece, Territoire territoire){
        this.espece = espece;
        this.territoire = territoire;
    }
    
    //

    public Territoire getTerritoire() {
        return this.territoire;
    }

    public String getEspece() {
        return this.espece;
    }

    //

    @Override
    public String toString(){
        return this.espece;
    }
}