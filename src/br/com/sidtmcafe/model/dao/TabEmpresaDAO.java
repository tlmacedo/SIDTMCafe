package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabEmpresaDAO extends BuscaBandoDados implements Constants {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresaVO empresaVO;
    List<TabEmpresaVO> empresaVOList;

    public TabEmpresaVO getEmpresaVO(int idTabEmpresaVO) {
        buscaTabEmpresaVO(idTabEmpresaVO);
        if (empresaVO != null)
            addObjetosPesquisa(empresaVO);
        return empresaVO;
    }

    public List<TabEmpresaVO> getEmpresaVOList() {
        buscaTabEmpresaVO(0);
        if (empresaVOList != null)
            for (TabEmpresaVO empresa : empresaVOList) {
                addObjetosPesquisa(empresa);
            }
        return empresaVOList;
    }

    void buscaTabEmpresaVO(int idTabEmpresaVO) {
        comandoSql = "SELECT * FROM tabEmpresa ";
        if (idTabEmpresaVO > 0) comandoSql += "WHERE id = '" + idTabEmpresaVO + "' ";
        comandoSql += "ORDER BY razao, fantasia ";

        empresaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                empresaVO = new TabEmpresaVO();
                empresaVO.setId(rs.getInt("id"));
                empresaVO.setIsEmpresa(rs.getInt("isEmpresa"));
                empresaVO.setCnpj(rs.getString("cnpj"));
                empresaVO.setIe(rs.getString("ie"));
                empresaVO.setRazao(rs.getString("razao"));
                empresaVO.setFantasia(rs.getString("fantasia"));
                empresaVO.setSisSituacaoSistema_id(rs.getInt("sisSituacaoSistema_id"));
                empresaVO.setUsuarioCadastro_id(rs.getInt("usuarioCadastro_id"));
                empresaVO.setDataCadastro(rs.getTimestamp("dataCadastro"));
                empresaVO.setUsuarioAtualizacao_id(rs.getInt("usuarioAtualizacao_id"));
                empresaVO.setDataAtualizacao(rs.getTimestamp("dataAtualizacao"));
                empresaVO.setDataAbertura(rs.getDate("dataAbertura"));
                empresaVO.setNaturezaJuridica(rs.getString("naturezaJuridica"));

                empresaVOList.add(empresaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    void addObjetosPesquisa(TabEmpresaVO empresa) {

        List<TabEnderecoVO> end = new ArrayList<>();
        List<SisRelEmpresaEnderecoVO> relEmpEnd = new ArrayList<>();
        relEmpEnd = new SisRelEmpresaEnderecoDAO().getRelEmpresaEnderecoVOList(empresa.getId());
        for (SisRelEmpresaEnderecoVO rel : relEmpEnd) {
//            int i = end.size();
            end.add(new TabEnderecoDAO().getEnderecoVO(rel.getTabEndereco_id()));
//            System.out.println("end: [" + end.get(i).getSisMunicipio_id() + "] " + end.get(i).getMunicipioVO().getId() +
//                    "  " + end.get(i).getMunicipioVO().getDescricao() + " " + end.get(i).getMunicipioVO().getUf_id() +
//                    "  " + end.get(i).getMunicipioVO().getUfVO());
        }
        empresa.setEnderecoVOList(end);

        empresa.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(empresa.getUsuarioCadastro_id(), false));
        empresa.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(empresa.getUsuarioAtualizacao_id(), false));
        empresa.setSisSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(empresa.getSisSituacaoSistema_id()));
    }

    public void updateTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
        comandoSql = "UPDATE tabEmpresa SET ";
        comandoSql += "isEmpresa = " + empresaVO.getIsEmpresa() + ", ";
        comandoSql += "cnpj = '" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
        comandoSql += "ie = '" + empresaVO.getIe().replaceAll("'", "") + "', ";
        comandoSql += "razao = '" + empresaVO.getRazao().replaceAll("'", "") + "', ";
        comandoSql += "fantasia = '" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
        comandoSql += "sisSituacaoSistema_id = " + empresaVO.getSisSituacaoSistema_id() + ", ";
        comandoSql += "usuarioAtualizacao_id = " + empresaVO.getUsuarioAtualizacao_id() + ", ";
        comandoSql += "dataAbertura = '" + empresaVO.getDataAbertura() + "', ";
        comandoSql += "naturezaJuridica = '" + empresaVO.getNaturezaJuridica().replaceAll("[']", "") + "' ";
        comandoSql += "WHERE id = " + empresaVO.getId();

        if (getUpdateBancoDados(conn, comandoSql)) ;

    }

    public int insertTabEmpresaVO(Connection conn, TabEmpresaVO empresaVO) throws SQLException {
        comandoSql = "INSERT INTO tabEmpresa ";
        comandoSql += "(isEmpresa, cnpj, ie, razao, fantasia, sisSituacaoSistema_id, ";
        comandoSql += "usuarioCadastro_id, dataAbertura, naturezaJuridica) ";
        comandoSql += "VALUES(";
        comandoSql += empresaVO.getIsEmpresa() + ", ";
        comandoSql += "'" + empresaVO.getCnpj().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getIe().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getRazao().replaceAll("'", "") + "', ";
        comandoSql += "'" + empresaVO.getFantasia().replaceAll("'", "") + "', ";
        comandoSql += empresaVO.getSisSituacaoSistema_id() + ", ";
        comandoSql += empresaVO.getUsuarioCadastro_id() + ", ";
        comandoSql += "'" + empresaVO.getDataAbertura() + "', ";
        comandoSql += "'" + empresaVO.getNaturezaJuridica().replaceAll("'", "") + "'";
        comandoSql += ") ";

        return getInsertBancoDados(conn, comandoSql);

    }

}
