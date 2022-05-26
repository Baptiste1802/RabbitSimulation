public class Enfant implements IDeplacer {

    public void seDeplacer(Animal a){

        Territoire territoire = a.getTerritoire().chercherMere(a.getMere());
        if (territoire == null){
            System.out.println("la mère est morte !!");
            a.mourrir();
            Monde.getInstance().addAction(new ActionRemove(a));
        }
        else {
            System.out.println("Je suis ma maman !");
            a.seDeplacer(territoire);
        }

    }
    
}
