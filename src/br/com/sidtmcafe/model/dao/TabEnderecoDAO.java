package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEnderecoVO;

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
        if (enderecoVO == null)
            enderecoVO = new TabEnderecoVO();
        return enderecoVO;
    }

    public List<TabEnderecoVO> getEnderecoVOList() {
        buscaTabEnderecoVO(0);
        if (enderecoVOList == null)
            enderecoVOList.add(new TabEnderecoVO());
        return enderecoVOList;
    }

    void buscaTabEnderecoVO(int idTabEnderecoVO) {
        comandoSql = "SELECT * FROM tabEndereco ";
        if (idTabEnderecoVO > 0) comandoSql += "WHERE id = '" + idTabEnderecoVO + "' ";
        comandoSql += "ORDER BY tipoEndereco_id, id ";

        enderecoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                enderecoVO = new TabEnderecoVO();
                enderecoVO.setId(rs.getInt("id"));

                enderecoVO.setTipoEndereco_id(rs.getInt("tipoEndereco_id"));
                enderecoVO.setTipoEnderecoVO(new SisTipoEnderecoDAO().getTipoEnderecoVO(enderecoVO.getTipoEndereco_id()));

                enderecoVO.setCep(rs.getString("cep"));
                enderecoVO.setLogradouro(rs.getString("logradouro"));
                enderecoVO.setNumero(rs.getString("numero"));
                enderecoVO.setComplemento(rs.getString("complemento"));
                enderecoVO.setBairro(rs.getString("bairro"));
                enderecoVO.setUf_id(rs.getInt("uf_id"));
                enderecoVO.setMunicipio_id(rs.getInt("municipio_id"));

                enderecoVO.setSistuacaoSistema_id(rs.getInt("situacaoSistema_id"));
                enderecoVO.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(enderecoVO.getSistuacaoSistema_id()));

                enderecoVOList.add(enderecoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
