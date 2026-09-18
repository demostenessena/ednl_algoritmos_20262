package arvore_generica;

public class Main {
	public static void main(String[] args) {
		Arvore<String> arvore_a = new Arvore<>();
		Arvore<String> arvore_b = new Arvore<>();
		Arvore<String> arvore_c = new Arvore<>();
		
		No<String> no_a = new No<>("A");
		arvore_a.criarRaiz(no_a);

		No<String> no_b = new No<>("B");
		No<String> no_c = new No<>("C");
		No<String> no_d = new No<>("D");
		No<String> no_e = new No<>("E");
		
		arvore_a.inserirNo(no_a,no_b);
		arvore_a.inserirNo(no_a,no_c);
		arvore_a.inserirNo(no_b,no_d);
		arvore_a.inserirNo(no_b,no_e);
		
		arvore_a.imprimir();
		System.out.println("Altura: " + arvore_a.altura());
		System.out.println("Resultado (buscar - D): " + arvore_a.buscar("D").dado);
		System.out.println("Resultado (buscar - F): " + arvore_a.buscar("F"));
		System.out.println("Resultado (buscar pai - D): " + arvore_a.retornarPai("D").dado);
		System.out.println("Resultado (buscar pai - F): " + arvore_a.retornarPai("F") + "\n");

		arvore_a.imprimirMD("arvore_generica_a1.md");		
		arvore_a.removerNo("C");		
		arvore_a.imprimirMD("arvore_generica_a2.md");
		arvore_a.removerNo("B");
		arvore_a.imprimirMD("arvore_generica_a3.md");

		// árvore somente com a raiz
		No<String> no_f = new No<>("F");
		arvore_b.criarRaiz(no_f);

		arvore_b.imprimir();
		arvore_b.imprimirMD("arvore_generica_b.md");
		System.out.println("Altura: " + arvore_b.altura());

		// árvore vazia
		arvore_c.imprimir();
		arvore_c.imprimirMD("arvore_generica_c.md");
		System.out.println("Altura: " + arvore_c.altura());

	}
}