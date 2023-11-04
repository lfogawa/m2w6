package exercise2to7;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosTarefas {
    private static List<Tarefa> listaDeTarefas = new ArrayList<>();

    public static void salvarTarefa(Tarefa tarefa) {
        listaDeTarefas.add(tarefa);
    }

    public static void removerTarefa(int id) {
        listaDeTarefas.removeIf(tarefa -> tarefa.getId() == id);
    }

    public static List<Tarefa> listarTarefas() {
        return listaDeTarefas;
    }

    public static List<Tarefa> listarTarefasPorStatus(Tarefa.Status status) {
        List<Tarefa> tarefasPorStatus = new ArrayList<>();
        for (Tarefa tarefa : listaDeTarefas) {
            if (tarefa.getStatus() == status) {
                tarefasPorStatus.add(tarefa);
            }
        }
        return tarefasPorStatus;
    }

    public static List<Tarefa> listarTarefasPorPrioridade(Tarefa.Prioridade prioridade) {
        List<Tarefa> tarefasPorPrioridade = new ArrayList<>();
        for (Tarefa tarefa : listaDeTarefas) {
            if (tarefa.getPrioridade() == prioridade) {
                tarefasPorPrioridade.add(tarefa);
            }
        }
        return tarefasPorPrioridade;
    }

    public static List<Tarefa> listarTarefasPorResponsavel(String responsavel) {
        List<Tarefa> tarefasPorResponsavel = new ArrayList<>();
        for (Tarefa tarefa : listaDeTarefas) {
            if (tarefa.getResponsavel().equalsIgnoreCase(responsavel)) {
                tarefasPorResponsavel.add(tarefa);
            }
        }
        return tarefasPorResponsavel;
    }
}
