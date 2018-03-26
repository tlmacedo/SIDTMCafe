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
        buscaTabEmpresaVO(idTabEmpresaVO);
        if (empresaVO != null)
            addObjetosPesquisa(empresaVO);
        return empresaVO;
    }

    public List<TabEmpresaVO> getEmpresaVOList() {
        buscaTabEmpresaVO(-1);
        if (empresaVOList != null)
            for (TabEmpresaVO empresa : empresaVOList) {
                addObjetosPesquisa(empresa);
            }
        return empresaVOList;
    }

    void buscaTabEmpresaVO(int idTabEmpresaVO) {
        comandoSql = "SELECT * FROM tabEmpresa ";
        if (idTabEmpresaVO > 0) comandoSql += "WHERE id = '" + idTabEmpresaVO + "' ";
        comandoSql += "ORDER BY razao, fantasia ";

        empresaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                empresaVO = new TabEmpresaVO();
                empresaVO.setId(rs.getInt("id"));
                empresaVO.setIsEmpresa(rs.getInt("isEmpresa"));
                empresaVO.setCnpj(rs.getString("cnpj"));
                empresaVO.setIe(rs.getString("ie"));
                empresaVO.setRazao(rs.getString("razao"));
                empresaVO.setFantasia(rs.getString("fantasia"));
                empresaVO.setSituacaoSistema_id(rs.getInt("sisSituacaoSistema_id"));
                empresaVO.setUsuarioCadastro_id(rs.getInt("usuarioCadastro_id"));
                empresaVO.setDataCadastro(rs.getTimestamp("dataCadastro"));
                empresaVO.setUsuarioAtualizacao_id(rs.getInt("usuarioAtualizacao_id"));
                empresaVO.setDataAtualizacao(rs.getTimestamp("dataAtualizacao"));
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

        empresa.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(empresa.getUsuarioCadastro_id(), false));
        empresa.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(empresa.getUsuarioAtualizacao_id(), false));
        empresa.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(empresa.getSituacaoSistema_id()));

        List<TabEnderecoVO> end = new ArrayList<>();
        List<SisRelacaoEmpresaEnderecoVO> relEmpEnd = new ArrayList<>();
        relEmpEnd = new SisRelacaoEmpresaEnderecoDAO().getRelacaoEmpresaEnderecoVOList(empresa.getId());

        for (SisRelacaoEmpresaEnderecoVO rel : relEmpEnd){
            //end.add()
        }


        //empresa.setDetalheReceitaFederalVOList(new TabEmpresaDetalheReceitaFederalDAO().getDetalheReceitaFederalVOList(empresa.getId()));
        
    }

    public void updateTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
//        comandoSql = "UPDATE tabEmpresa SET ";
//        comandoSql += "isPessoaJuridica = " + empresaVO.getIsPessoaJuridica() + ", ";
//        comandoSql += "cnpj = '" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
//        comandoSql += "ie = '" + empresaVO.getIe().replaceAll("'", "") + "', ";
//        comandoSql += "razao = '" + empresaVO.getRazao().replaceAll("'", "") + "', ";
//        comandoSql += "fantasia = '" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
//        comandoSql += "isCliente = " + empresaVO.getIsCliente() + ", ";
//        comandoSql += "isFornecedor = " + empresaVO.getIsFornecedor() + ", ";
//        comandoSql += "isTransportadora = " + empresaVO.getIsTransportadora() + ", ";
//        comandoSql += "endereco_ids = '" + empresaVO.getEndereco_ids() + "', ";
//        comandoSql += "telefone_ids = '" + empresaVO.getTelefone_ids() + "', ";
//        comandoSql += "contato_ids = '" + empresaVO.getContato_ids() + "', ";
//        comandoSql += "emailHomePage_ids = '" + empresaVO.getEmailHomePage_ids() + "', ";
//        comandoSql += "usuarioAtualizacao_id = " + empresaVO.getUsuarioAtualizacao_id() + ", ";
//        comandoSql += "situacaoSistema_id = " + empresaVO.getSituacaoSistema_id() + ", ";
//        comandoSql += "dataAbertura = '" + empresaVO.getDataAbertura() + "', ";
//        comandoSql += "naturezaJuridica = '" + empresaVO.getNaturezaJuridica().replaceAll("[']", "") + "' ";
//        comandoSql += "WHERE id = " + empresaVO.getId();
//
//        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
//        comandoSql = "INSERT INTO tabEmpresa ";
//        comandoSql += "(isPessoaJuridica, cnpj, ie, razao, fantasia, isCliente, isFornecedor, ";
//        comandoSql += "isTransportadora, endereco_ids, telefone_ids, contato_ids, emailHomePage_ids, ";
//        comandoSql += "usuarioCadastro_id, situacaoSistema_id, dataAbertura, naturezaJuridica) ";
//        comandoSql += "VALUES(";
//        comandoSql += empresaVO.getIsPessoaJuridica() + ", ";
//        comandoSql += "'" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
//        comandoSql += "'" + empresaVO.getIe().replaceAll("'", "") + "', ";
//        comandoSql += "'" + empresaVO.getRazao().replaceAll("'", "") + "', ";
//        comandoSql += "'" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
//        comandoSql += empresaVO.getIsCliente() + ", ";
//        comandoSql += empresaVO.getIsFornecedor() + ", ";
//        comandoSql += empresaVO.getIsTransportadora() + ", ";
//        comandoSql += "'" + empresaVO.getEndereco_ids() + "', ";
//        comandoSql += "'" + empresaVO.getTelefone_ids() + "', ";
//        comandoSql += "'" + empresaVO.getContato_ids() + "', ";
//        comandoSql += "'" + empresaVO.getEmailHomePage_ids() + "', ";
//        comandoSql += empresaVO.getUsuarioCadastro_id() + ", ";
//        comandoSql += empresaVO.getSituacaoSistema_id() + ", ";
//        comandoSql += "'" + empresaVO.getDataAbertura() + "', ";
//        comandoSql += "'" + empresaVO.getNaturezaJuridica().replaceAll("'", "") + "'";
//        comandoSql += ") ";
//
//        return getInsertBancoDados(conn, comandoSql);
        return 0;
    }

}
