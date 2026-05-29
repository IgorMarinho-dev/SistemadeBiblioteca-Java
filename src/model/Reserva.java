package model;

import model.enums.StatusReserva;
import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private LocalDateTime dataReserva;
    private StatusReserva status;
    private Cliente cliente;
    private Bibliotecario bibliotecario;

    public Reserva(int id, LocalDateTime dataReserva, Cliente cliente, Bibliotecario bibliotecario) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.status = StatusReserva.ATIVA;
        this.cliente = cliente;
        this.bibliotecario = bibliotecario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDateTime dataReserva) { this.dataReserva = dataReserva; }

    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Bibliotecario getBibliotecario() { return bibliotecario; }
    public void setBibliotecario(Bibliotecario bibliotecario) { this.bibliotecario = bibliotecario; }

    public void cancelarReserva() {
        this.status = StatusReserva.CANCELADA;
        System.out.println("Reserva cancelada.");
    }
}