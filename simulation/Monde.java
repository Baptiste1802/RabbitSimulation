import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monde{

    public static boolean showInfos = true;
    public static List<Animal> mortDeFaim;
    public static int carottesMangees = 0;

    private Carte carte;
    private int jour;
    private static Monde INSTANCE = new Monde();
    private Random random;
    private List<IAction> actionsJour;

    private Monde(){
        this.carte = new Carte();
        this.random = new Random();
        this.actionsJour = new ArrayList<>();
        Monde.mortDeFaim = new ArrayList<>();
    }

    public static Monde getInstance(){
        return INSTANCE;
    }

    public boolean getBool(){
        return this.random.nextBoolean();
    }

    public int getInt(int max){
        return this.random.nextInt(max);
    }

    // public void removeEspece(Espece e){
    //     e.getTerritoire().removeEspece(e);
    //     e = null;
    // }

    // public void addEspece(Espece e){
    //     e.getTerritoire().addEspece(e);
    // }

    public void deplacerAnimal(Animal a, Territoire ancien, Territoire nouveau){
        if (a.estVivant()){
            ancien.removeAnimal(a);
            nouveau.addAnimal(a);
            // System.out.println(a.getEspece() + "  " + ancien.getNom() + " vers " + nouveau.getNom());
        }
    }

    public void removeAnimal(Animal a){
        a.getTerritoire().removeAnimal(a);
        a = null;
    }

    public void addAnimal(Animal a){
        a.getTerritoire().addAnimal(a);
    }

    public Carte getCarte(){
        return this.carte;
    }

    public void lancerSimulation(){

        this.jour = 1;
        while(this.jour <= 1000){
            System.out.println("------------------------------------");
            System.out.println("             " + "Jour" + this.jour);
            System.out.println("------------------------------------");
            this.simulerJour();
            System.out.println(Monde.getInstance().carte);
            this.jour += 1;
        }
    }

    public void simulerJour(){
        this.actionsJour.clear();
        this.carte.simulerJour();
        this.effectuerActions();
    }

    public void addAction(IAction action){
        this.actionsJour.add(action);
    }

    private void effectuerActions(){
        for (IAction action : this.actionsJour){
            action.action();
        }
    }

    public static void main(String[] args) {

        Prairie p1 = new Prairie("p1");
        Prairie p2 = new Prairie("p2");
        Prairie p3 = new Prairie("p3");
        Prairie p4 = new Prairie("p4");
        Prairie p5 = new Prairie("p5");
        Prairie p6 = new Prairie("p6");
        Prairie p7 = new Prairie("p7");
        Prairie p8 = new Prairie("p8");
        Prairie p9 = new Prairie("p9");
        Prairie p10 = new Prairie("p10");

        Monde.getInstance().getCarte().addTerritoire(p1);
        Monde.getInstance().getCarte().addTerritoire(p2);
        Monde.getInstance().getCarte().addTerritoire(p3);
        Monde.getInstance().getCarte().addTerritoire(p4);
        Monde.getInstance().getCarte().addTerritoire(p5);
        Monde.getInstance().getCarte().addTerritoire(p6);
        Monde.getInstance().getCarte().addTerritoire(p7);
        Monde.getInstance().getCarte().addTerritoire(p8);
        Monde.getInstance().getCarte().addTerritoire(p9);
        Monde.getInstance().getCarte().addTerritoire(p10);
        Monde.getInstance().getCarte().addVoisin(p1, p2);
        Monde.getInstance().getCarte().addVoisin(p2, p3);
        Monde.getInstance().getCarte().addVoisin(p3, p4);
        Monde.getInstance().getCarte().addVoisin(p4, p5);
        Monde.getInstance().getCarte().addVoisin(p5, p1);
        Monde.getInstance().getCarte().addVoisin(p1, p3);
        Monde.getInstance().getCarte().addVoisin(p1, p10);
        Monde.getInstance().getCarte().addVoisin(p9, p10);
        Monde.getInstance().getCarte().addVoisin(p9, p4);
        Monde.getInstance().getCarte().addVoisin(p8, p9);
        Monde.getInstance().getCarte().addVoisin(p5, p8);
        Monde.getInstance().getCarte().addVoisin(p7, p3);
        Monde.getInstance().getCarte().addVoisin(p7, p6);
        Monde.getInstance().getCarte().addVoisin(p5, p6);

        Animal l1 = new Lapin(p1);
        Animal l2 = new Lapin(p2);
        p1.addAnimal(l1);
        p2.addAnimal(l2);
        l1.setSexe(1);
        l2.setSexe(0);
        l1.setAge(7);
        l2.setAge(7);

        Monde.getInstance().lancerSimulation();
        System.out.print("Animaux morts de faim : " + Monde.mortDeFaim.size() + "\n");
        System.out.print("Nombre de carottes mangées : " + Monde.carottesMangees + "\n");
    }

}