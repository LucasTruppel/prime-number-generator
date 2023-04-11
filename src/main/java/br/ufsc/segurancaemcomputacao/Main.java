package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        XorShift xorShiftGenerator = new XorShift();
        BigInteger numero = xorShiftGenerator.gerarNumeroAleatorio(80);

        System.out.println(numero);
        System.out.println(Arrays.toString(numero.toByteArray()));
        System.out.println(numero.toByteArray().length*8);

    }
}