package com.taskflow.modelo;

import java.sql.Timestamp;

public class Projeto {
  private Integer pro_id;
  private String pro_nome;
  private String pro_descricao;
  private Timestamp pro_data_criacao;

  public Projeto(Integer pro_id, String pro_nome, String pro_descricao, Timestamp pro_data_criacao) {
    this.pro_id = pro_id;
    this.pro_nome = pro_nome;
    this.pro_descricao = pro_descricao;
    this.pro_data_criacao = pro_data_criacao;
  }

  public Integer getPro_id() {
    return pro_id;
  }

  public void setPro_id(Integer pro_id) {
    this.pro_id = pro_id;
  }

  public String getPro_nome() {
    return pro_nome;
  }

  public void setPro_nome(String pro_nome) {
    this.pro_nome = pro_nome;
  }

  public String getPro_descricao() {
    return pro_descricao;
  }

  public void setPro_descricao(String pro_descricao) {
    this.pro_descricao = pro_descricao;
  }

  public Timestamp getPro_data_criacao() {
    return pro_data_criacao;
  }

  public void setPro_data_criacao(Timestamp pro_data_criacao) {
    this.pro_data_criacao = pro_data_criacao;
  }
}
