/*
  Felipe Galdino de Sousa
  Ítalo Alixandre Alves
*/
package sorteio;

import java.util.Optional;
import java.util.Random;

public class Sorteio {
    private int n;
    private final int[] numeros;
    private int min;
    private int max;
    private int step;

    public Sorteio(int n, int min, int max) {
        if(n <= 0 || n > 100)
            throw new IllegalArgumentException("n precisa estar entre 0 e 100");
        if(min < 0 || min > max)
            throw new IllegalArgumentException("min e max precisam ser maior que zero e min menor que max");
        if(max-min < n-1) throw new IllegalArgumentException("o intervalo min e max precisa ser maior ou igual a n");

        this.n = n;
        this.min = min;
        this.max = max;
        this.step = 0;

        this.numeros = new int[n];
    }

    private int novoNumeroBau() {
        return new Random().nextInt(this.max - this.min + 1) + this.min;
    }

    public void gerarNumeros() {
        this.step = 0;
        for(int i = 0; i < this.n; i++) {
            this.proximoNumero();
        }
    }
    
    public void proximoNumero(){
    	if (this.terminou()) throw new IllegalStateException("o sorteio ja terminou");
	    boolean existe;
	    if (step < n) {
            int numero = novoNumeroBau();
            existe = false;
            for(int i = 0; i < n; i++) {
                if(numero == this.numeros[i]) {
                existe = true;
                }
            }
            if(existe){
                proximoNumero();
            }
            else{
                this.numeros[this.step] = numero;
                this.step ++;
            }
	    }	
	}
    
    public boolean terminou() {
        return this.step >= this.n;
    }

    public String resultado(String padrao) {
        if(padrao == null)
            throw new IllegalArgumentException("padrao nao pode ser null");
        
        StringBuilder resultado = new StringBuilder();
        int temp;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(numeros[i]!= 0) {
                    if(numeros[i] < numeros[j]) {
                    temp = numeros[j];
                    numeros[j] = numeros[i];
                    numeros[i] = temp;
                    }
                }	
	        }
	    }
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
