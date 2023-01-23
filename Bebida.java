package Supermercado;

public class Bebida extends Produto {

	private static final long serialVersionUID = 1L;

	public String tipagem() {
		return "Liquido";
	}
	public Bebida(String item, String marca, int qtd) {
		super(item, marca, qtd);
		this.tipoProduto = "Bebida";
	}

}
