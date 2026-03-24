package com.taskbaseapi.servlet;

import com.taskbaseapi.modelo.Usuario;
import com.taskbaseapi.persistencia.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/usuario/*")
public class UsuarioServlet extends BaseServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String pathInfo = request.getPathInfo();

    try {
      // se for /usuario/1 busca pelo id
      if (pathInfo != null && pathInfo.matches("/\\d+")) {
        Integer usuarioId = Integer.parseInt(pathInfo.substring(1));
        Usuario usuarioEncontrado = UsuarioDAO.getInstance().listarUsuarioPorId(usuarioId);
        retorno(response, HttpServletResponse.SC_OK, new JSONObject(usuarioEncontrado));
        return;
      } else {
        try {
          List<Usuario> usuariosList = UsuarioDAO.getInstance().listarUsuarios();
          retorno(response, HttpServletResponse.SC_OK, new JSONArray(usuariosList));
        } catch (Exception ex) {
          retornarErro(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
      }
//      switch (pathInfo) {
//        case "/": {
//
//          break;
//        }
//
//        default: {
//          retornarErro(response, HttpServletResponse.SC_BAD_REQUEST, "Rota inválida!");
//          break;
//        }
//      }
    } catch (Exception ex) {
      retornarErro(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    JSONObject json = lerBody(request);
    try {
      Usuario novoUsuario = UsuarioDAO.getInstance().gravarUsuario(json.getString("nome"), json.getString("email"), json.getString("senha"));

      retorno(response, HttpServletResponse.SC_CREATED, new JSONObject(novoUsuario));
    } catch (Exception ex) {
      retornarErro(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    JSONObject json = lerBody(request);

    try {
      Integer usuarioId = Integer.parseInt(request.getPathInfo().substring(1));

      Usuario editarUsuario = UsuarioDAO.getInstance().editarUsuario(
              json.getString("nome"), json.getString("email"),
              json.getString("senha"), usuarioId
      );

      retorno(response, HttpServletResponse.SC_OK, new JSONObject(editarUsuario));
    } catch (Exception ex) {
      retornarErro(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    try {
      Integer usuarioId = Integer.parseInt(request.getPathInfo().substring(1));

      String resposta = UsuarioDAO.getInstance().deletarUsuario(usuarioId);
      retorno(response, HttpServletResponse.SC_OK, resposta);
    } catch (Exception ex) {
      retornarErro(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }
}