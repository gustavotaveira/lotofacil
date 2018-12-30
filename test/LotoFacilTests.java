import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LotoFacilTests {

    @Test
    public void testaCarregarDadosLotofacil() throws IOException {
        Lotofacil lotofacil = new Lotofacil();
        lotofacil.carregarResultados();
        assert lotofacil.getResultados().get(0).equals("01 02 05 06 07 08 12 13 16 17 19 21 22 23 24");
        assert lotofacil.getResultados().get(1).equals("01 02 03 08 09 11 13 16 18 19 20 21 22 23 25");
        assert lotofacil.getResultados().get(2).equals("01 02 03 04 05 10 12 13 14 15 17 20 21 24 25");
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

}
