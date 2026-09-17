package br.com.sistemamotiva.model;

public class EquipeManutencao {
    private Long id;
    private String identificadorEquipe;
    private int quantidadeMembros;

    public EquipeManutencao() {}

    public EquipeManutencao(String identificadorEquipe, int quantidadeMembros) {
        this.setIdentificadorEquipe(identificadorEquipe);
        this.setQuantidadeMembros(quantidadeMembros);
    }

    public EquipeManutencao(Long id, String identificadorEquipe, int quantidadeMembros) {
        this(identificadorEquipe, quantidadeMembros);
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificadorEquipe() { return identificadorEquipe; }

    public void setIdentificadorEquipe(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.identificadorEquipe = nome;
        } else {
            System.out.println("Erro! Informe o nome para que a equipe seja identificada.");
            this.identificadorEquipe = "EQUIPE-PADRAO";
        }
    }

    public int getQuantidadeMembros() { return quantidadeMembros; }

    public void setQuantidadeMembros(int quantidadeMembros) {
        if (quantidadeMembros >= 2) {
            this.quantidadeMembros = quantidadeMembros;
        } else {
            System.out.println("Erro! A equipe de manutenção deve ter ao menos 2 membros.");
            this.quantidadeMembros = 2;
        }
    }

    public void realizarTrabalho(IntervencaoOperacional servico, TrechoRodovia trechoAlvo) {
        System.out.println("Equipe " + this.identificadorEquipe + " mobilizada para execução.");
        servico.executarServico(trechoAlvo);
    }

    @Override
    public String toString() {
        return String.format("Equipe[id=%d, nome=%s, membros=%d]", id, identificadorEquipe, quantidadeMembros);
    }
}