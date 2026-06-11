package model;

import model.Livro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LivrosLoader {

    public static List<Livro> carregarDoTxt(String caminho) {
        List<Livro> lista = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));

            for (String linha : linhas) {
                if (linha.isBlank() || linha.startsWith("#")) continue;

                String[] partes = linha.split(";");

                if (partes.length != 5) {
                    System.err.println("Linha inválida ignorada: " + linha);
                    continue;
                }

                int id            = Integer.parseInt(partes[0].trim());
                String titulo     = partes[1].trim();
                String editora    = partes[2].trim();
                String autor      = partes[3].trim();
                String categoria  = partes[4].trim();

                lista.add(new Livro(id, titulo, editora, autor, categoria));
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return lista;
    }
}