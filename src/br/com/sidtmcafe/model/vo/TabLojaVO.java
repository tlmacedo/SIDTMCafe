package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class TabLojaVO extends RecursiveTreeObject<TabLojaVO> {

    List<TabEnderecoVO> enderecoVOList;

    IntegerProperty id, situacaoSistema_id;
    StringProperty cnpj, ie, razao, fantasia, endereco_ids, telefone_ids, contato_ids, emailHomePage_ids;

    public TabLojaVO() {
    }

    public List<TabEnderecoVO> getEnderecoVOList() {
        return enderecoVOList;
    }

    public void setEnderecoVOList(List<TabEnderecoVO> enderecoVOList) {
        this.enderecoVOList = enderecoVOList;
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

    @Override
    public String toString() {
        return razaoProperty().get() + " (" + fantasiaProperty().get() + ")";
    }
}
