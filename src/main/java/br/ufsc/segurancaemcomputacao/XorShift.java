package br.ufsc.segurancaemcomputacao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class XorShift {

    private BigInteger gerarNumeroAleatorio32Bits(int seed) {
        int numero = seed;
        numero ^= numero << 13;
        numero ^= numero >> 7;
        numero ^= numero << 17;
        return BigInteger.valueOf(numero);
    }

    public BigInteger gerarNumeroAleatorio(int numeroBits) {
        int tamanhoNumeroGerado = 0;
        ByteArrayOutputStream numeroGeradoOutputStream = new ByteArrayOutputStream();
        while (tamanhoNumeroGerado < numeroBits) {
            BigInteger numeroParcial = gerarNumeroAleatorio32Bits(GeradorSeed.gerarSeed());
            byte[] numeroParcialBytes = numeroParcial.toByteArray();
            try {
                numeroGeradoOutputStream.write(numeroParcialBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tamanhoNumeroGerado += 32;
        }

        // Troca os bytes sobrando por 0
        byte[] numeroGeradoBytes = numeroGeradoOutputStream.toByteArray();
        int quantidadeBytesSobrando = (numeroGeradoBytes.length * 8 - numeroBits) / 8;
        for (int i=0; i < quantidadeBytesSobrando; i+=1) {
            numeroGeradoBytes[i] = 0;
        }

        // Caso o número seja par, subtrai 1
        if (numeroGeradoBytes[numeroGeradoBytes.length-1] % 2 == 0) {
            numeroGeradoBytes[numeroGeradoBytes.length-1] -= 1;
        }

        return new BigInteger(numeroGeradoBytes);
    }
}

