package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisTelefoneOperadoraVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisTelefoneOperadoraDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisTelefoneOperadoraVO telefoneOperadoraVO;
    List<SisTelefoneOperadoraVO> telefoneOperadoraVOList;

    public SisTelefoneOperadoraVO getTelefoneOperadoraVO(int idSisTelefoneOperadoraVO) {
        buscaSisTelefoneOperadoraVO(idSisTelefoneOperadoraVO);
        return telefoneOperadoraVO;
    }

    public List<SisTelefoneOperadoraVO> getTelefoneOperadoraVOList() {
        buscaSisTelefoneOperadoraVO(-1);
        return telefoneOperadoraVOList;
    }

    void buscaSisTelefoneOperadoraVO(int idSisTelefoneOperadoraVO) {
        comandoSql = "SELECT * FROM sisTelefoneOperadora ";
        if (idSisTelefoneOperadoraVO > 0) comandoSql += "WHERE id ='" + idSisTelefoneOperadoraVO + "' ";
        comandoSql += "ORDER BY tipo DESC, descricao ";

        telefoneOperadoraVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                telefoneOperadoraVO = new SisTelefoneOperadoraVO();
                telefoneOperadoraVO.setId(rs.getInt("id"));
                telefoneOperadoraVO.setDescricao(rs.getString("descricao"));
                telefoneOperadoraVO.setTipo(rs.getInt("tipo"));
                telefoneOperadoraVO.setCodigoDDD(rs.getInt("ddd"));

                telefoneOperadoraVOList.add(telefoneOperadoraVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
