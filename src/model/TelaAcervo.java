package model;

import model.Livro;
import model.LivrosLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TelaAcervo extends JFrame {

    private final String caminhoLivros = "src/model/Livros.txt";
    private final boolean isBibliotecario;
    private JPanel painel;
    private JScrollPane scroll;

    public TelaAcervo(boolean isBibliotecario) {
        this.isBibliotecario = isBibliotecario;
        construirTela();
    }

    private void construirTela() {
        setTitle("Biblioteca — Acervo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(600, 500);
        setLocationRelativeTo(null);

        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(18, 24, 38));
        painel.setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel titulo = new JLabel("Acervo");
        titulo.setFont(new Font("Georgia", Font.BOLD, 26));
        titulo.setForeground(new Color(220, 190, 120));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(20));

        carregarLivros();

        scroll = new JScrollPane(painel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(18, 24, 38));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll);
        setVisible(true);
    }

    private void carregarLivros() {

        while (painel.getComponentCount() > 2) {
            painel.remove(painel.getComponentCount() - 1);
        }

        List<Livro> livros = LivrosLoader.carregarDoTxt(caminhoLivros);

        if (livros.isEmpty()) {
            JLabel vazio = new JLabel("Nenhum livro encontrado.");
            vazio.setFont(new Font("SansSerif", Font.PLAIN, 13));
            vazio.setForeground(new Color(150, 160, 180));
            vazio.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(vazio);
        } else {
            for (Livro l : livros) {
                painel.add(criarCardLivro(l));
                painel.add(Box.createVerticalStrut(10));
            }
        }

        painel.revalidate();
        painel.repaint();
    }

    private void apagarLivro(Livro livro) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apagar \"" + livro.getTitulo() + "\"?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminhoLivros));
            List<String> novasLinhas = linhas.stream()
                    .filter(l -> {
                        if (l.isBlank() || l.startsWith("#")) return true;
                        String[] p = l.split(";");
                        return p.length < 1 || !p[0].trim().equals(String.valueOf(livro.getId()));
                    })
                    .collect(Collectors.toList());

            Files.write(Paths.get(caminhoLivros), novasLinhas);
            carregarLivros();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao apagar: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel criarCardLivro(Livro l) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(30, 38, 55));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 72, 100), 1),
                new EmptyBorder(12, 16, 12, 16)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, isBibliotecario ? 140 : 120));

        JLabel tituloLivro = new JLabel(l.getTitulo());
        tituloLivro.setFont(new Font("Georgia", Font.BOLD, 15));
        tituloLivro.setForeground(new Color(220, 190, 120));

        JLabel autor = new JLabel("Autor: " + l.getAutor());
        autor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        autor.setForeground(new Color(200, 210, 230));

        JLabel editora = new JLabel("Editora: " + l.getEditora());
        editora.setFont(new Font("SansSerif", Font.PLAIN, 12));
        editora.setForeground(new Color(150, 160, 180));

        JLabel categoria = new JLabel("Categoria: " + l.getCategoria() + "  •  ID: " + l.getId());
        categoria.setFont(new Font("SansSerif", Font.PLAIN, 11));
        categoria.setForeground(new Color(100, 120, 150));

        card.add(tituloLivro);
        card.add(Box.createVerticalStrut(4));
        card.add(autor);
        card.add(Box.createVerticalStrut(2));
        card.add(editora);
        card.add(Box.createVerticalStrut(2));
        card.add(categoria);

        if (isBibliotecario) {
            card.add(Box.createVerticalStrut(8));

            JButton btnApagar = new JButton("Apagar");
            btnApagar.setFont(new Font("SansSerif", Font.BOLD, 11));
            btnApagar.setBackground(new Color(180, 60, 60));
            btnApagar.setForeground(Color.WHITE);
            btnApagar.setFocusPainted(false);
            btnApagar.setBorderPainted(false);
            btnApagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnApagar.setMaximumSize(new Dimension(80, 28));
            btnApagar.addActionListener(e -> apagarLivro(l));

            card.add(btnApagar);
        }

        return card;
    }
}