import java.util.ArrayList;
import java.util.Arrays;

public class Renard extends Animal{

    private Animal mere;
    
    public Renard(Territoire territoire, Animal mere) {
        super(territoire, "Renard", 5, new ArrayList<>(), new Carnivore(Arrays.asList("Lapin")));
        this.deplacement = new Enfant();
        this.mere = mere;
    }

    //

    @Override
    public Animal getMere(){
        return this.mere;
    }

    //

    @Override
    public void survivre(){
        this.deplacement.seDeplacer(this);
        this.agir();
    }

    @Override
    public Animal accoucher(){
        return new Renard(this.territoire, this);
    }

    @Override
    public boolean seNourrir(){
        if (this.estJeune()){

        } else {
            return this.regime.manger(this);
        }
        return true;
    }

}
