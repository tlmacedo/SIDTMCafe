package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tabEnderecoVO extends RecursiveTreeObject<tabEnderecoVO> {

    sisTipoEnderecoVO tipoEnderecoVO;

    IntegerProperty id, tipoEndereco_id, uf_id, municipio_id, sistuacaoSistema_id;
    StringProperty cep, logradouro, numero, complemento, bairro;

    public tabEnderecoVO() {
    }

    public sisTipoEnderecoVO getTipoEnderecoVO() {
        return tipoEnderecoVO;
    }

    public void setTipoEnderecoVO(sisTipoEnderecoVO tipoEnderecoVO) {
        this.tipoEnderecoVO = tipoEnderecoVO;
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

    public int getTipoEndereco_id() {
        return tipoEndereco_idProperty().get();
    }

    public IntegerProperty tipoEndereco_idProperty() {
        if (tipoEndereco_id == null) tipoEndereco_id = new SimpleIntegerProperty(-1);
        return tipoEndereco_id;
    }

    public void setTipoEndereco_id(int tipoEndereco_id) {
        tipoEndereco_idProperty().set(tipoEndereco_id);
    }

    public int getUf_id() {
        return uf_idProperty().get();
    }

    public IntegerProperty uf_idProperty() {
        if (uf_id == null) uf_id = new SimpleIntegerProperty(-1);
        return uf_id;
    }

    public void setUf_id(int uf_id) {
        uf_idProperty().set(uf_id);
    }

    public int getMunicipio_id() {
        return municipio_idProperty().get();
    }

    public IntegerProperty municipio_idProperty() {
        if (municipio_id == null) municipio_id = new SimpleIntegerProperty(-1);
        return municipio_id;
    }

    public void setMunicipio_id(int municipio_id) {
        municipio_idProperty().set(municipio_id);
    }

    public int getSistuacaoSistema_id() {
        return sistuacaoSistema_idProperty().get();
    }

    public IntegerProperty sistuacaoSistema_idProperty() {
        if (sistuacaoSistema_id == null) sistuacaoSistema_id = new SimpleIntegerProperty(-1);
        return sistuacaoSistema_id;
    }

    public void setSistuacaoSistema_id(int sistuacaoSistema_id) {
        sistuacaoSistema_idProperty().set(sistuacaoSistema_id);
    }

    public String getCep() {
        return cepProperty().get();
    }

    public StringProperty cepProperty() {
        if (cep == null) cep = new SimpleStringProperty("");
        return cep;
    }

    public void setCep(String cep) {
        cepProperty().set(cep);
    }

    public String getLogradouro() {
        return logradouroProperty().get();
    }

    public StringProperty logradouroProperty() {
        if (logradouro == null) logradouro = new SimpleStringProperty("");
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        logradouroProperty().set(logradouro);
    }

    public String getNumero() {
        return numeroProperty().get();
    }

    public StringProperty numeroProperty() {
        if (numero == null) numero = new SimpleStringProperty("");
        return numero;
    }

    public void setNumero(String numero) {
        numeroProperty().set(numero);
    }

    public String getComplemento() {
        return complementoProperty().get();
    }

    public StringProperty complementoProperty() {
        if (complemento == null) complemento = new SimpleStringProperty("");
        return complemento;
    }

    public void setComplemento(String complemento) {
        complementoProperty().set(complemento);
    }

    public String getBairro() {
        return bairroProperty().get();
    }

    public StringProperty bairroProperty() {
        if (bairro == null) bairro = new SimpleStringProperty("");
        return bairro;
    }

    public void setBairro(String bairro) {
        bairroProperty().set(bairro);
    }

    @Override
    public String toString() {
        return logradouroProperty().get();
    }
}
