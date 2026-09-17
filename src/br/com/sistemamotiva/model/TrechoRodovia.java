package br.com.sistemamotiva.model;
/*Esta classe é abstrata porque se trata de um termo genérico que não deve existir
  sozinho. O trecho de uma rodovia precisa ser identificado por tipos, especialmente
  em contextos de manutenção, é necessário saber características como; é uma pista dupla ou simples?
  Possui acostamento? É asfaltada? Possui canteiros? Etc.*/

public abstract class TrechoRodovia {
    private double nivelVegetacaoCm;
    private boolean regiaoUmida; 

    /*Conexão entre TrechoRododvia e IdentificacaoTrecho foi realizada através de uma
      associação. Aqui, IdentificacaoTrecho cumpre o papel de identificador, informando
      qual é o trecho, onde ele começa e onde termina. */
    private IdentificacaoTrecho identificador;

    public TrechoRodovia(IdentificacaoTrecho identificador, double nivelVegetacaoCm) {
        this.identificador = identificador;
        this.setNivelVegetacaoCm(nivelVegetacaoCm);
        this.regiaoUmida = false; 
    }

    /*Este método é abstrato porque cada tipo de rodovia possui critérios 
     diferentes para definir o que é uma prioridade, categorizando entre
     CRÍTICA, ALTA ou BAIXA.*/
    public abstract String calcularPrioridade();

    public double getNivelVegetacaoCm() {
        return nivelVegetacaoCm;
    }

    private void setNivelVegetacaoCm(double nivel) {
        if (nivel >= 0) {
            this.nivelVegetacaoCm = nivel;
        } else {
            this.nivelVegetacaoCm = 0;
        }
    }

    public boolean isRegiaoUmida() {
        return this.regiaoUmida;
    }

    public IdentificacaoTrecho getIdentificador() {
        return identificador;
    }
    

    public void marcarComoRegiaoUmida() {
        this.regiaoUmida = true;
    }

    /*Caso o trecho esteja marcado como região úmida, há um fator multiplicador acelerador no
      crescimento da vegetação. */
    public void registrarCrescimento(double taxa) {
        if (taxa > 0) {
            double taxaEfetiva = this.regiaoUmida ? taxa * 1.8 : taxa;
            this.nivelVegetacaoCm += taxaEfetiva;
        }
    }

    /*Após a manutenção (roçada) o nível de vegetação deve voltar para, no mínimo, 5cm.
      A razão pelo qual apliquei esta regra de negócio seria a legislação sob a qual a empresa
      Motiva é regida. O nível de vegetação não pode ser muito baixo (<5) e muito menos
      inexistente (0), pois a empresa possui um compromisso ambiental de manter uma quantidade
      significativa de vegetação em suas rodovias. */
    public void atualizarNivelVegetacao(double novoNivelCm) {
    if (novoNivelCm >= 5.0) {
        this.nivelVegetacaoCm = novoNivelCm;
    } else {
        this.nivelVegetacaoCm = 5.0;
    }
}

    public void exibirInformacoes() {
        String clima = this.regiaoUmida ? "Úmido (Acelerado)" : "Padrão";
        System.out.println("\nTrecho: " + this.identificador.getCodigoIdentificacao() + " | Extensão: " + identificador.getQuilometroInicial() + "km a " + identificador.getQuilometroFinal() + "km" + " | Ambiente: " + clima);
        System.out.println("Vegetação: " + this.nivelVegetacaoCm + "cm | Prioridade: " + this.calcularPrioridade());
    }
}