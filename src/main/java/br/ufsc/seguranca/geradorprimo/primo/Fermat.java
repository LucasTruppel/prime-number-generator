package br.ufsc.seguranca.geradorprimo.primo;

import java.math.BigInteger;
import java.util.Random;

/**
 * Essa classe implementa o algoritmo verificador de primos de Fermat.
 * Ele retorna false se o número é composto e true se ele é provavelmente primo.
 */
public class Fermat {

    Random geradorInt;
    public Fermat() {
        geradorInt = new Random();
    }

    /**
     * Verifica se número passado é provavelmente primo.
     * Esse teste é probabilistico, então a precisão depende da quantidadeIteracoes passada.
     *
     * @param n número a ser verificado.
     * @param quantidadeIteracoes quantidade de iterações do teste, quanto maior, mais precisão.
     * @return boolean que indica se o número é provavelmente primo.
     */
    public boolean ehPrimo(BigInteger n, int quantidadeIteracoes) {

        // Trata os números de 1 a 4
        if (n.equals(BigInteger.valueOf(2)) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }
        if (n.equals(BigInteger.valueOf(1)) || n.equals(BigInteger.valueOf(4))) {
            return false;
        }

        // Verifica se é par
        if (n.divideAndRemainder(BigInteger.valueOf(2))[1].equals(BigInteger.valueOf(0))) {
            return false;
        }

        // Precisa-se gerar um número aleatório a tal que 1 < a < n-2, porém como n é um número grande precisamos tratar
        // o limite superior da geração desse número a. Caso n-1 seja maior que o maior número int representavel,
        // define o limite superior como o maior número int representável, caso contrário o limite superior é igual a
        // n - 1. Isso ocorre pois n é um BigInteger, enquanto o limiteSuperior do método nextInt da biblioteca Random é
        // um int.
        int limiteSuperior;
        if (n.subtract(BigInteger.valueOf(1)).compareTo(BigInteger.valueOf(2147483647)) == 1) {
            limiteSuperior = 2147483647;
        } else {
            limiteSuperior = n.subtract(BigInteger.valueOf(2)).intValue();
        }

        // m = n - 1
        BigInteger m = n.subtract(BigInteger.valueOf(1));

        // Quanto maior a quantidadeIteracoes, maior a precisão.
        for (int i = 0; i < quantidadeIteracoes; i += 1) {

            // Gera um "a" aleatório.
            BigInteger a = BigInteger.valueOf(geradorInt.nextInt(2, limiteSuperior));

            // Teorema de fermat: a^(n-1) ≡ 1 (mod n)
            // Se essa congruência não for verdadeira, n é composto.
            if (!(a.modPow(m, n).equals(BigInteger.valueOf(1)))) {
                return false;
            }
        }

        // Depois de fazer o teste para quantidadeIteracoes "a"s diferentes, "n" é provavelmente primo.
        return true;
    }

}
