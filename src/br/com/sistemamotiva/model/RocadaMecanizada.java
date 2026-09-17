package br.com.sistemamotiva.model;

public class RocadaMecanizada extends IntervencaoOperacional {

    @Override
    public void executarServico(TrechoRodovia trechoAlvo) {
        trechoAlvo.atualizarNivelVegetacao(5.0);
        System.out.println("[Serviço] Roçada Mecanizada concluída no trecho: " 
            + trechoAlvo.getIdentificador().getCodigoIdentificacao());
    }
}