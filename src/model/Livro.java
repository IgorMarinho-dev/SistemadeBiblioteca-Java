package model;

public class Livro {
    private int id;
    private String titulo;
    private Editora editora;
    private Autor autor;
    private Categoria categoria;

    public Livro(int id, String titulo, Editora editora,Autor autor,Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
        this.categoria = categoria;}

    public int getId() {
        return id;}
    public void setId(int id) {
        this.id = id;}

    public String getTitulo() {
        return titulo;}

    public void setTitulo(String titulo) {
        this.titulo = titulo;}


    public Editora getEditora() {
        return editora;}

    public void setEditora(Editora editora) {
        this.editora = editora;}

    public Autor getAutor() {
        return autor;}

    public void setAutor(Autor autor) {
        this.autor = autor;}

    public Categoria getCategoria() {
        return categoria;}

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;}
