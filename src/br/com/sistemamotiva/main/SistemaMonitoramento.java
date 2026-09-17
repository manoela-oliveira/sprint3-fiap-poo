package br.com.sistemamotiva.main;

import br.com.sistemamotiva.model.Autoestrada;
import br.com.sistemamotiva.model.AutoestradaSensorizada;
import br.com.sistemamotiva.model.EquipeManutencao;
import br.com.sistemamotiva.model.EstradaVicinal;
import br.com.sistemamotiva.model.IdentificacaoTrecho;
import br.com.sistemamotiva.model.IntervencaoOperacional;
import br.com.sistemamotiva.model.MockTrechoSensorizado;
import br.com.sistemamotiva.model.MonitoravelViaIoT;
import br.com.sistemamotiva.model.RocadaMecanizada;
import br.com.sistemamotiva.service.GeradorRelatorio;

public class SistemaMonitoramento {
    public static void main(String[] args) {
        System.out.println("\n======= TESTE DE INTEGRAÇÃO LOCAL (SEM BANCO) =======");

        // 1. Testando modelos e herança (Autoestrada e EstradaVicinal)
        IdentificacaoTrecho iden1 = new IdentificacaoTrecho("BR-101-KM10", 0.0, 10.0);
        Autoestrada auto = new Autoestrada(iden1, 35.0, 4); // Nível > 30cm -> Prioridade CRITICA

        IdentificacaoTrecho iden2 = new IdentificacaoTrecho("VIC-PAV-01", 0.0, 5.0);
        EstradaVicinal vic = new EstradaVicinal(iden2, 15.0, true);

        System.out.println("Trecho 1: " + auto.getIdentificador().getCodigoIdentificacao() + " | Prioridade: " + auto.calcularPrioridade());
        System.out.println("Trecho 2: " + vic.getIdentificador().getCodigoIdentificacao() + " | Prioridade: " + vic.calcularPrioridade());

        // 2. Testando serviços e polimorfismo das intervenções
        System.out.println("\n--- Testando Intervenções em Memória ---");
        IntervencaoOperacional rocada = new RocadaMecanizada();
        System.out.println("Vegetação antes da roçada: " + auto.getNivelVegetacaoCm() + "cm");
        rocada.executarServico(auto);
        System.out.println("Vegetação após roçada: " + auto.getNivelVegetacaoCm() + "cm");

        // 3. Testando Equipe de Manutenção
        System.out.println("\n--- Testando Equipe ---");
        EquipeManutencao equipe = new EquipeManutencao("Equipe Alfa", 3);
        System.out.println(equipe);

        // 4. Testando IoT e Mock
        System.out.println("\n--- Testando Sensores IoT ---");
        MockTrechoSensorizado mock = new MockTrechoSensorizado(22.4);
        AutoestradaSensorizada autoSensor = new AutoestradaSensorizada(new IdentificacaoTrecho("SMART-01", 10, 20), 18.0, 2);
        
        GeradorRelatorio gerador = new GeradorRelatorio();
        gerador.processarLeiturasIoT(new MonitoravelViaIoT[] { mock, autoSensor });

        System.out.println("\nSUCESSO! Toda a lógica de negócio, OO e pacotes compilaram perfeitamente no VS Code!");
        System.out.println("Próximo passo: Rodar os scripts SQL no Oracle para depois testar os DAOs.");
    }
}