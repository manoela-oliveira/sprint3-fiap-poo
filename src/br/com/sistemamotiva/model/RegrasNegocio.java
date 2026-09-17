package br.com.sistemamotiva.model;

/* Esta classe foi criada para atuar como um Serviço de Domínio para encapsular a lógica de 
  orquestração do sistema evitando a poluição do Main (que deve servir apenas para a inicialização
  e testes do sistema) e garantindo o SRP (Princípio de Responsabilidade Única)*/

public class RegrasNegocio {

    public void gerarRelatorioPrioridade(TrechoRodovia[] trechos) {
        System.out.println("\n========================================================");
        System.out.println("      RELATÓRIO AUTOMÁTICO DE PRIORIDADE DE ROÇADA      ");
        System.out.println("========================================================");

        for (TrechoRodovia trecho : trechos) {
            String prioridade = trecho.calcularPrioridade();
            String identificador = trecho.getIdentificador().getCodigoIdentificacao();
            double kmInicial = trecho.getIdentificador().getQuilometroInicial();
            double kmFinal = trecho.getIdentificador().getQuilometroFinal();
            double nivel = trecho.getNivelVegetacaoCm();

            System.out.printf("\nTrecho: %s (KM %.1f até %.1f) | Nível: %.1fcm | Prioridade: %s\n",
                    identificador, kmInicial, kmFinal, nivel, prioridade);

            if (prioridade.equals("CRITICA")) {
                System.out.println("-> Recomendação: Roçada Mecanizada Imediata.");
                IntervencaoOperacional servico = new RocadaMecanizada();
                servico.executarServico(trecho);
            } else if (prioridade.equals("ALTA")) {
                System.out.println("-> Recomendação: Pulverização Preventiva Química.");
                IntervencaoOperacional servico = new Pulverizacao();
                servico.executarServico(trecho);
            } else {
                System.out.println("-> Recomendação: Monitoramento Preventivo (Sem intervenção necessária).");
            }
        }
        System.out.println("\n========================================================");
    }

    public void processarLeiturasIoT(MonitoravelViaIoT[] sensores) {
        System.out.println("\n--- PROCESSANDO LEITURAS DE TELEMETRIA IoT ---");
        for (MonitoravelViaIoT sensor : sensores) {
            double leitura = sensor.transmitirDadosSensor();
            System.out.printf("Dados processados com sucesso. Leitura capturada: %.1fcm\n", leitura);
        }
    }
}