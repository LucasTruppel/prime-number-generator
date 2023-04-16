package br.ufsc.seguranca.geradorprimo;

import br.ufsc.seguranca.geradorprimo.primo.Fermat;
import br.ufsc.seguranca.geradorprimo.primo.GeradorNumerosPrimos;
import br.ufsc.seguranca.geradorprimo.primo.MillerRabin;
import br.ufsc.seguranca.geradorprimo.numeroaleatorio.GeradorNumerosAleatorios;
import br.ufsc.seguranca.geradorprimo.util.EscritorArquivoCsv;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    static final int[] tamanhosBitsNumeros = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};

    public static void main(String[] args) {
        GeradorNumerosAleatorios geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        GeradorNumerosPrimos geradorNumerosPrimos = new GeradorNumerosPrimos();

        String caminhoArquivo = "src/main/resources/artefatos/numeros_aleatorios.csv";
        gerarRelatorio("xorshift", caminhoArquivo, true, geradorNumerosAleatorios);
        gerarRelatorio("blumblumshub", caminhoArquivo, false, geradorNumerosAleatorios);

        String caminhoArquivoPrimos = "src/main/resources/artefatos/numeros_aleatorios_primos.csv";
        gerarRelatorioPrimos("millerrabin", caminhoArquivoPrimos, true, geradorNumerosPrimos);
        gerarRelatorioPrimos("fermat", caminhoArquivoPrimos, false, geradorNumerosPrimos);

    }

    private static void gerarRelatorio(String algoritmo, String caminhoArquivo, boolean limpar,
                               GeradorNumerosAleatorios geradorNumerosAleatorios) {
        String[][] dados = new String[11][4];
        for (int i = 0; i < tamanhosBitsNumeros.length; i += 1) {
            BigInteger numero = null;
            long tempoTotal = 0;
            int quantidadeNumeros = 1000;
            for (int j = 0; j < quantidadeNumeros; j += 1) {
                long tempoInicial = System.nanoTime();
                numero = geradorNumerosAleatorios.gerarNumeroAleatorio(tamanhosBitsNumeros[i], algoritmo);
                long tempoFinal = System.nanoTime();
                tempoTotal += tempoFinal - tempoInicial;
            }
            float mediaTempo = (float) tempoTotal / quantidadeNumeros;

            dados[i][0] = algoritmo;
            dados[i][1] = tamanhosBitsNumeros[i] + " bits";
            dados[i][2] = String.format("%.2f", mediaTempo/1000).replace(",", ".");
            dados[i][3] = numero.toString();
        }
        EscritorArquivoCsv.escreverCsv(dados, caminhoArquivo, limpar);
    }

    private static void gerarRelatorioPrimos(String algoritmo, String caminhoArquivo, boolean limpar,
                                             GeradorNumerosPrimos geradorNumerosPrimos) {
        final int[] quantidadeNumeros = {100, 100, 100, 100, 50, 50, 50, 10, 2, 1, 1};
        String[][] dados = new String[11][5];
        for (int i = 0; i < tamanhosBitsNumeros.length; i += 1) {
            BigInteger numero = null;
            long tempoTotal = 0;
            for (int j = 0; j < quantidadeNumeros[i]; j += 1) {
                long tempoInicial = System.nanoTime();
                numero = geradorNumerosPrimos.gerarNumeroPrimo(tamanhosBitsNumeros[i], algoritmo);
                long tempoFinal = System.nanoTime();
                tempoTotal += tempoFinal - tempoInicial;
            }
            float mediaTempo = (float) tempoTotal / quantidadeNumeros[i];

            dados[i][0] = algoritmo;
            dados[i][1] = tamanhosBitsNumeros[i] + " bits";
            dados[i][2] = String.format("%.2f", mediaTempo/1000).replace(",", ".");
            dados[i][3] = Integer.toString(quantidadeNumeros[i]);
            dados[i][4] = numero.toString();
        }
        EscritorArquivoCsv.escreverCsv(dados, caminhoArquivo, limpar);

    }





}