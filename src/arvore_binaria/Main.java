package arvore_binaria;

public class Main {
	public static void main(String[] args) {
		ArvoreBinaria<Integer> arvore_a = new ArvoreBinaria<>();
		
		arvore_a.inserir(10);
		arvore_a.inserir(5);
		arvore_a.inserir(7);
		arvore_a.inserir(18);
		arvore_a.inserir(15);
		arvore_a.inserir(14);
		arvore_a.inserir(16);
		arvore_a.inserir(12);
		arvore_a.inserir(20);
		arvore_a.inserir(19);
		arvore_a.inserir(22);
		
		arvore_a.imprimir();
		System.out.println("Altura: " + arvore_a.altura());
		System.out.println("Resultado (buscar - 14): " + arvore_a.buscar(14).dado);
		System.out.println("Resultado (buscar - 13): " + arvore_a.buscar(13));

		arvore_a.imprimirMD("arvore_binaria_a1.md");		
		arvore_a.remover(12);		
		arvore_a.imprimirMD("arvore_binaria_a2.md");
		arvore_a.remover(5);
		arvore_a.imprimirMD("arvore_binaria_a3.md"); 				
		arvore_a.remover(18);
		arvore_a.imprimirMD("arvore_binaria_a4.md");
		arvore_a.remover(10);
		arvore_a.imprimirMD("arvore_binaria_a5.md");

	}
}