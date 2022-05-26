public class Instinct implements IEvenement {
    
    private IEtat etatCourant;
    private Animal animal;

    public Instinct(IEtat etatCourant, Animal animal){
        this.etatCourant = etatCourant;
        this.animal = animal;
    }

    //

    public Animal getAnimal(){
        return this.animal;
    }

    public IEtat getEtatCourant(){
        return this.etatCourant;
    }

    public void changerEtat(IEtat etat){
        this.etatCourant = etat;
    }

    //

    @Override
    public void agir(){
        this.etatCourant.agir(this);
    }

}
