import sorteio.Sorteio;
import tests.Tela;
import tests.Teste1;
import tests.Teste2;

public class Main {
    public static void main(String[] args) {
        Teste1 teste1 = new Teste1();
        Teste2 teste2 = new Teste2();
        Tela tela = new Tela();

        teste1.run();
        teste2.run();
        tela.run();
    }
}
