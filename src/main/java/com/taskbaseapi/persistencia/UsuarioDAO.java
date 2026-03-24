package com.taskbaseapi.persistencia;

import com.taskbaseapi.modelo.Usuario;

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
                  rs.getString("usu_email"), rs.getString("usu_senha")
          );

          responseUsuario.add(rsUsuario);
        }
      }
    }
    return responseUsuario;
  }

  private static final String LISTAR_USUARIO_POR_ID = """
          SELECT * FROM usuario WHERE usu_id = ?
          """;

  public Usuario listarUsuarioPorId(Integer usuarioId) throws SQLException {
    try (Connection con = conectar()) {
      con.setAutoCommit(false);
      Usuario usuarioEncontrado = new Usuario();
      try (PreparedStatement ps = con.prepareStatement(LISTAR_USUARIO_POR_ID)) {
        ps.setInt(1, usuarioId);

        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            usuarioEncontrado.setUsu_id(rs.getInt("usu_id"));
            usuarioEncontrado.setUsu_nome(rs.getString("usu_nome"));
            usuarioEncontrado.setUsu_email(rs.getString("usu_email"));
          }
        }
      } catch (SQLException ex) {
        con.rollback();
        throw ex;
      }

      return usuarioEncontrado;
    }
  }

  private static final String GRAVAR_USUARIO = """
          INSERT INTO usuario(usu_nome, usu_email, usu_senha) VALUES (?, ?, ?) RETURNING usu_id
          """;

  public Usuario gravarUsuario(String nome, String email, String senha) throws SQLException {
    try (Connection con = conectar()) {
      con.setAutoCommit(false);
      Usuario usuarioCadastrado = new Usuario(0, nome, email, senha);

      try (PreparedStatement ps = con.prepareStatement(GRAVAR_USUARIO)) {
        ps.setString(1, usuarioCadastrado.getUsu_nome());
        ps.setString(2, usuarioCadastrado.getUsu_email());
        ps.setString(3, usuarioCadastrado.getUsu_senha());

        try (ResultSet rs = ps.executeQuery()) {
          if (rs.next()) {
            int usuarioId = rs.getInt("usu_id");
            usuarioCadastrado.setUsu_id(usuarioId);
          }
        }

        con.commit();
      } catch (SQLException ex) {
        con.rollback();
        throw ex;
      }
      return usuarioCadastrado;
    }
  }

  private static final String EDITAR_USUARIO = """
          UPDATE usuario SET usu_nome = ?, usu_email = ?, usu_senha = ? WHERE usu_id = ?
          """;

  public Usuario editarUsuario(String nome, String email, String senha, Integer usuarioId) throws SQLException {
    try (Connection con = conectar()) {
      con.setAutoCommit(false);
      Usuario usuarioEditado = new Usuario(usuarioId, nome, email, senha);

      try (PreparedStatement ps = con.prepareStatement(EDITAR_USUARIO)) {
        ps.setString(1, usuarioEditado.getUsu_nome());
        ps.setString(2, usuarioEditado.getUsu_email());
        ps.setString(3, usuarioEditado.getUsu_senha());
        ps.setInt(4, usuarioEditado.getUsu_id());
        ps.executeUpdate();

        con.commit();
      } catch (SQLException ex) {
        con.rollback();
        throw ex;
      }

      return usuarioEditado;
    }
  }

  private static final String DELETAR_USUARIO = """
          DELETE FROM usuario WHERE usu_id = ?
          """;

  public String deletarUsuario(Integer usuarioId) throws SQLException {
    try (Connection con = conectar()) {
      con.setAutoCommit(false);
      String resposta = "";

      try (PreparedStatement ps = con.prepareStatement(DELETAR_USUARIO)) {
        ps.setInt(1, usuarioId);
        ps.executeUpdate();

        resposta = "Usuário deletado com sucesso!";
        con.commit();
      } catch (SQLException ex) {
        con.rollback();
        throw ex;
      }

      return resposta;
    }
  }
}