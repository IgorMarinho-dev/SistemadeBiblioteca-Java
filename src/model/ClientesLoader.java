package model;

import model.Cliente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ClientesLoader {

    public static List<Cliente> carregarDoTxt(String caminho) {
        List<Cliente> lista = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));

            for (String linha : linhas) {
                if (linha.isBlank() || linha.startsWith("#")) continue;

                String[] partes = linha.split(";");

                if (partes.length != 7) {
                    System.err.println("Linha inválida ignorada: " + linha);
                    continue;
                }

                int id               = Integer.parseInt(partes[0].trim());
                String nome          = partes[1].trim();
                String email         = partes[2].trim();
                String telefone      = partes[3].trim();
                String endereco      = partes[4].trim();
                LocalDate nascimento = LocalDate.parse(partes[5].trim());
                String senha         = partes[6].trim();

                lista.add(new Cliente(id, nome, email, telefone, endereco, nascimento, senha));
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Data inválida no arquivo: " + e.getMessage());
        }

        return lista;
    }
}