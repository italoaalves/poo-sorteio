/*
  Felipe Galdino de Sousa
  Ítalo Alixandre Alves
*/

package sorteio;

import java.util.ArrayList;
import java.util.Random;

public class Sorteio {
    private int n;
    private final int[] numeros;
    private int min;
    private int max;
    private int step;

    private final ArrayList<Integer> bau = new ArrayList<>();

    private final Random randomGen;

    public Sorteio(int n, int min, int max) {
        if(n <= 0 || n > 100)
            throw new IllegalArgumentException("n precisa estar entre 0 e 100");
        if(min < 0 || min > max)
            throw new IllegalArgumentException("min e max precisam ser maior que zero e min menor que max");

        this.n = n;
        this.min = min;
        this.max = max;
        this.step = 0;

        this.numeros = new int[n];

        this.randomGen = new Random();

        for(int i = this.min; i <= this.max; i++) {
            this.bau.add(i);
        }
    }

    private int novoNumeroBau() {
        return this.randomGen.nextInt(this.bau.size());
    }

    public void gerarNumeros() {
        for(int i = this.step; i < this.n; i++) {
            this.proximoNumero();
        }
    }

    public void proximoNumero() {
        if (this.terminou()) throw new IllegalStateException("o sorteio ja terminou");

        int numero = novoNumeroBau();

        this.numeros[step] = this.bau.get(numero);
        this.bau.remove(numero);

        this.step++;
    }

    public boolean terminou() {
        return this.step >= this.n;
    }

    public String resultado(String padrao) {
        if(padrao == null)
            throw new IllegalArgumentException("padrao nao pode ser null");

        StringBuilder resultado = new StringBuilder();

        for(int i = 0; i < this.n; i++) {
            resultado.append(this.numeros[i]);
            resultado.append((this.n - i > 1) ? padrao : "");
        }

        return resultado.toString();
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStep() {
        return step;
    }
}
