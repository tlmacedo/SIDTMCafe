package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEmpresa_DetalheReceitaFederalVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEmpresa_DetalheReceitaFederalDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresa_DetalheReceitaFederalVO detalheReceitaFederalVO;
    List<TabEmpresa_DetalheReceitaFederalVO> detalheReceitaFederalVOList;

    public TabEmpresa_DetalheReceitaFederalVO getDetalheReceitaFederalVO(int idTabEmpresa_DetalheReceitaFederalVO) {
        buscaTabEmpresa_DetalheReceitaFederal(idTabEmpresa_DetalheReceitaFederalVO, -1);
        return detalheReceitaFederalVO;
    }

    public List<TabEmpresa_DetalheReceitaFederalVO> getDetalheReceitaFederalVOList(int idTabEmpresaVO) {
        buscaTabEmpresa_DetalheReceitaFederal(-1, idTabEmpresaVO);
        return detalheReceitaFederalVOList;
    }

    void buscaTabEmpresa_DetalheReceitaFederal(int idTabEmpresa_DetalheReceitaFederalVO, int idTabEmpresaVO) {
        comandoSql = "SELECT * FROM tabEmpresa_DetalheReceitaFederal ";
        if (idTabEmpresa_DetalheReceitaFederalVO > 0)
            comandoSql += "WHERE id = '" + idTabEmpresa_DetalheReceitaFederalVO + "' ";
        if (idTabEmpresaVO > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "empresa_id = '" + idTabEmpresaVO + "' ";
        }
        comandoSql += "ORDER BY empresa_id, isAtividadePrincipal DESC, str_key ";
        detalheReceitaFederalVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                detalheReceitaFederalVO = new TabEmpresa_DetalheReceitaFederalVO();
                detalheReceitaFederalVO.setId(rs.getInt("id"));
                detalheReceitaFederalVO.setEmpresa_id(rs.getInt("empresa_id"));
                detalheReceitaFederalVO.setIsAtividadePrincipal(rs.getInt("isAtividadePrincipal"));
                detalheReceitaFederalVO.setStr_key(rs.getString("str_Key"));
                detalheReceitaFederalVO.setStr_value(rs.getString("str_Value"));

                detalheReceitaFederalVOList.add(detalheReceitaFederalVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public void updateTabEmpresa_DetalheReceitaFederalVO(Connection conn, TabEmpresa_DetalheReceitaFederalVO detalheReceitaFederalVO) throws SQLException {
        comandoSql = "UPDATE tabEmpresa_DetalheReceitaFederal SET ";
        comandoSql += "empresa_id = " + detalheReceitaFederalVO.getEmpresa_id() + ", ";
        comandoSql += "isAtividadePrincipal = " + detalheReceitaFederalVO.getIsAtividadePrincipal() + ", ";
        comandoSql += "str_Key = '" + detalheReceitaFederalVO.getStr_key() + "', ";
        comandoSql += "str_Value = '" + detalheReceitaFederalVO.getStr_value() + "' ";
        comandoSql += "WHERE id = " + detalheReceitaFederalVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabEmpresa_DetalheReceitaFederalVO(Connection conn, TabEmpresa_DetalheReceitaFederalVO detalheReceitaFederalVO) throws SQLException {
        comandoSql = "INSERT INTO tabEmpresa_DetalheReceitaFederal ";
        comandoSql += "(empresa_id, isAtividadePrincipal, str_Key, str_Value) ";
        comandoSql += "VALUES(";
        comandoSql += detalheReceitaFederalVO.getEmpresa_id() + ", ";
        comandoSql += detalheReceitaFederalVO.getIsAtividadePrincipal() + ", ";
        comandoSql += "'" + detalheReceitaFederalVO.getStr_key() + "', ";
        comandoSql += "'" + detalheReceitaFederalVO.getStr_value() + "'";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);
    }


}
