package br.ufsc.seguranca.geradorprimo.numeroaleatorio;

import br.ufsc.seguranca.geradorprimo.util.GeradorSeed;

/**
 * Essa classe implementa o algoritmo gerador de números pseudoaletórios Xor Shift.
 * O algoritmo recebe uma seed inicial e gera números por meio de 3 operações de deslocamento intercaladas por 3
 * operações XOR. Os sentidos dos deslocamentos e a quantidade de bits deslocados podem variar dependendo da
 * implementação, porém existem algumas boas escolhas que geram bons números.
 */
public class XorShift {

    int estado;

    public XorShift() {
        estado = GeradorSeed.gerarSeed();
    }

    /**
     * Realiza os deslocamentos e XORs no estado atual e o retorna como número gerado. Também atualiza o estado.
     * @return número gerado de 32 bits.
     */
    public int gerarNumeroAleatorio32Bits() {
        int numero = estado;
        numero ^= numero << 13;
        numero ^= numero >> 7;
        numero ^= numero << 17;
        estado = numero;
        return numero;
    }

}
