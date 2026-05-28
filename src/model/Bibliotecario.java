package model;
import model.interfaces.Notificavel;

public class Bibliotecario extends Usuario implements Notificavel {
    private String senha;

    public Bibliotecario(int id, String nome, String email, String telefone, String senha) {
        super(id, nome, email, telefone);
        this.senha = senha;
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public void acessarCatalogo() {
        System.out.println("Bibliotecário acessando catálogo...");
    }

    @Override
    public void atualizarDados() {
        System.out.println("Bibliotecário atualizando dados...");
    }

    @Override
    public void receberNotificacao(String mensagem) {
        System.out.println("Bibliotecário recebendo notificação: " + mensagem);
    }

    public void cadastrarLivro(String titulo) {
        System.out.println("Bibliotecário cadastrando livro: " + titulo);
    }

    public void cadastrarUsuario(String nome) {
        System.out.println("Bibliotecário cadastrando usuário: " + nome);
    }

    public void registrarEmprestimo(String titulo) {
        System.out.println("Bibliotecário registrando empréstimo: " + titulo);
    }

    public void registrarDevolucao(String titulo) {
        System.out.println("Bibliotecário registrando devolução: " + titulo);
    }

    public void acessarPendencias() {
        System.out.println("Bibliotecário acessando pendências...");
    }

    public void gerarRelatorios() {
        System.out.println("Bibliotecário gerando relatórios...");
    }
}