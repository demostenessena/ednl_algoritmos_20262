package arvore_binaria;

public class No<T extends Comparable<T>> {
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
	
	// insere um filho na sub-arvore à esquerda ou à direita
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
			return this.esquerda.menor();
		return this;
	}

	// retorna o nó com o maior valor
	public No<T> maior(){
		if(this.direita != null)
			return this.direita.maior();
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
			sb.append("idx").append(this.dado).append("((").append(this.dado).append("))");
			sb.append("-->");
			sb.append("idx").append(this.esquerda.dado).append("((").append(this.esquerda.dado).append("))\n");

			// imprimindo a ligação com o pai
			sb.append("idx").append(this.esquerda.dado).append("((").append(this.esquerda.dado).append("))");
			sb.append("-.->");
			sb.append("idx").append(this.dado).append("((").append(this.dado).append("))\n");						
		}

		if(this.direita != null) {
			sb.append("idx").append(this.dado).append("((").append(this.dado).append("))");
			sb.append("-->");
			sb.append("idx").append(this.direita.dado).append("((").append(this.direita.dado).append("))\n");

			// imprimindo a ligação com o pai
			sb.append("idx").append(this.direita.dado).append("((").append(this.direita.dado).append("))");
			sb.append("-.->");	
			sb.append("idx").append(this.dado).append("((").append(this.dado).append("))\n");
		}

		if(this.esquerda != null)
			sb.append(this.esquerda.imprimirNoMD());
		
		if(this.direita != null)
			sb.append(this.direita.imprimirNoMD());

		return sb.toString();
	}

}
