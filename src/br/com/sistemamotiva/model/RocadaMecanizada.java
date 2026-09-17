package br.com.sistemamotiva.model;

public class RocadaMecanizada extends IntervencaoOperacionalDAO {

    @Override
    public void executarServico(TrechoRodoviaDAO trechoAlvo) {
        trechoAlvo.atualizarNivelVegetacao(5.0);
        System.out.println("[Serviço] Roçada Mecanizada concluída no trecho: " 
            + trechoAlvo.getIdentificador().getCodigoIdentificacao());
    }
}