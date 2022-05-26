import java.util.ArrayList;
import java.util.List;

public abstract class Animal implements IEvenement {

    private String espece;
    private int energie;
    private int sexe;
    private int age;
    private Instinct instinct;
    private boolean vivant;
    protected Territoire territoire;
    protected IDeplacer deplacement;
    protected List<String> predateurs;
    protected IRegime regime;

    public Animal(Territoire territoire, String nomEspece, int energieBase, List<String> predateurs, IRegime regime){
        this.territoire = territoire;
        this.espece = nomEspece;
        this.energie = energieBase;
        this.sexe = Monde.getInstance().getBool() ? 1 : 0;
        this.age = 0;
        this.instinct = new Instinct(new EtatAffame(), this);
        this.predateurs = predateurs;
        this.regime = regime;
        this.vivant = true;
    }

    //

    public String getEspece(){return this.espece;}
    public int getSexe(){return this.sexe;}
    public int getEnergie(){return this.energie;}
    public boolean estVivant(){return this.vivant;}
    public List<String> getPredateurs(){return this.predateurs;}
    public Territoire getTerritoire(){return this.territoire;}
    public IRegime getRegime(){return this.regime;}
    public IEtat getEtat(){return this.instinct.getEtatCourant();}
    public int getAge(){return this.age;}
    
    public void setEtat(IEtat etat){this.instinct.changerEtat(etat);}
    public void setDeplacement(IDeplacer deplacement){this.deplacement = deplacement;}
    public void setEnergie(int energie){this.energie = energie;}
    public void setAge(int age){this.age = age;}
    public void setSexe(int sexe){this.sexe = sexe;}

    //

    public abstract void survivre();
    public abstract Animal accoucher();
    public abstract boolean seNourrir();
    public abstract Animal getMere();

    //

    public void mourrir(){
        this.vivant = false;
        Monde.getInstance().addAction(new ActionRemove(this));
    }

    public void ajouterEnergie(int quantite){
        this.energie += quantite;
        if (this.energie < 0){
            this.energie = 0;
        }
    }

    public boolean estAffame(){
        return this.instinct.getEtatCourant() instanceof EtatAffame;
    }

    public boolean estJeune(){
        return this.age < 8;
    }

    public void seDeplacer(Territoire t){
        if (t.equals(this.territoire)){
            return ;
        }
        Monde.getInstance().addAction(new ActionDeplacer(this, this.territoire, t));
        this.territoire = t;
        this.ajouterEnergie(-1);
    }

    public boolean seReproduire(){
    
        Animal animal = this.territoire.trouverReproduction(this);

        if (animal == null){
            return false;
        }

        this.ajouterEnergie(-3);
        Monde.getInstance().addAction(new ActionAdd(this.accoucher()));
        
        return true;

    }

    //

    @Override
    public void agir(){
        this.age+=1;
        this.instinct.agir();
    }

    @Override
    public String toString(){
       return "<" + this.espece + " : " + this.energie + ", " + this.sexe + ", " + this.hashCode() + this.instinct.getEtatCourant() + ">";
    }

}