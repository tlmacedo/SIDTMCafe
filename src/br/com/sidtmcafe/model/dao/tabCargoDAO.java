package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.model.vo.tabCargoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        return buscaTabCargoVO(comandoSql);
    }

    public List<tabCargoVO> getCargoVOList() {
        comandoSql = "SELECT * FROM tabCargo ";
        comandoSql += "ORDER BY descricao ";
        return buscaListaTabCargoVO(comandoSql);
    }

    private tabCargoVO buscaTabCargoVO(String instrucaoSql) {

        return cargoVO;
    }

    private List<tabCargoVO> buscaListaTabCargoVO(String instrucaoSql) {
        return cargoVOList;
    }
}
