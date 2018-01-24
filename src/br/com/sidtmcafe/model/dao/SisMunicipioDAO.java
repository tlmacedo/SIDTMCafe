package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisMunicipioVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisMunicipioDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisMunicipioVO municipioVO;
    List<SisMunicipioVO> municipioVOList;

    public SisMunicipioVO getMunicipioVO(int idSisMunicipioVO) {
        buscaSisMunicipioVO(idSisMunicipioVO);
        if (municipioVO == null)
            municipioVO = new SisMunicipioVO();
        return municipioVO;
    }

    public List<SisMunicipioVO> getMunicipioVOList() {
        buscaSisMunicipioVO(0);
        if (municipioVOList == null)
            municipioVOList.add(new SisMunicipioVO());
        return municipioVOList;
    }

    void buscaSisMunicipioVO(int idSisMunicipioVO) {
        comandoSql = "SELECT * FROM sisMunicipio ";
        if (idSisMunicipioVO > 0) comandoSql += "WHERE id = '" + idSisMunicipioVO + "' ";
        comandoSql += "ORDER BY isCapital DESC, descricao ";

        municipioVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                municipioVO = new SisMunicipioVO();
                municipioVO.setId(rs.getInt("id"));
                municipioVO.setDescricao(rs.getString("descricao"));

                municipioVO.setUf_id(rs.getInt("uf_id"));
                municipioVO.setUfVO(new SisUFDAO().getUfVO(municipioVO.getUf_id()));

                municipioVO.setIsCapital(rs.getInt("isCapital"));
                municipioVO.setIbge_id(rs.getInt("ibge_id"));

                municipioVOList.add(municipioVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
