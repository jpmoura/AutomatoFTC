package AFD;


public class Transicao {
	private char ativo;
	private char proximo;
	private char funcao;
	
	public Transicao(char a, char p, char f) {
		this.ativo = a;
		this.proximo = p;
		this.funcao = f;
	}
	
	public char getAtual() {
		return ativo;
	}
	public void setAtual(char atual) {
		this.ativo = atual;
	}
	public char getProximo() {
		return proximo;
	}
	public void setProximo(char proximo) {
		this.proximo = proximo;
	}
	public char getFuncao() {
		return funcao;
	}
	public void setFuncao(char funcao) {
		this.funcao = funcao;
	}
	
	/**
	 * Obtem todas as trasições de um determinado estado.
	 * @param atual Estado o qual se quer obter as transições
	 * @param t Vetor de transições
	 * @return Vetor com todas as trasições de  'atual'
	 */
	public Transicao[] getTransicoes(char atual, Transicao[] t) {
		int quant = 0; // Quantidade de transicoes que existem.
		
		for (int i = 0; i < t.length; ++i) if(atual == t[i].getAtual()) ++quant;
		
		if(quant == 0) return null; // Se quant é igual a 0 então não existem transicoes
		
		else { // Senão
			Transicao[] r = new Transicao[quant]; // Cria um vetor de Transicoes com tamanho igual a quant
			int j = 0; // Iterador do vetor resultante
			for (int i = 0; i < t.length; ++i) { //Para todo vetor
				if(atual == t[i].getAtual()) { // Se o nó atual é igual ao existente em t
					r[j] = t[i]; // Insere a transição no vetor resultante
					++j; // Incrementa o iterador do vetor resultante
				}
			}
			return r; // Retorna o vetor resultante com todas as transições de atual;
		}
	}
	
	public String toString() {
		return "Do estado " + this.ativo + " vai para o estado " + this.proximo + " com o símbolo " + this.funcao;
	}
	

}
