package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEmpresaDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresaVO empresaVO;
    List<TabEmpresaVO> empresaVOList;

    public TabEmpresaVO getEmpresaVO(int idTabEmpresaVO) {
        buscaTabEmpresaVO(idTabEmpresaVO);
        if (empresaVO == null)
            empresaVO = new TabEmpresaVO();
        return empresaVO;
    }

    public List<TabEmpresaVO> getEmpresaVOList() {
        buscaTabEmpresaVO(0);
        if (empresaVOList == null)
            empresaVOList.add(new TabEmpresaVO());
        return empresaVOList;
    }

    void buscaTabEmpresaVO(int idTabEmpresaVO) {
        comandoSql = "SELECT * FROM tabEmpresa ";
        if (idTabEmpresaVO > 0) comandoSql += "WHERE id = '" + idTabEmpresaVO + "' ";
        comandoSql = "ORDER BY razao, fantasia ";

        empresaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                empresaVO = new TabEmpresaVO();
                empresaVO.setId(rs.getInt("id"));
                empresaVO.setIsPessoaJuridica(rs.getInt("isPessoaJuridica"));
                empresaVO.setCnpj(rs.getString("cnpj"));
                empresaVO.setIe(rs.getString("ie"));
                empresaVO.setRazao(rs.getString("razao"));
                empresaVO.setFantasia(rs.getString("fantasia"));
                empresaVO.setIsCliente(rs.getInt("isCliente"));
                empresaVO.setIsFornecedor(rs.getInt("isFornecedor"));
                empresaVO.setIsTransportadora(rs.getInt("isTransportadora"));
                empresaVO.setEndereco_ids(rs.getString("endereco_ids"));
                empresaVO.setTelefone_ids(rs.getString("telefone_ids"));
                empresaVO.setContato_ids(rs.getString("contato_ids"));
                empresaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));
                empresaVO.setUsuarioCadastro_id(rs.getInt("usuarioCadastro_id"));
                empresaVO.setDataCadastro(rs.getTimestamp("dataCadastro"));
                empresaVO.setUsuarioAtualizacao_id(rs.getInt("usuarioAtualizacao_id"));
                empresaVO.setDataAtualizacao(rs.getTimestamp("dataAtualizacao"));
                empresaVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                empresaVO.setDataAbertura(rs.getTimestamp("dataAbertura"));
                empresaVO.setNaturezaJuridica(rs.getString("naturezaJuridica"));

                addObjetosPesquisa();

                empresaVOList.add(empresaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa() {
        List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
        for (String strCodEndereco : empresaVO.getEndereco_ids().split(";")) {
            enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
        }
        empresaVO.setEnderecoVOList(enderecoVOList);

        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
        for (String strCodTelefone : empresaVO.getTelefone_ids().split(";")) {
            telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
        }
        empresaVO.setTelefoneVOList(telefoneVOList);

        List<TabContatoVO> contatoVOList = new ArrayList<>();
        for (String strCodContato : empresaVO.getContato_ids().split(";")) {
            contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
        }
        empresaVO.setContatoVOList(contatoVOList);

        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
        for (String strCodEmailHomePage : empresaVO.getEmailHomePage_ids().split(";")) {
            emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
        }
        empresaVO.setEmailHomePageVOList(emailHomePageVOList);

        empresaVO.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(empresaVO.getUsuarioCadastro_id()));
        empresaVO.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(empresaVO.getUsuarioAtualizacao_id()));
        empresaVO.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(empresaVO.getSituacaoSistema_id()));
    }

}
