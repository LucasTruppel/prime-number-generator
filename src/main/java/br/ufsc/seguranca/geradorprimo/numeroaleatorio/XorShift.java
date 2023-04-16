package br.ufsc.seguranca.geradorprimo.numeroaleatorio;

import br.ufsc.seguranca.geradorprimo.util.GeradorSeed;

public class XorShift {

    public int gerarNumeroAleatorio32Bits() {
        int numero = GeradorSeed.gerarSeed();
        numero ^= numero << 13;
        numero ^= numero >> 7;
        numero ^= numero << 17;
        return numero;
    }

}
