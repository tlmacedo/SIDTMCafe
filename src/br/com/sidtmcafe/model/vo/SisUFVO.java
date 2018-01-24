package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class SisUFVO extends RecursiveTreeObject<SisUFVO> {

    IntegerProperty id, ibge_id;
    StringProperty descricao, sigla;

    public SisUFVO() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getIbge_id() {
        return ibge_id.get();
    }

    public IntegerProperty ibge_idProperty() {
        return ibge_id;
    }

    public void setIbge_id(int ibge_id) {
        this.ibge_id.set(ibge_id);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public String getSigla() {
        return sigla.get();
    }

    public StringProperty siglaProperty() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla.set(sigla);
    }

    @Override
    public String toString() {
        return siglaProperty().get();
    }
}
