package exercise2to7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AlterarTarefaServlet extends HttpServlet {
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        StringBuilder jsonBuffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(jsonBuffer.toString());
            JSONObject jsonObject = (JSONObject) obj;

            Tarefa tarefaExistente = encontrarTarefa(taskId);

            if (tarefaExistente != null) {
                String descricao = (String) jsonObject.get("descricao");
                String responsavel = (String) jsonObject.get("responsavel");
                Status status = Status.valueOf((String) jsonObject.get("status"));
                Prioridade prioridade = Prioridade.valueOf((String) jsonObject.get("prioridade"));

                tarefaExistente.setDescricao(descricao);
                tarefaExistente.setResponsavel(responsavel);
                tarefaExistente.setStatus(status);
                tarefaExistente.setPrioridade(prioridade);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(JsonUtils.toJson(tarefaExistente));
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tarefa não encontrada.");
            }
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro na requisição JSON.");
        }
    }

    private Tarefa encontrarTarefa(int taskId) {
        List<Tarefa> todasAsTarefas = BancoDeDadosTarefas.listarTarefas();
        return todasAsTarefas.stream()
                .filter(tarefa -> tarefa.getId() == taskId)
                .findFirst()
                .orElse(null);
    }
}