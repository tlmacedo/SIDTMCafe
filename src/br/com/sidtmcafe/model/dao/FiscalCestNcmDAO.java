package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.FiscalCestNcmVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FiscalCestNcmDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    FiscalCestNcmVO fiscalCestNcmVO;
    List<FiscalCestNcmVO> fiscalCestNcmVOList;

    public FiscalCestNcmVO getFiscalCestNcmVO(int id) {
        buscaFiscalCestNcmDAO(id, "");
        return fiscalCestNcmVO;
    }

    public List<FiscalCestNcmVO> getFiscalCestNcmVOList(String ncm) {
        buscaFiscalCestNcmDAO(0, ncm);
        return fiscalCestNcmVOList;
    }

    void buscaFiscalCestNcmDAO(int id, String ncm) {
        comandoSql = "SELECT id, segmento, descricao, cest, ncm ";
        comandoSql += "FROM fiscalCestNcm ";
        if (id > 0)
            comandoSql += "WHERE id = " + id + " ";
        else if (!ncm.equals(""))
            comandoSql += "WHERE ncm Like %" + ncm + "% ";

        comandoSql += "ORDER BY descricao ";

        if (id == 0) fiscalCestNcmVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);

        try {
            while (rs.next()) {
                fiscalCestNcmVO = new FiscalCestNcmVO();
                fiscalCestNcmVO.setId(rs.getInt("id"));
                fiscalCestNcmVO.setSegmento(rs.getString("segmento"));
                fiscalCestNcmVO.setDescricao(rs.getString("descricao"));
                fiscalCestNcmVO.setCest(rs.getString("cest"));
                fiscalCestNcmVO.setNcm(rs.getString("ncm"));

                if (id == 0) fiscalCestNcmVOList.add(fiscalCestNcmVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
}
