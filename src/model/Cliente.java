package model;

import model.interfaces.Notificavel;
import exception.ReservaJaExistenteException;
import exception.MultaEmAbertoException;
import exception.LivroJaDevolvidoException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Cliente extends Usuario implements Notificavel {
    private String endereco;
    private LocalDate dataNascimento;
    private List<Reserva> reservas;
    private List<Emprestimo> historico;
    private List<Multa> multas;

    public Cliente(int id, String nome, String email, String telefone, String endereco, LocalDate dataNascimento) {
        super(id, nome, email, telefone);
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.reservas = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.multas = new ArrayList<>();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Emprestimo> getHistorico() {
        return historico;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void adicionarMulta(Multa multa) {
        multas.add(multa);
    }

    @Override
    public void acessarCatalogo() {
        System.out.println("Cliente acessando catálogo...");
    }

    @Override
    public void atualizarDados() {
        System.out.println("Cliente atualizando dados...");
    }

    @Override
    public void receberNotificacao(String mensagem) {
        System.out.println("Cliente recebendo notificação: " + mensagem);
    }

    public void comprarLivro(String titulo) {
        System.out.println("Cliente comprando livro: " + titulo);
    }

    public void alugarLivro(String titulo) {
        System.out.println("Cliente alugando livro: " + titulo);
    }

    public void devolverLivro(String titulo) throws LivroJaDevolvidoException {
        for (Emprestimo e : historico) {
            if (e.getDataDevolucaoReal() != null) {
                throw new LivroJaDevolvidoException(
                        "O livro '" + titulo + "' já foi devolvido."
                );
            }
        }
        System.out.println("Cliente devolvendo livro: " + titulo);
    }

    public void visualizarHistorico() {
        System.out.println("Cliente visualizando histórico...");
    }

    public void reservarLivro(String titulo) throws ReservaJaExistenteException {
        if (!reservas.isEmpty()) {
            throw new ReservaJaExistenteException(
                    "O cliente já possui uma reserva cadastrada."
            );
        }
        System.out.println("Cliente reservando livro: " + titulo);
    }

    public void realizarEmprestimo(String titulo) throws MultaEmAbertoException {
        for (Multa m : multas) {
            if (!m.isPaga()) {
                throw new MultaEmAbertoException(
                        "O cliente possui multa em aberto. Quite o débito antes de realizar novos empréstimos."
                );
            }
        }
        System.out.println("Cliente realizando empréstimo: " + titulo);
    }
}