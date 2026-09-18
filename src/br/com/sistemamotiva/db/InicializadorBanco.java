package br.com.sistemamotiva.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBanco {

    public static void criarTabelasSeNaoExistirem() {
        System.out.println("Verificando e criando tabelas no Oracle...");

        String ddlEquipes = """
            CREATE TABLE EQUIPES (
                ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                IDENTIFICADOR VARCHAR2(50) NOT NULL,
                QUANTIDADE_MEMBROS NUMBER NOT NULL
            )
        """;

        String ddlTrechos = """
            CREATE TABLE TRECHOS (
                ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                CODIGO VARCHAR2(50) NOT NULL,
                KM_INICIAL NUMBER(8, 2) NOT NULL,
                KM_FINAL NUMBER(8, 2) NOT NULL,
                NIVEL_VEGETACAO NUMBER(6, 2) NOT NULL,
                TIPO VARCHAR2(30) NOT NULL,
                REGIAO_UMIDA NUMBER(1) DEFAULT 0 NOT NULL,
                QUANTIDADE_FAIXAS NUMBER DEFAULT 1,
                IS_PAVIMENTADA NUMBER(1) DEFAULT 1
            )
        """;

        String ddlIntervencoes = """
            CREATE TABLE INTERVENCOES (
                ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                TIPO VARCHAR2(30) NOT NULL,
                DESCRICAO VARCHAR2(255) NOT NULL,
                CUSTO_ESTIMADO NUMBER(10, 2) NOT NULL
            )
        """;

        String ddlRelatorios = """
            CREATE TABLE RELATORIOS (
                ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                DATA_GERACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                QT_CRITICO NUMBER NOT NULL,
                QT_ALTA NUMBER NOT NULL,
                QT_BAIXA NUMBER NOT NULL,
                RESUMO VARCHAR2(1000) NOT NULL
            )
        """;

        Connection conn = null;
        try {
            conn = ConexaoBanco.getConexao();

            executarCriacaoTabela(conn, "EQUIPES", ddlEquipes);
            executarCriacaoTabela(conn, "TRECHOS", ddlTrechos);
            executarCriacaoTabela(conn, "INTERVENCOES", ddlIntervencoes);
            executarCriacaoTabela(conn, "RELATORIOS", ddlRelatorios);

            System.out.println("Todas as tabelas prontas para uso!\n");

        } catch (Exception e) {
            System.err.println("Erro ao inicializar tabelas: " + e.getMessage());
        } finally {
            ConexaoBanco.fechar(conn);
        }
    }

    private static void executarCriacaoTabela(Connection conn, String nomeTabela, String sql) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabela '" + nomeTabela + "' criada com sucesso.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 955) {
                System.out.println("Tabela '" + nomeTabela + "' já existe no banco.");
            } else {
                System.err.println("Erro ao criar '" + nomeTabela + "': " + e.getMessage());
            }
        } finally {
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException ignored) {}
            }
        }
    }
}