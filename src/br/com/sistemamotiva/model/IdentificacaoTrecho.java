package br.com.sistemamotiva.model;

/*Esta classe foi criada para evitar a adição excessiva de parâmetros na classe TrechoRodovia
  Intuito: respeitar a regra de Clean Code mantendo no máximo 3 parâmetros, garantindo maior 
  legibilidade do codigo e facilitando a manutenção a longo prazo.

  Para definir/identificar um trecho rodoviários precisamos saber:
  - Código de identificação
  - Onde começa (Km)
  - Onde termina (Km)*/

public class IdentificacaoTrecho {
    private String codigoIdentificacao;
    private double quilometroInicial;
    private double quilometroFinal;

    public IdentificacaoTrecho(String codigoIdentificacao, double quilometroInicial, double quilometroFinal) {
        this.setCodigoIdentificacao(codigoIdentificacao);
        this.setQuilometroInicial(quilometroInicial);
        this.setQuilometroFinal(quilometroFinal);
    }

    public String getCodigoIdentificacao() { 
        return codigoIdentificacao; 
    }

    private void setCodigoIdentificacao(String codigo) {
        if (codigo != null) {
            this.codigoIdentificacao = codigo;
        } else {
            System.out.println("Erro! O código informado é inválido. Atribuindo valor padrão.");
            this.codigoIdentificacao = "TRECHO-INDETERMINADO";
        }
    }

    public double getQuilometroInicial() { 
        return quilometroInicial; 
    }

    private void setQuilometroInicial(double valor) {
        if (valor < 0) {
            System.out.println("Erro! Não é permitida a definição de quilometragem negativa.");
            this.quilometroInicial = 0.0;
        } 
        else {
            this.quilometroInicial = valor;
        }
    }

    public double getQuilometroFinal() { 
        return quilometroFinal; 
    }

    private void setQuilometroFinal(double valor) {
        if (valor < 0) {
            System.out.println("Erro! Não é permitida a definição de quilometragem negativa.");
        this.quilometroFinal = 0.0;
        } 
        else {
            this.quilometroFinal = valor;
        }
    }
    
}