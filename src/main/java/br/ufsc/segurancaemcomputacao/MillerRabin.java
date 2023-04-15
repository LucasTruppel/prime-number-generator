package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    Random geradorInt;

    MillerRabin() {
        geradorInt = new Random();
    }

    boolean ehPrimo(BigInteger n, int quantidadeIteracoes) {
        if (n.equals(BigInteger.valueOf(2)) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }

        if (n.equals(BigInteger.valueOf(1)) || n.equals(BigInteger.valueOf(4))) {
            return false;
        }

        if (n.divideAndRemainder(BigInteger.valueOf(2))[1].equals(BigInteger.valueOf(0))) {
            return false;
        }

        BigInteger m = n.subtract(BigInteger.valueOf(1));
        // Divide por 2 até chegar em um número ímpar
        while (m.divideAndRemainder(BigInteger.valueOf(2))[1].equals(BigInteger.valueOf(0))) {
            m = m.divide(BigInteger.valueOf(2));
        }

        // Caso n-1 seja maior que o maior número int representavel, define o limite superior como o maior número int
        // representável. Isso ocorre pois n é um BigInteger, enquanto o limiteSuperior do método nextInt da biblioteca
        // Random é um int.
        int limiteSuperior;
        if (n.subtract(BigInteger.valueOf(1)).compareTo(BigInteger.valueOf(2147483647)) == 1) {
            limiteSuperior = 2147483647;
        } else {
            limiteSuperior = n.subtract(BigInteger.valueOf(1)).intValue();
        }

        BigInteger a = BigInteger.valueOf(geradorInt.nextInt(2, limiteSuperior));
        BigInteger b = a.modPow(m, n);

        if (b.mod(n).equals(BigInteger.valueOf(1))) {
            return true;
        }

        for (int i = 0; i < quantidadeIteracoes; i += 1) {
            if (b.mod(n).equals(n.subtract(BigInteger.valueOf(1)))) {
                return true;
            } else {
                b = b.modPow(BigInteger.valueOf(2), n);
            }
        }

        return false;
    }

}
