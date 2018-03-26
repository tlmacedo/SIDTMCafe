package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SisRelacaoEmpresaEnderecoVO extends RecursiveTreeObject<SisRelacaoEmpresaEnderecoVO> {

    IntegerProperty id, tabEmpresa_id, tabEndereco_id;

    public SisRelacaoEmpresaEnderecoVO() {
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

    public int getTabEmpresa_id() {
        return tabEmpresa_idProperty().get();
    }

    public IntegerProperty tabEmpresa_idProperty() {
        if (tabEmpresa_id == null) tabEmpresa_id = new SimpleIntegerProperty(0);
        return tabEmpresa_id;
    }

    public void setTabEmpresa_id(int tabEmpresa_id) {
        tabEmpresa_idProperty().set(tabEmpresa_id);
    }

    public int getTabEndereco_id() {
        return tabEndereco_idProperty().get();
    }

    public IntegerProperty tabEndereco_idProperty() {
        if (tabEndereco_id == null) tabEndereco_id = new SimpleIntegerProperty(0);
        return tabEndereco_id;
    }

    public void setTabEndereco_id(int tabEndereco_id) {
        tabEndereco_idProperty().set(tabEndereco_id);
    }
    
}
