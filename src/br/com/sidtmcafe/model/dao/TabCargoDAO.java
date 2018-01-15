package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabCargoVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabCargoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabCargoVO cargoVO;
    List<TabCargoVO> cargoVOList;

    public TabCargoVO getCargoVO(int idTabCargoVO) {
        buscaTabCargoVO(idTabCargoVO);
        if (cargoVO == null)
            cargoVO = new TabCargoVO();
        return cargoVO;
    }

    public List<TabCargoVO> getCargoVOList() {
        buscaTabCargoVO(0);
        if (cargoVOList == null)
            cargoVOList.add(new TabCargoVO());
        return cargoVOList;
    }

    void buscaTabCargoVO(int idTabCargoVO) {
        comandoSql = "SELECT * FROM tabCargo ";
        if (idTabCargoVO > 0) comandoSql += "WHERE id = '" + idTabCargoVO + "' ";
        comandoSql += "ORDER BY descricao ";

        cargoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                cargoVO = new TabCargoVO();
                cargoVO.setId(rs.getInt("id"));
                cargoVO.setDescricao(rs.getString("descricao"));

                cargoVOList.add(cargoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
