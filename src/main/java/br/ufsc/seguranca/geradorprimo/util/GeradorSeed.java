package br.ufsc.seguranca.geradorprimo.util;

import java.util.Random;

/**
 * Essa classe gera as seeds usadas nos algoritmos usando a biblioteca Random do java.
 */
public class GeradorSeed {

    static Random gerador = new Random();

    public static int gerarSeed() {
        return gerador.nextInt();
    }
}
