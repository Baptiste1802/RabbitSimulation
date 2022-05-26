public class EtatAffame implements IEtat{

    @Override
    public void agir(Instinct i){
        Animal a = i.getAnimal();
        a.seNourrir();
        if (a.getEnergie() > 5){
            i.changerEtat(new EtatRassasie());
        }
    }

}