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
        if (idSisTelefoneOperadoraVO>0)
        buscaSisTelefoneOperadoraVO(idSisTelefoneOperadoraVO);
        if (telefoneOperadoraVO == null)
            telefoneOperadoraVO = new SisTelefoneOperadoraVO();
        return telefoneOperadoraVO;
    }

    public List<SisTelefoneOperadoraVO> getTelefoneOperadoraVOList() {
        buscaSisTelefoneOperadoraVO(0);
        if (telefoneOperadoraVOList == null)
            telefoneOperadoraVOList.add(new SisTelefoneOperadoraVO());
        return telefoneOperadoraVOList;
    }

    void buscaSisTelefoneOperadoraVO(int idSisTelefoneOperadoraVO) {
        comandoSql = "SELECT * FROM sisTelefoneOperadora ";
        if (idSisTelefoneOperadoraVO > 0) comandoSql += "WHERE id ='" + idSisTelefoneOperadoraVO + "' ";
        comandoSql += "ORDER BY tipo DESC, descricao ";

        telefoneOperadoraVOList = new ArrayList<>();
//        System.out.println("comandoSql: " + comandoSql);
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                telefoneOperadoraVO = new SisTelefoneOperadoraVO();
                telefoneOperadoraVO.setId(rs.getInt("id"));
                telefoneOperadoraVO.setDescricao(rs.getString("descricao"));
                telefoneOperadoraVO.setTipo(rs.getInt("tipo"));
                telefoneOperadoraVO.setCodigoDDD(rs.getInt("codigoDDD"));

                telefoneOperadoraVOList.add(telefoneOperadoraVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
