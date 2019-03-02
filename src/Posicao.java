import java.util.List;

public class Posicao {
    private int valor;
    private List<Numero> numerosDoMaisFrequenteParaOMenosFrequente;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Numero getNumeros(int prioridadeFrequencia) {
        return numerosDoMaisFrequenteParaOMenosFrequente.get(prioridadeFrequencia);
    }

    public void setNumerosDoMaisFrequenteParaOMenosFrequente(List<Numero> numerosDoMaisFrequenteParaOMenosFrequente) {
        this.numerosDoMaisFrequenteParaOMenosFrequente = numerosDoMaisFrequenteParaOMenosFrequente;
    }
}
