package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabProdutoEanVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabProdutoEanDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabProdutoEanVO produtoEanVO;
    List<TabProdutoEanVO> produtoEanVOList;


    public TabProdutoEanVO getProdutoEanVO(int idTabProdutoEanVO) {
        buscaTabProdutoEanVO(idTabProdutoEanVO, 0);
        return produtoEanVO;
    }

    public List<TabProdutoEanVO> getProdutoEanVOList(int produto_id) {
        buscaTabProdutoEanVO(0, produto_id);
        return produtoEanVOList;
    }

    void buscaTabProdutoEanVO(int idTabProdutoEanVO, int produto_id) {
        comandoSql = "SELECT * FROM tabProdutoEan ";
        if (idTabProdutoEanVO > 0) comandoSql = "WHERE produto_id = '" + idTabProdutoEanVO + "' ";
        if (produto_id > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "produto_id = '" + produto_id + "' ";
        }
        comandoSql += "ORDER BY id DESC ";

        produtoEanVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                produtoEanVO = new TabProdutoEanVO();
                produtoEanVO.setId(rs.getInt("id"));
                produtoEanVO.setProduto_id(rs.getInt("produto_id"));
                produtoEanVO.setDescricao(rs.getString("descricao"));

                produtoEanVOList.add(produtoEanVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public void updateTabProdutoEanVO(Connection conn, TabProdutoEanVO produtoEanVO) throws SQLException {
        comandoSql = "UPDATE tabProdutoEan SET ";
        comandoSql += "produto_id = " + produtoEanVO.getProduto_id() + ", ";
        comandoSql += "descricao = '" + produtoEanVO.getDescricao() + "' ";
        comandoSql += "WHERE id = " + produtoEanVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabProdutoEanVO(Connection conn, TabProdutoEanVO produtoEanVO) throws SQLException {
        comandoSql = "INSERT INTO tabProdutoEan ";
        comandoSql += "(produto_id, descricao) ";
        comandoSql += "VALUES(";
        comandoSql += produtoEanVO.getProduto_id() + ", ";
        comandoSql += "'" + produtoEanVO.getDescricao() + "' ";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);
    }

}
