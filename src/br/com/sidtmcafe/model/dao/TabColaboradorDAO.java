package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabColaboradorDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabColaboradorVO colaboradorVO;
    List<TabColaboradorVO> colaboradorVOList;

    public TabColaboradorVO getColaboradorVO(int idTabColaboradorVO) {
        buscaTabColaboradorVO(idTabColaboradorVO);
        if (colaboradorVO != null)
            addObjetosPesquisa(colaboradorVO);
        return colaboradorVO;
    }

    public List<TabColaboradorVO> getColaboradorVOList() {
        buscaTabColaboradorVO(-1);
        if (colaboradorVOList != null)
            for (TabColaboradorVO colaborador : colaboradorVOList)
                addObjetosPesquisa(colaborador);
        return colaboradorVOList;
    }

    void buscaTabColaboradorVO(int idTabColaboradorVO) {
        comandoSql = "SELECT * FROM tabColaborador ";
        if (idTabColaboradorVO > 0) comandoSql += " WHERE id = '" + idTabColaboradorVO + "' ";
        comandoSql += "ORDER BY nome ";

        colaboradorVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                colaboradorVO = new TabColaboradorVO();
                colaboradorVO.setId(rs.getInt("id"));
                colaboradorVO.setNome(rs.getString("nome"));
                colaboradorVO.setApelido(rs.getString("apelido"));
                colaboradorVO.setSenha(rs.getString("senha"));
                colaboradorVO.setSenhaSalt(rs.getString("senhaSalt"));
                colaboradorVO.setCargo_id(rs.getInt("cargo_id"));
                colaboradorVO.setLoja_id(rs.getInt("loja_id"));
                colaboradorVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                colaboradorVO.setEndereco_ids(rs.getString("endereco_ids"));
                colaboradorVO.setTelefone_ids(rs.getString("telefone_ids"));
                colaboradorVO.setContato_ids(rs.getString("contato_ids"));
                colaboradorVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));

                colaboradorVOList.add(colaboradorVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabColaboradorVO colaborador) {
        colaborador.setCargoVO(new TabCargoDAO().getCargoVO(colaborador.getCargo_id()));
        colaborador.setLojaVO(new TabLojaDAO().getLojaVO(colaborador.getLoja_id()));
        colaborador.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(colaborador.getSituacaoSistema_id()));

        List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
        for (String strCodEndereco : colaborador.getEndereco_ids().split(";"))
            if (strCodEndereco != "")
                enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
        colaborador.setEnderecoVOList(enderecoVOList);

        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
        for (String strCodTelefone : colaborador.getTelefone_ids().split(";"))
            if (strCodTelefone != "")
                telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
        colaborador.setTelefoneVOList(telefoneVOList);

        List<TabContatoVO> contatoVOList = new ArrayList<>();
        for (String strCodContato : colaborador.getContato_ids().split(";"))
            if (strCodContato != "")
                contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
        colaborador.setContatoVOList(contatoVOList);

        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
        for (String strCodEmailHomePage : colaborador.getEmailHomePage_ids().split(";"))
            if (strCodEmailHomePage != "")
                emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
        colaborador.setEmailHomePageVOList(emailHomePageVOList);
    }

}
