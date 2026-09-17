package br.com.sistemamotiva.model;

public class EquipeManutencao {
    private String identificadorEquipe;
    private int quantidadeMembros;

    public EquipeManutencao(String identificadorEquipe, int quantidadeMembros) {
        this.setIdentificadorEquipe(identificadorEquipe);
        this.setQuantidadeMembros(quantidadeMembros);
    }

    public String getIdentificadorEquipe() {
        return identificadorEquipe;
    }

    private void setIdentificadorEquipe(String nome) {
        if (nome != null) {
            this.identificadorEquipe = nome;
        } else {
            System.out.println("Erro! Informe o nome para que a equipe de manutenção seja identificada.");
        }
    }

    public int getQuantidadeMembros() {
        return quantidadeMembros;
    }

    private void setQuantidadeMembros(int quantidadeMembros) {
        if (quantidadeMembros >= 2) {
            this.quantidadeMembros = quantidadeMembros;
        } else {
            System.out.println("Erro! Por segurança, a equipe de manutenção deve ser composta por ao menos 2 membros.");
            this.quantidadeMembros = 2;
        }
    }

    /**
     * Realiza um serviço específico de intervenção operacional no trecho rodoviário designado.
     */
    public void realizarTrabalho(IntervencaoOperacional servico, TrechoRodovia trechoAlvo) {
        System.out.println("Equipe " + this.identificadorEquipe + " mobilizada para execução.");
        servico.executarServico(trechoAlvo);
    }
}