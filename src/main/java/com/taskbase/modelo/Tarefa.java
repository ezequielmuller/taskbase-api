package com.taskbase.modelo;

import java.sql.Timestamp;

public class Tarefa {
  private Integer ta_id;
  private Timestamp ta_data_criacao;
  private Timestamp ta_data_finalizacao;
  private String ta_titulo;
  private String ta_descricao;
  private Integer fk_usuario;
  private Integer fk_grupo;

  public Tarefa(Integer ta_id, Timestamp ta_data_criacao, Timestamp ta_data_finalizacao, String ta_titulo,
                String ta_descricao, Integer fk_usuario, Integer fk_grupo) {
    this.ta_id = ta_id;
    this.ta_data_criacao = ta_data_criacao;
    this.ta_data_finalizacao = ta_data_finalizacao;
    this.ta_titulo = ta_titulo;
    this.ta_descricao = ta_descricao;
    this.fk_usuario = fk_usuario;
    this.fk_grupo = fk_grupo;
  }

  public Integer getTa_id() {
    return ta_id;
  }

  public void setTa_id(Integer ta_id) {
    this.ta_id = ta_id;
  }

  public Timestamp getTa_data_criacao() {
    return ta_data_criacao;
  }

  public void setTa_data_criacao(Timestamp ta_data_criacao) {
    this.ta_data_criacao = ta_data_criacao;
  }

  public Timestamp getTa_data_finalizacao() {
    return ta_data_finalizacao;
  }

  public void setTa_data_finalizacao(Timestamp ta_data_finalizacao) {
    this.ta_data_finalizacao = ta_data_finalizacao;
  }

  public String getTa_titulo() {
    return ta_titulo;
  }

  public void setTa_titulo(String ta_titulo) {
    this.ta_titulo = ta_titulo;
  }

  public String getTa_descricao() {
    return ta_descricao;
  }

  public void setTa_descricao(String ta_descricao) {
    this.ta_descricao = ta_descricao;
  }

  public Integer getFk_usuario() {
    return fk_usuario;
  }

  public void setFk_usuario(Integer fk_usuario) {
    this.fk_usuario = fk_usuario;
  }

  public Integer getFk_grupo() {
    return fk_grupo;
  }

  public void setFk_grupo(Integer fk_grupo) {
    this.fk_grupo = fk_grupo;
  }
}
