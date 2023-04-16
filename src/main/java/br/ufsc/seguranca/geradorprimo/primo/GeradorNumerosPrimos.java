package br.ufsc.seguranca.geradorprimo.primo;

import br.ufsc.seguranca.geradorprimo.numeroaleatorio.GeradorNumerosAleatorios;

import java.math.BigInteger;

public class GeradorNumerosPrimos {

    GeradorNumerosAleatorios geradorNumerosAleatorios;
    MillerRabin millerRabin;
    Fermat fermat;


    public GeradorNumerosPrimos() {
        geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        millerRabin = new MillerRabin();
        fermat = new Fermat();
    }

    public BigInteger gerarNumeroPrimo(int tamanhoBitsNumero, String algoritmo) {
        algoritmo = algoritmo.toLowerCase();
        BigInteger numeroGerado = null;
        boolean primoEncontrado = false;
        while (!primoEncontrado) {
            numeroGerado = geradorNumerosAleatorios.gerarNumeroAleatorio(tamanhoBitsNumero, "xorshift");
            if (algoritmo.equals("millerrabin")) {
                if (millerRabin.ehPrimo(numeroGerado, 10)) {
                    primoEncontrado = true;
                }
            } else if (algoritmo.equals("fermat")) {
                if (fermat.ehPrimo(numeroGerado, 10)) {
                    primoEncontrado = true;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return numeroGerado;
    }
}
