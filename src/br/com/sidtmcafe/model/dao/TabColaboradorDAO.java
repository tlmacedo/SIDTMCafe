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

    public TabColaboradorVO getColaboradorVO(int idTabColaboradorVO, boolean detalhe) {
        buscaTabColaboradorVO(idTabColaboradorVO);
        if (colaboradorVO != null)
            if (detalhe)
                addObjetosPesquisa(colaboradorVO);
        return colaboradorVO;
    }

    public List<TabColaboradorVO> getColaboradorVOList(boolean detalhe) {
        buscaTabColaboradorVO(-1);
        if (colaboradorVOList != null)
            if (detalhe)
                for (TabColaboradorVO colaborador : colaboradorVOList)
                    addObjetosPesquisa(colaborador);
        return colaboradorVOList;
    }

    void buscaTabColaboradorVO(int idTabColaboradorVO) {
        comandoSql = "SELECT colab.id, colab.nome, colab.apelido, colab.senha, colab.senhaSalt, colab.sisCargo_id, " +
                "cargo.descricao as cargo, colab.tabLoja_id, loja.tabEmpresa_id, emp.razao, emp.fantasia, " +
                "colab.sisSituacaoSistema_id FROM tabColaborador as colab LEFT JOIN sisCargo as cargo ON " +
                "colab.sisCargo_id = cargo.id LEFT JOIN tabLoja as loja ON colab.tabLoja_id = loja.id " +
                "LEFT JOIN tabEmpresa as emp ON loja.tabEmpresa_id = emp.id ";
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
                colaboradorVO.setSisCargo_id(rs.getInt("sisCargo_id"));
                colaboradorVO.setTabLoja_id(rs.getInt("tabLoja_id"));
                colaboradorVO.setSisSituacaoSistema_id(rs.getInt("sisSituacaoSistema_id"));

                colaboradorVOList.add(colaboradorVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabColaboradorVO colaborador) {
        colaborador.setCargoVO(new SisCargoDAO().getCargoVO(colaborador.getSisCargo_id()));
        colaborador.setLojaVO(new TabLojaDAO().getLojaVO(colaborador.getTabLoja_id()));
        colaborador.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(colaborador.getSisSituacaoSistema_id()));
//        colaborador.setCargoVO(new SisCargoDAO().getCargoVO(colaborador.getSisCargo_id()));
//        colaborador.setLojaVO(new TabLojaDAO().getLojaVO(colaborador.getLoja_id()));
//        colaborador.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(colaborador.getSituacaoSistema_id()));
//
//        List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
//        for (String strCodEndereco : colaborador.getEndereco_ids().split(";"))
//            if (strCodEndereco != "")
//                enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
//        colaborador.setEnderecoVOList(enderecoVOList);
//
//        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
//        for (String strCodTelefone : colaborador.getTelefone_ids().split(";"))
//            if (strCodTelefone != "")
//                telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
//        colaborador.setTelefoneVOList(telefoneVOList);
//
//        List<TabContatoVO> contatoVOList = new ArrayList<>();
//        for (String strCodContato : colaborador.getContato_ids().split(";"))
//            if (strCodContato != "")
//                contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
//        colaborador.setContatoVOList(contatoVOList);
//
//        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
//        for (String strCodEmailHomePage : colaborador.getEmailHomePage_ids().split(";"))
//            if (strCodEmailHomePage != "")
//                emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
//        colaborador.setEmailHomePageVOList(emailHomePageVOList);
    }

}
