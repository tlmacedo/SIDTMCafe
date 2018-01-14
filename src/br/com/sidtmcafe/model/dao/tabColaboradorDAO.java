package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.tabColaboradorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tabColaboradorDAO {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    String comandoSql = "";
    tabColaboradorVO colaboradorVO;
    List<tabColaboradorVO> colaboradorVOList;

    public tabColaboradorVO getColaboradorVO(int idTabColaboradorVO) {
        comandoSql = "SELECT * FROM tabColaborador ";
        if (idTabColaboradorVO > 0) comandoSql += " WHERE id = '" + idTabColaboradorVO + "' ";
        comandoSql += "ORDER BY nome ";
        return buscaTabColaboradorVO(comandoSql);
    }

    public List<tabColaboradorVO> getColaboradorVOList() {
        comandoSql = "SELECT * FROM tabColaborador ";
        comandoSql += "ORDER BY nome ";
        return buscaListaTabColaboradorVO(comandoSql);
    }

    public void insert(tabColaboradorVO colaboradorVO) {

    }

    private tabColaboradorVO buscaTabColaboradorVO(String instrucaoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                colaboradorVO = new tabColaboradorVO();
                colaboradorVO.setId(rs.getInt("id"));
                colaboradorVO.setNome(rs.getString("nome"));
                colaboradorVO.setApelido(rs.getString("apelido"));
                colaboradorVO.setSenha(rs.getString("senha"));
                colaboradorVO.setCargo_id(rs.getInt("cargo_id"));
                colaboradorVO.setLoja_id(rs.getInt("loja_id"));
                colaboradorVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                colaboradorVO.setEndereco_ids(rs.getString("endereco_ids"));
                colaboradorVO.setTelefone_ids(rs.getString("telefone_ids"));
                colaboradorVO.setContato_ids(rs.getString("contato_ids"));
                colaboradorVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return colaboradorVO;
    }

    private List<tabColaboradorVO> buscaListaTabColaboradorVO(String instrucaoSql) {
        colaboradorVOList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                colaboradorVO = new tabColaboradorVO();
                colaboradorVO.setId(rs.getInt("id"));
                colaboradorVO.setNome(rs.getString("nome"));
                colaboradorVO.setApelido(rs.getString("apelido"));
                colaboradorVO.setSenha(rs.getString("senha"));
                colaboradorVO.setCargo_id(rs.getInt("cargo_id"));
                colaboradorVO.setLoja_id(rs.getInt("loja_id"));
                colaboradorVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                colaboradorVO.setEndereco_ids(rs.getString("endereco_ids"));
                colaboradorVO.setTelefone_ids(rs.getString("telefone_ids"));
                colaboradorVO.setContato_ids(rs.getString("contato_ids"));
                colaboradorVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));

                colaboradorVOList.add(colaboradorVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return colaboradorVOList;
    }

}
