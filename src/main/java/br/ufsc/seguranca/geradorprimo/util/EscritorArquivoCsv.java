package br.ufsc.seguranca.geradorprimo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorArquivoCsv {

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
