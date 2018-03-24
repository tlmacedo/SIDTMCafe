package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabProdutoVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabProdutoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabProdutoVO produtoVO;
    List<TabProdutoVO> produtoVOList;

    public TabProdutoVO getProdutoVO(int idTabProdutoVO) {
        buscaTabProdutoVO(idTabProdutoVO);
        if (produtoVO != null)
            addObjetosPesquisa(produtoVO);
        return produtoVO;
    }

    public List<TabProdutoVO> getProdutoVOList() {
        buscaTabProdutoVO(0);
        if (produtoVOList != null)
            for (TabProdutoVO produto : produtoVOList)
                addObjetosPesquisa(produto);
        return produtoVOList;
    }

    void buscaTabProdutoVO(int idTabProdutoVO) {
        comandoSql = "SELECT * FROM tabProduto ";
        if (idTabProdutoVO > 0) comandoSql += "WHERE id = '" + idTabProdutoVO + "' ";
        comandoSql += "ORDER BY descricao ";

        produtoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                produtoVO = new TabProdutoVO();
                produtoVO.setId(rs.getInt("id"));
                produtoVO.setCodigo(rs.getString("codigo"));
                produtoVO.setDescricao(rs.getString("descricao"));
                produtoVO.setPeso(rs.getDouble("peso"));
                produtoVO.setUnidadeComercial_id(rs.getInt("unidadeComercial_id"));
                produtoVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                produtoVO.setPrecoFabrica(rs.getDouble("precoFabrica"));
                produtoVO.setPrecoConsumidor(rs.getDouble("precoConsumidor"));
                produtoVO.setVarejo(rs.getInt("varejo"));
                produtoVO.setComissao(rs.getDouble("comissao"));
                produtoVO.setFiscalCstIcms_id(rs.getInt("fiscalCstIcms_id"));
                produtoVO.setFiscalCstPis_id(rs.getInt("fiscalCstPis_id"));
                produtoVO.setFiscalCstCofins_id(rs.getInt("fiscalCstCofins_id"));
                produtoVO.setFiscalNcm(rs.getString("fiscalNcm"));
                produtoVO.setFiscalCest(rs.getString("fiscalCest"));
                produtoVO.setFiscalOrigem_id(rs.getInt("fiscalOrigem_id"));
                produtoVO.setFiscalGenero(rs.getString("fiscalGenero"));
                produtoVO.setUsuarioCadastro_id(rs.getInt("usuarioCadastro_id"));
                produtoVO.setDataCadastro(rs.getTimestamp("dataCadastro"));
                produtoVO.setUsuarioAtualizacao_id(rs.getInt("usuarioAtualizacao_id"));
                produtoVO.setDataAtualizacao(rs.getTimestamp("dataAtualizacao"));

                produtoVOList.add(produtoVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabProdutoVO produto) {
        produto.setUnidadeComercialVO(new SisUnidadeComercialDAO().getUnidadeComercialVO(produto.getUnidadeComercial_id()));
        produto.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(produto.getSituacaoSistema_id()));
        produto.setFiscalCstIcmsVO(new SisFiscalCstIcmsDAO().getFiscalCstIcmsVO(produto.getFiscalCstIcms_id()));
        produto.setFiscalCstPisVO(new SisFiscalCstPisCofinsDAO().getSisFiscalCstPisCofinsVO(produto.getFiscalCstPis_id()));
        produto.setFiscalCstCofinsVO(new SisFiscalCstPisCofinsDAO().getSisFiscalCstPisCofinsVO(produto.getFiscalCstCofins_id()));
        produto.setFiscalCstOrigemVO(new SisFiscalCstOrigemDAO().getFiscalCstOrigemVO(produto.getFiscalOrigem_id()));
        produto.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(produto.getUsuarioCadastro_id(),false));
        produto.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(produto.getUsuarioAtualizacao_id(),false));
        produto.setEstoque(new TabProdutoEstoqueDAO().getProdutoEstoqueVOSUM(produto.getId()));
        produto.setProdutoEstoqueVOList(new TabProdutoEstoqueDAO().getProdutoEstoqueVOList(produto.getId()));
        produto.setProdutoEanVOList(new TabProdutoEanDAO().getProdutoEanVOList(produto.getId()));
    }

    public void updateTabProdutoVO(Connection conn, TabProdutoVO produtoVO) throws SQLException {
        comandoSql = "UPDATE tabProduto SET ";
        comandoSql += "codigo = '" + produtoVO.getCodigo() + "', ";
        comandoSql += "descricao = '" + produtoVO.getDescricao() + "', ";
        comandoSql += "peso = " + produtoVO.getPeso() + ", ";
        comandoSql += "unidadeComercial_id = " + produtoVO.getUnidadeComercial_id() + ", ";
        comandoSql += "situacaoSistema_id = " + produtoVO.getSituacaoSistema_id() + ", ";
        comandoSql += "precoFabrica = " + produtoVO.getPrecoFabrica() + ", ";
        comandoSql += "precoConsumidor = " + produtoVO.getPrecoConsumidor() + ", ";
        comandoSql += "varejo = " + produtoVO.getVarejo() + ", ";
        comandoSql += "comissao = " + produtoVO.getComissao() + ", ";
        comandoSql += "fiscalCstIcms_id = " + produtoVO.getFiscalCstIcms_id() + ", ";
        comandoSql += "fiscalCstPis_id = " + produtoVO.getFiscalCstPis_id() + ", ";
        comandoSql += "fiscalCstCofins_id = " + produtoVO.getFiscalCstCofins_id() + ", ";
        comandoSql += "fiscalNcm = '" + produtoVO.getFiscalNcm() + "', ";
        comandoSql += "fiscalCest = '" + produtoVO.getFiscalCest() + "', ";
        comandoSql += "fiscalOrigem_id = " + produtoVO.getFiscalOrigem_id() + ", ";
        comandoSql += "fiscalGenero = '" + produtoVO.getFiscalGenero() + "', ";
        comandoSql += "usuarioAtualizacao_id = " + produtoVO.getUsuarioAtualizacao_id() + " ";
        comandoSql += "WHERE id = " + produtoVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabProdutoVO(Connection conn, TabProdutoVO produtoVO) throws SQLException {
        comandoSql = "INSERT INTO tabProduto ";
        comandoSql += "(codigo, descricao, peso, unidadeComercial_id, situacaoSistema_id, precoFabrica, precoConsumidor, ";
        comandoSql += "varejo, comissao, fiscalCstIcms_id, fiscalCstPis_id, fiscalCstCofins_id, fiscalNcm, fiscalCest, ";
        comandoSql += "fiscalOrigem_id, fiscalGenero, usuarioCadastro_id) ";
        comandoSql += "VALUES(";
        comandoSql += "'" + produtoVO.getCodigo() + "', ";
        comandoSql += "'" + produtoVO.getDescricao() + "', ";
        comandoSql += produtoVO.getPeso() + ", ";
        comandoSql += produtoVO.getUnidadeComercial_id() + ", ";
        comandoSql += produtoVO.getSituacaoSistema_id() + ", ";
        comandoSql += produtoVO.getPrecoFabrica() + ", ";
        comandoSql += produtoVO.getPrecoConsumidor() + ", ";
        comandoSql += produtoVO.getVarejo() + ", ";
        comandoSql += produtoVO.getComissao() + ", ";
        comandoSql += produtoVO.getFiscalCstIcms_id() + ", ";
        comandoSql += produtoVO.getFiscalCstPis_id() + ", ";
        comandoSql += produtoVO.getFiscalCstCofins_id() + ", ";
        comandoSql += "'" + produtoVO.getFiscalNcm() + "', ";
        comandoSql += "'" + produtoVO.getFiscalCest() + "', ";
        comandoSql += produtoVO.getFiscalOrigem_id() + ", ";
        comandoSql += "'" + produtoVO.getFiscalGenero() + "', ";
        comandoSql += produtoVO.getUsuarioCadastro_id() + " ";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);
    }

}
