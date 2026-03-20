package com.taskbase.servlet;

import com.taskbase.modelo.Usuario;
import com.taskbase.persistencia.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      List<Usuario> usuariosList = UsuarioDAO.getInstance().listarUsuarios();
      JSONArray jsonArray = new JSONArray(usuariosList);

      retorno(response, jsonArray.toString());
    } catch (Exception ex) {
      JSONObject erro = new JSONObject();
      erro.put("status", 500);
      erro.put("mensagem", ex.getMessage());
      retorno(response, erro);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    try {
      StringBuilder sb = new StringBuilder();
      String linha;
      while ((linha = request.getReader().readLine()) != null) {
        sb.append(linha);
      }

      JSONObject json = new JSONObject(sb.toString());

      Usuario novoUsuario = UsuarioDAO.getInstance().gravarUsuario(
              json.getString("nome"),
              json.getString("email"),
              json.getString("senha"),
              json.getBoolean("gerenciador")
      );

      JSONObject resposta = new JSONObject();
      resposta.put("mensagem", "Usuário criado");
      resposta.put("novoUsuario", novoUsuario);

      retorno(response, resposta.toString());

    } catch (Exception ex) {
      JSONObject erro = new JSONObject();
      erro.put("status", 500);
      erro.put("mensagem", ex.getMessage());
      retorno(response, erro);
    }
  }

  // formata para JSON o retorno da api
  private void retorno(HttpServletResponse response, Object obj) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    if (obj instanceof String) {
      response.getWriter().write((String) obj);
    } else {
      response.getWriter().write(obj.toString());
    }
  }
}