package com.taskbase.persistencia;

import com.taskbase.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Conexao {

  private static UsuarioDAO instance;

  public static UsuarioDAO getInstance() {
    if (instance == null) {
      instance = new UsuarioDAO();
    }
    return instance;
  }

  private static final String LISTAR_USUARIO = "select * from usuario";

  public List<Usuario> listarUsuarios() throws SQLException {
    List<Usuario> responseUsuario = new ArrayList<>();

    try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(LISTAR_USUARIO)) {
      try (ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
          Usuario rsUsuario = new Usuario(
                  rs.getInt("usu_id"), rs.getString("usu_nome"),
                  rs.getString("usu_email"), rs.getString("usu_senha"),
                  rs.getBoolean("usu_gerenciador")
          );

          responseUsuario.add(rsUsuario);
        }
      }
    }
    return responseUsuario;
  }

  private static final String GRAVAR_USUARIO = "insert into usuario(usu_nome, usu_email, usu_senha, usu_gerenciador) " +
          "values (?, ?, ?, ?) RETURNING usu_id";

  public Usuario gravarUsuario(String nome, String email, String senha, Boolean gerenciador) throws SQLException {
    try (Connection con = conectar()) {
      Usuario novoUsuario = new Usuario(0, nome, email, senha, gerenciador);
      con.setAutoCommit(false);

      try (PreparedStatement ps = con.prepareStatement(GRAVAR_USUARIO)) {
        ps.setString(1, novoUsuario.getUsu_nome());
        ps.setString(2, novoUsuario.getUsu_email());
        ps.setString(3, novoUsuario.getUsu_senha());
        ps.setBoolean(4, novoUsuario.getUsu_gerenciador());

        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            int usuarioId = rs.getInt("usu_id");
            novoUsuario.setUsu_id(usuarioId);
          }
        }

        con.commit();
      } catch (SQLException ex) {
        con.rollback();
        throw ex;
      }
      return novoUsuario;
    }
  }


}