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
    SisMunicipioVO sisMunicipioVO;
    List<SisMunicipioVO> sisMunicipioVOList;

    public SisMunicipioVO getSisMunicipioVO(int id) {
        buscaSisMunicipioVO(id, "", 0);
        if (sisMunicipioVO!=null)
            addDetalheObjeto(sisMunicipioVO);
        return sisMunicipioVO;
    }

    public SisMunicipioVO getSisMunicipioVO(String municipio) {
        buscaSisMunicipioVO(0, municipio, 0);
        if (sisMunicipioVO!=null)
            addDetalheObjeto(sisMunicipioVO);
        return sisMunicipioVO;
    }

    public List<SisMunicipioVO> getMunicipioVOList(int uf_id) {
        buscaSisMunicipioVO(0, "", uf_id);
//        if (sisMunicipioVOList!=null)
//            for (SisMunicipioVO municipioVO : sisMunicipioVOList)
//                addDetalheObjeto(municipioVO);
        return sisMunicipioVOList;
    }

    void buscaSisMunicipioVO(int id, String municipio, int uf_id) {
        comandoSql = "SELECT id, descricao, sisUF_id, isCapital, ibge_id " +
                "FROM sisMunicipio ";
        if (id > 0) comandoSql += "WHERE id = '" + id + "' ";
        if (uf_id > 0) {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "sisUF_id = '" + uf_id + "' ";
        }
        if (municipio != "") {
            if (!comandoSql.contains("WHERE")) {
                comandoSql += "WHERE ";
            } else {
                comandoSql += "AND ";
            }
            comandoSql += "descricao = '" + municipio + "' ";
        }
        comandoSql += "ORDER BY isCapital DESC, descricao ";

        if (id == 0 && uf_id > 0) sisMunicipioVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                sisMunicipioVO = new SisMunicipioVO();
                sisMunicipioVO.setId(rs.getInt("id"));
                sisMunicipioVO.setDescricao(rs.getString("descricao"));
                sisMunicipioVO.setSisUF_id(rs.getInt("sisUF_id"));
                sisMunicipioVO.setIsCapital(rs.getInt("isCapital"));
                sisMunicipioVO.setIbge_id(rs.getInt("ibge_id"));

                if (id == 0 && uf_id > 0) sisMunicipioVOList.add(sisMunicipioVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addDetalheObjeto(SisMunicipioVO municipio){
        municipio.setUfVO(new SisUFDAO().getSisUFVO(municipio.getSisUF_id()));
    }

}
