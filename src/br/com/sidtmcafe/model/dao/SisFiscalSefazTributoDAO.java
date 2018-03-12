package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFiscalSefazTributoVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFiscalSefazTributoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFiscalSefazTributoVO fiscalSefazTributoVO;
    List<SisFiscalSefazTributoVO> fiscalSefazTributoVOList;

    public SisFiscalSefazTributoVO getFiscalSefazTributoVO(int idSisFiscalSefazTributoVO) {
        buscaSisFiscalSefazTributoVO(idSisFiscalSefazTributoVO);
        return fiscalSefazTributoVO;
    }

    public List<SisFiscalSefazTributoVO> getFiscalSefazTributoVOList() {
        buscaSisFiscalSefazTributoVO(0);
        return fiscalSefazTributoVOList;
    }

    void buscaSisFiscalSefazTributoVO(int idSisFiscalSefazTributoVO) {
        comandoSql = "SELECT * FROM sisFiscalSefazTributo ";
        if (idSisFiscalSefazTributoVO > 0) comandoSql += "WHERE id = '" + idSisFiscalSefazTributoVO + "' ";
        comandoSql += "ORDER BY id ";

        rs = getResultadosBandoDados(comandoSql);
        fiscalSefazTributoVOList = new ArrayList<>();
        try {
            while (rs.next()) {
                fiscalSefazTributoVO = new SisFiscalSefazTributoVO();
                fiscalSefazTributoVO.setId(rs.getInt("id"));
                fiscalSefazTributoVO.setDescricao(rs.getString("descricao"));

                fiscalSefazTributoVOList.add(fiscalSefazTributoVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
}
