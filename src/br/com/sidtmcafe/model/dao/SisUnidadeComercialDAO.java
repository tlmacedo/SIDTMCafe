package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisUnidadeComercialVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisUnidadeComercialDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisUnidadeComercialVO unidadeComercialVO;
    List<SisUnidadeComercialVO> unidadeComercialVOList;

    public SisUnidadeComercialVO getUnidadeComercialVO(int idSisUnidadeComercialVO) {
        buscaSisUnidadeComercialVO(idSisUnidadeComercialVO);
        return unidadeComercialVO;
    }

    public List<SisUnidadeComercialVO> getUnidadeComercialVOList() {
        buscaSisUnidadeComercialVO(0);
        return unidadeComercialVOList;
    }

    void buscaSisUnidadeComercialVO(int idSisUnidadeComercialVO) {
        comandoSql = "SELECT * FROM sisUnidadeComercial ";
        if (idSisUnidadeComercialVO > 0) comandoSql += "WHERE id = '" + idSisUnidadeComercialVO + "' ";
        comandoSql += "ORDER BY descricao ";

        unidadeComercialVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                unidadeComercialVO = new SisUnidadeComercialVO();
                unidadeComercialVO.setId(rs.getInt("id"));
                unidadeComercialVO.setDescricao(rs.getString("descricao"));
                unidadeComercialVO.setSigla(rs.getString("sigla"));

                unidadeComercialVOList.add(unidadeComercialVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
