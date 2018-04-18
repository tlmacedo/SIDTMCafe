package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabContatoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabContatoVO tabContatoVO;

    public TabContatoVO getTabContatoVO(int id) {
        buscaTabContatoVO(id);
        if (tabContatoVO != null)
            addObjetosPesquisa(tabContatoVO);
        return tabContatoVO;
    }

    void buscaTabContatoVO(int id) {
        comandoSql = "SELECT id, descricao, sisCargo_id " +
                "FROM tabContato " +
                "WHERE id = '" + id + "' ";

        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                tabContatoVO = new TabContatoVO();
                tabContatoVO.setId(rs.getInt("id"));
                tabContatoVO.setDescricao(rs.getString("descricao"));
                tabContatoVO.setSisCargo_id(rs.getInt("sisCargo_id"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabContatoVO contatoVO) {
        contatoVO.setSisCargoVO(new SisCargoDAO().getSisCargoVO(contatoVO.getSisCargo_id()));

        List<RelContatoEmailHomePageVO> relContatoEmailHomePageVOList
                = new ArrayList<RelContatoEmailHomePageVO>(new RelContatoEmailHomePageDAO().getRelContatoEmailHomePageVOList(contatoVO.getId()));
        List<TabEmailHomePageVO> tabEmailHomePageVOList = new ArrayList<TabEmailHomePageVO>();
        for (RelContatoEmailHomePageVO relContatoEmailHomePageVO : relContatoEmailHomePageVOList) {
            tabEmailHomePageVOList.add(new TabEmailHomePageDAO().getTabEmailHomePageVO(relContatoEmailHomePageVO.getTabContato_id()));
        }
        contatoVO.setTabEmailHomePageVOList(tabEmailHomePageVOList);


        List<RelContatoTelefoneVO> relContatoTelefoneVOList
                = new ArrayList<RelContatoTelefoneVO>(new RelContatoTelefoneDAO().getRelContatoTelefoneVOVOList(contatoVO.getId()));
        List<TabTelefoneVO> tabTelefoneVOList = new ArrayList<TabTelefoneVO>();
        for (RelContatoTelefoneVO relContatoTelefoneVO : relContatoTelefoneVOList) {
            tabTelefoneVOList.add(new TabTelefoneDAO().getTabTelefoneVO(relContatoTelefoneVO.getTabTelefone_id()));
        }
        contatoVO.setTabTelefoneVOList(tabTelefoneVOList);

    }

    public void updateTabContatoVO(Connection conn, TabContatoVO contatoVO) throws SQLException {
        comandoSql = "UPDATE tabContato SET ";
        comandoSql += "descricao = '" + contatoVO.getDescricao() + "', ";
        comandoSql += "sisCargo_id = " + contatoVO.getSisCargo_id() + " ";
        comandoSql += "WHERE id = " + contatoVO.getId() + " ";

        getUpdateBancoDados(conn, comandoSql);
    }

    public int insertTabContatoVO(Connection conn, TabContatoVO contatoVO) throws SQLException {
        comandoSql = "INSERT INTO tabContato ";
        comandoSql += "(descricao, sisCargo_id) ";
        comandoSql += "VALUES (";
        comandoSql += "'" + contatoVO.getDescricao() + "', ";
        comandoSql += +contatoVO.getSisCargo_id() + " ";
        comandoSql += ")";

        return getInsertBancoDados(conn, comandoSql);
    }

    public void deleteTabContatoVO(Connection conn, TabContatoVO contatoVO) throws SQLException {
        comandoSql = "DELETE FROM tabContato ";
        comandoSql += "WHERE id = " + contatoVO.getId() + " ";

        getDeleteBancoDados(conn, comandoSql);
    }

}
