package exercise2to7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RemoverTarefaServlet extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupere o identificador da tarefa a ser removida da URL
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        // Encontre a tarefa a ser removida no banco de dados fictício
        Tarefa tarefaParaRemover = encontrarTarefa(taskId);

        if (tarefaParaRemover != null) {
            // Remova a tarefa do banco de dados fictício
            BancoDeDadosTarefas.removerTarefa(taskId);

            // Responda com um código 204 (No Content) para indicar sucesso sem conteúdo
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            // Tarefa não encontrada, retorne um erro 404 (Not Found)
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tarefa não encontrada.");
        }
    }

    private Tarefa encontrarTarefa(int taskId) {
        // Encontre a tarefa com o identificador especificado no banco de dados fictício
        List<Tarefa> todasAsTarefas = BancoDeDadosTarefas.listarTarefas();
        return todasAsTarefas.stream()
                .filter(tarefa -> tarefa.getId() == taskId)
                .findFirst()
                .orElse(null);
    }
}