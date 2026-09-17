import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class No<T extends Comparable<T>> {
	T dado;
	No<T> esquerda;
	No<T> direita;
	No<T> pai;
	
	// construtor
	public No(T dado){
		this.dado = dado;
		this.esquerda = null;
		this.direita = null;
	}
	
	// insere um filho à direita na lista
	public void inserir(T dado){		
		if(this.dado.compareTo(dado) >= 1){ // sub-árvore esquerda
			if(this.esquerda == null) {
				No<T> no_novo = new No<T>(dado);
				this.esquerda = no_novo;
				no_novo.pai = this;
			}else {
				this.esquerda.inserir(dado);
			}
		}else { // sub-árvore esquerda
			if(this.direita == null) {
				No<T> no_novo = new No<T>(dado);
				this.direita = no_novo;
				no_novo.pai = this;
			}else {
				this.direita.inserir(dado);
			}
		}
	}

	// retorna o nó com o menor valor
	public No<T> menor(){
		if(this.esquerda != null)
			this.esquerda.menor();
		return this;
	}

	// retorna o nó com o maior valor
	public No<T> maior(){
		if(this.direita != null)
			this.direita.maior();
		return this;
	}

	// retorna o sucessor do nó
	public No<T> sucessor(){
		if(this.direita == null){
			return null;
		}
		return this.direita.menor();
	}

	// retorna o antecessor do nó
	public No<T> antecessor(){
		if(this.esquerda == null){
			return null;
		}
		return this.esquerda.maior();
	}

	// identifica se o nó é uma folha
	public boolean noFolha() {
		if(this.esquerda == null && this.direita == null)
			return true;
		return false;
	}
	
	// calcula a altura do nó
	public int alturaNo(){
		int maiorAltura = 0;

		if(this.esquerda != null) {
			maiorAltura = this.esquerda.alturaNo() + 1;
		}

		if(this.direita != null) {
			int alturaDireita = this.direita.alturaNo() + 1;
			if(alturaDireita > maiorAltura){
				maiorAltura = alturaDireita;	
			}
		}
			
		return maiorAltura;
	}

	// procura um nó que armazena 'dado'
	public No<T> buscarNo(T dado){
		if(this.dado.equals(dado)){ // nó contém o dado
			return this;
		}else { // procurar o dado nos filhos do nó atual
			if(this.dado.compareTo(dado) >= 1 && this.esquerda != null) {
				return this.esquerda.buscarNo(dado);
			}else if(this.dado.compareTo(dado) < 1 && this.direita != null) {
				return this.direita.buscarNo(dado);
			}
			else 
				return null; // não encontrado!
		}
	}

	// retorna o pai do nó que armazena o 'dado'
	public No<T> retornarPai(T dado){		
		return this.pai;
	}

	// imprime a sub-árvore
	public void imprimirNo(String recuo){
		System.out.println(recuo + "+- " + this.dado);
		
		if(this.esquerda != null)
			this.esquerda.imprimirNo(recuo + " ");

		if(this.direita != null)
			this.direita.imprimirNo(recuo + " ");
	}

	// imprime a sub-árvore em markdown
	public String imprimirNoMD(){
		StringBuilder sb = new StringBuilder();

		if(this.esquerda != null) {
			sb.append(this.dado).append("((").append(this.dado).append("))");
			sb.append("--- ");
			sb.append(this.esquerda.dado).append("((").append(this.esquerda.dado).append("))\n");
		}

		if(this.direita != null) {
			sb.append(this.dado).append("((").append(this.dado).append("))");
			sb.append("--- ");
			sb.append(this.direita.dado).append("((").append(this.direita.dado).append("))\n");
		}

		if(this.esquerda != null)
			sb.append(this.esquerda.imprimirNoMD());
		
		if(this.direita != null)
			sb.append(this.direita.imprimirNoMD());

		return sb.toString();
	}

}

public class ArvoreBinaria<T extends Comparable<T>> {
	No<T> raiz;
	
	// construtor com a raiz
	public ArvoreBinaria(T dadoRaiz){
		this.raiz = new No<T>(dadoRaiz);
	}

	// construtor de árvore vazia
	public ArvoreBinaria(){
		this.raiz = null;
	}

	// verifica se a árvore é vazia
	public boolean arvoreVazia(){
		if(this.raiz == null)
			return true;
		return false;
	}

	// criar uma raiz para a árvore vazia
	public void criarRaiz(No<T> raiz){
		if(this.arvoreVazia()) {
			this.raiz = raiz;
		}
	}

	// retorna a raiz da árvore
	public No<T> obterRaiz() {
		return this.raiz;
	}
	
	// insere um filho com a chave "dadoFilho"
	public void inserir(T dado){		
		if(this.arvoreVazia())
			this.raiz = new No<T>(dado);
		else 
			this.raiz.inserir(dado);
	}

	// TODO - método auxiliar para transposicao
	private void transposicao(No<T> no_antigo, No<T> no_novo) {
		if(no_antigo.pai == null) {
			this.raiz = no_novo;			
		}else if(no_antigo == no_antigo.pai.esquerda){
			no_antigo.pai.esquerda = no_novo;
		}else {
			no_antigo.pai.direita = no_novo;
		}

		if(no_novo != null){
			no_novo.pai = no_antigo.pai;
		}
	}

	// remove o nó na árvore que mantém o 'dado'
	public void remover(T dado){
		if(!this.arvoreVazia()){
			if(this.raiz.dado.equals(dado) && this.raiz.noFolha()){
				this.raiz = null;
			}else {
				No<T> no = this.buscar(dado);

				if(no != null){
					this.remover(no);
				}				
			}
		}
	}

	// remove o nó (após a busca)
	public void remover(No<T> no){
		if(no.esquerda == null) {
			transposicao(no, no.direita);
		}else if(no.direita == null) {
			transposicao(no, no.esquerda);
		}else {
			No<T> no_sucessor = no.sucessor();
			if(no_sucessor.pai != no) {
				transposicao(no_sucessor, no.direita);
				no_sucessor.direita = no.direita;
				no_sucessor.direita.pai = no_sucessor;
			}
			transposicao(no, no_sucessor);
			no_sucessor.esquerda = no.esquerda;
			no_sucessor.esquerda.pai = no_sucessor;
		}
	}

	// retorna a altura da árvore
	public int altura(){
		if(this.arvoreVazia())
			return -1;
		else {
			return this.raiz.alturaNo();
		}
	}

	// busca o nó que armazena o dado
	public No<T> buscar(T dado){
		if(this.arvoreVazia()){
			return null;
		}else {
			return this.raiz.buscarNo(dado);
		}
	}

	// retorna o nó pai do nó armazena o 'dado'.
	public No<T> retornarPai(T dado){
		if(this.arvoreVazia() || this.raiz.equals(dado))
			return null;
		else {
			return this.raiz.retornarPai(dado);
		}
	}

	// imprime a árvore no "terminal"
	public void imprimir() {
		if(this.arvoreVazia()){
			System.out.println("Árvore vazia!");	
		}else {
			this.raiz.imprimirNo("");
		}
		System.out.println("");
	}

	// imprime a árvore no arquivo "nomeArquivo" no formato Markdown com Mermaid
	public void imprimirMD(String nomeArquivo){
		StringBuilder sb = new StringBuilder();

		sb.append("## Árvore Binária\n");
		sb.append("```mermaid\n");
		sb.append("graph TD\n");

		if(!this.arvoreVazia()){
			if(this.raiz.noFolha()){
				sb.append(this.raiz.dado + "((" + this.raiz.dado + "))\n");
			}else {
				sb.append(this.raiz.imprimirNoMD());
			}
		}

		sb.append("```");

		FileWriter fw = null;

		try {
			fw = new FileWriter(nomeArquivo);
			fw.write(sb.toString());
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(fw != null){
				try{
					fw.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
}