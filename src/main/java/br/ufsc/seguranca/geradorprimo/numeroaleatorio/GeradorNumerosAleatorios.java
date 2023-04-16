package br.ufsc.seguranca.geradorprimo.numeroaleatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Essa classe serve para criar números aleatórios com quantidades de bits especifícias.
 * Ela utiliza os algoritmos Xor Shift ou Blum Blum Shub, que geram números de 32 bits. Assim, ela gera números e os
 * concatena até chegar na quantidade de bits desejada. Como o processo acontece de 32 em 32 bits, também corta os bits
 * a mais do que o especificado.
 */
public class GeradorNumerosAleatorios {

    XorShift xorShift;
    BlumBlumShub blumBlumShub;

    public GeradorNumerosAleatorios() {
        xorShift = new XorShift();
        blumBlumShub = new BlumBlumShub();
    }

    /**
     * Gera números pseudo-aleatórios de 32 bits com o algoritmo especificado, concanteca e corta para chegar no número
     * de bits especificado.
     *
     * @param numeroBits número de bits do número a ser gerado.
     * @param algoritmo algoritmo a ser utilizado para gerar os números.
     * @return número gerado.
     */
    public BigInteger gerarNumeroAleatorio(int numeroBits, String algoritmo) {
        int tamanhoNumeroGeradoBits = 0;
        ByteArrayOutputStream numeroGeradoOutputStream = new ByteArrayOutputStream();
        algoritmo = algoritmo.toLowerCase();
        // Gera números aleatórios e os concatena até chegar ou ultrapassar o numeroBits desejado.
        while (tamanhoNumeroGeradoBits < numeroBits) {
            int numeroParcial;
            if (algoritmo.equals("xorshift")) {
                numeroParcial = xorShift.gerarNumeroAleatorio32Bits();
            } else if (algoritmo.equals("blumblumshub")) {
                numeroParcial = blumBlumShub.gerarNumeroAleatorio32Bits();
            } else {
                throw new IllegalArgumentException();
            }

            // Concatenação dos ‘bytes’
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
