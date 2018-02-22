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
    int idInclusao = 0;

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

    boolean getUpdateBancoDados(String comandoSql) {
        con = ConnectionFactory.getConnection();
        try {
            //System.out.println("comandoSql: " + comandoSql);
            stmt = con.prepareStatement(comandoSql);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            new AlertMensagem("Erro.", new Exception().getStackTrace()[0].getClassName() + ".",
                    "ic_msg_erro_circulo_white_24dp.png").errorException(ex);
            ex.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return true;
    }

    int getInsertBancoDados(String comandoSql) {
        con = ConnectionFactory.getConnection();
        try {
            //System.out.print("comandoSql: " + comandoSql);
            stmt = con.prepareStatement(comandoSql);
            stmt.execute();
            stmt.close();

            rs = con.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            if (rs.next())
                idInclusao = rs.getInt("LAST_INSERT_ID()");
            //System.out.print("     idInclusao: " + idInclusao + "\n");

        } catch (Exception ex) {
            new AlertMensagem("Erro.", new Exception().getStackTrace()[0].getClassName() + ".",
                    "ic_msg_erro_circulo_white_24dp.png").errorException(ex);
            ex.printStackTrace();
            return 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return idInclusao;
    }
}
