package Supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Supermercado {
	private ArrayList<Produto> produtos;

	public Supermercado() {
		this.produtos = new ArrayList<Produto>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Bebida leBebida (){

		String [] valores = new String [3];
		String [] nomeVal = {"Item", "Marca", "Quantidade"};
		valores = leValores (nomeVal);

		int qtd = this.retornaInteiro(valores[2]);

		Bebida bebida = new Bebida (valores[0], valores[1], qtd);
		return bebida;
	}

	public Limpeza leLimpeza (){

		String [] valores = new String [3];
		String [] nomeVal = {"Item", "Marca", "Quantidade"};
		valores = leValores (nomeVal);

		int qtd = this.retornaInteiro(valores[1]);

		Limpeza limpeza = new Limpeza (valores[0], valores[1], qtd);
		return limpeza;
	}

	public Laticinio leLaticinio (){

		String [] valores = new String [3];
		String [] nomeVal = {"Item", "Marca", "Quantidade"};
		valores = leValores (nomeVal);

		int qtd = this.retornaInteiro(valores[1]);

		Laticinio laticinio = new Laticinio (valores[0], valores[1], qtd);
		return laticinio;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Metodo estatico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // nao conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto nao for possivel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaprodutos (ArrayList<Produto> produtos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\Supermercado.dados"));
			for (int i=0; i < produtos.size(); i++)
				outputStream.writeObject(produtos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Produto> recuperaprodutos (){
		ArrayList<Produto> produtosTemp = new ArrayList<Produto>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\Supermercado.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Produto) {
					produtosTemp.add((Produto) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com produtos nao existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return produtosTemp;
		}
	}

	public void menuSupermercado (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Supermercado\n" +
					"Opcoes:\n" + 
					"1. Entrar produtos\n" +
					"2. Exibir produtos\n" +
					"3. Limpar produtos\n" +
					"4. Gravar produtos\n" +
					"5. Recuperar produtos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de produtos\n" +
						"Opcoes:\n" + 
						"1. Limpeza\n" +
						"2. Bebida\n" +
						"3. Laticinio\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: produtos.add((Produto)leLimpeza());
				break;
				case 2: produtos.add((Produto)leBebida());
				break;
				case 3: produtos.add((Produto)leLaticinio());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Produto para entrada nao escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < produtos.size(); i++)	{
					dados += produtos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				produtos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				salvaprodutos(produtos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				produtos = recuperaprodutos();
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Supermercado");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Supermercado mercadoria = new Supermercado ();
		mercadoria.menuSupermercado();

	}

}
