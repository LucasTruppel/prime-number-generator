package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        GeradorNumerosAleatorios geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        MillerRabin millerRabin = new MillerRabin();

        BigInteger numeroXor;
        int contador = 0;
        while (true) {
            contador += 1;
            numeroXor = geradorNumerosAleatorios.gerarNumeroAleatorio(80, "xorshift");
            //System.out.println("Numero gerado = " + numeroXor2);
            if (millerRabin.ehPrimo(numeroXor, 10)) {
                System.out.println("Numero primo: " + numeroXor);
                break;
            }
        }
        System.out.println("contador = " + contador);

    }
}