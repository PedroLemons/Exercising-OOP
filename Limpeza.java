package Supermercado;

public class Limpeza extends Produto {

	private static final long serialVersionUID = 1L;

	public String tipagem() {
		return "Higiene";
	}
	public Limpeza(String item,String marca, int qtd) {
		super(item, marca, qtd);
		this.tipoProduto = "Limpeza";
	}

}
