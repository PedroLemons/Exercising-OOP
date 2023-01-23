package Supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Supermercado1 {

	private ArrayList<Produto> produtos;


	public Supermercado1( ) {
		this.produtos = new ArrayList<Produto>();
	}

	public void adicionaItem(Produto mani) {
		this.produtos.add(mani);
	}

	public void listarItens() {
		for(Produto mani:produtos) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.produtos.size() + " Itens listados com sucesso!\n");
	}
	
	public void excluirItem(Produto mani) {
		if (this.produtos.contains(mani)) {
			this.produtos.remove(mani);
			System.out.println("Item " + mani.toString() + "excluido com sucesso!\n");
		}
		else
			System.out.println("Item inexistente!\n");
	}

	public void excluirItens() {
		produtos.clear();
		System.out.println("Itens excluidos com sucesso!\n");
	}
	public void gravarItens()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\Itens.dat"));
			for(Produto mani:produtos) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarItens() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\Itens.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Bebida)  
					this.produtos.add((Bebida)obj);
				if (obj instanceof Limpeza)  
					this.produtos.add((Limpeza)obj);
				else if (obj instanceof Laticinio)  
					this.produtos.add((Laticinio)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Itens recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Supermercado1 mercadoria  = new Supermercado1();

		Bebida   coca     = new Bebida("Refrigerante", "Coca", 8);
		Bebida   tang    = new Bebida("Suco", "Tang", 9);
		Limpeza  veja     = new Limpeza ("Desinfetante", "Veja",  1);
		Limpeza  ype  = new Limpeza ("Detergente", "Ype", 7);
		Laticinio danone = new Laticinio("Iogurte", "Danone", 1);
		mercadoria.adicionaItem(coca);
		mercadoria.adicionaItem(tang);
		mercadoria.adicionaItem(veja);
		mercadoria.adicionaItem(ype);
		mercadoria.adicionaItem(danone);
		mercadoria.listarItens();
		mercadoria.gravarItens();
		mercadoria.excluirItem(coca);
		mercadoria.listarItens();
		mercadoria.excluirItens();
		mercadoria.listarItens();
		mercadoria.recuperarItens();
		mercadoria.listarItens();
	}

}
