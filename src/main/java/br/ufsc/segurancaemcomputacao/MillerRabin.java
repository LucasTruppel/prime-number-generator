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
        // Divide por 2 até chegar em um número ímpar,
        while (m.divideAndRemainder(BigInteger.valueOf(2))[1].equals(BigInteger.valueOf(0))) {
            m = m.divide(BigInteger.valueOf(2));
        }
        //System.out.println("n = " + n);
        //System.out.println("m = " + m);

        //System.out.println("tamanho = " + n.bitLength());
        BigInteger a = BigInteger.valueOf(geradorInt.nextInt(2, n.subtract(BigInteger.valueOf(1)).intValue()));
        BigInteger b = a.modPow(m, n);

        //System.out.println("a = " + a);
        //System.out.println("b = " + b);

        //System.out.println("b mod n = " + b.mod(n));
        if (b.mod(n).equals(BigInteger.valueOf(1))) {
            return true;
        }

        for (int i = 0; i < quantidadeIteracoes; i += 1) {
            if (b.mod(n).equals(n.subtract(BigInteger.valueOf(1)))) {
                return true;
            } else {
                b = b.modPow(BigInteger.valueOf(2), n);
                //System.out.println("b^2 mod n = " + b);
            }
        }

        return false;
    }

}
