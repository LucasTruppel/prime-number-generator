package br.ufsc.seguranca.geradorprimo.primo;

import br.ufsc.seguranca.geradorprimo.numeroaleatorio.GeradorNumerosAleatorios;

import java.math.BigInteger;

/**
 * Essa classe serve para criar números aleatórios primos.
 * Ela simplesmente utiliza a classe GeradorNumerosAleatorios para gerar números até encontrar um número primo
 * utilizando o algoritmo de teste de primalidade passado como parâmetro. As opções de algoritmo sõa Miller-Rabin e
 * Fermat.
 */
public class GeradorNumerosPrimos {

    GeradorNumerosAleatorios geradorNumerosAleatorios;
    MillerRabin millerRabin;
    Fermat fermat;


    public GeradorNumerosPrimos() {
        geradorNumerosAleatorios = new GeradorNumerosAleatorios();
        millerRabin = new MillerRabin();
        fermat = new Fermat();
    }

    /**
     * Gera um número pseudo-aleatório e testa sua primalidade. Isso se repete até encotrar um primo.
     *
     * @param tamanhoBitsNumero tamanho em bits do número a ser gerado.
     * @param algoritmo nome do algoritmo a ser utilizado.
     * @return número primo gerado.
     */
    public BigInteger gerarNumeroPrimo(int tamanhoBitsNumero, String algoritmo) {
        algoritmo = algoritmo.toLowerCase();
        BigInteger numeroGerado = null;
        boolean primoEncontrado = false;
        while (!primoEncontrado) {
            // Gera o número com Xor Shift
            numeroGerado = geradorNumerosAleatorios.gerarNumeroAleatorio(tamanhoBitsNumero, "xorshift");

            // Seleciona o algoritmo de teste a ser usado, miller-rabin ou fermat.
            if (algoritmo.equals("millerrabin")) {
                if (millerRabin.ehPrimo(numeroGerado, 10)) {
                    primoEncontrado = true;
                }
            } else if (algoritmo.equals("fermat")) {
                if (fermat.ehPrimo(numeroGerado, 10)) {
                    primoEncontrado = true;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return numeroGerado;
    }
}
