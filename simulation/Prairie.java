public class Prairie extends Territoire{

    public Prairie(String nom){
        super(nom);
    }

    @Override
    public void actualiser(){
        this.addVegetal(new Vegetal("Carotte", this));
    }

}