package com.taskbase.modelo;

public class Usuario {
  private Integer usu_id;
  private String usu_nome;
  private String usu_email;
  private String usu_senha;
  private Boolean usu_gerenciador;

  public Usuario(Integer usu_id, String usu_nome, String usu_email, String usu_senha, Boolean usu_gerenciador) {
    this.usu_id = usu_id;
    this.usu_nome = usu_nome;
    this.usu_email = usu_email;
    this.usu_senha = usu_senha;
    this.usu_gerenciador = usu_gerenciador;
  }

  public Integer getUsu_id() {
    return usu_id;
  }

  public void setUsu_id(Integer usu_id) {
    this.usu_id = usu_id;
  }

  public String getUsu_nome() {
    return usu_nome;
  }

  public void setUsu_nome(String usu_nome) {
    this.usu_nome = usu_nome;
  }

  public String getUsu_email() {
    return usu_email;
  }

  public void setUsu_email(String usu_email) {
    this.usu_email = usu_email;
  }

  public String getUsu_senha() {
    return usu_senha;
  }

  public void setUsu_senha(String usu_senha) {
    this.usu_senha = usu_senha;
  }

  public Boolean getUsu_gerenciador() {
    return usu_gerenciador;
  }

  public void setUsu_gerenciador(Boolean usu_gerenciador) {
    this.usu_gerenciador = usu_gerenciador;
  }
}
