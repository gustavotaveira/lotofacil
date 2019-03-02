import org.junit.Test;
import org.testng.Assert;

import java.io.IOException;
import java.util.*;

public class LotoFacilTests {

    @Test
    public void testaCarregarDadosLotofacil() throws IOException {
        Lotofacil lotofacil = new Lotofacil();
        lotofacil.carregarResultados();
//        assert lotofacil.getResultados().get(0).equals("01 02 05 06 07 08 12 13 16 17 19 21 22 23 24");
//        assert lotofacil.getResultados().get(1).equals("01 02 03 08 09 11 13 16 18 19 20 21 22 23 25");
//        assert lotofacil.getResultados().get(2).equals("01 02 03 04 05 10 12 13 14 15 17 20 21 24 25");
    }

    @Test
    public void testaSorteie() throws IOException {
        Lotofacil lotofacil = new Lotofacil();

        lotofacil.getResultados().add("01 02 05 06 07 08 12 13 16 17 19 21 22 23 24");
        lotofacil.getResultados().add("01 02 03 08 09 11 13 16 18 19 20 21 22 23 25");
        lotofacil.getResultados().add("01 02 03 04 05 10 12 13 14 15 17 20 21 24 25");

        Sorteio sorteio = lotofacil.sorteie(1);

        assert sorteio.getJogosString().size() == 1;

        assert lotofacil.getResultados().stream().noneMatch(sorteio.getJogosString().get(0)::equals);

        System.out.print(sorteio.getJogosString().get(0));
    }

    @Test
    public void testaNoneMatch(){
        List<String> resultados = new ArrayList<>();
        resultados.add("01 02 05 06 07 08 12 13 16 17 19 21 22 23 24");
        resultados.add("01 02 03 08 09 11 13 16 18 19 20 21 22 23 25");
        resultados.add("01 02 03 04 05 10 12 13 14 15 17 20 21 24 25");

        String numerosSorteados = "01 03 05 06 07 08 12 13 16 17 19 21 22 23 24";

        boolean isValido = resultados.stream().noneMatch(numerosSorteados::equals);
        assert isValido;
    }

    @Test
    public void testeEncontrarNumerosFrequentesPorPosicao(){
        List<String> resultados = new ArrayList<>();
        resultados.add("01 02 05 06");
        resultados.add("01 02 03 08");
        resultados.add("01 02 03 04");

        ContadorDeNumerosFrequentesPorPosicaoNaJogada contador = new ContadorDeNumerosFrequentesPorPosicaoNaJogada();
        Map<Integer, List<Integer>> numerosMap = contador.processe(resultados);

        Assert.assertEquals(4, numerosMap.keySet().size());

        Assert.assertEquals(3, numerosMap.get(0).size());
        Assert.assertEquals(1, numerosMap.get(0).get(0).intValue());
        Assert.assertEquals(1, numerosMap.get(0).get(1).intValue());
        Assert.assertEquals(1, numerosMap.get(0).get(2).intValue());

        Assert.assertEquals(3, numerosMap.get(1).size());
        Assert.assertEquals(2, numerosMap.get(1).get(0).intValue());
        Assert.assertEquals(2, numerosMap.get(1).get(1).intValue());
        Assert.assertEquals(2, numerosMap.get(1).get(2).intValue());

        Assert.assertEquals(3, numerosMap.get(2).size());
        Assert.assertEquals(5, numerosMap.get(2).get(0).intValue());
        Assert.assertEquals(3, numerosMap.get(2).get(1).intValue());
        Assert.assertEquals(3, numerosMap.get(2).get(2).intValue());

        Assert.assertEquals(3, numerosMap.get(3).size());
        Assert.assertEquals(6, numerosMap.get(3).get(0).intValue());
        Assert.assertEquals(8, numerosMap.get(3).get(1).intValue());
        Assert.assertEquals(4, numerosMap.get(3).get(2).intValue());

    }

    @Test
    public void testeMetodoObtenhaFrequencia(){
        Map<Integer, List<Integer>> numerosPorPosicao = new HashMap<>();
        numerosPorPosicao.put(0, Arrays.asList(1,2,2,1,1,2,3,2,2,2,1,3));
        numerosPorPosicao.put(1, Arrays.asList(2,2,2,4,4,3,5,4,4,4,4,4,2,2,3,3,2));

        Map<Integer, Map<Integer, Integer>> frequenciaNumeroPorPosicao = Main.obtenhaFrequencia(numerosPorPosicao);

        Assert.assertEquals(2, frequenciaNumeroPorPosicao.keySet().size());

        Assert.assertEquals(4, frequenciaNumeroPorPosicao.get(0).get(1).intValue());
        Assert.assertEquals(6, frequenciaNumeroPorPosicao.get(0).get(2).intValue());
        Assert.assertEquals(2, frequenciaNumeroPorPosicao.get(0).get(3).intValue());

        Assert.assertEquals(6, frequenciaNumeroPorPosicao.get(1).get(2).intValue());
        Assert.assertEquals(7, frequenciaNumeroPorPosicao.get(1).get(4).intValue());
        Assert.assertEquals(3, frequenciaNumeroPorPosicao.get(1).get(3).intValue());
        Assert.assertEquals(1, frequenciaNumeroPorPosicao.get(1).get(5).intValue());
    }

}
