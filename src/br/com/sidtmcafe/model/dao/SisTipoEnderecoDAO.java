package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisTipoEnderecoVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisTipoEnderecoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisTipoEnderecoVO tipoEnderecoVO;
    List<SisTipoEnderecoVO> tipoEnderecoVOList;

    public SisTipoEnderecoVO getTipoEnderecoVO(int idSisTipoEnderecoVO) {
        buscaSisTipoEnderecoVO(idSisTipoEnderecoVO);
        return tipoEnderecoVO;
    }

    public List<SisTipoEnderecoVO> getTipoEnderecoVOList() {
        buscaSisTipoEnderecoVO(-1);
        return tipoEnderecoVOList;
    }

    void buscaSisTipoEnderecoVO(int idSisTipoEnderecoVO) {
        comandoSql = "SELECT * FROM sisTipoEndereco ";
        if (idSisTipoEnderecoVO > 0) comandoSql += "WHERE id = '" + idSisTipoEnderecoVO + "' ";
        comandoSql += "ORDER BY id ";

        tipoEnderecoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                tipoEnderecoVO = new SisTipoEnderecoVO();
                tipoEnderecoVO.setId(rs.getInt("id"));
                tipoEnderecoVO.setDescricao(rs.getString("descricao"));

                tipoEnderecoVOList.add(tipoEnderecoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
