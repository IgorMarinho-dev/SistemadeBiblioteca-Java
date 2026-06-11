package model;

import model.Bibliotecario;
import model.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaLogin extends JFrame {

    private final List<Bibliotecario> bibliotecarios;
    private final List<Cliente> clientes;

    private JTextField campoId;
    private JPasswordField campoSenha;
    private JRadioButton radioBibliotecario;
    private JRadioButton radioCliente;
    private JButton btnEntrar;
    private JLabel labelErro;

    public TelaLogin(List<Bibliotecario> bibliotecarios, List<Cliente> clientes) {
        this.bibliotecarios = bibliotecarios;
        this.clientes = clientes;
        construirTela();
    }

    private void construirTela() {
        setTitle("Biblioteca — Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 420);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(18, 24, 38));
        painel.setBorder(new EmptyBorder(40, 50, 40, 50));

        // Título
        JLabel titulo = new JLabel("Biblioteca");
        titulo.setFont(new Font("Georgia", Font.BOLD, 28));
        titulo.setForeground(new Color(220, 190, 120));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Acesse sua conta");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(150, 160, 180));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel labelId = criarLabel("ID");
        campoId = new JTextField();
        estilizarCampo(campoId);

        JLabel labelSenha = criarLabel("Senha");
        campoSenha = new JPasswordField();
        estilizarCampo(campoSenha);


        JLabel labelTipo = criarLabel("Entrar como");
        radioBibliotecario = new JRadioButton("Bibliotecário");
        radioCliente = new JRadioButton("Cliente");

        estilizarRadio(radioBibliotecario);
        estilizarRadio(radioCliente);
        radioBibliotecario.setSelected(true);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioBibliotecario);
        grupo.add(radioCliente);

        JPanel painelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelRadio.setBackground(new Color(18, 24, 38));
        painelRadio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        painelRadio.add(radioBibliotecario);
        painelRadio.add(Box.createHorizontalStrut(16));
        painelRadio.add(radioCliente);

        // Botão d entrar
        btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(220, 190, 120));
        btnEntrar.setForeground(new Color(18, 24, 38));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.addActionListener(e -> realizarLogin());

        // Label erro
        labelErro = new JLabel(" ");
        labelErro.setFont(new Font("SansSerif", Font.PLAIN, 12));
        labelErro.setForeground(new Color(220, 80, 80));
        labelErro.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Montagem
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(4));
        painel.add(subtitulo);
        painel.add(Box.createVerticalStrut(28));
        painel.add(labelId);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoId);
        painel.add(Box.createVerticalStrut(16));
        painel.add(labelSenha);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoSenha);
        painel.add(Box.createVerticalStrut(16));
        painel.add(labelTipo);
        painel.add(Box.createVerticalStrut(6));
        painel.add(painelRadio);
        painel.add(Box.createVerticalStrut(24));
        painel.add(btnEntrar);
        painel.add(Box.createVerticalStrut(10));
        painel.add(labelErro);

        add(painel);
        setVisible(true);
    }

    private void realizarLogin() {
        String idTexto = campoId.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();

        if (idTexto.isEmpty() || senha.isEmpty()) {
            labelErro.setText("Preencha todos os campos.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            labelErro.setText("ID inválido.");
            return;
        }

        if (radioBibliotecario.isSelected()) {
            for (Bibliotecario b : bibliotecarios) {
                if (b.getId() == id && b.getSenha().equals(senha)) {
                    JOptionPane.showMessageDialog(this,
                            "Bem-vindo, " + b.getNome() + "!",
                            "Login realizado", JOptionPane.INFORMATION_MESSAGE);
                    labelErro.setText(" ");
                    // Aqui eu vou botar a futura janela
                    return;
                }
            }
        } else {
            for (Cliente c : clientes) {
                if (c.getId() == id) {
                    JOptionPane.showMessageDialog(this,
                            "Bem-vindo, " + c.getNome() + "!",
                            "Login realizado", JOptionPane.INFORMATION_MESSAGE);
                    labelErro.setText(" ");
                    // Aqui tbm
                    return;
                }
            }
        }

        labelErro.setText("ID ou senha incorretos.");
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

    private void estilizarRadio(JRadioButton radio) {
        radio.setFont(new Font("SansSerif", Font.PLAIN, 13));
        radio.setForeground(new Color(200, 210, 230));
        radio.setBackground(new Color(18, 24, 38));
        radio.setFocusPainted(false);
    }
}