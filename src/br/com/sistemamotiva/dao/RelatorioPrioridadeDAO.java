package br.com.sistemamotiva.dao;

import br.com.sistemamotiva.db.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioPrioridadeDAO {

    public record RelatorioRegistro(
        Long id,
        Timestamp dataGeracao,
        int qtCritico,
        int qtAlta,
        int qtBaixa,
        String resumo
    ) {}

    private static final String SQL_INSERT = 
        "INSERT INTO relatorios (qt_critico, qt_alta, qt_baixa, resumo) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = 
        "SELECT * FROM relatorios ORDER BY data_geracao DESC";

    public RelatorioPrioridadeDAO() {}

    public void salvarRelatorio(int qtCritico, int qtAlta, int qtBaixa, String resumo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexaoBanco.getConexao();
            pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setInt(1, qtCritico);
            pstmt.setInt(2, qtAlta);
            pstmt.setInt(3, qtBaixa);
            pstmt.setString(4, resumo);
            pstmt.executeUpdate();
            System.out.println(" Relatório de prioridades persistido com sucesso no Oracle.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar relatório: " + e.getMessage(), e);
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException ignored) {}
            ConexaoBanco.fechar(conn);
        }
    }

    public List<RelatorioRegistro> listarTodas() {
        List<RelatorioRegistro> lista = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexaoBanco.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                lista.add(new RelatorioRegistro(
                    rs.getLong("id"),
                    rs.getTimestamp("data_geracao"),
                    rs.getInt("qt_critico"),
                    rs.getInt("qt_alta"),
                    rs.getInt("qt_baixa"),
                    rs.getString("resumo")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar relatórios: " + e.getMessage(), e);
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
            ConexaoBanco.fechar(conn);
        }
    }
}