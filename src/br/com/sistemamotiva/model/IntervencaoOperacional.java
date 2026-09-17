package br.com.sistemamotiva.model;
/*Esta classe é abstrata porque se trata de um termo genérico que não deve existir sozinho, visto 
  que não é possível instanciar um "Serviço Genérico" em campo no novo sistema, apenas ações reais 
  como Roçar ou Pulverizar.*/

public abstract class IntervencaoOperacional {
    public abstract void executarServico(TrechoRodovia trechoAlvo);
}