package br.ufsc.seguranca.geradorprimo.primo;

import java.math.BigInteger;
import java.util.Random;

/**
 * Esta classe implementa o algoritmo verificador de primos Miller-Rabin.
 * Ele retorna false se o número é composto e true se ele é provavelmente primo.
 */
public class MillerRabin {

    Random geradorInt;

    public MillerRabin() {
        geradorInt = new Random();
    }

    /**
     * Verifica se número passado é provavelmente primo.
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

        // m = (n - 1) / 2^k
        BigInteger m = n.subtract(BigInteger.valueOf(1));
        // Divide por 2 até chegar em um número ímpar
        while (m.divideAndRemainder(BigInteger.valueOf(2))[1].equals(BigInteger.valueOf(0))) { // m mod 2 = 0
            m = m.divide(BigInteger.valueOf(2));
        }

        // Precisa-se gerar um número aleatório a tal que 1 < a < n-1, porém como n é um número grande precisamos tratar
        // o limite superior da geração desse número a. Caso n-1 seja maior que o maior número int representavel,
        // define o limite superior como o maior número int representável, caso contrário o limite superior é igual a
        // n - 1. Isso ocorre pois n é um BigInteger, enquanto o limiteSuperior do método nextInt da biblioteca Random é
        // um int.
        int limiteSuperior;
        if (n.subtract(BigInteger.valueOf(1)).compareTo(BigInteger.valueOf(2147483647)) == 1) {
            limiteSuperior = 2147483647;
        } else {
            limiteSuperior = n.subtract(BigInteger.valueOf(1)).intValue();
        }

        // "a" é um número aleatório gerado e 1 < a < n-1
        BigInteger a = BigInteger.valueOf(geradorInt.nextInt(2, limiteSuperior));

        // b = a^m mod n
        BigInteger b = a.modPow(m, n);

        // b ≡ 1 (mod n)
        if (b.mod(n).equals(BigInteger.valueOf(1))) {
            return true;
        }

        // Realiza esse teste quantidadeIteracoes vezes. A cada tentiva a probabilidade de acerto é 75%, então quanto
        // maior o valor passado de quantidadeIteracoes, maior a precisão do teste.
        for (int i = 0; i < quantidadeIteracoes; i += 1) {
            // b ≡ - 1 (mod n)
            if (b.mod(n).equals(n.subtract(BigInteger.valueOf(1)))) {
                return true;
            } else {
                // b = b^2 mod n
                b = b.modPow(BigInteger.valueOf(2), n);
            }
        }

        return false;
    }

}
