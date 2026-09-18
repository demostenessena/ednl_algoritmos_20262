package arvore_binaria;

import java.io.FileWriter;
import java.io.IOException;

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

	// método auxiliar para transposicao
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
				transposicao(no_sucessor, no_sucessor.direita);
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
		if(this.arvoreVazia() || this.raiz.dado.equals(dado))
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
				sb.append("idx" + this.raiz.dado + "((" + this.raiz.dado + "))\n");
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