package model;

import model.enums.StatusExemplar;

public class Exemplar {
    private int id;
    private String codigo;
    private StatusExemplar status;
    private Reserva reserva;
    private Livro livro;

    public Exemplar(int id, String codigo, Reserva reserva, Livro livro){
        this.id = id;
        this.codigo = codigo;
        this.status = StatusExemplar.DISPONIVEL;
        this.reserva = reserva;
        this.livro = livro;
    }

    public int getId() {return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public void marcarcomoemprestado() {
        this.status = StatusExemplar.EMPRESTADO;
        System.out.println("Exemplar Emprestado.");
    }

    public void marcarcomodisponivel() {
        this.status = StatusExemplar.DISPONIVEL;
        System.out.println("Exemplar Disponível.");
    }
}
