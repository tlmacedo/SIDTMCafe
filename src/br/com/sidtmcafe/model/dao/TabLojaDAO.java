package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.model.vo.TabEnderecoVO;
import br.com.sidtmcafe.model.vo.TabLojaVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabLojaDAO extends BuscaBandoDados {

    ResultSet rs;

    String comandoSql = "";
    TabLojaVO lojaVO;
    List<TabLojaVO> lojaVOList;

    public TabLojaVO getLojaVO(int idTabLoja) {
        buscaTabLojaVO(idTabLoja);
        if (lojaVO == null)
            lojaVO = new TabLojaVO();
        return lojaVO;
    }

    public List<TabLojaVO> getLojaVOList() {
        buscaTabLojaVO(0);
        if (lojaVOList == null)
            lojaVOList.add(new TabLojaVO());
        return lojaVOList;
    }

    void buscaTabLojaVO(int idTabLoja) {
        comandoSql = "SELECT * FROM tabLoja ";
        if (idTabLoja > 0) comandoSql += "WHERE id = '" + idTabLoja + "' ";
        comandoSql += "ORDER BY id DESC ";

        lojaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                lojaVO = new TabLojaVO();
                lojaVO.setId(rs.getInt("id"));
                lojaVO.setCnpj(rs.getString("cnpj"));
                lojaVO.setIe(rs.getString("ie"));
                lojaVO.setRazao(rs.getString("razao"));
                lojaVO.setFantasia(rs.getString("fantasia"));
                lojaVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));

                lojaVO.setEndereco_ids(rs.getString("endereco_ids"));
                List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
                for (String strCodEndereco : lojaVO.getEndereco_ids().split(";")) {
                    enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
                }
                lojaVO.setEnderecoVOList(enderecoVOList);

                lojaVO.setTelefone_ids(rs.getString("telefone_ids"));
                lojaVO.setContato_ids(rs.getString("contato_ids"));
                lojaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
