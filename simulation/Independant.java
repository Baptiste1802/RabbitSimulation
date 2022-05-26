public class Independant implements IDeplacer {
    
    @Override
    public void seDeplacer(Animal a){
        
        if (a.estAffame() || a.estJeune()){
            Territoire choix = a.getRegime().chercherNourriture(a);
            Territoire territoire = a.getTerritoire();
            if (choix == null){
                choix = territoire.trouveTerritoireSafe(a.getPredateurs());
            }
            if (choix == null){
                choix = territoire.aleatoire();
            } 
            a.seDeplacer(choix);
        }

        else {
            a.seDeplacer(a.getTerritoire().chercherReproduction(a));
        }

    }

}
