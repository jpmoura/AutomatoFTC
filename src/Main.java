import java.util.Scanner;

import AFD.Automato;
import AFD.Transicao;
import Conversor.Conversor;

/**
 * Classe para interagir com o usuário.
 * @author João Pedro Santos de Moura
 *
 */
public class Main {
	
	public static void runTest(Automato a, String[] words) {
		System.out.printf("\n\nTESTE\n\n");
		for(int i = 0; i < words.length; ++i) {
			System.out.print(words[i] + " -> ");
			if(a.testWord(words[i])) System.out.println("aceita");
			else System.out.println("rejeitada");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Automato afd;
		Conversor con;
		
		System.out.print("CLASSE MAIN \n\n");
		
		if(args.length == 1) con = new Conversor(args[0]); // Se foi passado um argumento então este é o nome do arquivo
		else { // Senão obtém do usuário
			System.out.printf("Informe o nome do arquivo de entrada. Caso não esteja na mesma pasta onde a aplicação está sendo executada, informe o caminho completo.\nArquivo: ");
			Scanner input = new Scanner(System.in);
			String file = input.next();
			input.close();
			con = new Conversor(file);
		}
		/*
		// TESTES
		
		//Vetor de Estados - OK
		char[] states = con.makeAlphabet();
		String est = new String(states);
		System.out.println("Imprimindo estados: " + est);
		
		
		//ALFABETO - OK
		char[] alpha = con.makeAlphabet();
		String alfa = new String(alpha);
		System.out.println("Imprimindo alfabeto: " + alfa);
		
		//TRANSICOES -OK
		Transicao[] trans = con.makeTransitions(alpha, states);
		System.out.println("Tamanho do vetor de transicoes: " + trans.length);
		for(int i = 0; i < trans.length; ++i) {
			System.out.println(trans[i].toString());
		}
		
		//ESTADO INICIAL - OK
		char inicial = con.makeInitial();
		System.out.println("Estado inicial: " + inicial);
		
		//ESTADO FINAL - OK
		
		char[] finais = con.makeFinal();
		System.out.print("Tamanho do vetor de estados finais: " + finais.length + "	Estados finais: ");
		for(int i = 0; i < finais.length; ++i) {
			System.out.print(finais[i] + " ");
		}
		
		//PALAVRAS DE TESTE - OK
		
		String[] palavras = con.makeWords();
		System.out.printf("\nTamanho do vetor: %d\nPalavras de teste: ", palavras.length);
		for(int i = 0; i < palavras.length; ++i) {
			System.out.print(palavras[i] + ", ");
		}
		
		
		// FIM TESTES
		
        afd = new Automato(alpha, trans, states, inicial, finais);
		
		runTest(afd, palavras);
		
		*/ //Main
		
		char[] est = con.makeAlphabet();
		char[] alp = con.makeAlphabet();
		Transicao[] t = con.makeTransitions(alp, est);
		char i = con.makeInitial();
		char[] f = con.makeFinal();
		String[] palavras = con.makeWords();
		
		afd = new Automato(alp, t, est, i, f);
		
		runTest(afd, palavras);
		
		//*/

	}

}
