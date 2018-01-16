package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisMenuPrincipalVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisMenuPrincipalDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisMenuPrincipalVO menuPrincipalVO;
    List<SisMenuPrincipalVO> menuPrincipalVOList;

    public SisMenuPrincipalVO getMenuPrincipalVO(int idSisMenuPrincipalVO) {
        buscaSisMenuPrincipalVO(idSisMenuPrincipalVO);
        if (menuPrincipalVO == null)
            menuPrincipalVO = new SisMenuPrincipalVO();
        return menuPrincipalVO;
    }

    public List<SisMenuPrincipalVO> getMenuPrincipalVOList() {
        buscaSisMenuPrincipalVO(0);
        if (menuPrincipalVOList == null)
            menuPrincipalVOList.add(new SisMenuPrincipalVO());
        return menuPrincipalVOList;
    }

    void buscaSisMenuPrincipalVO(int idSisMenuPrincipalVO) {
        comandoSql = "SELECT * FROM sisMenuPrincipal ";
        if (idSisMenuPrincipalVO > 0) comandoSql += "WHERE id '" + idSisMenuPrincipalVO + "' ";
        comandoSql += "ORDER BY id ";

        menuPrincipalVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                menuPrincipalVO = new SisMenuPrincipalVO();
                menuPrincipalVO.setId(rs.getInt("id"));
                menuPrincipalVO.setDescricao(rs.getString("descricao"));
                menuPrincipalVO.setFilho_id(rs.getInt("filho_id"));
                menuPrincipalVO.setIcoMenu(rs.getString("icoMenu"));
                menuPrincipalVO.setTabPane(rs.getInt("tabPane"));
                menuPrincipalVO.setTeclaAtalho(rs.getString("teclaAtalho"));

                menuPrincipalVOList.add(menuPrincipalVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }
}
