package br.ufsc.segurancaemcomputacao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class XorShift {

    private int gerarNumeroAleatorio32Bits(int seed) {
        int numero = seed;
        numero ^= numero << 13;
        numero ^= numero >> 7;
        numero ^= numero << 17;
        return numero;
    }

    public BigInteger gerarNumeroAleatorio(int numeroBits) {
        int tamanhoNumeroGeradoBits = 0;
        ByteArrayOutputStream numeroGeradoOutputStream = new ByteArrayOutputStream();
        while (tamanhoNumeroGeradoBits < numeroBits) {
            int numeroParcial = gerarNumeroAleatorio32Bits(GeradorSeed.gerarSeed());
            byte[] numeroParcialBytes = ByteBuffer.allocate(4).putInt(numeroParcial).array();
            try {
                numeroGeradoOutputStream.write(numeroParcialBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tamanhoNumeroGeradoBits += 32;
        }

        // Corta os ‘bytes’ que estão sobrando
        byte[] numeroGeradoBytes = numeroGeradoOutputStream.toByteArray();
        int quantidadeBytesSobrando = (numeroGeradoBytes.length * 8 - numeroBits) / 8;
        byte[] numeroGeradoCortadoBytes = Arrays.copyOfRange(numeroGeradoBytes, quantidadeBytesSobrando,
                numeroGeradoBytes.length);

        // Caso o número seja par, subtrai 1
        if (numeroGeradoBytes[numeroGeradoBytes.length-1] % 2 == 0) {
            numeroGeradoBytes[numeroGeradoBytes.length-1] -= 1;
        }

        return new BigInteger(numeroGeradoCortadoBytes);
    }
}

