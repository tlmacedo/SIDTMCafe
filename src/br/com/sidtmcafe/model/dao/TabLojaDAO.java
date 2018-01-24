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
        if (lojaVO == null)
            lojaVO = new TabLojaVO();
        return lojaVO;
    }

    public List<TabLojaVO> getLojaVOList() {
        buscaTabLojaVO(0);
        if (lojaVOList == null)
            lojaVOList.add(new TabLojaVO());
        return lojaVOList;
    }

    void buscaTabLojaVO(int idTabLoja) {
        comandoSql = "SELECT * FROM tabLoja ";
        if (idTabLoja > 0) comandoSql += "WHERE id = '" + idTabLoja + "' ";
        comandoSql += "ORDER BY id DESC ";

        lojaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                lojaVO = new TabLojaVO();
                lojaVO.setId(rs.getInt("id"));
                lojaVO.setCnpj(rs.getString("cnpj"));
                lojaVO.setIe(rs.getString("ie"));
                lojaVO.setRazao(rs.getString("razao"));
                lojaVO.setFantasia(rs.getString("fantasia"));

                lojaVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                lojaVO.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(lojaVO.getSituacaoSistema_id()));

                lojaVO.setEndereco_ids(rs.getString("endereco_ids"));
                List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
                for (String strCodEndereco : lojaVO.getEndereco_ids().split(";")) {
                    enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
                }
                lojaVO.setEnderecoVOList(enderecoVOList);

                lojaVO.setTelefone_ids(rs.getString("telefone_ids"));
                List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
                for (String strCodTelefone : lojaVO.getTelefone_ids().split(";")) {
                    telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
                }
                lojaVO.setTelefoneVOList(telefoneVOList);


                lojaVO.setContato_ids(rs.getString("contato_ids"));
                List<TabContatoVO> contatoVOList = new ArrayList<>();
                for (String strCodContato : lojaVO.getContato_ids().split(";")) {
                    contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
                }
                lojaVO.setContatoVOList(contatoVOList);



                lojaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));
                List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
                for (String strCodEmailHomePage : lojaVO.getEmailHomePage_ids().split(";")) {
                    emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
                }
                lojaVO.setEmailHomePageVOList(emailHomePageVOList);

                lojaVOList.add(lojaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
