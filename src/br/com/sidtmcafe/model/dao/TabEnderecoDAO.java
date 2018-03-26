package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEmpresaVO;
import br.com.sidtmcafe.model.vo.TabEnderecoVO;
import br.com.sidtmcafe.model.vo.WsCepPostmonVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEnderecoDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabEnderecoVO enderecoVO;
    List<TabEnderecoVO> enderecoVOList;

    public TabEnderecoVO getEnderecoVO(int idTabEnderecoVO) {
        buscaTabEnderecoVO(idTabEnderecoVO);
        if (enderecoVO != null)
            addObjetosPesquisa(enderecoVO);
        return enderecoVO;
    }

    public List<TabEnderecoVO> getEnderecoVOList() {
        buscaTabEnderecoVO(-1);
        if (enderecoVOList != null)
            for (TabEnderecoVO endereco : enderecoVOList)
                addObjetosPesquisa(endereco);
        return enderecoVOList;
    }

    void buscaTabEnderecoVO(int idTabEnderecoVO) {
        comandoSql = "SELECT id, sisTipoEndereco_id, cep, logradouro, numero, complemento, bairro, " +
                "sisMunicipio_id, pontoReferencia FROM tabEndereco ";
        if (idTabEnderecoVO > 0) comandoSql += "WHERE id = '" + idTabEnderecoVO + "' ";
        comandoSql += "ORDER BY tipoEndereco_id, id ";

        enderecoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                enderecoVO = new TabEnderecoVO();
                enderecoVO.setId(rs.getInt("id"));
                enderecoVO.setSisTipoEndereco_id(rs.getInt("sisTipoEndereco_id"));
                enderecoVO.setCep(rs.getString("cep"));
                enderecoVO.setLogradouro(rs.getString("logradouro"));
                enderecoVO.setNumero(rs.getString("numero"));
                enderecoVO.setComplemento(rs.getString("complemento"));
                enderecoVO.setBairro(rs.getString("bairro"));
                enderecoVO.setSisMunicipio_id(rs.getInt("sisMunicipio_id"));
                enderecoVO.setPontoReferencia(rs.getString("pontoReferencia"));

                enderecoVOList.add(enderecoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabEnderecoVO endereco) {

        endereco.setTipoEnderecoVO(new SisTipoEnderecoDAO().getTipoEnderecoVO(endereco.getSisTipoEndereco_id()));
        endereco.setMunicipioVO(new SisMunicipioDAO().getMunicipioVO(endereco.getSisMunicipio_id()));

    }

    public void updateTabEnderecoVO(Connection conn, TabEnderecoVO enderecoVO) throws SQLException {
//        comandoSql = "UPDATE tabEndereco SET ";
//        comandoSql += "tipoEndereco_id = " + enderecoVO.getTipoEndereco_id() + ", ";
//        comandoSql += "cep = '" + enderecoVO.getCep() + "', ";
//        comandoSql += "logradouro = '" + enderecoVO.getLogradouro() + "', ";
//        comandoSql += "numero = '" + enderecoVO.getNumero() + "', ";
//        comandoSql += "complemento = '" + enderecoVO.getComplemento() + "', ";
//        comandoSql += "bairro = '" + enderecoVO.getBairro() + "', ";
//        comandoSql += "uf_id = " + enderecoVO.getUf_id() + ", ";
//        comandoSql += "municipio_id = " + enderecoVO.getMunicipio_id() + ", ";
//        comandoSql += "pontoReferencia = '" + enderecoVO.getPontoReferencia() + "' ";
//        comandoSql += "WHERE id = " + enderecoVO.getId();
//
//        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabEnderecoVO(Connection conn, TabEnderecoVO enderecoVO) throws SQLException {
//        comandoSql = "INSERT INTO tabEndereco ";
//        comandoSql += "(tipoEndereco_id, cep, logradouro, numero, complemento, bairro, uf_id, municipio_id, pontoReferencia) ";
//        comandoSql += "VALUES(";
//        comandoSql += enderecoVO.getTipoEndereco_id() + ", ";
//        comandoSql += "'" + enderecoVO.getCep() + "', ";
//        comandoSql += "'" + enderecoVO.getLogradouro() + "', ";
//        comandoSql += "'" + enderecoVO.getNumero() + "', ";
//        comandoSql += "'" + enderecoVO.getComplemento() + "', ";
//        comandoSql += "'" + enderecoVO.getBairro() + "', ";
//        comandoSql += enderecoVO.getUf_id() + ", ";
//        comandoSql += enderecoVO.getMunicipio_id() + ", ";
//        comandoSql += "'" + enderecoVO.getPontoReferencia() + "'";
//        comandoSql += ") ";
//
//        return getInsertBancoDados(conn, comandoSql);
        return 0;
    }

}
