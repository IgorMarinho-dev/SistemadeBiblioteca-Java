package model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private LocalDateTime dataReserva;
    private LocalDate dataExpiracao;
    private LocalDate dataDisponivel;
    private String status;
    private Cliente cliente;
    private String tituloLivro;

    public Reserva(int id, LocalDateTime dataReserva, LocalDate dataExpiracao, Cliente cliente, String tituloLivro) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.dataExpiracao = dataExpiracao;
        this.dataDisponivel = null;
        this.status = "ativa";
        this.cliente = cliente;
        this.tituloLivro = tituloLivro;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDateTime dataReserva) { this.dataReserva = dataReserva; }

    public LocalDate getDataExpiracao() { return dataExpiracao; }
    public void setDataExpiracao(LocalDate dataExpiracao) { this.dataExpiracao = dataExpiracao; }

    public LocalDate getDataDisponivel() { return dataDisponivel; }
    public void setDataDisponivel(LocalDate dataDisponivel) { this.dataDisponivel = dataDisponivel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getTituloLivro() { return tituloLivro; }
    public void setTituloLivro(String tituloLivro) { this.tituloLivro = tituloLivro; }

    public void cancelarReserva() {
        this.status = "cancelada";
        System.out.println("Reserva cancelada.");
    }

    public void concluirReserva() {
        this.status = "concluida";
        System.out.println("Reserva concluída.");
    }

    public void marcarDisponivel() {
        this.dataDisponivel = LocalDate.now();
        System.out.println("Livro disponível para retirada.");
    }

    public void verificarExpiracao() {
        if (status.equals("ativa") && LocalDate.now().isAfter(dataExpiracao)) {
            this.status = "expirada";
            System.out.println("Reserva expirada.");
        }
    }

    public boolean isAtiva() {
        return "ativa".equals(status);
    }

    public boolean isExpirada() {
        return "expirada".equals(status);
    }
}