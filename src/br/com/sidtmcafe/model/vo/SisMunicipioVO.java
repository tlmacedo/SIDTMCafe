package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SisMunicipioVO extends RecursiveTreeObject<SisMunicipioVO> {

    SisUFVO ufVO;

    IntegerProperty id, sisUF_id, isCapital, ibge_id;
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
        if (id == null) id = new SimpleIntegerProperty(0);
        return id;
    }

    public void setId(int id) {
        idProperty().set(id);
    }

    public int getSisUF_id() {
        return sisUF_idProperty().get();
    }

    public IntegerProperty sisUF_idProperty() {
        if (sisUF_id == null) sisUF_id = new SimpleIntegerProperty(0);
        return sisUF_id;
    }

    public void setSisUF_id(int sisUF_id) {
        sisUF_idProperty().set(sisUF_id);
    }

    public int getIsCapital() {
        return isCapitalProperty().get();
    }

    public IntegerProperty isCapitalProperty() {
        if (isCapital == null) isCapital = new SimpleIntegerProperty(0);
        return isCapital;
    }

    public void setIsCapital(int isCapital) {
        isCapitalProperty().set(isCapital);
    }

    public int getIbge_id() {
        return ibge_idProperty().get();
    }

    public IntegerProperty ibge_idProperty() {
        if (ibge_id == null) ibge_id = new SimpleIntegerProperty(0);
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
