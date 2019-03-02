import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            int sorteios = Integer.parseInt(args[0]);
            Lotofacil lotofacil = new Lotofacil();
            List<String> resultados = lotofacil.carregarResultados();
            ContadorDeNumerosFrequentesPorPosicaoNaJogada contador = new ContadorDeNumerosFrequentesPorPosicaoNaJogada();
            Map<Integer, List<Integer>> numerosPorPosicao = contador.processe(resultados);
            Map<Integer, Map<Integer, Integer>> frequenciaNumerosPorPosicao = obtenhaFrequencia(numerosPorPosicao);
            Sorteio sorteio = lotofacil.sorteie(sorteios);
            System.out.println("Os numeros sorteados foram:");
            for (int i = 0; i < sorteio.getJogosString().size(); i++) {
                System.out.println(sorteio.getJogosString().get(i));
            }

            System.out.println("Os numeros que mais apareceram nas posicoes dos jogos ate hoje sao");
            int i = 0;
            for (i = 0; i < 15; i++) {
                System.out.println("Posicao " + (i + 1) + ": ");
                Map<Integer, Integer> numeroEFrequenciaNaPosicaoAtual = frequenciaNumerosPorPosicao.get(i);
                for (Integer numero : numeroEFrequenciaNaPosicaoAtual.keySet()){
                    Integer frequencia = numeroEFrequenciaNaPosicaoAtual.get(numero);
                    System.out.println((numero < 10 ? "0" + numero : numero) + " - " + frequencia + " vezes");
                }
            }


        } catch (NumberFormatException e) {
            System.out.println("Informe o numero de sorteios como primeiro parametro. Ex: lotofacil 1.(Um sorteio)");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Informe o numero de sorteios a ser gerado pelo programa. Ex: lotofacil 1 (um sorteio)");
        } catch (Exception e) {
            System.out.print("Um erro ocorreu durante a execucao do programa: " + e.getClass() + " " + e.getMessage());
        } finally {
            System.out.println("Fim da execucao...");
        }

    }

    public static Map<Integer, Map<Integer, Integer>> obtenhaFrequencia(Map<Integer, List<Integer>> numerosPorPosicao) {
        int i = 0;
        Map<Integer, Map<Integer, Integer>> mapaDeFrequenciaDeNumerosPorPosicao = new HashMap<>();
        for(i = 0; i < numerosPorPosicao.keySet().size(); i++){

            List<Integer> numerosDaPosicaoAtual = numerosPorPosicao.get(i);
            Collections.sort(numerosDaPosicaoAtual);
            Collections.reverse(numerosDaPosicaoAtual);
            Set<Integer> numerosNaoRepetidos = new HashSet<>(numerosDaPosicaoAtual);
            Iterator<Integer> numerosNaoRepetidositerator = numerosNaoRepetidos.iterator();
            Map<Integer, Integer> mapaDeFrequenciaDeNumerosParaPosicaoAtual = new HashMap<Integer, Integer>();
            while (numerosNaoRepetidositerator.hasNext()){
                Integer numeroAtual = numerosNaoRepetidositerator.next();
                int frequenciaNumeroAtual = Collections.frequency(numerosDaPosicaoAtual, numeroAtual);
                mapaDeFrequenciaDeNumerosParaPosicaoAtual.put(numeroAtual, frequenciaNumeroAtual);
            }
            mapaDeFrequenciaDeNumerosPorPosicao.put(i, mapaDeFrequenciaDeNumerosParaPosicaoAtual);
        }
        return mapaDeFrequenciaDeNumerosPorPosicao;
    }
}
