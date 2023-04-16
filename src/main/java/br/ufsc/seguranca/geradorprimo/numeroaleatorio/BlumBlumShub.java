package br.ufsc.seguranca.geradorprimo.numeroaleatorio;

import br.ufsc.seguranca.geradorprimo.util.GeradorSeed;

import java.math.BigInteger;

/**
 * Essa classe implementa o algoritmo gerador de números pseudoaletórios Blum Blum Shub.
 * O algoritmo é baseado na seguinte formula:
 * x[n+1] = x[n]^2 mod m
 * em que m = p * q, sendo "p" e "q" primos grandes e congruntes a 3 mod 4.
 * Além disso, mdc (φ(p-1), φ(q-1)) deve ser um número pequeno.
 */
public class BlumBlumShub {

    final int p = 5651;
    final int q = 5623;
    final BigInteger m = BigInteger.valueOf(p * q);
    BigInteger numeroAnterior;

    public BlumBlumShub() {
        numeroAnterior = BigInteger.valueOf(GeradorSeed.gerarSeed());
    }

    /**
     * Realiza a operação:
     * x[n+1] = x[n]^2 mod m
     * e atualizada numeroAnterior pora ser utilizado no próximo número gerado.
     * @return número gerado de 32 bits.
     */
    public int gerarNumeroAleatorio32Bits() {
        BigInteger numeroGerado = (numeroAnterior.multiply(numeroAnterior)).mod(m);
        numeroAnterior = numeroGerado;
        return numeroGerado.intValue();
    }

}
