package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEmpresa_DetalheReceitaFederalVO;

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
        buscaTabEmpresa_DetalheReceitaFederal(idTabEmpresa_DetalheReceitaFederalVO);
        if (detalheReceitaFederalVO == null)
            detalheReceitaFederalVO = new TabEmpresa_DetalheReceitaFederalVO();
        return detalheReceitaFederalVO;
    }

    public List<TabEmpresa_DetalheReceitaFederalVO> getDetalheReceitaFederalVOList() {
        buscaTabEmpresa_DetalheReceitaFederal(0);
        if (detalheReceitaFederalVOList == null)
            detalheReceitaFederalVOList.add(new TabEmpresa_DetalheReceitaFederalVO());
        return detalheReceitaFederalVOList;
    }

    void buscaTabEmpresa_DetalheReceitaFederal(int idTabEmpresa_DetalheReceitaFederalVO) {
        comandoSql = "SELECT * FROM tabEmpresa_DetalheReceitaFederal ";
        if (idTabEmpresa_DetalheReceitaFederalVO > 0)
            comandoSql += "WHERE id = '" + idTabEmpresa_DetalheReceitaFederalVO + "' ";
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

}
