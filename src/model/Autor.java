package model;

public class Autor {

    private int id;
    private String nome;
    private String instagram;
    private String facebook;
    private String twitter;

    public Autor(int id, String nome, String instagram,
                 String facebook, String twitter) {
        this.id = id;
        this.nome = nome;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;}

    public int getId() {
        return id;}

    public void setId(int id) {
        this.id = id;}


    public String getNome() {
        return nome;}

    public void setNome(String nome) {
        this.nome = nome;}


    public String getInstagram() {
        return instagram;}

    public void setInstagram(String instagram) {
        this.instagram = instagram;}


    public String getFacebook() {
        return facebook;}

    public void setFacebook(String facebook) {
        this.facebook = facebook;}


    public String getTwitter() {
        return twitter;}

    public void setTwitter(String twitter) {
        this.twitter = twitter;}
}