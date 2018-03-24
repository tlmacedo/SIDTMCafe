package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabLojaDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabLojaVO lojaVO;
    List<TabLojaVO> lojaVOList;

    public TabLojaVO getLojaVO(int idTabLoja) {
        buscaTabLojaVO(idTabLoja);
        if (lojaVO != null)
            addObjetosPesquisa(lojaVO);
        return lojaVO;
    }

    public List<TabLojaVO> getLojaVOList() {
        buscaTabLojaVO(-1);
        if (lojaVOList != null)
            for (TabLojaVO loja : lojaVOList)
                addObjetosPesquisa(loja);
        return lojaVOList;
    }

    void buscaTabLojaVO(int idTabLoja) {
        comandoSql = "SELECT id, tabEmpresa_id FROM tabLoja ";
        if (idTabLoja > 0) comandoSql += "WHERE id = '" + idTabLoja + "' ";
        comandoSql += "ORDER BY id DESC ";

        lojaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                lojaVO = new TabLojaVO();
                lojaVO.setId(rs.getInt("id"));
                lojaVO.setTabEmpresa_id(rs.getInt("tabEmpresa_id"));

                lojaVOList.add(lojaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabLojaVO loja) {

        loja.setEmpresaVO(new TabEmpresaDAO().getEmpresaVO(loja.getTabEmpresa_id()));

    }

}
