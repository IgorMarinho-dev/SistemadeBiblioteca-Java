package model;

import model.Cliente;
import model.Emprestimo;
import model.Livro;
import model.LivrosLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TelaCliente extends JFrame {

    private final Cliente cliente;
    private final String caminhoEmprestimos = "src/model/Emprestimos.txt";
    private final String caminhoLivros = "src/model/Livros.txt";
    private JPanel painelEmprestimos;

    public TelaCliente(Cliente cliente) {
        this.cliente = cliente;
        construirTela();
    }

    private void construirTela() {
        setTitle("Biblioteca — Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setSize(520, 680);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(new Color(18, 24, 38));
        painelPrincipal.setBorder(new EmptyBorder(30, 40, 30, 40));


        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(new Color(18, 24, 38));
        painelTopo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel titulo = new JLabel("Boa leitura, " + cliente.getNome() + "!");
        titulo.setFont(new Font("Georgia", Font.BOLD, 22));
        titulo.setForeground(new Color(220, 190, 120));

        JButton btnAcervo = new JButton("Acervo");
        btnAcervo.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAcervo.setBackground(new Color(60, 72, 100));
        btnAcervo.setForeground(Color.WHITE);
        btnAcervo.setFocusPainted(false);
        btnAcervo.setBorderPainted(false);
        btnAcervo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAcervo.addActionListener(e -> new TelaAcervo(false));

        painelTopo.add(titulo, BorderLayout.WEST);
        painelTopo.add(btnAcervo, BorderLayout.EAST);


        JLabel labelNovoEmprestimo = criarLabel("Novo Empréstimo");
        labelNovoEmprestimo.setFont(new Font("Georgia", Font.BOLD, 16));
        labelNovoEmprestimo.setForeground(new Color(220, 190, 120));

        JLabel labelIdLivro = criarLabel("ID do Livro");
        JTextField campoIdLivro = new JTextField();
        estilizarCampo(campoIdLivro);

        JLabel labelData = criarLabel("Data (dd/MM/yyyy)");
        JTextField campoData = new JTextField();
        estilizarCampo(campoData);

        JLabel labelDataLimite = criarLabel("Data Limite (dd/MM/yyyy)");
        JTextField campoDataLimite = new JTextField();
        estilizarCampo(campoDataLimite);

        JLabel labelFeedback = new JLabel(" ");
        labelFeedback.setFont(new Font("SansSerif", Font.PLAIN, 12));
        labelFeedback.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnEmprestar = new JButton("Criar Empréstimo");
        btnEmprestar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnEmprestar.setBackground(new Color(220, 190, 120));
        btnEmprestar.setForeground(new Color(18, 24, 38));
        btnEmprestar.setFocusPainted(false);
        btnEmprestar.setBorderPainted(false);
        btnEmprestar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEmprestar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btnEmprestar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEmprestar.addActionListener(e -> {
            criarEmprestimo(campoIdLivro.getText().trim(),
                    campoData.getText().trim(),
                    campoDataLimite.getText().trim(),
                    labelFeedback);
            campoIdLivro.setText("");
            campoData.setText("");
            campoDataLimite.setText("");
        });


        JPanel painelHeaderEmprestimos = new JPanel(new BorderLayout());
        painelHeaderEmprestimos.setBackground(new Color(18, 24, 38));
        painelHeaderEmprestimos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel labelMeusEmprestimos = criarLabel("Meus Empréstimos");
        labelMeusEmprestimos.setFont(new Font("Georgia", Font.BOLD, 16));
        labelMeusEmprestimos.setForeground(new Color(220, 190, 120));

        JButton btnRefresh = new JButton("Atualizar");
        btnRefresh.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnRefresh.setBackground(new Color(60, 72, 100));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBorderPainted(false);
        btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(e -> carregarEmprestimos());

        painelHeaderEmprestimos.add(labelMeusEmprestimos, BorderLayout.WEST);
        painelHeaderEmprestimos.add(btnRefresh, BorderLayout.EAST);

        painelEmprestimos = new JPanel();
        painelEmprestimos.setLayout(new BoxLayout(painelEmprestimos, BoxLayout.Y_AXIS));
        painelEmprestimos.setBackground(new Color(18, 24, 38));

        carregarEmprestimos();


        painelPrincipal.add(painelTopo);
        painelPrincipal.add(Box.createVerticalStrut(24));
        painelPrincipal.add(labelNovoEmprestimo);
        painelPrincipal.add(Box.createVerticalStrut(12));
        painelPrincipal.add(labelIdLivro);
        painelPrincipal.add(Box.createVerticalStrut(6));
        painelPrincipal.add(campoIdLivro);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(labelData);
        painelPrincipal.add(Box.createVerticalStrut(6));
        painelPrincipal.add(campoData);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(labelDataLimite);
        painelPrincipal.add(Box.createVerticalStrut(6));
        painelPrincipal.add(campoDataLimite);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(btnEmprestar);
        painelPrincipal.add(Box.createVerticalStrut(6));
        painelPrincipal.add(labelFeedback);
        painelPrincipal.add(Box.createVerticalStrut(24));
        painelPrincipal.add(painelHeaderEmprestimos);
        painelPrincipal.add(Box.createVerticalStrut(12));
        painelPrincipal.add(painelEmprestimos);

        JScrollPane scroll = new JScrollPane(painelPrincipal);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(18, 24, 38));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll);
        setVisible(true);
    }

    private void criarEmprestimo(String idLivroTexto, String data, String dataLimite, JLabel labelFeedback) {
        if (idLivroTexto.isEmpty() || data.isEmpty() || dataLimite.isEmpty()) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("Preencha todos os campos.");
            return;
        }

        int idLivro;
        try {
            idLivro = Integer.parseInt(idLivroTexto);
        } catch (NumberFormatException e) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("ID do livro inválido.");
            return;
        }


        List<Livro> livros = LivrosLoader.carregarDoTxt(caminhoLivros);
        Livro livroEncontrado = livros.stream()
                .filter(l -> l.getId() == idLivro)
                .findFirst()
                .orElse(null);

        if (livroEncontrado == null) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("Livro não encontrado.");
            return;
        }


        String novaLinha = cliente.getId() + ";" + data + ";" + dataLimite + ";" + idLivro;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoEmprestimos, true))) {
            bw.newLine();
            bw.write(novaLinha);
            labelFeedback.setForeground(new Color(100, 200, 120));
            labelFeedback.setText("Empréstimo de \"" + livroEncontrado.getTitulo() + "\" criado!");
            carregarEmprestimos();
        } catch (IOException e) {
            labelFeedback.setForeground(new Color(220, 80, 80));
            labelFeedback.setText("Erro ao salvar: " + e.getMessage());
        }
    }

    private void carregarEmprestimos() {
        painelEmprestimos.removeAll();

        List<Livro> livros = LivrosLoader.carregarDoTxt(caminhoLivros);

        try {
            if (!Files.exists(Paths.get(caminhoEmprestimos))) {
                painelEmprestimos.revalidate();
                painelEmprestimos.repaint();
                return;
            }

            List<String> linhas = Files.readAllLines(Paths.get(caminhoEmprestimos));
            boolean algum = false;

            for (String linha : linhas) {
                if (linha.isBlank() || linha.startsWith("#")) continue;

                String[] p = linha.split(";");
                if (p.length != 4) continue;
                if (Integer.parseInt(p[0].trim()) != cliente.getId()) continue;

                String data       = p[1].trim();
                String dataLimite = p[2].trim();
                int idLivro       = Integer.parseInt(p[3].trim());

                Livro livro = livros.stream()
                        .filter(l -> l.getId() == idLivro)
                        .findFirst()
                        .orElse(null);

                String tituloLivro = livro != null ? livro.getTitulo() : "Livro #" + idLivro;

                painelEmprestimos.add(criarCardEmprestimo(linha, tituloLivro, data, dataLimite));
                painelEmprestimos.add(Box.createVerticalStrut(8));
                algum = true;
            }

            if (!algum) {
                JLabel vazio = new JLabel("Nenhum empréstimo encontrado.");
                vazio.setFont(new Font("SansSerif", Font.PLAIN, 13));
                vazio.setForeground(new Color(150, 160, 180));
                painelEmprestimos.add(vazio);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler empréstimos: " + e.getMessage());
        }

        painelEmprestimos.revalidate();
        painelEmprestimos.repaint();
        SwingUtilities.invokeLater(() -> {
            painelEmprestimos.revalidate();
            painelEmprestimos.repaint();
            getContentPane().revalidate();
            getContentPane().repaint();
        });
    }

    private void apagarEmprestimo(String linhaOriginal) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apagar este empréstimo?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminhoEmprestimos));
            List<String> novas = linhas.stream()
                    .filter(l -> !l.equals(linhaOriginal))
                    .collect(Collectors.toList());

            Files.write(Paths.get(caminhoEmprestimos), novas);
            carregarEmprestimos();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao apagar: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel criarCardEmprestimo(String linhaOriginal, String tituloLivro, String data, String dataLimite) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(30, 38, 55));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 72, 100), 1),
                new EmptyBorder(10, 14, 10, 14)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(new Color(30, 38, 55));

        JLabel labelTitulo = new JLabel(tituloLivro);
        labelTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        labelTitulo.setForeground(new Color(220, 190, 120));

        JLabel labelDatas = new JLabel("Retirado: " + data + "  •  Devolução: " + dataLimite);
        labelDatas.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelDatas.setForeground(new Color(150, 160, 180));

        info.add(labelTitulo);
        info.add(Box.createVerticalStrut(4));
        info.add(labelDatas);

        JButton btnApagar = new JButton("Apagar");
        btnApagar.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnApagar.setBackground(new Color(180, 60, 60));
        btnApagar.setForeground(Color.WHITE);
        btnApagar.setFocusPainted(false);
        btnApagar.setBorderPainted(false);
        btnApagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnApagar.addActionListener(e -> apagarEmprestimo(linhaOriginal));

        card.add(info, BorderLayout.CENTER);
        card.add(btnApagar, BorderLayout.EAST);

        return card;
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