package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        GeradorNumerosAleatorios geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        MillerRabin millerRabin = new MillerRabin();
        Fermat fermat = new Fermat();

        BigInteger numeroXor;
        int contador = 0;
        boolean primoEncontrado = false;
        while (!primoEncontrado) {
            contador += 1;
            numeroXor = geradorNumerosAleatorios.gerarNumeroAleatorio(80, "xorshift");
            //System.out.println("Numero gerado = " + numeroXor2);
            if (millerRabin.ehPrimo(numeroXor, 10)) {
                System.out.println("Numero primo miller-rabin: " + numeroXor);
                primoEncontrado = true;
            }
            if (fermat.ehPrimo(numeroXor, 10)) {
                System.out.println("Numero primo fermat: " + numeroXor);
                primoEncontrado = true;
            }
        }
        System.out.println("contador = " + contador);

    }
}