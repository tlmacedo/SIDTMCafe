package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class TabColaboradorVO extends RecursiveTreeObject<TabColaboradorVO> {

    SisCargoVO cargoVO;
    TabLojaVO lojaVO;
    SisSituacaoSistemaVO situacaoSistemaVO;
    List<TabEnderecoVO> enderecoVOList;
    List<TabTelefoneVO> telefoneVOList;
    List<TabContatoVO> contatoVOList;
    List<TabEmailHomePageVO> emailHomePageVOList;


    IntegerProperty id, sisCargo_id, tabLoja_id, sisSituacaoSistema_id;
    StringProperty nome, apelido, senha, senhaSalt;

    public TabColaboradorVO() {
    }

    public SisCargoVO getCargoVO() {
        return cargoVO;
    }

    public void setCargoVO(SisCargoVO cargoVO) {
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
        if (id == null) id = new SimpleIntegerProperty(0);
        return id;
    }

    public void setId(int id) {
        idProperty().set(id);
    }

    public int getSisCargo_id() {
        return sisCargo_idProperty().get();
    }

    public IntegerProperty sisCargo_idProperty() {
        if (sisCargo_id == null) sisCargo_id = new SimpleIntegerProperty(0);
        return sisCargo_id;
    }

    public void setSisCargo_id(int sisCargo_id) {
        sisCargo_idProperty().set(sisCargo_id);
    }

    public int getTabLoja_id() {
        return tabLoja_idProperty().get();
    }

    public IntegerProperty tabLoja_idProperty() {
        if (tabLoja_id == null) tabLoja_id = new SimpleIntegerProperty(0);
        return tabLoja_id;
    }

    public void setTabLoja_id(int tabLoja_id) {
        tabLoja_idProperty().set(tabLoja_id);
    }

    public int getSisSituacaoSistema_id() {
        return sisSituacaoSistema_idProperty().get();
    }

    public IntegerProperty sisSituacaoSistema_idProperty() {
        if (sisSituacaoSistema_id == null) sisSituacaoSistema_id = new SimpleIntegerProperty(0);
        return sisSituacaoSistema_id;
    }

    public void setSisSituacaoSistema_id(int sisSituacaoSistema_id) {
        sisSituacaoSistema_idProperty().set(sisSituacaoSistema_id);
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

    public String getDetalheColaborador() {
        if (nomeProperty().get() != "")
            return "[Usuário]: " + nomeProperty().get() + " (" + apelidoProperty().get() + ") "
                    + ";[Cargo]: " + cargoVO.getDescricao() + "  |  [Loja]: " + lojaVO.getEmpresaVO().getFantasia();
//                    + ";[End. Loja]: " + lojaVO.getEnderecoVOList().get(0).getLogradouro() + ", "
//                    + lojaVO.getEnderecoVOList().get(0).getNumero();

        return "";
    }

    @Override
    public String toString() {
        return apelidoProperty().get();
    }
}
