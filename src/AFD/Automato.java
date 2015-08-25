package AFD;

/**
 * Classe que representa o autômato finito determinístico.
 * @author João Pedro Santos de Moura
 *
 */
public class Automato {
	private char[] alphabet; // Alfabeto
	private Transicao[] transitions; // Transições
	private char[] states; // Estados
	private char init; // Estado inicial
	private char[] fin; // Conjunto de estados finais	
	
	public Automato(char[] a, Transicao[] t, char[] e, char i, char[] f) {
		this.alphabet = a;
		this.transitions = t;
		this.states = e;
		this.init = i;
		this.fin = f;
	}
	
	/**
	 * Verifica se um estado é final.
	 * @param e Estado a ser verificado.
	 * @return TRUE se o estado pertence ao conjunto de estado finais, FALSE caso contrário.
	 */
	private boolean isFinal(char e) {
		for(int i = 0; i < fin.length; ++i) {
			if(e == fin[i]) return true;
		}
		return false;
	}
	
	/**
     * Realiza a transição entre estados dado um determinado símbolo.
	 * @param symbol Símbolo da transição.
	 * @param e Estado atual.
	 */
	private char doTransition(char symbol, char e) {
		for(int i = 0; i < this.transitions.length; ++i) { // Para todo conjunto de transições
			if(e == this.transitions[i].getAtual() && symbol == this.transitions[i].getFuncao()) { // Se o estado atual for igual ao estado e o símbolo da Transição na posição 'i'
				e = this.transitions[i].getProximo(); // Então atualiza o estado atual
				return e; // Retorna pois a transição já foi feita
			}
		}
		return e;
	}
	
	/**
	 * Testa uma palavra se uma palavra pertence a Linguagem reconhecida pelo autômato.
	 * @param s Palavra a ser testada.
	 * @return TRUE se a palavra pertence a linguagem, FALSE caso contrário.
	 */
	public boolean testWord(String s) {
		char[] word = s.toCharArray(); // conversão de string para array de char
		char atual = this.init; // Estado onde o autômato inicia a execução
		
		for(int i = 0; i < word.length; ++i) { // Para cada símbolo da palavra,
			atual = doTransition(word[i], atual); // Realiza a transição
		}
		
		if(isFinal(atual)) return true; // Se o estado atual ao final do processamento for final, então retorna TRUE
		else return false; // Senão retorna FALSE
	}

}
