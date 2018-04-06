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
    SisCargoVO sisCargoVO;
    List<SisCargoVO> sisCargoVOList;

    public SisCargoVO getSisCargoVO(int id) {
        buscaTabCargoVO(id);
        return sisCargoVO;
    }

    public List<SisCargoVO> getSisCargoVOList() {
        buscaTabCargoVO(0);
        return sisCargoVOList;
    }

    void buscaTabCargoVO(int id) {
        comandoSql = "SELECT id, descricao " +
                "FROM SisCargo ";
        if (id > 0) comandoSql += "WHERE id = '" + id + "' ";
        comandoSql += "ORDER BY descricao ";

        if (id == 0) sisCargoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                sisCargoVO = new SisCargoVO();
                sisCargoVO.setId(rs.getInt("id"));
                sisCargoVO.setDescricao(rs.getString("descricao"));

                if (id == 0) sisCargoVOList.add(sisCargoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
