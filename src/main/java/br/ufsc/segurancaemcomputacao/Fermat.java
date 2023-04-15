package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;
import java.util.Random;

public class Fermat {

    Random geradorInt;
    Fermat() {
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

        // Caso n-1 seja maior que o maior número int representavel, define o limite superior como o maior número int
        // representável. Isso ocorre pois n é um BigInteger, enquanto o limiteSuperior do método nextInt da biblioteca
        // Random é um int.
        int limiteSuperior;
        if (n.subtract(BigInteger.valueOf(1)).compareTo(BigInteger.valueOf(2147483647)) == 1) {
            limiteSuperior = 2147483647;
        } else {
            limiteSuperior = n.subtract(BigInteger.valueOf(2)).intValue();
        }
        BigInteger a = BigInteger.valueOf(geradorInt.nextInt(2, limiteSuperior));

        // m = n - 1
        BigInteger m = n.subtract(BigInteger.valueOf(1));
        for (int i = 0; i < quantidadeIteracoes; i += 1) {
            // Teorema de fermat
            if (!(a.modPow(m, n).equals(BigInteger.valueOf(1)))) {
                return false;
            }
        }
        return true;
    }

}
