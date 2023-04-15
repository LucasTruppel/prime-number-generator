package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;

public class BlumBlumShub {

    final int p = 62653;
    final int q = 24197;
    final BigInteger m = BigInteger.valueOf(p * q);
    BigInteger numeroAnterior;

    BlumBlumShub() {
        numeroAnterior = BigInteger.valueOf(GeradorSeed.gerarSeed());
    }

    public int gerarNumeroAleatorio32Bits() {
        BigInteger numeroGerado = (numeroAnterior.multiply(numeroAnterior)).mod(m);
        numeroAnterior = numeroGerado;
        return numeroGerado.intValue();
    }

}
