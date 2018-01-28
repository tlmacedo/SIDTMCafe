package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabCargoVO;
import br.com.sidtmcafe.model.vo.TabContatoVO;
import br.com.sidtmcafe.model.vo.TabEmailHomePageVO;
import br.com.sidtmcafe.model.vo.TabTelefoneVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabContatoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabContatoVO contatoVO;
    List<TabContatoVO> contatoVOList;

    public TabContatoVO getContatoVO(int idTabContatoVO) {
        buscaTabContatoVO(idTabContatoVO);
        if (contatoVO == null)
            contatoVO = new TabContatoVO();
        return contatoVO;
    }

    public List<TabContatoVO> getContatoVOList() {
        buscaTabContatoVO(0);
        if (contatoVOList == null)
            contatoVOList.add(new TabContatoVO());
        return contatoVOList;
    }

    void buscaTabContatoVO(int idTabContatoVO) {
        comandoSql = "SELECT * FROM tabContato ";
        if (idTabContatoVO > 0) comandoSql += "WHERE id = '" + idTabContatoVO + "' ";
        comandoSql += "ORDER BY id DESC ";

        contatoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                contatoVO = new TabContatoVO();
                contatoVO.setId(rs.getInt("id"));
                contatoVO.setDescricao(rs.getString("descricao"));
                contatoVO.setCargo_id(rs.getInt("cargo_id"));
                contatoVO.setTelefone_ids(rs.getString("telefone_ids"));
                contatoVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));

                addObjetosPesquisa();

                contatoVOList.add(contatoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa() {
        contatoVO.setCargoVO(new TabCargoDAO().getCargoVO(contatoVO.getId()));

        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
        for (String strCodTelefone : contatoVO.getTelefone_ids().split(";")) {
            telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
        }
        contatoVO.setTelefoneVOList(telefoneVOList);

        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
        for (String strCodEmailHomePage : contatoVO.getEmailHomePage_ids().split(";")) {
            emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
        }
        contatoVO.setEmailHomePageVOList(emailHomePageVOList);
    }

}
