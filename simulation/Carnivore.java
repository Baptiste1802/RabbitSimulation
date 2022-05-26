import java.util.List;

public class Carnivore implements IRegime{

    private List<String> aliments;

    public Carnivore(List<String> aliments){
        this.aliments = aliments;
    }

    //

    @Override
    public Territoire chercherNourriture(Animal a){

        Territoire territoire = a.getTerritoire();
        List<String> predateurs = a.getPredateurs();

        if (!territoire.contientPredateur(predateurs)){
            for (String espece : this.aliments){
                if (territoire.trouverAnimal(espece) != null){
                    return territoire;
                }
            } 
        }

        for (Territoire t : territoire.getVoisins()){
            if (!t.contientPredateur(predateurs)){
                for (String espece : this.aliments){
                    if (t.trouverAnimal(espece) != null){
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
            Animal animal = territoire.trouverAnimal(espece);
            if (animal != null){
                animal.mourrir();
                a.ajouterEnergie(1);
                Monde.getInstance().addAction(new ActionRemove(animal));
                return true;
            }
        }
        return false;
    }

}