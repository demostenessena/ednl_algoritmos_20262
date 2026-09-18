package arvore_generica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class No<T> {
	T dado;
	List<No<T>> filhos;
	
	// construtor
	public No(T dado){
		this.dado = dado;
		this.filhos = new ArrayList<>();
	}
	
	// insere um filho à direita na lista
	public void inserirFilho(No<T> filho){
		this.filhos.add(filho);
	}
	
	// remover o nó na sub-árvore 
	public void removerNo(T dado){
		if(!this.noFolha()){
			// procurando nos filhos imediatos
			for(No<T> filho : this.filhos){
				if(filho.dado.equals(dado)){
					if(filho.noFolha()){						
						this.filhos.remove(filho);						
					}else {
						No<T> ultimoNoFilho = filho.filhos.getLast();
						filho.dado = ultimoNoFilho.dado;
						filho.filhos.remove(ultimoNoFilho);
					}
					return;
				}
			}

			// procurando nos descendentes
			for(No<T> filho : this.filhos){
				filho.removerNo(dado);
			}
		}
	}


	// identifica se o nó é uma folha
	public boolean noFolha() {
		return this.filhos.isEmpty();
	}
	
	// calcula a altura do nó
	public int alturaNo(){
		int maiorAltura = -1;
		
		for(No<T> filho: this.filhos){
			int alturaFilho = filho.alturaNo();
			if(alturaFilho > maiorAltura)
				maiorAltura = alturaFilho;
		}
		
		return maiorAltura + 1;
	}

	// procura um nó que armazena 'dado'
	public No<T> buscarNo(T dado){
		if(this.dado.equals(dado)){ // nó contém o dado
			return this;
		}else { // procurar o dado nos filhos do nó atual
			for(No<T> filho : this.filhos) {
				No<T> no = filho.buscarNo(dado);
				if(no != null)
					return no;
			}
		}
		return null; // dado não encontrado!
	}

	// retorna o pai do nó que armazena o 'dado'
	public No<T> retornarPai(T dado){
		// procura se algum filho armazena o 'dado'
		for(No<T> filho : this.filhos){
			if(filho.dado.equals(dado)){
				return this;
			}
		}

		// procura recursivamente o 'dado' nos descendentes
		for(No<T> filho : this.filhos){
			No<T> pai = filho.retornarPai(dado);
			if(pai!=null)
				return pai;
		}

		return null;
	}

	// imprime a sub-árvore
	public void imprimirNo(String recuo){
		System.out.println(recuo + "+- " + this.dado);
		
		for(No<T> filho: this.filhos){
				filho.imprimirNo(recuo + " ");
		}
	}

	// imprime a sub-árvore em markdown
	public String imprimirNoMD(){
		StringBuilder sb = new StringBuilder();

		for(No<T> filho: this.filhos){ 
			sb.append(this.dado).append("((").append(this.dado).append("))");
			sb.append("--- ");
			sb.append(filho.dado).append("((").append(filho.dado).append("))\n");
		}
		for(No<T> filho: this.filhos){ 
			sb.append(filho.imprimirNoMD());
		}

		return sb.toString();
	}

}


public class Arvore<T> {
	No<T> raiz;
	
	public Arvore(T dadoRaiz){
		this.raiz = new No<>(dadoRaiz);
	}

	// construtor de árvore vazia
	public Arvore(){
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
	
	// insere um filho com a chave "dadoFilho" em "pai"
	public void inserirNo(No<T> pai, T dadoFilho){
		if(pai != null){
			No<T> noFilho = new No<>(dadoFilho);
			pai.inserirFilho(noFilho);
		}
	}

	public void inserirNo(No<T> pai, No<T> filho){
		if(pai != null){
			pai.inserirFilho(filho);
		}
	}

	// remove o nó na árvore que mantém o 'dado'
	public void removerNo(T dado){
		if(!this.arvoreVazia()){
			if(this.raiz.dado.equals(dado) && this.raiz.noFolha()){
				this.raiz = null;
			}else {
				this.raiz.removerNo(dado);
			}
		}
	}

	public int altura(){
		if(this.arvoreVazia())
			return -1;
		else {
			return this.raiz.alturaNo();
		}
	}

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

		sb.append("## Árvore Genérica\n");
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