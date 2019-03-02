import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContadorDeNumerosFrequentesPorPosicaoNaJogada {
    private Map<Integer, List<Integer>> frequenciaPorPosicao = new HashMap<>();

    public ContadorDeNumerosFrequentesPorPosicaoNaJogada() {
    }

    public Map<Integer, List<Integer>> getFrequenciaPorPosicao() {
        return frequenciaPorPosicao;
    }

    public void setFrequenciaPorPosicao(Map<Integer, List<Integer>> frequenciaPorPosicao) {
        this.frequenciaPorPosicao = frequenciaPorPosicao;
    }

    public Map<Integer, List<Integer>> processe(List<String> resultados) {
        if(resultados == null || resultados.isEmpty()){
            throw new RuntimeException("Classe: ContadorDeNumerosFrequentesPorPosicaoNaJogada, " +
                    "metodo: processe, " +
                    "mensagem: A lista de resultados nao foi carregada ou esta vazia, " +
                    "verifique o arquivo resultados_lotofacil.txt na pasta resources");
        }
        List<List<Integer>> resultadosComoListaDeInteiros = convertaParaListaDeInteiros(resultados);
        int posicao = 0;
        for (posicao = 0; posicao < resultadosComoListaDeInteiros.get(0).size(); posicao++){
            List<Integer> numerosFrequentesNaPosicaoAtual = new ArrayList<>();
            for(List<Integer> resultado : resultadosComoListaDeInteiros){
                Integer numero = resultado.get(posicao);
                numerosFrequentesNaPosicaoAtual.add(numero);
            }
            frequenciaPorPosicao.put(posicao, numerosFrequentesNaPosicaoAtual);
        }
        return frequenciaPorPosicao;
    }

    private List<List<Integer>> convertaParaListaDeInteiros(List<String> resultados) {
        int indice = 0;
        List<List<Integer>> listaDeResultadosEmInteiros = new ArrayList<>();
        for (String resultado : resultados){
            List<Integer> resultadoIntegers = new ArrayList<>();
            String[] resultadoArrayDeString = resultado.split(" ");
            for(indice=0; indice < resultadoArrayDeString.length; indice++){
                resultadoIntegers.add(Integer.parseInt(resultadoArrayDeString[indice]));
            }
            listaDeResultadosEmInteiros.add(resultadoIntegers);
        }
        return listaDeResultadosEmInteiros;
    }
}
