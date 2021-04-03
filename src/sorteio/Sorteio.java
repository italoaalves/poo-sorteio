/*
  Felipe Galdino de Sousa
  Ítalo Alixandre Alves
*/
package sorteio;

import java.util.Random;

public class Sorteio {
    private final int n;
    private final int[] numeros;
    private final int min;
    private final int max;
    private int passo;

    public Sorteio(int n, int min, int max) {
        if (n <= 0 || n > 100)
            throw new IllegalArgumentException("n precisa estar entre 0 e 100");
        if (min < 0 || min > max)
            throw new IllegalArgumentException("min e max precisam ser maior que zero e min menor que max");
        if (max - min < n - 1)
            throw new IllegalArgumentException("o intervalo min e max precisa ser maior ou igual a n");

        this.n = n;
        this.min = min;
        this.max = max;
        this.passo = 0;

        this.numeros = new int[n];
    }

    private int novoNumero() {
        return new Random().nextInt(this.max - this.min + 1) + this.min;
    }

    public void gerarNumeros() {
        this.passo = 0;
        for (int i = 0; i < this.n; i++) {
            this.proximoNumero();
        }
    }

    public void proximoNumero() {
        if (this.terminou()) throw new IllegalStateException("o sorteio ja terminou");

        int numero = novoNumero();

        if (jaFoiSorteado(numero)) {
            proximoNumero();
        } else {
            this.numeros[this.passo] = numero;
            this.passo++;
        }
    }

    public boolean terminou() {
        return this.passo >= this.n;
    }

    public String resultado(String padrao) {
        if (padrao == null)
            throw new IllegalArgumentException("padrao nao pode ser null");

        StringBuilder resultado = new StringBuilder();
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (numeros[i] != 0) {
                    if (numeros[i] < numeros[j]) {
                        temp = numeros[j];
                        numeros[j] = numeros[i];
                        numeros[i] = temp;
                    }
                }
            }
        }
        for (int i = 0; i < this.n; i++) {
            resultado.append(this.numeros[i]);
            resultado.append((this.n - i > 1) ? padrao : "");
        }
        return resultado.toString();
    }

    private boolean jaFoiSorteado(int numero) {
        for (int i = 0; i < n; i++) {
            if (numero == this.numeros[i]) {
                return true;
            }
        }
        return false;
    }

    public int[] getNumeros() {
        return numeros;
    }
}
