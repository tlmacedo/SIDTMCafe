package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFreteSituacaoTributariaVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFreteSituacaoTributariaDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFreteSituacaoTributariaVO sisFreteSituacaoTributariaVO;
    List<SisFreteSituacaoTributariaVO> sisFreteSituacaoTributariaVOList;

    public SisFreteSituacaoTributariaVO getSisFreteSituacaoTributariaVO(int idSisFreteSituacaoTributariaVO) {
        buscaSisFreteSituacaoTributariaVO(idSisFreteSituacaoTributariaVO);
        return sisFreteSituacaoTributariaVO;
    }

    public List<SisFreteSituacaoTributariaVO> getSisFreteSituacaoTributariaVOList() {
        buscaSisFreteSituacaoTributariaVO(0);
        return sisFreteSituacaoTributariaVOList;
    }

    void buscaSisFreteSituacaoTributariaVO(int idSisFreteSituacaoTributariaVO) {
        comandoSql = "SELECT * FROM sisFreteSituacaoTributaria ";
        if (idSisFreteSituacaoTributariaVO > 0) comandoSql += "WHERE id = '" + idSisFreteSituacaoTributariaVO + "' ";
        comandoSql += "ORDER BY id ";

        rs = getResultadosBandoDados(comandoSql);
        sisFreteSituacaoTributariaVOList = new ArrayList<>();
        try {
            while (rs.next()) {
                sisFreteSituacaoTributariaVO = new SisFreteSituacaoTributariaVO();
                sisFreteSituacaoTributariaVO.setId(rs.getInt("id"));
                sisFreteSituacaoTributariaVO.setDescricao(rs.getString("descricao"));
                
                sisFreteSituacaoTributariaVOList.add(sisFreteSituacaoTributariaVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
