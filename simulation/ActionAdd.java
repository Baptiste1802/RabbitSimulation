public class ActionAdd implements IAction{

    private Animal animal;
    
    public ActionAdd(Animal animal){
        this.animal = animal;
    }

    @Override
    public void action(){
        Monde.getInstance().addAnimal(this.animal);
    }
    
}
