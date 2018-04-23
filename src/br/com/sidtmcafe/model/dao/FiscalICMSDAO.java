package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.FiscalICMSVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FiscalICMSDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    FiscalICMSVO fiscalICMSVO;
    List<FiscalICMSVO> fiscalICMSVOList;

    public FiscalICMSVO getFiscalICMSVO(int id) {
        buscaFiscalICMSVO(id);
        return fiscalICMSVO;
    }

    public List<FiscalICMSVO> getFiscalICMSVOList() {
        buscaFiscalICMSVO(0);
        return fiscalICMSVOList;
    }

    void buscaFiscalICMSVO(int id) {
        comandoSql = "SELECT id, descricao ";
        comandoSql += "FROM fiscalICMS ";
        if (id > 0) comandoSql += "WHERE id = '" + id + "' ";
        comandoSql += "ORDER BY id ";

        if (id == 0) fiscalICMSVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                fiscalICMSVO = new FiscalICMSVO();
                fiscalICMSVO.setId(rs.getInt("id"));
                fiscalICMSVO.setDescricao(rs.getString("descricao"));

                if (id == 0) fiscalICMSVOList.add(fiscalICMSVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
