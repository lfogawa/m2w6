package exercise2to7;

public class Tarefa {
    private int id;
    private String descricao;
    private Status status;
    private Prioridade prioridade;
    private String responsavel;

    public Tarefa(int id, String descricao, Status status, Prioridade prioridade, String responsavel) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.responsavel = responsavel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

enum Status {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDA
}

enum Prioridade {
    BAIXA,
    MEDIA,
    ALTA
}
}
