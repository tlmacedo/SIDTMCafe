package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.tabLojaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tabLojaDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    String comandoSql = "";
    tabLojaVO lojaVO;
    List<tabLojaVO> lojaVOList;

    public tabLojaVO getLojaVO(int idTabLoja) {
        comandoSql = "SELECT * FROM tabLoja ";
        if (idTabLoja > 0) comandoSql += "WHERE id = '" + idTabLoja + "' ";
        comandoSql += "ORDER BY id DESC ";
        if (buscaTabLojaVO(comandoSql) == null)
            lojaVO = new tabLojaVO();
        return lojaVO;
    }

    public List<tabLojaVO> getLojaVOList() {
        comandoSql = "SELECT * FROM tabLoja ";
        comandoSql += "ORDER BY id DESC ";
        if (buscaListaTabLojaVO(comandoSql) == null)
            lojaVOList.add(new tabLojaVO());
        return lojaVOList;
    }

    private tabLojaVO buscaTabLojaVO(String instrucaoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lojaVO = new tabLojaVO();
                lojaVO.setId(rs.getInt("id"));
                lojaVO.setCnpj(rs.getString("cnpj"));
                lojaVO.setIe(rs.getString("ie"));
                lojaVO.setRazao(rs.getString("razao"));
                lojaVO.setFantasia(rs.getString("fantasia"));
                lojaVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                lojaVO.setEndereco_ids(rs.getString("endereco_ids"));
                lojaVO.setTelefone_ids(rs.getString("telefone_ids"));
                lojaVO.setContato_ids(rs.getString("contato_ids"));
                lojaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lojaVO;
    }

    private List<tabLojaVO> buscaListaTabLojaVO(String instrucaoSql) {
        lojaVOList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lojaVO = new tabLojaVO();
                lojaVO.setId(rs.getInt("id"));
                lojaVO.setCnpj(rs.getString("cnpj"));
                lojaVO.setIe(rs.getString("ie"));
                lojaVO.setRazao(rs.getString("razao"));
                lojaVO.setFantasia(rs.getString("fantasia"));
                lojaVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                lojaVO.setEndereco_ids(rs.getString("endereco_ids"));
                lojaVO.setTelefone_ids(rs.getString("telefone_ids"));
                lojaVO.setContato_ids(rs.getString("contato_ids"));
                lojaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));

                lojaVOList.add(lojaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lojaVOList;
    }
}
