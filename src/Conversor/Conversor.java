package Conversor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import AFD.Transicao;

/**
 * Classe responsável por fazer a conversão de um arquivo de entrada em objetos que serão tratados pelo AFD.
 * @author João Pedro Santos de Moura
 *
 */
public class Conversor {
	FileManager fm;
	BufferedReader reader;
	
	
	/**
	 * Construtor que abre um arquivo para somente leitura.
	 * @param s Nome do arquivo a ser lido.
	 */
	public Conversor(String s) {
		fm = new FileManager(s, false);
		if(fm.openReader()) reader = fm.getReader();
		else reader = null;
	}
	
	/**
	 * Verifica se a linha lida é uma linha válida para processamento.
	 * @param array String de caracteres convertida em um vetor de char.
	 * @return TRUE se é uma linha válida, FALSE caso contrário.
	 */
	private boolean checkLine(char[] array) {
		if(array == null) return false;
		else if(array.length == 0) return false;
		else if(array[0] == '/' || array[0] == ' ') return false;
		else return true;
	}
	
	/**
	 * Verifica se é uma linha final de parâmtro
	 * @param s Linha a ser verificada
	 * @return TRUE se é uma linha final, FALSE caso contrário
	 */
	private boolean isFinalLine(String s) {
		char[] line = s.toCharArray();
		for(int i = 0; i < line.length; ++i) {
			if(line[i] == ';') return true;
		}
		return false;
	}
	
	/**
	 * Cria o alfabeto/estados a partir da leitura de uma linha do arquivo.
	 * @return Um vetor de char onde cada posição representa um símbolo do alfabeto ou um estado.
	 */
	public char[] makeAlphabet() {
		String linha = null;
		char[] buffer = null;
		CharBuffer alphabet;
		int amountChars = 0;
		
		try {
			while(!checkLine(buffer)) { // Enquanto a linha NAO for valida
				linha = reader.readLine(); // Le a linha
				buffer = linha.toCharArray(); // Converte a linha em um vetor de char
			}
			
			alphabet = CharBuffer.allocate(buffer.length); // Aloca um vetor do mesmo tamanho do buffer
			
			for(int i = 0; i < buffer.length; ++i) {
				if(buffer[i] == ';') break; // Assim que for lido marcador de fim de linha, para a iteracao
				if(buffer[i] != ' ') {
					alphabet.put(buffer[i]); // Se o simbolo lido nao for um espaco em branco, entao eh adicionado ao alfabeto
					++amountChars; // Incrementa a quantidade de caracteres lidos
				}
			}
			
			String test = new String(alphabet.array());
			test = test.substring(0, amountChars); // Recorta somente o que foi efetivamente usado do buffer
			
		    return test.toCharArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Lê as transições do arquivo e converte para objetos Transicao.
	 * @param alphabet Conjunto de símbolos do autômato
	 * @param states Conjunto de estados do autômato
	 * @return um vetor de Transicao, contendo todas as transições
	 */
	public Transicao[] makeTransitions(char[] alphabet, char[] states) {
		String linha = null;
		char[] buffer = null;
		
		char ativo, funcao, proximo; // Parametros de uma transicao
		
		int indexState = 0; // índice do estado
		int indexAlpha = 0; // indice do símbolo do alfabeto
		int i; // usado nos FOR's
		
		List<Transicao> lista = new ArrayList<Transicao>(); // lista de transicoes
		Transicao t;
		
		try {
			
			while(!checkLine(buffer)) { // Enquanto a linha NAO for valida
				linha = reader.readLine(); // Le a linha
				buffer = linha.toCharArray(); // Converte a linha em um vetor de char
			}
			
			
			while(!isFinalLine(linha)) { // Enquanto não for a última linha
				
				ativo = states[indexState]; // Determina o estado ativo, para toda transição existente em uma linha, o estado ativo é comum para todas.
				
				/*
				 *  Uma linha pode ter até [quantidade de símbolos de um alfabeto]-transições, por isso o uso de um FOR
				 *  então, enquanto o índice da linha 'i' for menor que o índice da vírgula 'indexComma', então existem transições a serem criadas
				 */
				
				for(i = 0; indexAlpha < alphabet.length && i < buffer.length; i += 2) { // Estados são separados por espaços em branco ' ', por isso o + 2
					funcao = alphabet[indexAlpha];  // Obtem o simbolo da transicao
					
					proximo = buffer[i]; // Para qual estado vai com este simbolo
					
					t = new Transicao(ativo, proximo, funcao); // Cria a nova transicao
					
					lista.add(t); // Adiciona a transicao na lista
					
					++indexAlpha; // incrementa a o simbolo do alfabeto
				}
				
				++indexState;
				indexAlpha = 0;
				//refreshIndex(indexState, indexAlpha); // Incrementa o estado e reseta o alfabeto para a próxima linha
				
				linha = reader.readLine();
				buffer = linha.toCharArray();
				

			}
			
			// Tratamento para a última linha
			
			ativo = states[indexState];
			for(i = 0; indexAlpha < alphabet.length && i < buffer.length; i += 2) {
				funcao = alphabet[indexAlpha];
				proximo = buffer[i];
				t = new Transicao(ativo, proximo, funcao);
				lista.add(t);
				++indexAlpha;
			}
			
			// Fim do tratamento
			
			Transicao[] omega = new Transicao[lista.size()];
			
			for(i = 0; i < omega.length; ++i) {
				omega[i] = lista.get(i);
			}
			
			return omega;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Lê o estado inicial do arquivo.
	 * @return Um char correspondente ao estado inicial.
	 */
	public char makeInitial() {
		String linha = null;
		char[] buffer = null;
		
		try {
			
			while(!checkLine(buffer)) { // Enquanto a linha NAO for valida
				linha = reader.readLine(); // Le a linha
				buffer = linha.toCharArray(); // Converte a linha em um vetor de char
			}
			
			return buffer[0];
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return '?';
		}

	}
	
	/**
	 * Lê o(s) estado(s) final(ais) do arquivo.
	 * @return Um vetor de char contendo o(s) estado(s) final(ais).
	 */
	public char[] makeFinal() {
		String linha = null;
		char[] buffer = null;
		int indexSemicolon = 0;
	    CharBuffer finals;
	    int amountChar = 0;
		
		try {
			
			while(!checkLine(buffer)) { // Enquanto a linha NAO for valida
				linha = reader.readLine(); // Le a linha
				buffer = linha.toCharArray(); // Converte a linha em um vetor de char
			}
			
			indexSemicolon = linha.indexOf(";"); // Índice do ponto-vírgula
			
			finals = CharBuffer.allocate(indexSemicolon); // Aloca um buffer do tamanho da substring de tamanho [indexSemicolon]
			
			for(int i = 0; i < indexSemicolon; ++i) {
				if(buffer[i] != ' ') {
					finals.put(buffer[i]);
					++amountChar; //Quantidade de estados lidos
				}
			}
			
			String fin = new String(finals.array());
			fin = fin.substring(0, amountChar);
			
			return fin.toCharArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Lê as palavras de teste no arquivo. A última palavra deve ser seguida pelo caracter ';'
	 * @return Um vetor de string contendo todas as palavras
	 */
	public String[] makeWords() {
		String linha = null;
		char[] buffer = null;
		List<String> words = new ArrayList<String>(); // Lista de palavras
		int commaIndex; // Índice da vírgula
		
		try {
			while(!checkLine(buffer)) { // Enquanto a linha NAO for valida
				linha = reader.readLine(); // Le a linha
				buffer = linha.toCharArray(); // Converte a linha em um vetor de char
			}
			
			while(!isFinalLine(linha)) { //Enquanto não for a última linha
				
				commaIndex = linha.indexOf(','); // Obtem o índice da vírgula
				
				linha = linha.substring(0, commaIndex); // Obtem a palavra da linha lida
				
				words.add(linha); // Adiciona a palavra na lista
				
				linha = reader.readLine(); // Lê a próxima linha
			}
			
			// Armazena a última linha
			
			commaIndex = linha.indexOf(';'); // A única diferença da última linha para as outras é que ela termina com ';'
			linha = linha.substring(0, commaIndex);
			words.add(linha);
			
			//Conversão de List para array
			// O metodo toArray() da coleção não funcionou como o esperado
			
			String[] palavras = new String[words.size()];
			for(int i = 0; i < words.size(); ++i) {
				palavras[i] = words.get(i);
			}
			
			return palavras;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
