package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisMunicipioVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SisMunicipioDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisMunicipioVO municipioVO;
    List<SisMunicipioVO> municipioVOList;

    public SisMunicipioVO getMunicipioVO(int idSisMunicipioVO) {
        buscaSisMunicipioVO(idSisMunicipioVO, -1, "");
        if (municipioVO != null)
            addObjetosPesquisa(municipioVO);
        return municipioVO;
    }

    public SisMunicipioVO getMunicipioVO(String strMunicipio) {
        buscaSisMunicipioVO(-1, -1, strMunicipio);
        if (municipioVO != null)
            addObjetosPesquisa(municipioVO);
        return municipioVO;
    }

    public List<SisMunicipioVO> getMunicipioVOList() {
        buscaSisMunicipioVO(-1, -1, "");
        if (municipioVOList != null)
            for (SisMunicipioVO municipio : municipioVOList)
                addObjetosPesquisa(municipio);
        return municipioVOList;
    }

    public List<SisMunicipioVO> getMunicipioVOList(int idUf_id) {
        buscaSisMunicipioVO(-1, idUf_id, "");
        if (municipioVOList != null)
            for (SisMunicipioVO municipio : municipioVOList)
                addObjetosPesquisa(municipio);
        return municipioVOList;
    }

    void buscaSisMunicipioVO(int idSisMunicipioVO, int idUf_id, String strMunicipio) {
        comandoSql = "SELECT * FROM sisMunicipio ";
        if (idSisMunicipioVO > 0) comandoSql += "WHERE id = '" + idSisMunicipioVO + "' ";
        if (idUf_id > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "sisUF_id = '" + idUf_id + "' ";
        }
        if (strMunicipio != "") {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "descricao = '" + strMunicipio + "' ";
        }
        comandoSql += "ORDER BY isCapital DESC, descricao ";

        municipioVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                municipioVO = new SisMunicipioVO();
                municipioVO.setId(rs.getInt("id"));
                municipioVO.setDescricao(rs.getString("descricao"));
                municipioVO.setUf_id(rs.getInt("sisUF_id"));
                municipioVO.setIsCapital(rs.getInt("isCapital"));
                municipioVO.setIbge_id(rs.getInt("ibge_id"));

                municipioVOList.add(municipioVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(SisMunicipioVO municipio) {
        municipioVO.setUfVO(new SisUFDAO().getUfVO(municipio.getUf_id()));
    }

}
