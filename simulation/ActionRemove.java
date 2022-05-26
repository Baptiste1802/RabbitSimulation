public class ActionRemove implements IAction {

    private Animal animal;
    
    public ActionRemove(Animal animal){
        this.animal = animal;
    }

    @Override
    public void action(){
        Monde.getInstance().removeAnimal(this.animal);
    }

}
