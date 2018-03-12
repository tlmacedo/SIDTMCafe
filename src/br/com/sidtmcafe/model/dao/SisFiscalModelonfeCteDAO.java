package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFiscalModeloNfeCteVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFiscalModelonfeCteDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFiscalModeloNfeCteVO sisFiscalModeloNfeCteVO;
    List<SisFiscalModeloNfeCteVO> sisFiscalModeloNfeCteVOList;

    public SisFiscalModeloNfeCteVO getFiscalModeloNfeCteVO(int idSisFiscalModeloNfeCteVO) {
        buscaSisFiscalModeloNfeCteVO(idSisFiscalModeloNfeCteVO);
        return sisFiscalModeloNfeCteVO;
    }

    public List<SisFiscalModeloNfeCteVO> getSisFiscalModeloNfeCteVOList() {
        buscaSisFiscalModeloNfeCteVO(0);
        return sisFiscalModeloNfeCteVOList;
    }

    void buscaSisFiscalModeloNfeCteVO(int idSisFiscalModeloNfeCteVO) {
        comandoSql = "SELECT * FROM sisFiscalModeloNfeCte ";
        if (idSisFiscalModeloNfeCteVO > 0) comandoSql += "WHERE id = '" + idSisFiscalModeloNfeCteVO + "' ";
        comandoSql += "ORDER BY id ";

        rs = getResultadosBandoDados(comandoSql);
        sisFiscalModeloNfeCteVOList = new ArrayList<>();
        try {
            while (rs.next()) {
                sisFiscalModeloNfeCteVO = new SisFiscalModeloNfeCteVO();
                sisFiscalModeloNfeCteVO.setId(rs.getInt("id"));
                sisFiscalModeloNfeCteVO.setDescricao(rs.getString("descricao"));

                sisFiscalModeloNfeCteVOList.add(sisFiscalModeloNfeCteVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
