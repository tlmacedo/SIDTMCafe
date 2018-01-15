package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.tabCargoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tabCargoDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    String comandoSql = "";
    tabCargoVO cargoVO;
    List<tabCargoVO> cargoVOList;

    public tabCargoVO getCargoVO(int idTabCargoVO) {
        comandoSql = "SELECT * FROM tabCargo ";
        if (idTabCargoVO > 0) comandoSql += "WHERE id = '" + idTabCargoVO + "' ";
        comandoSql += "ORDER BY descricao ";
        if (buscaTabCargoVO(comandoSql) == null)
            cargoVO = new tabCargoVO();
        return cargoVO;
    }

    public List<tabCargoVO> getCargoVOList() {
        comandoSql = "SELECT * FROM tabCargo ";
        comandoSql += "ORDER BY descricao ";
        if (buscaListaTabCargoVO(comandoSql) == null)
            cargoVOList.add(new tabCargoVO());
        return cargoVOList;
    }

    private tabCargoVO buscaTabCargoVO(String instrucaoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cargoVO = new tabCargoVO();
                cargoVO.setId(rs.getInt("id"));
                cargoVO.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cargoVO;
    }

    private List<tabCargoVO> buscaListaTabCargoVO(String instrucaoSql) {
        cargoVOList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cargoVO = new tabCargoVO();
                cargoVO.setId(rs.getInt("id"));
                cargoVO.setDescricao(rs.getString("descricao"));

                cargoVOList.add(cargoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cargoVOList;
    }
}
