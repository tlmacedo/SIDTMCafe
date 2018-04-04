package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.SisRelEmpresaEnderecoVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisRelEmpresaEnderecoDAO extends BuscaBandoDados implements Constants {

    ResultSet rs;

    String comandoSql = "";
    SisRelEmpresaEnderecoVO relEmpresaEnderecoVO;
    List<SisRelEmpresaEnderecoVO> relEmpresaEnderecoVOList;

    public List<SisRelEmpresaEnderecoVO> getRelEmpresaEnderecoVOList(int idEmpresa) {
        buscaSisRelEmpresaEnderecoVO(idEmpresa, 0);
        return relEmpresaEnderecoVOList;
    }

    public SisRelEmpresaEnderecoVO getRelEmpresaEnderecoVO(int idEmpresa, int idEndereco) {
        buscaSisRelEmpresaEnderecoVO(idEmpresa, idEndereco);
        return relEmpresaEnderecoVO;
    }

    void buscaSisRelEmpresaEnderecoVO(int idEmpresa, int idEndereco) {
        comandoSql = "SELECT id, tabEmpresa_id, tabEndereco_id FROM sisRelacaoEmpresaEndereco ";
        if (idEmpresa > 0) comandoSql += "WHERE tabEmpresa_id = '" + idEmpresa + "' ";
        if (idEndereco > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "tabEndereco_id = '" + idEndereco + "' ";
        }
        comandoSql += "ORDER BY tabEmpresa_id ";

        relEmpresaEnderecoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                relEmpresaEnderecoVO = new SisRelEmpresaEnderecoVO();
                relEmpresaEnderecoVO.setId(rs.getInt("id"));
                relEmpresaEnderecoVO.setTabEmpresa_id(rs.getInt("tabEmpresa_id"));
                relEmpresaEnderecoVO.setTabEndereco_id(rs.getInt("tabEndereco_id"));
                relEmpresaEnderecoVO.setEnderecoVO(new TabEnderecoDAO().getEnderecoVO(relEmpresaEnderecoVO.getTabEndereco_id()));

                relEmpresaEnderecoVOList.add(relEmpresaEnderecoVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public void updateRelEmpresaEnderecoVO(Connection conn, SisRelEmpresaEnderecoVO relEmpresaEnderecoVO) throws SQLException {
        comandoSql = "UPDATE sisRelacaoEmpresaEndereco SET ";
        comandoSql += "tabEmpresa_id = " + relEmpresaEnderecoVO.getTabEmpresa_id() + ", ";
        comandoSql += "tabEndereco_id = '" + relEmpresaEnderecoVO.getTabEndereco_id() + "' ";
        comandoSql += "WHERE id = " + relEmpresaEnderecoVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertRelEmpresaEnderecoVO(Connection conn, int tabEmpresa_id, int tabEndereco_id) throws SQLException {
        comandoSql = "INSERT INTO sisRelacaoEmpresaEndereco ";
        comandoSql += "(tabEmpresa_id, tabEndereco_id) ";
        comandoSql += "VALUES(";
        comandoSql += "'" + tabEmpresa_id + "', ";
        comandoSql += "'" + tabEndereco_id + "'";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);
    }

}
