package model;

import model.Livro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TelaBibliotecario extends JFrame {

    private JTextField campoId;
    private JTextField campoTitulo;
    private JTextField campoEditora;
    private JTextField campoAutor;
    private JTextField campoCategoria;
    private JLabel labelFeedback;

    private final String caminhoLivros = "src/model/Livros.txt";

    public TelaBibliotecario(String nomeBibliotecario) {
        construirTela(nomeBibliotecario);
    }

    private void construirTela(String nomeBibliotecario) {
        setTitle("Biblioteca — Painel do Bibliotecário");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(440, 640);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(18, 24, 38));
        painel.setBorder(new EmptyBorder(40, 50, 40, 50));


        JLabel titulo = new JLabel("Novo Livro");
        titulo.setFont(new Font("Georgia", Font.BOLD, 26));
        titulo.setForeground(new Color(220, 190, 120));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Olá, " + nomeBibliotecario);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(150, 160, 180));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel labelId       = criarLabel("ID");
        campoId              = new JTextField();
        estilizarCampo(campoId);

        JLabel labelTitulo   = criarLabel("Título");
        campoTitulo          = new JTextField();
        estilizarCampo(campoTitulo);

        JLabel labelEditora  = criarLabel("Editora");
        campoEditora         = new JTextField();
        estilizarCampo(campoEditora);

        JLabel labelAutor    = criarLabel("Autor");
        campoAutor           = new JTextField();
        estilizarCampo(campoAutor);

        JLabel labelCategoria = criarLabel("Categoria");
        campoCategoria        = new JTextField();
        estilizarCampo(campoCategoria);


        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAdicionar.setBackground(new Color(220, 190, 120));
        btnAdicionar.setForeground(new Color(18, 24, 38));
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdicionar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdicionar.addActionListener(e -> adicionarLivro());


        JButton btnAcervo = new JButton("Acervo");
        btnAcervo.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAcervo.setBackground(new Color(60, 72, 100));
        btnAcervo.setForeground(Color.WHITE);
        btnAcervo.setFocusPainted(false);
        btnAcervo.setBorderPainted(false);
        btnAcervo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAcervo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btnAcervo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAcervo.addActionListener(e -> new TelaAcervo(true));


        labelFeedback = new JLabel(" ");
        labelFeedback.setFont(new Font("SansSerif", Font.PLAIN, 12));
        labelFeedback.setForeground(new Color(100, 200, 120));
        labelFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);


        painel.add(titulo);
        painel.add(Box.createVerticalStrut(4));
        painel.add(subtitulo);
        painel.add(Box.createVerticalStrut(28));
        painel.add(labelId);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoId);
        painel.add(Box.createVerticalStrut(12));
        painel.add(labelTitulo);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoTitulo);
        painel.add(Box.createVerticalStrut(12));
        painel.add(labelEditora);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoEditora);
        painel.add(Box.createVerticalStrut(12));
        painel.add(labelAutor);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoAutor);
        painel.add(Box.createVerticalStrut(12));
        painel.add(labelCategoria);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoCategoria);
        painel.add(Box.createVerticalStrut(24));
        painel.add(btnAdicionar);
        painel.add(Box.createVerticalStrut(10));
        painel.add(btnAcervo);
        painel.add(Box.createVerticalStrut(10));
        painel.add(labelFeedback);

        add(painel);
        setVisible(true);
    }

    private void adicionarLivro() {
        String idTexto   = campoId.getText().trim();
        String titulo    = campoTitulo.getText().trim();
        String editora   = campoEditora.getText().trim();
        String autor     = campoAutor.getText().trim();
        String categoria = campoCategoria.getText().trim();

        if (idTexto.isEmpty() || titulo.isEmpty() || editora.isEmpty()
                || autor.isEmpty() || categoria.isEmpty()) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("Preencha todos os campos.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("ID inválido.");
            return;
        }

        String novaLinha = id + ";" + titulo + ";" + editora + ";" + autor + ";" + categoria;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoLivros, true))) {
            bw.newLine();
            bw.write(novaLinha);

            labelFeedback.setForeground(new Color(100, 200, 120));
            labelFeedback.setText("Livro \"" + titulo + "\" adicionado!");

            campoId.setText("");
            campoTitulo.setText("");
            campoEditora.setText("");
            campoAutor.setText("");
            campoCategoria.setText("");

        } catch (IOException e) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("Erro ao salvar: " + e.getMessage());
        }
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
        label.setForeground(new Color(150, 160, 180));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setBackground(new Color(30, 38, 55));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 72, 100), 1),
                new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}