package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public class TabEmpresaVO extends RecursiveTreeObject<TabEmpresaVO> {

    List<TabEnderecoVO> enderecoVOList;
    List<TabTelefoneVO> telefoneVOList;
    List<TabContatoVO> contatoVOList;
    List<TabEmailHomePageVO> emailHomePageVOList;
    TabColaboradorVO usuarioCadastroVO;
    TabColaboradorVO usuarioAtualizacaoVO;
    SisSituacaoSistemaVO situacaoSistemaVO;
    List<TabEmpresaDetalheReceitaFederalVO> detalheReceitaFederalVOList;

    Date dataAbertura;
    Timestamp dataCadastro, dataAtualizacao;
    IntegerProperty id, isEmpresa, usuarioCadastro_id, usuarioAtualizacao_id, situacaoSistema_id;
    StringProperty cnpj, ie, razao, fantasia, naturezaJuridica;

    public TabEmpresaVO() {
    }

    public List<TabEnderecoVO> getEnderecoVOList() {
        return enderecoVOList;
    }

    public void setEnderecoVOList(List<TabEnderecoVO> enderecoVOList) {
        this.enderecoVOList = enderecoVOList;
    }

    public List<TabTelefoneVO> getTelefoneVOList() {
        return telefoneVOList;
    }

    public void setTelefoneVOList(List<TabTelefoneVO> telefoneVOList) {
        this.telefoneVOList = telefoneVOList;
    }

    public List<TabContatoVO> getContatoVOList() {
        return contatoVOList;
    }

    public void setContatoVOList(List<TabContatoVO> contatoVOList) {
        this.contatoVOList = contatoVOList;
    }

    public List<TabEmailHomePageVO> getEmailHomePageVOList() {
        return emailHomePageVOList;
    }

    public void setEmailHomePageVOList(List<TabEmailHomePageVO> emailHomePageVOList) {
        this.emailHomePageVOList = emailHomePageVOList;
    }

    public TabColaboradorVO getUsuarioCadastroVO() {
        return usuarioCadastroVO;
    }

    public void setUsuarioCadastroVO(TabColaboradorVO usuarioCadastroVO) {
        this.usuarioCadastroVO = usuarioCadastroVO;
    }

    public TabColaboradorVO getUsuarioAtualizacaoVO() {
        return usuarioAtualizacaoVO;
    }

    public void setUsuarioAtualizacaoVO(TabColaboradorVO usuarioAtualizacaoVO) {
        this.usuarioAtualizacaoVO = usuarioAtualizacaoVO;
    }

    public SisSituacaoSistemaVO getSituacaoSistemaVO() {
        return situacaoSistemaVO;
    }

    public void setSituacaoSistemaVO(SisSituacaoSistemaVO situacaoSistemaVO) {
        this.situacaoSistemaVO = situacaoSistemaVO;
    }

    public List<TabEmpresaDetalheReceitaFederalVO> getDetalheReceitaFederalVOList() {
        return detalheReceitaFederalVOList;
    }

    public void setDetalheReceitaFederalVOList(List<TabEmpresaDetalheReceitaFederalVO> detalheReceitaFederalVOList) {
        this.detalheReceitaFederalVOList = detalheReceitaFederalVOList;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Timestamp getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Timestamp dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getId() {
        return idProperty().get();
    }

    public IntegerProperty idProperty() {
        if (id == null) id = new SimpleIntegerProperty(0);
        return id;
    }

    public void setId(int id) {
        idProperty().set(id);
    }

    public int getIsEmpresa() {
        return isEmpresaProperty().get();
    }

    public IntegerProperty isEmpresaProperty() {
        if (isEmpresa==null)isEmpresa=new SimpleIntegerProperty(0);
        return isEmpresa;
    }

    public void setIsEmpresa(int isEmpresa) {
        isEmpresaProperty().set(isEmpresa);
    }

    public int getUsuarioCadastro_id() {
        return usuarioCadastro_idProperty().get();
    }

    public IntegerProperty usuarioCadastro_idProperty() {
        if (usuarioCadastro_id == null) usuarioCadastro_id = new SimpleIntegerProperty(0);
        return usuarioCadastro_id;
    }

    public void setUsuarioCadastro_id(int usuarioCadastro_id) {
        usuarioCadastro_idProperty().set(usuarioCadastro_id);
    }

    public int getUsuarioAtualizacao_id() {
        return usuarioAtualizacao_idProperty().get();
    }

    public IntegerProperty usuarioAtualizacao_idProperty() {
        if (usuarioAtualizacao_id == null) usuarioAtualizacao_id = new SimpleIntegerProperty(0);
        return usuarioAtualizacao_id;
    }

    public void setUsuarioAtualizacao_id(int usuarioAtualizacao_id) {
        usuarioAtualizacao_idProperty().set(usuarioAtualizacao_id);
    }

    public int getSituacaoSistema_id() {
        return situacaoSistema_idProperty().get();
    }

    public IntegerProperty situacaoSistema_idProperty() {
        if (situacaoSistema_id == null) situacaoSistema_id = new SimpleIntegerProperty(0);
        return situacaoSistema_id;
    }

    public void setSituacaoSistema_id(int situacaoSistema_id) {
        situacaoSistema_idProperty().set(situacaoSistema_id);
    }

    public String getCnpj() {
        return cnpjProperty().get();
    }

    public StringProperty cnpjProperty() {
        if (cnpj == null) cnpj = new SimpleStringProperty("");
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        cnpjProperty().set(cnpj);
    }

    public String getIe() {
        return ieProperty().get();
    }

    public StringProperty ieProperty() {
        if (ie == null) ie = new SimpleStringProperty("");
        return ie;
    }

    public void setIe(String ie) {
        ieProperty().set(ie);
    }

    public String getRazao() {
        return razaoProperty().get();
    }

    public StringProperty razaoProperty() {
        if (razao == null) razao = new SimpleStringProperty("");
        return razao;
    }

    public void setRazao(String razao) {
        razaoProperty().set(razao);
    }

    public String getFantasia() {
        return fantasiaProperty().get();
    }

    public StringProperty fantasiaProperty() {
        if (fantasia == null) fantasia = new SimpleStringProperty("");
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        fantasiaProperty().set(fantasia);
    }

    public String getNaturezaJuridica() {
        return naturezaJuridicaProperty().get();
    }

    public StringProperty naturezaJuridicaProperty() {
        if (naturezaJuridica == null) naturezaJuridica = new SimpleStringProperty("");
        return naturezaJuridica;
    }

    public void setNaturezaJuridica(String naturezaJuridica) {
        naturezaJuridicaProperty().set(naturezaJuridica);
    }

    public String getDetalheTransportadora() {
        if ((razaoProperty().get() != "") & enderecoVOList.size() > 0)
            return "[Transp]: " + razaoProperty().get() + " (" + fantasiaProperty().get() + ") "
                    + ";[End.]: " + enderecoVOList.get(0).getLogradouro() + ", "
                    + enderecoVOList.get(0).getNumero() + " - " + enderecoVOList.get(0).getBairro();
        return "";
    }

    public String getDetalheFornecedor() {
        if ((razaoProperty().get() != "") & enderecoVOList.size() > 0)
            return "[Fornec]: " + razaoProperty().get() + " (" + fantasiaProperty().get() + ") "
                    + ";[End.]: " + enderecoVOList.get(0).getLogradouro() + ", "
                    + enderecoVOList.get(0).getNumero() + " - " + enderecoVOList.get(0).getBairro();
        return "";
    }

    public String getDetalheCliente() {
        if ((razaoProperty().get() != "") & enderecoVOList.size() > 0)
            return "[Cliente]: " + razaoProperty().get() + " (" + fantasiaProperty().get() + ") "
                    + ";[End.]: " + enderecoVOList.get(0).getLogradouro() + ", "
                    + enderecoVOList.get(0).getNumero() + " - " + enderecoVOList.get(0).getBairro();
        return "";
    }

    @Override
    public String toString() {
        return razaoProperty().get() + " (" + fantasiaProperty().get() + ")";
    }
}
