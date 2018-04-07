package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEnderecoVO;

import java.sql.ResultSet;
import java.util.List;

public class TabEnderecoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEnderecoVO tabEnderecoVO;

    public TabEnderecoVO getTabEnderecoVO(int id) {
        buscaTabEnderecoVO(id);
        return tabEnderecoVO;
    }

    void buscaTabEnderecoVO(int id) {
        comandoSql = "SELECT id, sisTipoEndereco_id, cep, logradouro, numero, complemento, bairro, " +
                "sisMunicipio_id, pontoReferencia " +
                "FROM TabEndereco " +
                "WHERE id = '" + id + "' ";

        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                tabEnderecoVO = new TabEnderecoVO();
                tabEnderecoVO.setId(rs.getInt("id"));
                tabEnderecoVO.setSisTipoEndereco_id(rs.getInt("sisTipoEndereco_id"));
                tabEnderecoVO.setCep(rs.getString("cep"));
                tabEnderecoVO.setLogradouro(rs.getString("logradouro"));
                tabEnderecoVO.setNumero(rs.getString("numero"));
                tabEnderecoVO.setComplemento(rs.getString("complemento"));
                tabEnderecoVO.setBairro(rs.getString("bairro"));
                tabEnderecoVO.setSisMunicipio_id(rs.getInt("sisMunicipio_id"));
                tabEnderecoVO.setPontoReferencia(rs.getString("pontoReferencia"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }


}
