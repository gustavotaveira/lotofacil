import java.util.ArrayList;
import java.util.List;

/**
 * Representa um resultado da lotofacil, contento a lista de posicoes de numeros do resultado.
 */
public class Resultado {

    private Integer id;
    private List<Posicao> posicoes = new ArrayList<>();

    public Posicao getPosicao(Integer indiceDaPosicao){
        return posicoes.get(indiceDaPosicao);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
