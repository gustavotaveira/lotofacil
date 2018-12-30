import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            int sorteios = Integer.parseInt(args[0]);
            Lotofacil lotofacil = new Lotofacil();
            lotofacil.carregarResultados();
            Sorteio sorteio = lotofacil.sorteie(sorteios);
            System.out.println("Os numeros sorteados foram:");
            for(int i = 0; i < sorteio.getJogosString().size(); i++){
                System.out.println(sorteio.getJogosString().get(i));
            }
        }catch (NumberFormatException e){
            System.out.println("Informe o numero de sorteios como primeiro parametro. Ex: lotofacil 1.(Um sorteio)");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Informe o numero de sorteios a ser gerado pelo programa. Ex: lotofacil 1 (um sorteio)");
        }catch (Exception e){
            System.out.print("Um erro ocorreu durante a execucao do programa: "+e.getClass()+" "+e.getMessage());
        }finally {
            System.out.println("Fim da execucao...");
        }

    }
}
