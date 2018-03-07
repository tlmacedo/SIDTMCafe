package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.SisFiscalCstPisCofinsVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisFiscalCstPisCofinsDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    SisFiscalCstPisCofinsVO fiscalCstPisCofinsVO;
    List<SisFiscalCstPisCofinsVO> fiscalCstPisCofinsVOList;

    public SisFiscalCstPisCofinsVO getSisFiscalCstPisCofinsVO(int idSisFiscalCstPisCofinsVO) {
        buscaSisFiscalCstPisCofinsVO(idSisFiscalCstPisCofinsVO);
        return fiscalCstPisCofinsVO;
    }

    public List<SisFiscalCstPisCofinsVO> getSisFiscalCstPisCofinsVOList() {
        buscaSisFiscalCstPisCofinsVO(0);
        return fiscalCstPisCofinsVOList;
    }

    void buscaSisFiscalCstPisCofinsVO(int idSisFiscalCstPisCofinsVO) {
        comandoSql = "SELECT * FROM sisFiscalCstPisCofins ";
        if (idSisFiscalCstPisCofinsVO > 0) comandoSql += "WHERE id = '" + idSisFiscalCstPisCofinsVO + "' ";
        comandoSql += "ORDER BY id ";

        rs = getResultadosBandoDados(comandoSql);
        fiscalCstPisCofinsVOList = new ArrayList<>();
        try {
            while (rs.next()) {
                fiscalCstPisCofinsVO = new SisFiscalCstPisCofinsVO();
                fiscalCstPisCofinsVO.setId(rs.getInt("id"));
                fiscalCstPisCofinsVO.setDescricao(rs.getString("descricao"));

                fiscalCstPisCofinsVOList.add(fiscalCstPisCofinsVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

}
