package br.com.sistemamotiva.model;

public class Pulverizacao extends IntervencaoOperacionalDAO {

    @Override
    public void executarServico(TrechoRodoviaDAO trechoAlvo) {
        // Pulverização química preventiva reduz o nível de vegetação para 8cm
        trechoAlvo.atualizarNivelVegetacao(8.0);
        System.out.println("[Serviço] Pulverização Química de inibição concluída no trecho: " 
            + trechoAlvo.getIdentificador().getCodigoIdentificacao());
    }
}