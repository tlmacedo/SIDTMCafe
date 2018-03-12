package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEmpresaDAO extends BuscaBandoDados implements Constants {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresaVO empresaVO;
    List<TabEmpresaVO> empresaVOList;

    public TabEmpresaVO getEmpresaVO(int idTabEmpresaVO) {
        buscaTabEmpresaVO(idTabEmpresaVO, false, false, false);
        if (empresaVO != null)
            addObjetosPesquisa(empresaVO);
        return empresaVO;
    }

    public List<TabEmpresaVO> getEmpresaVOList(boolean isCliente, boolean isFornecedor, boolean isTransportadora) {
        buscaTabEmpresaVO(-1, isCliente, isFornecedor, isTransportadora);
        if (empresaVOList != null)
            for (TabEmpresaVO empresa : empresaVOList) {
                addObjetosPesquisa(empresa);
            }
        return empresaVOList;
    }

    void buscaTabEmpresaVO(int idTabEmpresaVO, boolean isCliente, boolean isFornecedor, boolean isTransportadora) {
        comandoSql = "SELECT * FROM tabEmpresa ";
        if (idTabEmpresaVO > 0) comandoSql += "WHERE id = '" + idTabEmpresaVO + "' ";
        if (isCliente) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "isCliente = 1 ";
        }
        if (isFornecedor) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "isFornecedor = 1 ";
        }
        if (isTransportadora) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "isTransportadora = 1 ";
        }

        comandoSql += "ORDER BY razao, fantasia ";

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
                empresaVO.setDataAbertura(rs.getDate("dataAbertura"));
                empresaVO.setNaturezaJuridica(rs.getString("naturezaJuridica"));

                empresaVOList.add(empresaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabEmpresaVO empresa) {
        List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
        for (String strCodEndereco : empresa.getEndereco_ids().split(";"))
            if (strCodEndereco != "")
                enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
        empresa.setEnderecoVOList(enderecoVOList);

        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
        for (String strCodTelefone : empresa.getTelefone_ids().split(";"))
            if (strCodTelefone != "")
                telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
        empresa.setTelefoneVOList(telefoneVOList);

        List<TabContatoVO> contatoVOList = new ArrayList<>();
        for (String strCodContato : empresa.getContato_ids().split(";"))
            if (strCodContato != "")
                contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
        empresa.setContatoVOList(contatoVOList);

        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
        for (String strCodEmailHomePage : empresa.getEmailHomePage_ids().split(";"))
            if (strCodEmailHomePage != "")
                emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
        empresa.setEmailHomePageVOList(emailHomePageVOList);

        empresa.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(empresa.getUsuarioCadastro_id()));
        empresa.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(empresa.getUsuarioAtualizacao_id()));
        empresa.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(empresa.getSituacaoSistema_id()));

        empresa.setDetalheReceitaFederalVOList(new TabEmpresaDetalheReceitaFederalDAO().getDetalheReceitaFederalVOList(empresa.getId()));
    }

    public void updateTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
        comandoSql = "UPDATE tabEmpresa SET ";
        comandoSql += "isPessoaJuridica = " + empresaVO.getIsPessoaJuridica() + ", ";
        comandoSql += "cnpj = '" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
        comandoSql += "ie = '" + empresaVO.getIe().replaceAll("'", "") + "', ";
        comandoSql += "razao = '" + empresaVO.getRazao().replaceAll("'", "") + "', ";
        comandoSql += "fantasia = '" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
        comandoSql += "isCliente = " + empresaVO.getIsCliente() + ", ";
        comandoSql += "isFornecedor = " + empresaVO.getIsFornecedor() + ", ";
        comandoSql += "isTransportadora = " + empresaVO.getIsTransportadora() + ", ";
        comandoSql += "endereco_ids = '" + empresaVO.getEndereco_ids() + "', ";
        comandoSql += "telefone_ids = '" + empresaVO.getTelefone_ids() + "', ";
        comandoSql += "contato_ids = '" + empresaVO.getContato_ids() + "', ";
        comandoSql += "emailHomePage_ids = '" + empresaVO.getEmailHomePage_ids() + "', ";
        comandoSql += "usuarioAtualizacao_id = " + empresaVO.getUsuarioAtualizacao_id() + ", ";
        comandoSql += "situacaoSistema_id = " + empresaVO.getSituacaoSistema_id() + ", ";
        comandoSql += "dataAbertura = '" + empresaVO.getDataAbertura() + "', ";
        comandoSql += "naturezaJuridica = '" + empresaVO.getNaturezaJuridica().replaceAll("[']", "") + "' ";
        comandoSql += "WHERE id = " + empresaVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
        comandoSql = "INSERT INTO tabEmpresa ";
        comandoSql += "(isPessoaJuridica, cnpj, ie, razao, fantasia, isCliente, isFornecedor, ";
        comandoSql += "isTransportadora, endereco_ids, telefone_ids, contato_ids, emailHomePage_ids, ";
        comandoSql += "usuarioCadastro_id, situacaoSistema_id, dataAbertura, naturezaJuridica) ";
        comandoSql += "VALUES(";
        comandoSql += empresaVO.getIsPessoaJuridica() + ", ";
        comandoSql += "'" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getIe().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getRazao().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
        comandoSql += empresaVO.getIsCliente() + ", ";
        comandoSql += empresaVO.getIsFornecedor() + ", ";
        comandoSql += empresaVO.getIsTransportadora() + ", ";
        comandoSql += "'" + empresaVO.getEndereco_ids() + "', ";
        comandoSql += "'" + empresaVO.getTelefone_ids() + "', ";
        comandoSql += "'" + empresaVO.getContato_ids() + "', ";
        comandoSql += "'" + empresaVO.getEmailHomePage_ids() + "', ";
        comandoSql += empresaVO.getUsuarioCadastro_id() + ", ";
        comandoSql += empresaVO.getSituacaoSistema_id() + ", ";
        comandoSql += empresaVO.getDataAbertura() + ", ";
        comandoSql += "'" + empresaVO.getNaturezaJuridica().replaceAll("'", "") + "'";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);
    }

}
