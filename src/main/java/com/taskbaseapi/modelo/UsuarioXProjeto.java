package com.taskbaseapi.modelo;

public class UsuarioXProjeto {
  private Integer fk_usuario;
  private Integer fk_projeto;
  private Boolean up_gerenciador;

  public UsuarioXProjeto(Integer fk_usuario, Integer fk_projeto, Boolean up_gerenciador) {
    this.fk_usuario = fk_usuario;
    this.fk_projeto = fk_projeto;
    this.up_gerenciador = up_gerenciador;
  }

  public Integer getFk_usuario() {
    return fk_usuario;
  }

  public void setFk_usuario(Integer fk_usuario) {
    this.fk_usuario = fk_usuario;
  }

  public Integer getFk_projeto() {
    return fk_projeto;
  }

  public void setFk_projeto(Integer fk_projeto) {
    this.fk_projeto = fk_projeto;
  }

  public Boolean getUp_gerenciador(){
    return up_gerenciador;
  }

  public void setUp_gerenciador(Boolean up_gerenciador){
    this.up_gerenciador = up_gerenciador;
  }
}
