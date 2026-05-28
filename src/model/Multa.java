package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Multa {
    private int id;
    private BigDecimal valor;
    private LocalDate dataGeracao;
    private boolean paga;
    private Emprestimo emprestimo;

    public Multa(int id, BigDecimal valor, LocalDate dataGeracao, Emprestimo emprestimo) {
        this.id = id;
        this.valor = valor;
        this.dataGeracao = dataGeracao;
        this.paga = false;
        this.emprestimo = emprestimo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDate dataGeracao) { this.dataGeracao = dataGeracao; }

    public boolean isPaga() { return paga; }
    public void setPaga(boolean paga) { this.paga = paga; }

    public Emprestimo getEmprestimo() { return emprestimo; }
    public void setEmprestimo(Emprestimo emprestimo) { this.emprestimo = emprestimo; }

    public void marcarComoPaga() {
        this.paga = true;
        System.out.println("Multa marcada como paga.");
    }
}