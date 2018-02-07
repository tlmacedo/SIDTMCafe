package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEmpresaVO;
import br.com.sidtmcafe.model.vo.TabEnderecoVO;
import br.com.sidtmcafe.model.vo.WsCepPostmonVO;

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
        if (idTabEnderecoVO >= 0) comandoSql += "WHERE id = '" + idTabEnderecoVO + "' ";
        comandoSql += "ORDER BY tipoEndereco_id, id ";

        enderecoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                enderecoVO = new TabEnderecoVO();
                enderecoVO.setId(rs.getInt("id"));
                enderecoVO.setTipoEndereco_id(rs.getInt("tipoEndereco_id"));
                enderecoVO.setCep(rs.getString("cep"));
                enderecoVO.setLogradouro(rs.getString("logradouro"));
                enderecoVO.setNumero(rs.getString("numero"));
                enderecoVO.setComplemento(rs.getString("complemento"));
                enderecoVO.setBairro(rs.getString("bairro"));
                enderecoVO.setUf_id(rs.getInt("uf_id"));
                enderecoVO.setMunicipio_id(rs.getInt("municipio_id"));
                enderecoVO.setPontoReferencia(rs.getString("pontoReferencia"));
                enderecoVO.setSistuacaoSistema_id(rs.getInt("situacaoSistema_id"));

                addObjetosPesquisa();

                enderecoVOList.add(enderecoVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public TabEnderecoVO getEnderecoVO(WsCepPostmonVO wsCepPostmonVO, TabEnderecoVO enderecoAnt) {
        buscaTabEnderecoVO(enderecoAnt.getId());

        if (wsCepPostmonVO == null)
            return enderecoVO;

        enderecoVO.setCep(wsCepPostmonVO.getCep());
        enderecoVO.setLogradouro(wsCepPostmonVO.getLogradouro());
        enderecoVO.setNumero("");
        enderecoVO.setComplemento("");
        enderecoVO.setBairro(wsCepPostmonVO.getBairro());
        enderecoVO.setUf_id(new SisUFDAO().getUfVO(wsCepPostmonVO.getEstado_sigla()).getId());
        enderecoVO.setMunicipio_id(new SisMunicipioDAO().getMunicipioVO(wsCepPostmonVO.getCidade_codigo_ibge()).getId());
        enderecoVO.setPontoReferencia("");
        enderecoVO.setSistuacaoSistema_id(1);

        addObjetosPesquisa();

        return enderecoVO;
    }

    void addObjetosPesquisa() {
        enderecoVO.setTipoEnderecoVO(new SisTipoEnderecoDAO().getTipoEnderecoVO(enderecoVO.getTipoEndereco_id()));
        enderecoVO.setUfVO(new SisUFDAO().getUfVO(enderecoVO.getUf_id()));
        enderecoVO.setMunicipioVO(new SisMunicipioDAO().getMunicipioVO(enderecoVO.getMunicipio_id()));
        enderecoVO.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(enderecoVO.getSistuacaoSistema_id()));
    }
}
