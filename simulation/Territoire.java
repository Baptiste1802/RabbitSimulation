import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Territoire {

    private String nom;
    protected List<Animal> animaux;
    protected List<Vegetal> vegetaux;
    protected List<Territoire> voisins;

    public Territoire(String nom){
        this.voisins = new ArrayList<>();
        this.animaux = new ArrayList<>();
        this.vegetaux = new ArrayList<>();
        this.nom = nom;
    }

    //

    public List<Animal> getAnimaux() {return this.animaux;}

    public List<Vegetal> getVegetaux() {return this.vegetaux;}

    public String getNom() {return this.nom;}

    public List<Territoire> getVoisins() {return this.voisins;}

    public void removeAnimal(Animal a){this.animaux.remove(a);}

    public void removeVegetal(Vegetal v){this.vegetaux.remove(v);}

    public void addAnimal(Animal a){this.animaux.add(a);}

    public void addVegetal(Vegetal v){this.vegetaux.add(v);}

    public void addVoisin(Territoire t){this.voisins.add(t);}

    //

    public abstract void actualiser();

    //

    public Territoire chercherMere(Animal mere){

        for (Animal a : this.animaux){
            if (a.equals(mere) && a.estVivant()){
                System.out.println("MERE");
                return this;
            }
        }

        for (Territoire territoire : this.voisins){
            System.out.println("slaut");
            for (Animal a : territoire.getAnimaux()){
                if (a.equals(mere) && a.estVivant()){
                    System.out.println("MERE");
                    return territoire;
                }
            }
        }
        return null;
    }

    public Territoire chercherReproduction(Animal a){
        if (this.trouverReproduction(a) != null){
            return this;
        }
        for (Territoire t : this.voisins){
            if (t.trouverReproduction(a) != null){
                return t;
            }
        }
        return this.trouveTerritoireSafe(a.getPredateurs());
    }

    public Animal trouverReproduction(Animal a){
        for (Animal a2 : this.animaux){
            if (a2.getEspece().equals(a.getEspece())){
                if (a2.estVivant() && a2.getSexe() != a.getSexe() && !a2.estAffame() && !a2.estJeune() && a2.getTerritoire().equals(a.getTerritoire())){
                    return a2;
                } 
            }
        }
        return null;
    }

    public Territoire trouveTerritoireSafe(List<String> predateurs){
        for (Territoire territoire : this.voisins){
            if (!territoire.contientPredateur(predateurs)){
                return territoire;
            }
        }
        return null;
    }

    public Territoire aleatoire(){
        return this.voisins.get(Monde.getInstance().getInt(this.voisins.size()));
    }

    public boolean contientPredateur(List<String> predateurs){
        for (String p : predateurs){
            for (Animal a : this.animaux){
                if (a.getEspece().equals(p) && a.estVivant()){
                    return true;
                }
            }
        }
        return false;
    }

    public Vegetal trouverVegetal(String espece){
        for (Vegetal v : this.vegetaux){
            if (v.getEspece().equals(espece)){
                return v;
            }
        }
        return null;
    }

    public Animal trouverAnimal(String espece){
        for (Animal a : this.animaux){
            if (a.getEspece().equals(espece)){
                return a;
            }
        }
        return null;
    }

    public void simulerJour(){
        this.actualiser();
        for (Animal a : this.animaux){
            if (a.getEnergie() == 0){
                a.mourrir();
                Monde.mortDeFaim.add(a);
            }

            if (a.estVivant()){
                a.survivre();
            }        
        }
    }

    public int getNombreAnimal(String espece){
        int cpt = 0;
        for (Animal a : this.animaux){
            if (a.estVivant() && a.getEspece().equals(espece)){
                cpt+=1;
            }        
        }
        return cpt;
    }

    public int getNombreVegetal(String espece){
        int cpt = 0;
        for (Vegetal v : this.vegetaux){
            if (v.getEspece().equals(espece)){
                cpt+=1;
            }        
        }
        return cpt;
    }

    //

    @Override
    public String toString(){
        String str = this.nom + "\n";
        for (String espece : Arrays.asList("Lapin")){
            str += "nombre de " + espece + " - " + this.getNombreAnimal(espece) + " | ";
        }
        for (String espece : Arrays.asList("Carotte")){
            str += "nombre de " + espece + " - " + this.getNombreVegetal(espece)+ " | ";
        }
        str+="\n------------------------------------\n";
        if (Monde.showInfos){
            for (Animal a : this.animaux){
                str += a.getEspece() + " - energie : " + a.getEnergie() + ", age : " + a.getAge() + "\n" ;     
            }
        }
        return str;
        // return this.nom;
    }

}
