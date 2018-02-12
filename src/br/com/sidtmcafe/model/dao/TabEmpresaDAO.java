package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.database.ConnectionFactory;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TabEmpresaDAO extends BuscaBandoDados implements Constants {

    ResultSet rs;

    String comandoSql = "";
    TabEmpresaVO empresaVO;
    List<TabEmpresaVO> empresaVOList;

    public TabEmpresaVO getEmpresaVO(int idTabEmpresaVO) {
        buscaTabEmpresaVO(idTabEmpresaVO);
        if (empresaVO == null)
            empresaVO = new TabEmpresaVO();
        return empresaVO;
    }

    public List<TabEmpresaVO> getEmpresaVOList() {
        buscaTabEmpresaVO(-1);
        if (empresaVOList == null)
            empresaVOList.add(new TabEmpresaVO());
        return empresaVOList;
    }

    void buscaTabEmpresaVO(int idTabEmpresaVO) {
        comandoSql = "SELECT * FROM tabEmpresa ";
        if (idTabEmpresaVO >= 0) comandoSql += "WHERE id = '" + idTabEmpresaVO + "' ";
        comandoSql += "ORDER BY razao, fantasia ";

        empresaVOList = new ArrayList<>();
        rs = getResultadosBandoDados(comandoSql);
        try {
            while (rs.next()) {
                empresaVO = new TabEmpresaVO();
                empresaVO.setId(rs.getInt("id"));
                empresaVO.setIsPessoaJuridica(rs.getInt("isPessoaJuridica"));
                empresaVO.setCnpj(rs.getString("cnpj"));
                empresaVO.setIe(rs.getString("ie"));
                empresaVO.setRazao(rs.getString("razao"));
                empresaVO.setFantasia(rs.getString("fantasia"));
                empresaVO.setIsCliente(rs.getInt("isCliente"));
                empresaVO.setIsFornecedor(rs.getInt("isFornecedor"));
                empresaVO.setIsTransportadora(rs.getInt("isTransportadora"));
                empresaVO.setEndereco_ids(rs.getString("endereco_ids"));
                empresaVO.setTelefone_ids(rs.getString("telefone_ids"));
                empresaVO.setContato_ids(rs.getString("contato_ids"));
                empresaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));
                empresaVO.setUsuarioCadastro_id(rs.getInt("usuarioCadastro_id"));
                empresaVO.setDataCadastro(rs.getTimestamp("dataCadastro"));
                empresaVO.setUsuarioAtualizacao_id(rs.getInt("usuarioAtualizacao_id"));
                empresaVO.setDataAtualizacao(rs.getTimestamp("dataAtualizacao"));
                empresaVO.setSituacaoSistema_id(rs.getInt("situacaoSistema_id"));
                empresaVO.setDataAbertura(rs.getTimestamp("dataAbertura"));
                empresaVO.setNaturezaJuridica(rs.getString("naturezaJuridica"));

                addObjetosPesquisa();

                empresaVOList.add(empresaVO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public TabEmpresaVO getEmpresaVO(WsCnpjReceitaWsVO receitaWsVO, TabEmpresaVO empresaAnt) {
        buscaTabEmpresaVO(empresaAnt.getId());

        if (receitaWsVO == null)
            return empresaAnt;

        empresaVO.setIsPessoaJuridica(1);
        empresaVO.setCnpj(receitaWsVO.getCnpj());
        empresaVO.setRazao(receitaWsVO.getNome());
        empresaVO.setFantasia(receitaWsVO.getFantasia());

        LocalDate dtTemp = LocalDate.parse(receitaWsVO.getAbertura(), DTFORMAT_DATA);
        LocalDateTime dtAbertura = LocalDateTime.of(dtTemp.getYear(), dtTemp.getMonth(), dtTemp.getDayOfMonth(), 0, 0, 0);
        empresaVO.setDataAbertura(Timestamp.valueOf(dtAbertura));

        empresaVO.setNaturezaJuridica(receitaWsVO.getNaturezaJuridica());

        TabEnderecoVO enderecoVO = empresaVO.getEnderecoVOList().get(0);

        enderecoVO.setCep(receitaWsVO.getCep().replaceAll("[\\-/.]", ""));
        enderecoVO.setLogradouro(receitaWsVO.getLogradouro());
        enderecoVO.setNumero(receitaWsVO.getNumero());
        enderecoVO.setComplemento(receitaWsVO.getComplemento());
        enderecoVO.setBairro(receitaWsVO.getBairro());
        if (receitaWsVO.getUf().equals("")) receitaWsVO.setUf("AM");
        enderecoVO.setUfVO(new SisUFDAO().getUfVO(receitaWsVO.getUf()));
        enderecoVO.setUf_id(enderecoVO.getUfVO().getId());
        if (receitaWsVO.getMunicipio().equals("")) receitaWsVO.setMunicipio("MANAUS");
        enderecoVO.setMunicipioVO(new SisMunicipioDAO().getMunicipioVO(receitaWsVO.getMunicipio()));
        enderecoVO.setMunicipio_id(enderecoVO.getMunicipioVO().getId());
        empresaVO.getEnderecoVOList().set(0, enderecoVO);

        List<TabTelefoneVO> telefoneVOList = empresaVO.getTelefoneVOList();
        if (receitaWsVO.getTelefone() != "") {
            List<String> telefonesReceitaWsVOList = new ArrayList<>();
            for (String strCodTelefone : receitaWsVO.getTelefone().split(" / ")) {
                strCodTelefone = strCodTelefone.replaceAll("[\\-/.() ]", "");
                telefonesReceitaWsVOList.add(strCodTelefone.substring(2));
            }
            for (int i = 0; i < telefonesReceitaWsVOList.size(); i++) {
                TabTelefoneVO tel = new TabTelefoneVO();
                tel.setDescricao(telefonesReceitaWsVOList.get(i));
                tel.setTelefoneOperadora_id(2);
                tel.setTelefoneOperadoraVO(new SisTelefoneOperadoraDAO().getTelefoneOperadoraVO(2));
                if (telefoneVOList.size() > i) {
                    telefoneVOList.set(i, tel);
                } else {
                    tel = new TabTelefoneVO();
                    tel.setId(0);
                    telefoneVOList.add(tel);
                }
            }
            empresaVO.setTelefoneVOList(telefoneVOList);
        }


//        empresaVO.setTelefone_ids(rs.getString("telefone_ids"));
//        empresaVO.setContato_ids(rs.getString("contato_ids"));
//        empresaVO.setEmailHomePage_ids(rs.getString("emailHomePage_ids"));

        return empresaVO;
    }

    void addObjetosPesquisa() {
        List<TabEnderecoVO> enderecoVOList = new ArrayList<>();
        for (String strCodEndereco : empresaVO.getEndereco_ids().split(";")) {
            if (strCodEndereco == "") strCodEndereco = "0";
            enderecoVOList.add(new TabEnderecoDAO().getEnderecoVO(Integer.parseInt(strCodEndereco)));
        }
        empresaVO.setEnderecoVOList(enderecoVOList);

        List<TabTelefoneVO> telefoneVOList = new ArrayList<>();
        for (String strCodTelefone : empresaVO.getTelefone_ids().split(";")) {
            if (strCodTelefone == "") strCodTelefone = "0";
            telefoneVOList.add(new TabTelefoneDAO().getTelefoneVO(Integer.parseInt(strCodTelefone)));
        }
        empresaVO.setTelefoneVOList(telefoneVOList);

        List<TabContatoVO> contatoVOList = new ArrayList<>();
        for (String strCodContato : empresaVO.getContato_ids().split(";")) {
            if (strCodContato == "") strCodContato = "0";
            contatoVOList.add(new TabContatoDAO().getContatoVO(Integer.parseInt(strCodContato)));
        }
        empresaVO.setContatoVOList(contatoVOList);

        List<TabEmailHomePageVO> emailHomePageVOList = new ArrayList<>();
        for (String strCodEmailHomePage : empresaVO.getEmailHomePage_ids().split(";")) {
            if (strCodEmailHomePage == "") strCodEmailHomePage = "0";
            emailHomePageVOList.add(new TabEmailHomePageDAO().getEmailHomePageVO(Integer.parseInt(strCodEmailHomePage)));
        }
        empresaVO.setEmailHomePageVOList(emailHomePageVOList);

        empresaVO.setUsuarioCadastroVO(new TabColaboradorDAO().getColaboradorVO(empresaVO.getUsuarioCadastro_id()));
        empresaVO.setUsuarioAtualizacaoVO(new TabColaboradorDAO().getColaboradorVO(empresaVO.getUsuarioAtualizacao_id()));
        empresaVO.setSituacaoSistemaVO(new SisSituacaoSistemaDAO().getSituacaoSistemaVO(empresaVO.getSituacaoSistema_id()));

        empresaVO.setDetalheReceitaFederalVOList(new TabEmpresa_DetalheReceitaFederalDAO().getDetalheReceitaFederalVOList(empresaVO.getId()));
    }

}
