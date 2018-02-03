package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEmailHomePageVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEmailHomePageDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEmailHomePageVO emailHomePageVO;
    List<TabEmailHomePageVO> emailHomePageVOList;

    public TabEmailHomePageVO getEmailHomePageVO(int idTabEmailHomePageVO) {
        if (idTabEmailHomePageVO > 0)
            buscaTabEmailHomePageVO(idTabEmailHomePageVO, -1);
        if (emailHomePageVO == null)
            emailHomePageVO = new TabEmailHomePageVO();
        return emailHomePageVO;
    }

    public TabEmailHomePageVO getEmailHomePageVO(int idTabEmailHomePageVO, boolean isEmail) {
        int buscaEmail = 0;
        if (isEmail) buscaEmail = 1;
        buscaTabEmailHomePageVO(idTabEmailHomePageVO, buscaEmail);
        if (emailHomePageVO == null)
            emailHomePageVO = new TabEmailHomePageVO();
        return emailHomePageVO;
    }


    public List<TabEmailHomePageVO> getEmailHomePageVOList() {
        buscaTabEmailHomePageVO(0, -1);
        if (emailHomePageVOList == null)
            emailHomePageVOList.add(new TabEmailHomePageVO());
        return emailHomePageVOList;
    }

    void buscaTabEmailHomePageVO(int idTabEmailHomePageVO, int isEmail) {
        comandoSql = "SELECT * FROM tabEmailHomePage ";
        if (idTabEmailHomePageVO > 0) comandoSql += "WHERE id = '" + idTabEmailHomePageVO + "' ";
        if (isEmail >= 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "isEmail = '" + isEmail + "' ";
        }
        comandoSql += "ORDER BY isEmail, id DESC ";

        emailHomePageVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                emailHomePageVO = new TabEmailHomePageVO();
                emailHomePageVO.setId(rs.getInt("id"));
                emailHomePageVO.setDescricao(rs.getString("descricao"));
                emailHomePageVO.setIsEmail(rs.getInt("isEmail"));

                emailHomePageVOList.add(emailHomePageVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
