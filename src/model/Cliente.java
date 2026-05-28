package model;

import model.interfaces.Notificavel;
import exception.ReservaJaExistenteException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Cliente extends Usuario implements Notificavel {
    private String endereco;
    private LocalDate dataNascimento;
    private List<Reserva> reservas;
    private List<Emprestimo> historico;

    public Cliente(int id, String nome, String email, String telefone, String endereco, LocalDate dataNascimento) {
        super(id, nome, email, telefone);
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.reservas = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public List<Reserva> getReservas() { return reservas; }

    public List<Emprestimo> getHistorico() { return historico; }

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

    public void devolverLivro(String titulo) {
        System.out.println("Cliente devolvendo livro: " + titulo);
    }

    public void visualizarHistorico() {
        System.out.println("Cliente visualizando histórico...");
    }

    public void reservarLivro(String titulo) throws ReservaJaExistenteException {
        System.out.println("Cliente reservando livro: " + titulo);
    }
}