package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SisMunicipioVO extends RecursiveTreeObject<SisMunicipioVO> {

    SisUFVO ufVO;

    IntegerProperty id, uf_id, isCapital, ibge_id;
    StringProperty descricao;

    public SisMunicipioVO() {
    }

    public SisUFVO getUfVO() {
        return ufVO;
    }

    public void setUfVO(SisUFVO ufVO) {
        this.ufVO = ufVO;
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

    public int getIsCapital() {
        return isCapitalProperty().get();
    }

    public IntegerProperty isCapitalProperty() {
        if (isCapital == null) isCapital = new SimpleIntegerProperty(-1);
        return isCapital;
    }

    public void setIsCapital(int isCapital) {
        isCapitalProperty().set(isCapital);
    }

    public int getIbge_id() {
        return ibge_idProperty().get();
    }

    public IntegerProperty ibge_idProperty() {
        if (ibge_id == null) ibge_id = new SimpleIntegerProperty(-1);
        return ibge_id;
    }

    public void setIbge_id(int ibge_id) {
        ibge_idProperty().set(ibge_id);
    }

    public String getDescricao() {
        return descricaoProperty().get();
    }

    public StringProperty descricaoProperty() {
        if (descricao == null) descricao = new SimpleStringProperty("");
        return descricao;
    }

    public void setDescricao(String descricao) {
        descricaoProperty().set(descricao);
    }

    @Override
    public String toString() {
        return descricaoProperty().get();
    }
}
