package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisCargoVO;
import br.com.sidtmcafe.model.vo.SisCargoVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisCargoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisCargoVO cargoVO;
    List<SisCargoVO> cargoVOList;

    public SisCargoVO getCargoVO(int idTabCargoVO) {
        buscaTabCargoVO(idTabCargoVO);
        return cargoVO;
    }

    public List<SisCargoVO> getCargoVOList() {
        buscaTabCargoVO(-1);
        return cargoVOList;
    }

    void buscaTabCargoVO(int idTabCargoVO) {
        comandoSql = "SELECT id, descricao FROM SisCargo ";
        if (idTabCargoVO > 0) comandoSql += "WHERE id = '" + idTabCargoVO + "' ";
        comandoSql += "ORDER BY descricao ";

        cargoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                cargoVO = new SisCargoVO();
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
