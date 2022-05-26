public class EtatRassasie implements IEtat{

    @Override
    public void agir(Instinct i){
        Animal a = i.getAnimal();
        if (!a.seReproduire()){
            a.seNourrir();
        }
        if (a.getEnergie() <= 5){
            i.changerEtat(new EtatAffame());
        }
    }

}