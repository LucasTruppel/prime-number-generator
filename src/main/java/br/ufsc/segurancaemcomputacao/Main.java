package br.ufsc.segurancaemcomputacao;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        GeradorNumerosAleatorios geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        BigInteger numeroXor = geradorNumerosAleatorios.gerarNumeroAleatorio(80, "xorshift");
        System.out.println(numeroXor);
        System.out.println(numeroXor.toByteArray().length*8);

        BigInteger numeroBlum = geradorNumerosAleatorios.gerarNumeroAleatorio(80, "blumblumshub");
        System.out.println(numeroBlum);
        System.out.println(numeroBlum.toByteArray().length*8);

    }
}