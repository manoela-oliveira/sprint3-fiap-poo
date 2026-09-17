package br.com.sistemamotiva.model;

public class Pulverizacao extends IntervencaoOperacional {

    @Override
    public void executarServico(TrechoRodovia trechoAlvo) {
        // Pulverização química preventiva reduz o nível de vegetação para 8cm
        trechoAlvo.atualizarNivelVegetacao(8.0);
        System.out.println("[Serviço] Pulverização Química de inibição concluída no trecho: " 
            + trechoAlvo.getIdentificador().getCodigoIdentificacao());
    }
}