import java.util.List;

public interface IRegime{

    public boolean manger(Animal a);
    public List<String> getAliments();
    public Territoire chercherNourriture(Animal a);

}