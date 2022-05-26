import java.util.Arrays;

public class Lapin extends Animal{
    
    public Lapin(Territoire territoire) {
        super(territoire, "Lapin", 4, Arrays.asList("Renard"), new Herbivore(Arrays.asList("Carotte")));
        this.deplacement = new Independant();
    }

    //

    @Override
    public Animal getMere(){
        return null;
    }

    @Override
    public void survivre(){
        this.deplacement.seDeplacer(this);
        this.agir();
    }

    @Override
    public Animal accoucher(){
        return new Lapin(this.territoire);
    }

    @Override
    public boolean seNourrir(){
        return this.regime.manger(this);
    }

}