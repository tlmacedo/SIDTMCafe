package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.RelEmpresaEnderecoVO;
import br.com.sidtmcafe.model.vo.SisSituacaoSistemaVO;
import br.com.sidtmcafe.model.vo.TabEmpresaVO;
import br.com.sidtmcafe.model.vo.TabEnderecoVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEmpresaDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresaVO tabEmpresaVO;
    List<TabEmpresaVO> tabEmpresaVOList;

    public TabEmpresaVO getTabEmpresaVO_Simples(int id) {
        buscaTabEmpresaVO(id);
        return tabEmpresaVO;
    }

    public TabEmpresaVO getTabEmpresaVO(int id) {
        buscaTabEmpresaVO(id);
        if (tabEmpresaVO != null)
            addObjetosPesquisa(tabEmpresaVO);
        return tabEmpresaVO;
    }

    public List<TabEmpresaVO> getTabEmpresaVOList() {
        buscaTabEmpresaVO(0);
        if (tabEmpresaVOList != null)
            for (TabEmpresaVO empresaVO : tabEmpresaVOList)
                addObjetosPesquisa(empresaVO);
        return tabEmpresaVOList;
    }

    void buscaTabEmpresaVO(int id) {
        comandoSql = "SELECT id, isEmpresa, cnpj, ie, razao, fantasia, isLoja, isCliente, isFornecedor, " +
                "isTransportadora, sisSituacaoSistema_id, usarioCadastro_id, dataCadastro, " +
                "usuarioAtualizacao_id, dataAtualizacao, dataAbertura, naturezaJuridica " +
                "FROM tabEmpresa " +
                "WHERE (isCliente = '1' OR isFornecedor= '1' OR isTransportadora = '1') ";
        if (id > 0) comandoSql += " AND id = '" + id + "' ";
        comandoSql += "ORDER BY razao ";

        if (id == 0) tabEmpresaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                tabEmpresaVO = new TabEmpresaVO();
                tabEmpresaVO.setId(rs.getInt("id"));
                tabEmpresaVO.setIsEmpresa(rs.getInt("isEmpresa"));
                tabEmpresaVO.setCnpj(rs.getString("cnpj"));
                tabEmpresaVO.setIe(rs.getString("ie"));
                tabEmpresaVO.setRazao(rs.getString("razao"));
                tabEmpresaVO.setFantasia(rs.getString("fantasia"));
                tabEmpresaVO.setIsLoja(rs.getBoolean("isLoja"));
                tabEmpresaVO.setIsCliente(rs.getBoolean("isCliente"));
                tabEmpresaVO.setIsFornecedor(rs.getBoolean("isFornecedor"));
                tabEmpresaVO.setIsTransportadora(rs.getBoolean("isTransportadora"));
                tabEmpresaVO.setSisSituacaoSistema_id(rs.getInt("sisSituacaoSistema_id"));
                tabEmpresaVO.setUsuarioCadastro_id(rs.getInt("usarioCadastro_id"));
                tabEmpresaVO.setDataCadastro(rs.getTimestamp("dataCadastro"));
                tabEmpresaVO.setUsuarioAtualizacao_id(rs.getInt("usuarioAtualizacao_id"));
                tabEmpresaVO.setDataAtualizacao(rs.getTimestamp("dataAtualizacao"));
                tabEmpresaVO.setDataAbertura(rs.getDate("dataAbertura"));
                tabEmpresaVO.setNaturezaJuridica(rs.getString("naturezaJuridica"));

                if (id == 0) tabEmpresaVOList.add(tabEmpresaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabEmpresaVO empresa) {
        empresa.setSisSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSisSituacaoSistemaVO(empresa.getSisSituacaoSistema_id()));

        empresa.setTabEmpresaReceitaFederalVOList(
                new TabEmpresaReceitaFederalDAO().getTabEmpresaReceitaFederalVOList(empresa.getId(), 0));
        List<RelEmpresaEnderecoVO> relEmpresaEnderecoVOList =
                new ArrayList<>(new RelEmpresaEnderecoDAO().getRelEmpresaEnderecoVOList(empresa.getId()));
        List<TabEnderecoVO> tabEnderecoVOList = new ArrayList<>();
        for (RelEmpresaEnderecoVO relEmpresaEnderecoVO : relEmpresaEnderecoVOList)
            tabEnderecoVOList.add(new TabEnderecoDAO().getTabEnderecoVO(relEmpresaEnderecoVO.getTabEndereco_id()));
        empresa.setTabEnderecoVOList(tabEnderecoVOList);

        empresa.setUsuarioCadastroVO(new TabColaboradorDAO().getTabColaboradorVO_Simples(empresa.getUsuarioCadastro_id()));
        empresa.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getTabColaboradorVO_Simples(empresa.getUsuarioAtualizacao_id()));
    }

    public void updateTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
        comandoSql = "UPDATE tabEmpresa SET ";
        comandoSql += "isEmpresa = " + empresaVO.getIsEmpresa() + ", ";
        comandoSql += "cnpj = '" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
        comandoSql += "ie = '" + empresaVO.getIe().replaceAll("'", "") + "', ";
        comandoSql += "razao = '" + empresaVO.getRazao().replaceAll("'", "") + "', ";
        comandoSql += "fantasia = '" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
        comandoSql += "isLoja = " + empresaVO.isIsLoja() + ", ";
        comandoSql += "isCliente = " + empresaVO.isIsCliente() + ", ";
        comandoSql += "isFornecedor = " + empresaVO.isIsFornecedor() + ", ";
        comandoSql += "isTransportadora = " + empresaVO.isIsTransportadora() + ", ";
        comandoSql += "sisSituacaoSistema_id = " + empresaVO.getSisSituacaoSistema_id() + ", ";
        comandoSql += "usuarioAtualizacao_id = " + empresaVO.getUsuarioAtualizacao_id() + ", ";
        comandoSql += "dataAbertura = '" + empresaVO.getDataAbertura() + "', ";
        comandoSql += "naturezaJuridica = '" + empresaVO.getNaturezaJuridica().replaceAll("[']", "") + "' ";
        comandoSql += "WHERE id = " + empresaVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;

    }

    public int insertTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
        comandoSql = "INSERT INTO tabEmpresa ";
        comandoSql += "(isEmpresa, cnpj, ie, razao, fantasia, sisSituacaoSistema_id, ";
        comandoSql += "usuarioCadastro_id, dataAbertura, naturezaJuridica) ";
        comandoSql += "VALUES(";
        comandoSql += empresaVO.getIsEmpresa() + ", ";
        comandoSql += "'" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getIe().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getRazao().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
        comandoSql += empresaVO.getSisSituacaoSistema_id() + ", ";
        comandoSql += empresaVO.getUsuarioCadastro_id() + ", ";
        comandoSql += "'" + empresaVO.getDataAbertura() + "', ";
        comandoSql += "'" + empresaVO.getNaturezaJuridica().replaceAll("'", "") + "'";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);

    }


}
