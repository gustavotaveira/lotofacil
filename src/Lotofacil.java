import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lotofacil {

    public static final String ARQUIVO_COM_RESULTADOS_LOTOFACIL = "resources/resultados_lotofacil.txt";
    public static final int NUMEROS_LOTOFACIL = 15;
    public static final int NUMERO_MAXIMO_POSSIVEL_LOTOFACIL = 25;

    private List<String> resultados = new ArrayList<>();

    public List<String> carregarResultados() throws IOException {
        System.out.println("Carregando resultados anteriores da lotofacil.");
        Path path = Paths.get(ARQUIVO_COM_RESULTADOS_LOTOFACIL);
        List<String> linhas = Files.lines(path).collect(Collectors.toList());
        for (String linha : linhas) {
            String resultadoAtual = recupereResultados(linha);
            if (resultadoAtual != "") {
                resultados.add(resultadoAtual);
            }
        }
        System.out.println("Resultados carregados com sucesso.");
        return resultados;
    }

    public Map<Integer, List<Integer>> calculeFrequenciaDeNumerosPorPosicao(){
        ContadorDeNumerosFrequentesPorPosicaoNaJogada contador = new ContadorDeNumerosFrequentesPorPosicaoNaJogada();
        return contador.processe(resultados);
    }

    private String recupereResultados(String linha) {
        Pattern padrao = Pattern.compile("(?<=\\) )(.*)$");
        Matcher comparador = padrao.matcher(linha);
        return comparador.find() ? comparador.group(1) : "";
    }

    public Sorteio sorteie(int tentativas) {
        List<String> jogosString = new ArrayList<>();
        List<List<Integer>> jogos = new ArrayList<>();
        for (int j = 0; j < tentativas; j++) {
            boolean jogoValido = false;
            while (!jogoValido) {
                List<Integer> numerosSorteados = new ArrayList<>();
                System.out.println("Sorteando numeros para tentativa "+(j+1));
                int i = 0;
                while (i < NUMEROS_LOTOFACIL) {
                    int numeroSorteado = (int) Math.ceil((Math.random() * NUMERO_MAXIMO_POSSIVEL_LOTOFACIL));
                    if (numerosSorteados.indexOf(numeroSorteado) < 0) {
                        numerosSorteados.add(numeroSorteado);
                        i++;
                    }
                }
                Collections.sort(numerosSorteados);

                String numerosSemVirgula = numerosSorteados.toString().replace(", ", " ");
                String numerosParaValidarComResultados = numerosSemVirgula.substring(1, numerosSemVirgula.length() - 1);

                String[] numerosString = numerosParaValidarComResultados.split(" ");
                for(int k = 0; k<numerosString.length; k++){
                   int number = Integer.parseInt(numerosString[k]);
                   if(number < 10){
                       numerosString[k] = "0".concat(numerosString[k]);
                   }
                }

                String numerosCorretosParaValidar = Arrays.toString(numerosString);

                String numerosSemVirgula2 = numerosCorretosParaValidar.replace(", ", " ");
                String numerosParaValidarComResultados2 = numerosSemVirgula2.substring(1, numerosSemVirgula2.length() - 1);

                jogoValido = resultados.stream().noneMatch(numerosParaValidarComResultados2::equals);
                if (jogoValido) {
                    jogosString.add(numerosParaValidarComResultados2);
                    jogos.add(numerosSorteados);
                }
            }
        }
        return new Sorteio(jogos, jogosString);
    }

    public List<String> getResultados() {
        return resultados;
    }
}
