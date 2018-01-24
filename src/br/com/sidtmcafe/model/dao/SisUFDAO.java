package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisUFVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisUFDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisUFVO ufVO;
    List<SisUFVO> ufVOList;

    public SisUFVO getUfVO(int idSisUFVO) {
        buscaSisUFVO(idSisUFVO);
        if (ufVO == null)
            ufVO = new SisUFVO();
        return ufVO;
    }

    public List<SisUFVO> getUfVOList() {
        buscaSisUFVO(0);
        if (ufVOList == null)
            ufVOList.add(new SisUFVO());
        return ufVOList;
    }

    void buscaSisUFVO(int idSisUFVO) {
        comandoSql = "SELECT * FROM sisUF ";
        if (idSisUFVO > 0) comandoSql += "WHERE id = '" + idSisUFVO + "' ";
        comandoSql += "ORDER BY sigla ";

        ufVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                ufVO = new SisUFVO();
                ufVO.setId(rs.getInt("id"));
                ufVO.setDescricao(rs.getString("descricao"));
                ufVO.setSigla(rs.getString("sigla"));
                ufVO.setIbge_id(rs.getInt("ibge_id"));

                ufVOList.add(ufVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
