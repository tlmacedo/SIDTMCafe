package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFreteTomadorVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFreteTomadorDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFreteTomadorVO sisFreteTomadorVO;
    List<SisFreteTomadorVO> sisFreteTomadorVOList;

    public SisFreteTomadorVO getSisFreteTomadorVO(int idSisFreteTomadorVO) {
        buscaSisFreteTomadorVO(idSisFreteTomadorVO);
        return sisFreteTomadorVO;
    }

    public List<SisFreteTomadorVO> getSisFreteTomadorVOList() {
        buscaSisFreteTomadorVO(0);
        return sisFreteTomadorVOList;
    }

    void buscaSisFreteTomadorVO(int idSisFreteTomadorVO) {
        comandoSql = "SELECT * FROM sisFreteTomador ";
        if (idSisFreteTomadorVO > 0) comandoSql += "WHERE id = '" + idSisFreteTomadorVO + "' ";
        comandoSql += "ORDER BY id ";

        rs = getResultadosBandoDados(comandoSql);
        sisFreteTomadorVOList = new ArrayList<>();
        try {
            while (rs.next()) {
                sisFreteTomadorVO = new SisFreteTomadorVO();
                sisFreteTomadorVO.setId(rs.getInt("id"));
                sisFreteTomadorVO.setDescricao(rs.getString("descricao"));

                sisFreteTomadorVOList.add(sisFreteTomadorVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
}
