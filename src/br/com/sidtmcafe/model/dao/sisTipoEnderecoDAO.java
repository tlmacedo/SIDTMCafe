package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.sisTipoEnderecoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class sisTipoEnderecoDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    String comandoSql = "";
    sisTipoEnderecoVO tipoEnderecoVO;
    List<sisTipoEnderecoVO> tipoEnderecoVOList;

    public sisTipoEnderecoVO getTipoEnderecoVO(int idSisTipoEnderecoVO) {
        comandoSql = "SELECT * FROM sisTipoEndereco ";
        if (idSisTipoEnderecoVO > 0) comandoSql += "WHERE id = '" + idSisTipoEnderecoVO + "' ";
        comandoSql += "ORDER BY descricao ";
        if (buscaSisTipoEnderecoVO(comandoSql) == null)
            tipoEnderecoVO = new sisTipoEnderecoVO();
        return tipoEnderecoVO;
    }

    public List<sisTipoEnderecoVO> getTipoEnderecoVOList() {
        comandoSql = "SELECT * FROM sisTipoEndereco ";
        comandoSql += "ORDER BY descricao ";
        if (buscaListaSisTipoEnderecoVO(comandoSql) == null)
            tipoEnderecoVOList.add(new sisTipoEnderecoVO());
        return tipoEnderecoVOList;
    }

    private sisTipoEnderecoVO buscaSisTipoEnderecoVO(String instrucaoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tipoEnderecoVO = new sisTipoEnderecoVO();
                tipoEnderecoVO.setId(rs.getInt("id"));
                tipoEnderecoVO.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return tipoEnderecoVO;
    }

    private List<sisTipoEnderecoVO> buscaListaSisTipoEnderecoVO(String instrucaoSql) {
        tipoEnderecoVOList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tipoEnderecoVO = new sisTipoEnderecoVO();
                tipoEnderecoVO.setId(rs.getInt("id"));
                tipoEnderecoVO.setDescricao(rs.getString("descricao"));

                tipoEnderecoVOList.add(tipoEnderecoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return tipoEnderecoVOList;
    }
}
