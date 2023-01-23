package Supermercado;

public class Laticinio extends Produto {

	private static final long serialVersionUID = 1L;

	public String tipagem() {
		return "Alimenticio";
	}
	public Laticinio(String item, String marca, int qtd) {
		super(item, marca, qtd);
		this.tipoProduto = "Laticinio";
	}

}
