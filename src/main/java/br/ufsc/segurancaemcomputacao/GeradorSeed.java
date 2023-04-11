package br.ufsc.segurancaemcomputacao;

import java.util.Random;

public class GeradorSeed {

    static Random gerador = new Random();

    public static int gerarSeed() {
        return gerador.nextInt();
    }
}
