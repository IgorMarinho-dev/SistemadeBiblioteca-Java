package model;

import model.Bibliotecario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BibliotecariosLoader {

    public static List<Bibliotecario> carregarDoTxt(String caminho) {
        List<Bibliotecario> lista = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));

            for (String linha : linhas) {
                // Ignora linhas vazias ou comentários
                if (linha.isBlank() || linha.startsWith("#")) continue;

                String[] partes = linha.split(";");

                if (partes.length != 5) {
                    System.err.println("Linha inválida ignorada: " + linha);
                    continue;
                }

                int id          = Integer.parseInt(partes[0].trim());
                String nome     = partes[1].trim();
                String email    = partes[2].trim();
                String telefone = partes[3].trim();
                String senha    = partes[4].trim();

                lista.add(new Bibliotecario(id, nome, email, telefone, senha));
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return lista;
    }
}
