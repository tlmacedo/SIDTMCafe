package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.SisRelacaoEmpresaEnderecoVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SisRelacaoEmpresaEnderecoDAO extends BuscaBandoDados implements Constants {

    ResultSet rs;

    String comandoSql = "";
    SisRelacaoEmpresaEnderecoVO relacaoEmpresaEnderecoVO;
    List<SisRelacaoEmpresaEnderecoVO> relacaoEmpresaEnderecoVOList;


//    public SisRelacaoEmpresaEnderecoVO getRelacaoEmpresaEnderecoVO(int idRelacaoEmpresaEnderecoVO) {
//        buscaSisRelacaoEmpresaEnderecoVO(idRelacaoEmpresaEnderecoVO);
//        return relacaoEmpresaEnderecoVO;
//    }

    public List<SisRelacaoEmpresaEnderecoVO> getRelacaoEmpresaEnderecoVOList(int idEmpresa) {
        buscaSisRelacaoEmpresaEnderecoVO(0);
        return relacaoEmpresaEnderecoVOList;
    }

    void buscaSisRelacaoEmpresaEnderecoVO(int idRelacaoEmpresaEnderecoVO) {
        comandoSql = "SELECT id, tabEmpresa_id, tabEndereco_id FROM sisRelacaoEmpresaEndereco ";
        if (idRelacaoEmpresaEnderecoVO > 0) comandoSql += "WHERE id = '" + idRelacaoEmpresaEnderecoVO + "' ";
        comandoSql += "ORDER BY tabEmpresa_id ";

        relacaoEmpresaEnderecoVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                relacaoEmpresaEnderecoVO = new SisRelacaoEmpresaEnderecoVO();
                relacaoEmpresaEnderecoVO.setId(rs.getInt("id"));
                relacaoEmpresaEnderecoVO.setTabEmpresa_id(rs.getInt("tabEmpresa_id"));
                relacaoEmpresaEnderecoVO.setTabEndereco_id(rs.getInt("tabEndereco_id"));

                relacaoEmpresaEnderecoVOList.add(relacaoEmpresaEnderecoVO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
