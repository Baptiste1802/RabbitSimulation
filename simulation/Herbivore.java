import java.util.List;

public class Herbivore implements IRegime{

    private List<String> aliments;

    public Herbivore(List<String> aliments){
        this.aliments = aliments;
    }

    //

    @Override
    public Territoire chercherNourriture(Animal a){

        Territoire territoire = a.getTerritoire();
        List<String> predateurs = a.getPredateurs();

        if (!territoire.contientPredateur(predateurs)){
            for (String espece : this.aliments){
                if (territoire.trouverVegetal(espece) != null){
                    return territoire;
                }
            } 
        }

        for (Territoire t : territoire.getVoisins()){
            if (!t.contientPredateur(predateurs)){
                for (String espece : this.aliments){
                    if (t.trouverVegetal(espece) != null){
                        return t;
                    }
                } 
            }
        }

        return null;
    }

    @Override
    public List<String> getAliments() {
        return this.aliments;
    }
    
    @Override
    public boolean manger(Animal a){
        Territoire territoire = a.getTerritoire();
        for (String espece : this.aliments){
            Vegetal vegetal = territoire.trouverVegetal(espece);
            if (vegetal != null){
                a.getTerritoire().removeVegetal(vegetal);
                vegetal = null;
                a.ajouterEnergie(1);
                Monde.carottesMangees+=1;
                return true;
            }
        }
        return false;
    }

}