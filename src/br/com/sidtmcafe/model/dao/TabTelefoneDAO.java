package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabTelefoneVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabTelefoneDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabTelefoneVO telefoneVO;
    List<TabTelefoneVO> telefoneVOList;

    public TabTelefoneVO getTelefoneVO(int idTabTelefoneVO) {
        buscaTabTelefoneVO(idTabTelefoneVO);
        if (telefoneVO != null)
            addObjetosPesquisa(telefoneVO);
        return telefoneVO;
    }

    public List<TabTelefoneVO> getTelefoneVOList() {
        buscaTabTelefoneVO(-1);
        if (telefoneVOList != null)
            for (TabTelefoneVO telefone : telefoneVOList)
                addObjetosPesquisa(telefone);
        return telefoneVOList;
    }

    void buscaTabTelefoneVO(int idTabTelefoneVO) {
        comandoSql = "SELECT * FROM tabTelefone ";
        if (idTabTelefoneVO > 0) comandoSql += "WHERE id = '" + idTabTelefoneVO + "' ";
        comandoSql += "ORDER BY id DESC ";

        telefoneVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                telefoneVO = new TabTelefoneVO();
                telefoneVO.setId(rs.getInt("id"));
                telefoneVO.setDescricao(rs.getString("descricao"));
                telefoneVO.setTelefoneOperadora_id(rs.getInt("telefoneOperadora_id"));

                telefoneVOList.add(telefoneVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabTelefoneVO telefone) {
        telefoneVO.setTelefoneOperadoraVO(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVO(telefone.getTelefoneOperadora_id()));
    }

    public void updateTabTelefoneVO(Connection conn, TabTelefoneVO telefoneVO) throws SQLException {
        comandoSql = "UPDATE tabTelefone SET ";
        comandoSql += "descricao = '" + telefoneVO.getDescricao() + "', ";
        comandoSql += "telefoneOperadora_id = " + telefoneVO.getTelefoneOperadora_id() + " ";
        comandoSql += "WHERE id = " + telefoneVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabTelefoneVO(Connection conn, TabTelefoneVO telefoneVO) throws SQLException {
        comandoSql = "INSERT INTO tabTelefone ";
        comandoSql += "(descricao, telefoneOperadora_id) ";
        comandoSql += "VALUES (";
        comandoSql += "'" + telefoneVO.getDescricao() + "', ";
        comandoSql += telefoneVO.getTelefoneOperadora_id();
        comandoSql += ")";

        return getInsertBancoDados(conn, comandoSql);
    }

}
