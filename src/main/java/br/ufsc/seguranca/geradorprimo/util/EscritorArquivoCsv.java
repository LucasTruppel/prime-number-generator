package br.ufsc.seguranca.geradorprimo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe gera um arquivo CSV com os dados passados em formato de matriz.
 */
public class EscritorArquivoCsv {

    /**
     * Gera o arquivo CSV.
     *
     * @param dados matriz com os dados.
     * @param nomeArquivo nome do arquivo com seu caminho.
     * @param limpar se true limpa o arquivo, se false adiciona novas linhas embaixo das linhas já existentes
     */
    public static void escreverCsv(String[][] dados, String nomeArquivo, boolean limpar) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, !limpar));
            for (String[] linha : dados) {
                String linhaString = String.join(",", linha) + "\n";
                writer.write(linhaString);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
