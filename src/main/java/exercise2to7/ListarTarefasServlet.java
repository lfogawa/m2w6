package exercise2to7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListarTarefasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String statusParam = request.getParameter("status");
        String priorityParam = request.getParameter("priority");
        String ownerParam = request.getParameter("owner");

        List<Tarefa> tarefasFiltradas = filtrarTarefas(statusParam, priorityParam, ownerParam);

        response.setContentType("application/json");

        String tarefasJson = JsonUtils.toJson(tarefasFiltradas);
        response.getWriter().write(tarefasJson);
    }

    private List<Tarefa> filtrarTarefas(String statusParam, String priorityParam, String ownerParam) {
        // Obtenha todas as tarefas do banco de dados fictício
        List<Tarefa> todasAsTarefas = BancoDeDadosTarefas.listarTarefas();

        // Aplicar filtros, se fornecidos
        List<Tarefa> tarefasFiltradas = todasAsTarefas.stream()
                .filter(tarefa -> (statusParam == null || tarefa.getStatus().name().equalsIgnoreCase(statusParam)))
                .filter(tarefa -> (priorityParam == null || tarefa.getPrioridade().name().equalsIgnoreCase(priorityParam)))
                .filter(tarefa -> (ownerParam == null || tarefa.getResponsavel().equalsIgnoreCase(ownerParam)))
                .collect(Collectors.toList());

        return tarefasFiltradas;
    }
}