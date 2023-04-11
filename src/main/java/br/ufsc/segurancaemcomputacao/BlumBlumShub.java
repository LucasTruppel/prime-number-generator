package br.ufsc.segurancaemcomputacao;

public class BlumBlumShub {

    final int p = 62653;
    final int q = 24197;
    final int m = p * q;
    int numeroAnterior;

    BlumBlumShub() {
        numeroAnterior = GeradorSeed.gerarSeed();
    }

    public int gerarNumeroAleatorio32Bits() {
        int numeroGerado = (numeroAnterior * numeroAnterior) % m;
        numeroAnterior = numeroGerado;
        return numeroGerado;
    }

}

