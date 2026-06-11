package model;

public class Emprestimo {
    private String data;
    private String dataLimite;
    private Livro livro;
    private Cliente cliente;

    public Emprestimo(String data, String dataLimite, Livro livro, Cliente cliente) {
        this.data = data;
        this.dataLimite = dataLimite;
        this.livro = livro;
        this.cliente = cliente;
    }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getDataLimite() { return dataLimite; }
    public void setDataLimite(String dataLimite) { this.dataLimite = dataLimite; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}