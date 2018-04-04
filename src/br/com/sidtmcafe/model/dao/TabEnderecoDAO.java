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
        comandoSql += "ORDER BY sistipoEndereco_id, id ";

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
        comandoSql = "UPDATE tabEndereco SET ";
        comandoSql += "sisTipoEndereco_id = " + enderecoVO.getSisTipoEndereco_id() + ", ";
        comandoSql += "cep = '" + enderecoVO.getCep() + "', ";
        comandoSql += "logradouro = '" + enderecoVO.getLogradouro() + "', ";
        comandoSql += "numero = '" + enderecoVO.getNumero() + "', ";
        comandoSql += "complemento = '" + enderecoVO.getComplemento() + "', ";
        comandoSql += "bairro = '" + enderecoVO.getBairro() + "', ";
        comandoSql += "sisMunicipio_id = " + enderecoVO.getSisMunicipio_id() + ", ";
        comandoSql += "pontoReferencia = '" + enderecoVO.getPontoReferencia() + "' ";
        comandoSql += "WHERE id = " + enderecoVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;
    }

    public int insertTabEnderecoVO(Connection conn, TabEnderecoVO enderecoVO) throws SQLException {
        comandoSql = "INSERT INTO tabEndereco ";
        comandoSql += "(sisTipoEndereco_id, cep, logradouro, numero, complemento, bairro, sisMunicipio_id, pontoReferencia) ";
        comandoSql += "VALUES(";
        comandoSql += enderecoVO.getSisTipoEndereco_id() + ", ";
        comandoSql += "'" + enderecoVO.getCep() + "', ";
        comandoSql += "'" + enderecoVO.getLogradouro() + "', ";
        comandoSql += "'" + enderecoVO.getNumero() + "', ";
        comandoSql += "'" + enderecoVO.getComplemento() + "', ";
        comandoSql += "'" + enderecoVO.getBairro() + "', ";
        comandoSql += enderecoVO.getSisMunicipio_id() + ", ";
        comandoSql += "'" + enderecoVO.getPontoReferencia() + "'";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);
    }

}
