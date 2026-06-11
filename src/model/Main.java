package model;

import model.Bibliotecario;
import model.Cliente;
import model.BibliotecariosLoader;
import model.ClientesLoader;
import javax.swing.SwingUtilities;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Bibliotecario> bibliotecarios =
                BibliotecariosLoader.carregarDoTxt("src/model/Bibliotecarios.txt");

        List<Cliente> clientes = ClientesLoader.carregarDoTxt("src/model/Clientes.txt");

        List<Livro> livros = LivrosLoader.carregarDoTxt("src/model/Livros.txt");

        // Aqui ENTRA O SWING GENTE

        SwingUtilities.invokeLater(() -> new TelaLogin(bibliotecarios, clientes));

    }
}