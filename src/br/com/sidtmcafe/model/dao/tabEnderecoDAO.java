package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.tabEnderecoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tabEnderecoDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    String comandoSql = "";
    tabEnderecoVO enderecoVO;
    List<tabEnderecoVO> enderecoVOList;

    public tabEnderecoVO getEnderecoVO(int idTabEnderecoVO) {
        comandoSql = "SELECT * FROM tabEndereco ";
        if (idTabEnderecoVO > 0) comandoSql += "WHERE id = '" + idTabEnderecoVO + "' ";
        comandoSql += "ORDER BY id ";
        if (buscaTabEnderecoVO(comandoSql) == null)
            enderecoVO = new tabEnderecoVO();
        return enderecoVO;
    }

    public List<tabEnderecoVO> getEnderecoVOList() {
        comandoSql = "SELECT * FROM tabEndereco ";
        comandoSql += "ORDER BY id ";
        if (buscaListaTabEnderecoVO(comandoSql) == null)
            enderecoVOList.add(new tabEnderecoVO());
        return enderecoVOList;
    }

    private tabEnderecoVO buscaTabEnderecoVO(String instrucaoSql) {
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                enderecoVO = new tabEnderecoVO();
                enderecoVO.setId(rs.getInt("id"));

                enderecoVO.setTipoEndereco_id(rs.getInt("tipoEndereco_id"));
                enderecoVO.setTipoEnderecoVO(new sisTipoEnderecoDAO().getTipoEnderecoVO(enderecoVO.getTipoEndereco_id()));

                enderecoVO.setCep(rs.getString("cep"));
                enderecoVO.setLogradouro(rs.getString("logradouro"));
                enderecoVO.setNumero(rs.getString("numero"));
                enderecoVO.setComplemento(rs.getString("complemento"));
                enderecoVO.setBairro(rs.getString("bairro"));
                enderecoVO.setUf_id(rs.getInt("uf_id"));
                enderecoVO.setMunicipio_id(rs.getInt("municipio_id"));
                enderecoVO.setSistuacaoSistema_id(rs.getInt("situacaoSistema_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return enderecoVO;
    }

    private List<tabEnderecoVO> buscaListaTabEnderecoVO(String instrucaoSql) {
        enderecoVOList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement(instrucaoSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                enderecoVO = new tabEnderecoVO();
                enderecoVO.setId(rs.getInt("id"));

                enderecoVO.setTipoEndereco_id(rs.getInt("tipoEndereco_id"));
                enderecoVO.setTipoEnderecoVO(new sisTipoEnderecoDAO().getTipoEnderecoVO(enderecoVO.getTipoEndereco_id()));

                enderecoVO.setCep(rs.getString("cep"));
                enderecoVO.setLogradouro(rs.getString("logradouro"));
                enderecoVO.setNumero(rs.getString("numero"));
                enderecoVO.setComplemento(rs.getString("complemento"));
                enderecoVO.setBairro(rs.getString("bairro"));
                enderecoVO.setUf_id(rs.getInt("uf_id"));
                enderecoVO.setMunicipio_id(rs.getInt("municipio_id"));
                enderecoVO.setSistuacaoSistema_id(rs.getInt("situacaoSistema_id"));

                enderecoVOList.add(enderecoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return enderecoVOList;
    }
}
