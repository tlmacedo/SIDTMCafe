package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFiscalCstIcmsVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFiscalCstIcmsDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFiscalCstIcmsVO fiscalCstIcmsVO;
    List<SisFiscalCstIcmsVO> fiscalCstIcmsVOList;

    public SisFiscalCstIcmsVO getFiscalCstIcmsVO(int idSisFiscalCstIcmsVO) {
        buscaSisFiscalCstIcmsVO(idSisFiscalCstIcmsVO);
        return fiscalCstIcmsVO;
    }

    public List<SisFiscalCstIcmsVO> getFiscalCstIcmsVOList() {
        buscaSisFiscalCstIcmsVO(0);
        return fiscalCstIcmsVOList;
    }

    void buscaSisFiscalCstIcmsVO(int idSisFiscalCstIcmsVO) {
        comandoSql = "SELECT * FROM sisFiscalCstIcms ";
        if (idSisFiscalCstIcmsVO > 0) comandoSql += "WHERE id = '" + idSisFiscalCstIcmsVO + "' ";
        comandoSql += "ORDER BY descricao ";

        rs = getResultadosBandoDados(comandoSql);
        fiscalCstIcmsVOList = new ArrayList<>();
        try {
            fiscalCstIcmsVO = new SisFiscalCstIcmsVO();
            fiscalCstIcmsVO.setId(rs.getInt("id"));
            fiscalCstIcmsVO.setDescricao(rs.getString("descricao"));

            fiscalCstIcmsVOList.add(fiscalCstIcmsVO);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
