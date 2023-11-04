package exercise2to7;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroTarefaServlet extends HttpServlet {

    private static final List<Tarefa> tarefas = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String conteudo = request.getReader().readLine();

        Tarefa tarefa = JsonUtils.fromJson(conteudo, Tarefa.class);

        int id = tarefas.size() + 1;
        tarefa.setId(id);

        tarefas.add(tarefa);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.getWriter().write(JsonUtils.toJson(tarefa));
    }

}