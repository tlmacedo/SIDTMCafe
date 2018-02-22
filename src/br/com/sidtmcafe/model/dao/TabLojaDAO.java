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
                lojaVO.setEndereco_ids(rs.getString("endereco_ids"));
                lojaVO.setTelefone_ids(rs.getString("telefone_ids"));
                lojaVO.setContato_ids(rs.getString("contato_ids"));
                lojaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));

                lojaVOList.add(lojaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabLojaVO loja) {
        loja.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(loja.getSituacaoSistema_id()));

        List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
        for (String strCodEndereco : loja.getEndereco_ids().split(";"))
            if (strCodEndereco != "")
                enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
        loja.setEnderecoVOList(enderecoVOList);

        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
        for (String strCodTelefone : loja.getTelefone_ids().split(";"))
            if (strCodTelefone != "")
                telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
        loja.setTelefoneVOList(telefoneVOList);

        List<TabContatoVO> contatoVOList = new ArrayList<>();
        for (String strCodContato : loja.getContato_ids().split(";"))
            if (strCodContato != "")
                contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
        loja.setContatoVOList(contatoVOList);

        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
        for (String strCodEmailHomePage : loja.getEmailHomePage_ids().split(";"))
            if (strCodEmailHomePage != "")
                emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
        loja.setEmailHomePageVOList(emailHomePageVOList);
    }

}
