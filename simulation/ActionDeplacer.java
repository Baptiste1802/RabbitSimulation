public class ActionDeplacer implements IAction {

    private Animal a;
    private Territoire ancien;
    private Territoire nouveau;
    
    public ActionDeplacer(Animal a, Territoire ancien, Territoire nouveau){
        this.a = a;
        this.ancien = ancien;
        this.nouveau = nouveau;
    }

    @Override
    public void action(){
        Monde.getInstance().deplacerAnimal(this.a, this.ancien, this.nouveau);
    }

}
