import java.util.List;

public class Sorteio {


    private List<List<Integer>> jogos;
    private List<String> jogosString;

    public Sorteio(List<List<Integer>> jogos, List<String> jogosString) {
        this.jogos = jogos;
        this.jogosString = jogosString;
    }

    public List<String> getJogosString() {
        return jogosString;
    }

    public List<List<Integer>> getJogos() {
        return jogos;
    }
}
