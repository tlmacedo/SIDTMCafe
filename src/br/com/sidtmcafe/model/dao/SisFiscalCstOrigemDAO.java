package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFiscalCstOrigemVO;

import java.awt.datatransfer.Transferable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFiscalCstOrigemDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFiscalCstOrigemVO fiscalCstOrigemVO;
    List<SisFiscalCstOrigemVO> fiscalCstOrigemVOList;

    public SisFiscalCstOrigemVO getFiscalCstOrigemVO(int idSisFiscalCstOrigemVO) {
        buscaSisFiscalCstOrigemVO(idSisFiscalCstOrigemVO);
        return fiscalCstOrigemVO;
    }

    public List<SisFiscalCstOrigemVO> getFiscalCstOrigemVOList() {
        buscaSisFiscalCstOrigemVO(-1);
        return fiscalCstOrigemVOList;
    }

    void buscaSisFiscalCstOrigemVO(int idSisFiscalCstOrigemVO) {
        comandoSql = "SELECT * FROM sisFiscalCstOrigem ";
        if (idSisFiscalCstOrigemVO >= 0) comandoSql += "WHERE id = '" + idSisFiscalCstOrigemVO + "' ";
        comandoSql += "ORDER BY id ";

        rs = getResultadosBandoDados(comandoSql);
        fiscalCstOrigemVOList = new ArrayList<>();
        try {
            while (rs.next()) {
                fiscalCstOrigemVO = new SisFiscalCstOrigemVO();
                fiscalCstOrigemVO.setId(rs.getInt("id"));
                fiscalCstOrigemVO.setDescricao(rs.getString("descricao"));

                fiscalCstOrigemVOList.add(fiscalCstOrigemVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
