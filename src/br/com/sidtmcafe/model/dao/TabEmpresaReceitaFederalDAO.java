package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.model.vo.TabEmpresaReceitaFederalVO;

import java.sql.ResultSet;
import java.util.List;

public class TabEmpresaReceitaFederalDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresaReceitaFederalVO tabEmpresaReceitaFederalVO;
    List<TabEmpresaReceitaFederalVO> tabEmpresaReceitaFederalVOList;

    public TabEmpresaReceitaFederalVO getTabEmpresaReceitaFederalVO(int id) {
        buscaTabEmpresaReceitaFederalVO(id, 0, 0);
        return tabEmpresaReceitaFederalVO;
    }

    public List<TabEmpresaReceitaFederalVO> getTabEmpresaReceitaFederalVOList(int empresa_id, int isAtividdePrincipal) {
        buscaTabEmpresaReceitaFederalVO(0, empresa_id, isAtividdePrincipal);
        return tabEmpresaReceitaFederalVOList;
    }

    void buscaTabEmpresaReceitaFederalVO(int id, int empresa_id, int isAtividadePrincipal) {
        comandoSql = "SELECT id, tabEmpresa_id, isAtividadePrincipal, str_Key, str_Value " +
                "FROM TabEmpresaReceitaFederalVO ";
        if (id > 0) comandoSql += "WHERE id = '" + id + "' ";
        if (empresa_id > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "tabEmpresa_id = '" + empresa_id + "' ";
        }
        if (isAtividadePrincipal > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "isAtividadePrincipal = '" + isAtividadePrincipal + "' ";
        }

        comandoSql += "ORDER BY isAtividadePrincipal, id ";
    }


}
