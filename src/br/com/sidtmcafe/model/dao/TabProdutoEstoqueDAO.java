package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabProdutoEstoqueVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TabProdutoEstoqueDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabProdutoEstoqueVO produtoEstoqueVO;
    List<TabProdutoEstoqueVO> produtoEstoqueVOList;

    public TabProdutoEstoqueVO getProdutoEstoqueVO(int idTabProdutoEstoqueVO) {
        buscaTabProdutoEstoqueVO(idTabProdutoEstoqueVO, 0);
        return produtoEstoqueVO;
    }

    public int getProdutoEstoqueVOSUM(int produto_id) {
        comandoSql = "SELECT Sum(qtd) as estoque FROM tabProdutoEstoque ";
        comandoSql += "WHERE produto_id = '" + produto_id + "' ";

        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                return rs.getInt("estoque");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return 0;
    }

    public List<TabProdutoEstoqueVO> getProdutoEstoqueVOList(int produto_id) {
        buscaTabProdutoEstoqueVO(0, produto_id);
        return produtoEstoqueVOList;
    }

    void buscaTabProdutoEstoqueVO(int idTabProdutoEstoqueVO, int produto_id) {
        comandoSql = "SELECT * FROM tabProdutoEstoque ";
        if (idTabProdutoEstoqueVO > 0) comandoSql += "WHERE id = '" + idTabProdutoEstoqueVO + "' ";
        if (produto_id > 0) {
            if (comandoSql.contains("WHERE")) {
                comandoSql += "AND ";
            } else {
                comandoSql += "WHERE ";
            }
            comandoSql += "produto_id = '" + produto_id + "' ";
        }
        comandoSql += "ORDER BY produto_id, validade ";

        produtoEstoqueVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                produtoEstoqueVO = new TabProdutoEstoqueVO();
                produtoEstoqueVO.setId(rs.getInt("id"));
                produtoEstoqueVO.setProduto_id(rs.getInt("produto_id"));
                produtoEstoqueVO.setQtd(rs.getInt("qtd"));
                produtoEstoqueVO.setLote(rs.getString("lote"));
                produtoEstoqueVO.setValidade(rs.getTimestamp("validade"));
                produtoEstoqueVO.setDocumentoOrigem(rs.getString("documentoOrigem"));

                produtoEstoqueVOList.add(produtoEstoqueVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
