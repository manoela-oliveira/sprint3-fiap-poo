package br.com.sistemamotiva.main;

// Importa todas as classes do pacote model unificado
import br.com.sistemamotiva.model.*;

public class SistemaMonitoramento {
    public static void main(String[] args) {
        System.out.println("\n======= INICIANDO SISTEMA MOTIVA =======");

        // Simulação de comportamento de crescimento seco vs úmido
        System.out.println("\n>>> [TESTE 1] Validando crescimento acelerado em regiões úmidas");
        
        IdentificacaoTrecho localSeco = new IdentificacaoTrecho("BR-101-SECO", 0, 10);
        TrechoRodovia trechoSeco = new Autoestrada(localSeco, 10.0, 2);

        IdentificacaoTrecho localUmido = new IdentificacaoTrecho("BR-101-UMIDO", 10, 20);
        TrechoRodovia trechoUmido = new Autoestrada(localUmido, 10.0, 2);
        trechoUmido.marcarComoRegiaoUmida();

        System.out.println("--- Estado Inicial ---");
        trechoSeco.exibirInformacoes();
        trechoUmido.exibirInformacoes();

        System.out.println("\n... Precipitação de chuvas registrada. Simulando crescimento base de 12cm ...");
        trechoSeco.registrarCrescimento(12.0);
        trechoUmido.registrarCrescimento(12.0);

        System.out.println("--- Estado Pós-Precipitação ---");
        trechoSeco.exibirInformacoes();
        trechoUmido.exibirInformacoes();


        // Transmissão IoT e teste de integridade com Mock
        System.out.println("\n>>> [TESTE 2] Validando interface IoT e o objeto Mock");

        IdentificacaoTrecho localSensorizado = new IdentificacaoTrecho("BR-381-SMART", 45, 50);
        AutoestradaSensorizada trechoSensorizado = new AutoestradaSensorizada(localSensorizado, 18.0,4);

        MockTrechoSensorizado mockIot = new MockTrechoSensorizado(28.5);
        RegrasNegocio motor = new RegrasNegocio();

        MonitoravelViaIoT[] sensoresEmCampo = new MonitoravelViaIoT[] {
            trechoSensorizado,
            mockIot
        };
        motor.processarLeiturasIoT(sensoresEmCampo);

        // Relatório de prioridades (varredura automatizada)
        System.out.println("\n>>> [TESTE 3] Geração do Relatório Automático de Prioridades e Intervenções");

        IdentificacaoTrecho loc1 = new IdentificacaoTrecho("BR-040", 20, 30);
        TrechoRodovia rodoviaCritica = new Autoestrada(loc1, 35.0, 4); // Prioridade CRITICA

        IdentificacaoTrecho loc2 = new IdentificacaoTrecho("BR-116", 200, 210);
        TrechoRodovia rodoviaAlta = new Autoestrada(loc2, 25.0, 4); // Prioridade ALTA

        IdentificacaoTrecho loc3 = new IdentificacaoTrecho("VIC-B", 0, 8);
        TrechoRodovia vicinalBaixa = new EstradaVicinal(loc3, 12.0, true); // Prioridade BAIXA

        TrechoRodovia[] malhaRodoviaria = new TrechoRodovia[] {
            rodoviaCritica,
            rodoviaAlta,
            vicinalBaixa
        };
        motor.gerarRelatorioPrioridade(malhaRodoviaria);

        // Simulação de ordem de serviço controlada no fluxo tradicional
        System.out.println("\n>>> [TESTE 4] Acionamento de intervenção com equipe de campo");
        EquipeManutencao equipeGama = new EquipeManutencao("Gama", 3);
        Manutencao osPulverizacao = new Manutencao("25/08/2026", equipeGama, rodoviaAlta, new Pulverizacao());
        osPulverizacao.executarManutencao();


        // Proteção da classe abstrata
        System.out.println("\n>>> [TESTE 5] Verificando proteção de instanciamento de classes abstratas");
        System.out.println("As linhas de código referentes ao TESTE 5 estão intencionalmente comentadas. Para testar a proteção do Java, descomente-as no código fonte.");

                // O Java deve impedir fisicamente o nascimento de conceitos genéricos na memória, garantindo o isolamento das classes abstratas

        // Teste 5.1: Criar uma intervenção sem especificar qual é
        // IntervencaoOperacional servicoInvalido = new IntervencaoOperacional();
        
        // Teste 5.2: Criar um trecho de rodovia genérico (sem ser Autoestrada ou EstradaVicinal)
        // TrechoRodovia trechoInvalido = new TrechoRodovia(new IdentificacaoTrecho("BR-TESTE", 0, 10), 12.0);

        // >>> [TESTE 6] Validação de regras de proteção e limites do sistema
        System.out.println("\n>>> [TESTE 6] Validando eficiência do sistema frente a dados inválidos");

        System.out.println("\n--- Testando criação de equipe com tamanho insuficiente ---");
        EquipeManutencao equipeInvalida = new EquipeManutencao("Gama-Invalida", 1);
        System.out.println("Membros finais atribuídos: " + equipeInvalida.getQuantidadeMembros());

        System.out.println("\n--- Testando quilometragem negativa no trecho ---");
        IdentificacaoTrecho trechoNegativo = new IdentificacaoTrecho("BR-999", -10, -5);
        System.out.println("KM Inicial: " + trechoNegativo.getQuilometroInicial() + " | KM Final: " + trechoNegativo.getQuilometroFinal());

        System.out.println("\n--- Testando limite de proteção ecológica ---");
        IdentificacaoTrecho locTeste = new IdentificacaoTrecho("BR-Teste", 100, 105);
        TrechoRodovia trechoTeste = new Autoestrada(locTeste, 30.0, 2);
        
        System.out.println("Vegetação inicial: " + trechoTeste.getNivelVegetacaoCm() + "cm");
        trechoTeste.atualizarNivelVegetacao(2.0);
        System.out.println("Vegetação após tentativa: " + trechoTeste.getNivelVegetacaoCm() + "cm");
    }
}