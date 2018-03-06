package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabProdutoVO;

import java.sql.ResultSet;
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
        produto.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(produto.getUsuarioCadastro_id()));
        produto.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(produto.getUsuarioAtualizacao_id()));
        produto.setEstoque(new TabProdutoEstoqueDAO().getProdutoEstoqueVOSUM(produto.getId()));
        produto.setProdutoEstoqueVOList(new TabProdutoEstoqueDAO().getProdutoEstoqueVOList(produto.getId()));
        produto.setProdutoEanVOList(new TabProdutoEanDAO().getProdutoEanVOList(produto.getId()));
    }
}
