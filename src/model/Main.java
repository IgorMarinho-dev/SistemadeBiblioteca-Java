package model;

import model.Bibliotecario;
import model.Cliente;
import model.BibliotecariosLoader;
import model.ClientesLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Bibliotecario> bibliotecarios =
                BibliotecariosLoader.carregarDoTxt("src/model/Bibliotecarios.txt");

        List<Cliente> clientes =
                ClientesLoader.carregarDoTxt("src/model/Clientes.txt");

        // Aqui ENTRA O SWING GENTE

    }
}