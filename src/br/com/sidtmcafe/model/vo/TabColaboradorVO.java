package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class TabColaboradorVO extends RecursiveTreeObject<TabColaboradorVO> {

    TabCargoVO cargoVO;
    TabLojaVO lojaVO;
    SisSituacaoSistemaVO situacaoSistemaVO;
    List<TabEnderecoVO> enderecoVOList;
    List<TabTelefoneVO> telefoneVOList;
    List<TabContatoVO> contatoVOList;
    List<TabEmailHomePageVO> emailHomePageVOList;


    IntegerProperty id, cargo_id, loja_id, situacaoSistema_id;
    StringProperty nome, apelido, senha, senhaSalt, endereco_ids, telefone_ids, contato_ids, emailHomePage_ids;

    public TabColaboradorVO() {
    }

    public TabCargoVO getCargoVO() {
        return cargoVO;
    }

    public void setCargoVO(TabCargoVO cargoVO) {
        this.cargoVO = cargoVO;
    }

    public TabLojaVO getLojaVO() {
        return lojaVO;
    }

    public void setLojaVO(TabLojaVO lojaVO) {
        this.lojaVO = lojaVO;
    }

    public SisSituacaoSistemaVO getSituacaoSistemaVO() {
        return situacaoSistemaVO;
    }

    public void setSituacaoSistemaVO(SisSituacaoSistemaVO situacaoSistemaVO) {
        this.situacaoSistemaVO = situacaoSistemaVO;
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

    public int getId() {
        return idProperty().get();
    }

    public IntegerProperty idProperty() {
        if (id == null) id = new SimpleIntegerProperty(-1);
        return id;
    }

    public void setId(int id) {
        idProperty().set(id);
    }

    public int getCargo_id() {
        return cargo_idProperty().get();
    }

    public IntegerProperty cargo_idProperty() {
        if (cargo_id == null) cargo_id = new SimpleIntegerProperty(-1);
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        cargo_idProperty().set(cargo_id);
    }

    public int getLoja_id() {
        return loja_idProperty().get();
    }

    public IntegerProperty loja_idProperty() {
        if (loja_id == null) loja_id = new SimpleIntegerProperty(-1);
        return loja_id;
    }

    public void setLoja_id(int loja_id) {
        loja_idProperty().set(loja_id);
    }

    public int getSituacaoSistema_id() {
        return situacaoSistema_idProperty().get();
    }

    public IntegerProperty situacaoSistema_idProperty() {
        if (situacaoSistema_id == null) situacaoSistema_id = new SimpleIntegerProperty(-1);
        return situacaoSistema_id;
    }

    public void setSituacaoSistema_id(int situacaoSistema_id) {
        situacaoSistema_idProperty().set(situacaoSistema_id);
    }

    public String getNome() {
        return nomeProperty().get();
    }

    public StringProperty nomeProperty() {
        if (nome == null) nome = new SimpleStringProperty("");
        return nome;
    }

    public void setNome(String nome) {
        nomeProperty().set(nome);
    }

    public String getApelido() {
        return apelidoProperty().get();
    }

    public StringProperty apelidoProperty() {
        if (apelido == null) apelido = new SimpleStringProperty("");
        return apelido;
    }

    public void setApelido(String apelido) {
        apelidoProperty().set(apelido);
    }

    public String getSenha() {
        return senhaProperty().get();
    }

    public StringProperty senhaProperty() {
        if (senha == null) senha = new SimpleStringProperty("");
        return senha;
    }

    public void setSenha(String senha) {
        senhaProperty().set(senha);
    }

    public String getSenhaSalt() {
        return senhaSaltProperty().get();
    }

    public StringProperty senhaSaltProperty() {
        if (senhaSalt == null) senhaSalt = new SimpleStringProperty("");
        return senhaSalt;
    }

    public void setSenhaSalt(String senhaSalt) {
        senhaSaltProperty().set(senhaSalt);
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

    public String getDetalheColaborador() {
        if (nomeProperty().get() != "")
            return "[Usuário]: " + nomeProperty().get() + " (" + apelidoProperty().get() + ") "
                    + ";[Cargo]: " + cargoVO.getDescricao() + "  |  [Loja]: " + lojaVO.getFantasia();
//                    + ";[End. Loja]: " + lojaVO.getEnderecoVOList().get(0).getLogradouro() + ", "
//                    + lojaVO.getEnderecoVOList().get(0).getNumero();

        return "";
    }

    @Override
    public String toString() {
        return apelidoProperty().get();
    }
}
