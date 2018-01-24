package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisSituacaoSistemaVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisSituacaoSistemaDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisSituacaoSistemaVO situacaoSistemaVO;
    List<SisSituacaoSistemaVO> situacaoSistemaVOList;

    public SisSituacaoSistemaVO getSituacaoSistemaVO(int idSisSituacaoSistemaVO) {
        buscaSisSituacaoSistemaVO(idSisSituacaoSistemaVO);
        if (situacaoSistemaVO == null)
            situacaoSistemaVO = new SisSituacaoSistemaVO();
        return situacaoSistemaVO;
    }

    public List<SisSituacaoSistemaVO> getSituacaoSistemaVOList() {
        buscaSisSituacaoSistemaVO(0);
        if (situacaoSistemaVOList == null)
            situacaoSistemaVOList.add(new SisSituacaoSistemaVO());
        return situacaoSistemaVOList;
    }

    void buscaSisSituacaoSistemaVO(int idSisSituacaoSistemaVO) {
        comandoSql = "SELECT * FROM sisSituacaoSistema ";
        if (idSisSituacaoSistemaVO > 0) comandoSql += "WHERE id = '" + idSisSituacaoSistemaVO + "' ";
        comandoSql += "ORDER BY descricao ";

        situacaoSistemaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                situacaoSistemaVO = new SisSituacaoSistemaVO();
                situacaoSistemaVO.setId(rs.getInt("id"));
                situacaoSistemaVO.setDescricao(rs.getString("descricao"));
                situacaoSistemaVO.setClassificacao(rs.getInt("classificacao"));

                situacaoSistemaVOList.add(situacaoSistemaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
