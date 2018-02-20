package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabTelefoneVO;

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
        if (telefoneVO == null)
            telefoneVO = new TabTelefoneVO();
        return telefoneVO;
    }

    public List<TabTelefoneVO> getTelefoneVOList() {
        buscaTabTelefoneVO(-1);
        if (telefoneVOList == null)
            telefoneVOList.add(new TabTelefoneVO());
        return telefoneVOList;
    }

    void buscaTabTelefoneVO(int idTabTelefoneVO) {
        comandoSql = "SELECT * FROM tabTelefone ";
        if (idTabTelefoneVO >= 0) comandoSql += "WHERE id = '" + idTabTelefoneVO + "' ";
        comandoSql += "ORDER BY id DESC ";

        telefoneVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                telefoneVO = new TabTelefoneVO();
                telefoneVO.setId(rs.getInt("id"));
                telefoneVO.setDescricao(rs.getString("descricao"));
                telefoneVO.setTelefoneOperadora_id(rs.getInt("telefoneOperadora_id"));

                addObjetosPesquisa();

                telefoneVOList.add(telefoneVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa() {
        telefoneVO.setTelefoneOperadoraVO(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVO(telefoneVO.getTelefoneOperadora_id()));
    }

    public void updateTelefoneVO(TabTelefoneVO telefoneVO) {
        comandoSql = "UPDATE tabTelefone SET ";
        comandoSql += "descricao = " + telefoneVO.getDescricao() + ", ";
        comandoSql += "telefoneOperadora_id = " + telefoneVO.getTelefoneOperadora_id() + ", ";
        comandoSql += "WHERE = " + telefoneVO.getId() + " ";

        if (getUpdateBancoDados("tabTelefone", comandoSql)) ;
    }

    public void insertTelefoneVO(TabTelefoneVO telefoneVO) {
        comandoSql = "INSERT INTO tabTelefone ";
        comandoSql += "(descricao, telefoneOperadora_id) ";
        comandoSql += "VALUES(";
        comandoSql += telefoneVO.getDescricao() + ", ";
        comandoSql += telefoneVO.getTelefoneOperadora_id();
        comandoSql += ") ";

        if (getInsertBancoDados("tabTelefone", comandoSql)) ;

    }
}
