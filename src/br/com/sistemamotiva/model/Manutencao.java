package br.com.sistemamotiva.model;

/*Criando relacionamento/associação entre Manutencao + EquipeManutencao + TrechoRodovia
  + IntervencaoOperacional

  Para uma manutenção acontecer precisamos saber:
  - Data marcada
  - Equipe responsável
  - Local*/

public class Manutencao {
    private String data;
    private EquipeManutencao responsavel;
    private TrechoRodovia localizador;
    private IntervencaoOperacional servico; 

    public Manutencao(String data, EquipeManutencao responsavel, TrechoRodovia localizador, IntervencaoOperacional servico) {
        this.setData(data);
        this.responsavel = responsavel;
        this.localizador = localizador;
        this.setDefinirServico(servico);
    }

    public String getData() {
        return this.data;
    }

    private void setData(String data) {
        if (data != null) {
            this.data = data;
        } else {
            System.out.println("Erro! Por favor, defina uma data válida.");
        }
    }

    public EquipeManutencao getResponsavel() {
        return this.responsavel;
    }

    public TrechoRodovia getLocalizador() {
        return this.localizador;
    }

    public IntervencaoOperacional getDefinirServico() {
        return this.servico;
    }

    private void setDefinirServico(IntervencaoOperacional servicoRecomendado) {
        if (servicoRecomendado != null) {
            this.servico = servicoRecomendado;
        } else {
            System.out.println("Erro! O serviço de intervenção não pode ser nulo.");
        }
    }

    public void executarManutencao() {
        System.out.println("\n--- INICIANDO EXECUÇÃO DE ORDEM DE SERVIÇO ---");
        System.out.println("Data Planejada: " + this.data);
        this.responsavel.realizarTrabalho(this.servico, this.localizador);
    }
}