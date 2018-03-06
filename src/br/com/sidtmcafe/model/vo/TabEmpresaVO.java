package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.joda.time.DateTime;

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
    List<TabEmpresa_DetalheReceitaFederalVO> detalheReceitaFederalVOList;

    Date dataAbertura;
    Timestamp dataCadastro, dataAtualizacao;
    IntegerProperty id, isPessoaJuridica, isCliente, isFornecedor, isTransportadora, usuarioCadastro_id, usuarioAtualizacao_id, situacaoSistema_id;
    StringProperty cnpj, ie, razao, fantasia, endereco_ids, telefone_ids, contato_ids, emailHomePage_ids, naturezaJuridica;

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

    public List<TabEmpresa_DetalheReceitaFederalVO> getDetalheReceitaFederalVOList() {
        return detalheReceitaFederalVOList;
    }

    public void setDetalheReceitaFederalVOList(List<TabEmpresa_DetalheReceitaFederalVO> detalheReceitaFederalVOList) {
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

    public int getIsPessoaJuridica() {
        return isPessoaJuridicaProperty().get();
    }

    public IntegerProperty isPessoaJuridicaProperty() {
        if (isPessoaJuridica == null) isPessoaJuridica = new SimpleIntegerProperty(0);
        return isPessoaJuridica;
    }

    public void setIsPessoaJuridica(int isPessoaJuridica) {
        isPessoaJuridicaProperty().set(isPessoaJuridica);
    }

    public int getIsCliente() {
        return isClienteProperty().get();
    }

    public IntegerProperty isClienteProperty() {
        if (isCliente == null) isCliente = new SimpleIntegerProperty(0);
        return isCliente;
    }

    public void setIsCliente(int isCliente) {
        isClienteProperty().set(isCliente);
    }

    public int getIsFornecedor() {
        return isFornecedorProperty().get();
    }

    public IntegerProperty isFornecedorProperty() {
        if (isFornecedor == null) isFornecedor = new SimpleIntegerProperty(0);
        return isFornecedor;
    }

    public void setIsFornecedor(int isFornecedor) {
        isFornecedorProperty().set(isFornecedor);
    }

    public int getIsTransportadora() {
        return isTransportadoraProperty().get();
    }

    public IntegerProperty isTransportadoraProperty() {
        if (isTransportadora == null) isTransportadora = new SimpleIntegerProperty(0);
        return isTransportadora;
    }

    public void setIsTransportadora(int isTransportadora) {
        isTransportadoraProperty().set(isTransportadora);
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

    public String getEndereco_ids() {
        return endereco_idsProperty().get();
    }

    public StringProperty endereco_idsProperty() {
        if (endereco_ids == null) endereco_ids = new SimpleStringProperty("");
        return endereco_ids;
    }

    public void setEndereco_ids(String endereco_ids) {
        endereco_idsProperty().set(endereco_ids);
    }

    public String getTelefone_ids() {
        return telefone_idsProperty().get();
    }

    public StringProperty telefone_idsProperty() {
        if (telefone_ids == null) telefone_ids = new SimpleStringProperty("");
        return telefone_ids;
    }

    public void setTelefone_ids(String telefone_ids) {
        telefone_idsProperty().set(telefone_ids);
    }

    public String getContato_ids() {
        return contato_idsProperty().get();
    }

    public StringProperty contato_idsProperty() {
        if (contato_ids == null) contato_ids = new SimpleStringProperty("");
        return contato_ids;
    }

    public void setContato_ids(String contato_ids) {
        contato_idsProperty().set(contato_ids);
    }

    public String getEmailHomePage_ids() {
        return emailHomePage_idsProperty().get();
    }

    public StringProperty emailHomePage_idsProperty() {
        if (emailHomePage_ids == null) emailHomePage_ids = new SimpleStringProperty("");
        return emailHomePage_ids;
    }

    public void setEmailHomePage_ids(String emailHomePage_ids) {
        emailHomePage_idsProperty().set(emailHomePage_ids);
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

    @Override
    public String toString() {
        return razaoProperty().get();
    }
}
