import java.util.ArrayList;
import java.util.List;

public class Carte {
    
    // nodes
    private List<Territoire> territoires;

    public Carte(){
        this.territoires = new ArrayList<>();
    }

    //

    public void addVoisin(Territoire t1, Territoire t2){
        t1.addVoisin(t2);
        t2.addVoisin(t1);
    }

    public List<Territoire> getTerritoires(){
        return this.territoires;
    }

    public Territoire getTerritoire(int index){
        return this.territoires.get(index);
    }

    public void addTerritoire(Territoire t){
        this.territoires.add(t);
    }

    //

    public void simulerJour(){
        for (Territoire t : territoires){
            t.simulerJour();
        }
    }

    //

    @Override
    public String toString(){
        String res = "";
        for (Territoire t : this.territoires){
            res += t.toString() + "\n";
        }
        return res;
    }

}
