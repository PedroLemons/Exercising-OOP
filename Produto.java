package Supermercado;

import java.io.Serializable;

public abstract class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String item;
	private   String marca;
	private   int qtd;
	protected String tipoProduto;
	
	public Produto(String item, String marca, int qtd) {
		this.item = item;
		this.marca = marca;
		this.qtd = qtd;
	}
	public String toString() {
		String retorno = "";
		retorno += "Item: "     + this.item     + "\n";
		retorno += "Marca: "     + this.marca     + "\n";
		retorno += "Quantidade: "    + this.qtd    + " unidades\n";
		retorno += "Tipo do produto: "  + this.tipoProduto  + "\n";
		retorno += "Natureza: "  + tipagem()        + "\n";
		return retorno;
	}
	public abstract String tipagem();
}
