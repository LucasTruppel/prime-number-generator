package br.ufsc.segurancaemcomputacao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class XorShift {

    public int gerarNumeroAleatorio32Bits() {
        int numero = GeradorSeed.gerarSeed();
        numero ^= numero << 13;
        numero ^= numero >> 7;
        numero ^= numero << 17;
        return numero;
    }

}

