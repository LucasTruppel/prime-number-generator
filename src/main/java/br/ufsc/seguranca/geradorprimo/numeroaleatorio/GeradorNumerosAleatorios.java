package br.ufsc.seguranca.geradorprimo.numeroaleatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class GeradorNumerosAleatorios {

    XorShift xorShift;
    BlumBlumShub blumBlumShub;

    public GeradorNumerosAleatorios() {
        xorShift = new XorShift();
        blumBlumShub = new BlumBlumShub();
    }

    public BigInteger gerarNumeroAleatorio(int numeroBits, String algoritmo) {
        int tamanhoNumeroGeradoBits = 0;
        ByteArrayOutputStream numeroGeradoOutputStream = new ByteArrayOutputStream();
        algoritmo = algoritmo.toLowerCase();
        while (tamanhoNumeroGeradoBits < numeroBits) {
            int numeroParcial;
            if (algoritmo.equals("xorshift")) {
                numeroParcial = xorShift.gerarNumeroAleatorio32Bits();
            } else if (algoritmo.equals("blumblumshub")) {
                numeroParcial = blumBlumShub.gerarNumeroAleatorio32Bits();
            } else {
                throw new IllegalArgumentException();
            }

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
        if (numeroGeradoCortadoBytes[numeroGeradoCortadoBytes.length-1] % 2 == 0) {
            numeroGeradoCortadoBytes[numeroGeradoCortadoBytes.length-1] -= 1;
        }

        return new BigInteger(numeroGeradoCortadoBytes).abs();
    }

}
