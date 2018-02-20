package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscaBandoDados {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    ResultSet getResultadosBandoDados(String instrucaoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    boolean getUpdateBancoDados(String tabela, String comandoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(comandoSql);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            new AlertMensagem("Erro. [" + tabela + "].", new Exception().getStackTrace()[0].getClassName() + ".",
                    "ic_msg_erro_circulo_white_24dp.png").errorException(ex);
            ex.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return true;
    }

    boolean getInsertBancoDados(String instrucaoSql, String tabela) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            //inserindo no bando de dados
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            new AlertMensagem("Erro. [" + tabela + "]", new Exception().getStackTrace()[0].getClassName() + ".",
                    "ic_msg_erro_circulo_white_24dp.png").errorException(ex);
            ex.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return true;
    }
}
