package br.ufsc.seguranca.geradorprimo;

import br.ufsc.seguranca.geradorprimo.primo.GeradorNumerosPrimos;
import br.ufsc.seguranca.geradorprimo.numeroaleatorio.GeradorNumerosAleatorios;
import br.ufsc.seguranca.geradorprimo.util.EscritorArquivoCsv;

import java.math.BigInteger;

public class Main {

    // tamanho em bits dos números a serem gerados
    static final int[] tamanhosBitsNumeros = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};

    /**
     * Utiliza as funções gerarRelatorio para criar números pseudo-aleatórios e salvá-los em um CSV.
     */
    public static void main(String[] args) {
        // Objetos que geram os números
        GeradorNumerosAleatorios geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        GeradorNumerosPrimos geradorNumerosPrimos = new GeradorNumerosPrimos();

        String caminhoArquivo = "src/main/resources/artefatos/numeros_aleatorios.csv";
        gerarRelatorio("xorshift", caminhoArquivo, true, geradorNumerosAleatorios);
        gerarRelatorio("blumblumshub", caminhoArquivo, false, geradorNumerosAleatorios);

        String caminhoArquivoPrimos = "src/main/resources/artefatos/numeros_aleatorios_primos.csv";
        gerarRelatorioPrimos("millerrabin", caminhoArquivoPrimos, true, geradorNumerosPrimos);
        gerarRelatorioPrimos("fermat", caminhoArquivoPrimos, false, geradorNumerosPrimos);

    }

    /**
     * Utiliza as classes criadas para gerar a quantidade de números aleatórios desejada e salva os resultados em um
     * arquivo CSV.
     *
     * @param algoritmo algoritmo gerador de números aleatórios a ser utilizado.
     * @param caminhoArquivo caminho do arquivo onde será salvo o relatório.
     * @param limpar se true limpa o arquivo, se false adiciona novas linhas embaixo das linhas já existentes
     * @param geradorNumerosAleatorios objeto que implementa a geração dos números pseudo-aleatórios
     */
    private static void gerarRelatorio(String algoritmo, String caminhoArquivo, boolean limpar,
                               GeradorNumerosAleatorios geradorNumerosAleatorios) {
        // Matriz com os dados gerados, será tranformada em um arquivo CSV
        String[][] dados = new String[11][4];
        for (int i = 0; i < tamanhosBitsNumeros.length; i += 1) {

            BigInteger numero = null;
            long tempoTotal = 0;
            int quantidadeNumeros = 1000;

            // Gera quantidadeNumeros números de cada tamanho de bits especificado e calcula o tempo em nanosegundos.
            for (int j = 0; j < quantidadeNumeros; j += 1) {
                long tempoInicial = System.nanoTime();
                numero = geradorNumerosAleatorios.gerarNumeroAleatorio(tamanhosBitsNumeros[i], algoritmo);
                long tempoFinal = System.nanoTime();
                tempoTotal += tempoFinal - tempoInicial;
            }
            float mediaTempo = (float) tempoTotal / quantidadeNumeros;

            // Salva os dados na matriz, transforma o tempo para microsegundos
            dados[i][0] = algoritmo;
            dados[i][1] = Integer.toString(tamanhosBitsNumeros[i]);
            dados[i][2] = String.format("%.2f", mediaTempo/1000).replace(",", ".");
            dados[i][3] = numero.toString();
        }

        // Com a matriz completa, cria o CSV
        EscritorArquivoCsv.escreverCsv(dados, caminhoArquivo, limpar);
    }

    /**
     * Utiliza as classes criadas para gerar números aleatórios e salvar os resultados em um arquivo CSV.
     * A quantidade de números gerados é diferente para cada tamanho de bits, pois números grandes demandam muito mais
     * tempo para serem gerados. O array quantidadeNumeros define quantos números são gerados para cada tamanho.
     *
     * @param algoritmo algoritmo gerador de números aleatórios a ser utilizado.
     * @param caminhoArquivo caminho do arquivo onde será salvo o relatório.
     * @param limpar se true limpa o arquivo, se false adiciona novas linhas embaixo das linhas já existentes
     * @param geradorNumerosPrimos objeto que implementa a geração dos números primos pseudo-aleatórios
     */
    private static void gerarRelatorioPrimos(String algoritmo, String caminhoArquivo, boolean limpar,
                                             GeradorNumerosPrimos geradorNumerosPrimos) {

        // Quantidade de números gerados de cada tamanho. Index é o mesmo do array tamanhosBitsNumeros.
        final int[] quantidadeNumeros = {100, 100, 100, 100, 50, 50, 50, 25, 25, 10, 10};

        // Matriz com os dados gerados, será tranformada em um arquivo CSV
        String[][] dados = new String[11][5];

        // Gera os números na quantidade desejada e calcula o tempo de geração em nanosegundos
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

            // Salva os dados na matriz, transforma o tempo para microsegundos
            dados[i][0] = algoritmo;
            dados[i][1] = Integer.toString(tamanhosBitsNumeros[i]);
            dados[i][2] = String.format("%.2f", mediaTempo/1000).replace(",", ".");
            dados[i][3] = Integer.toString(quantidadeNumeros[i]);
            dados[i][4] = numero.toString();
        }

        // Cria o CSV a partir da matriz
        EscritorArquivoCsv.escreverCsv(dados, caminhoArquivo, limpar);

    }

}
