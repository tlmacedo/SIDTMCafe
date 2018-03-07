package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabEmpresaDetalheReceitaFederalVO extends RecursiveTreeObject<TabEmpresaDetalheReceitaFederalVO> {

    IntegerProperty id, empresa_id, isAtividadePrincipal;
    StringProperty str_key, str_value;

    public TabEmpresaDetalheReceitaFederalVO() {
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

    public int getEmpresa_id() {
        return empresa_idProperty().get();
    }

    public IntegerProperty empresa_idProperty() {
        if (empresa_id == null) empresa_id = new SimpleIntegerProperty(0);
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        empresa_idProperty().set(empresa_id);
    }

    public int getIsAtividadePrincipal() {
        return isAtividadePrincipalProperty().get();
    }

    public IntegerProperty isAtividadePrincipalProperty() {
        if (isAtividadePrincipal == null) isAtividadePrincipal = new SimpleIntegerProperty(0);
        return isAtividadePrincipal;
    }

    public void setIsAtividadePrincipal(int isAtividadePrincipal) {
        isAtividadePrincipalProperty().set(isAtividadePrincipal);
    }

    public String getStr_key() {
        return str_keyProperty().get();
    }

    public StringProperty str_keyProperty() {
        if (str_key == null) str_key = new SimpleStringProperty("");
        return str_key;
    }

    public void setStr_key(String str_key) {
        str_keyProperty().set(str_key);
    }

    public String getStr_value() {
        return str_valueProperty().get();
    }

    public StringProperty str_valueProperty() {
        if (str_value == null) str_value = new SimpleStringProperty("");
        return str_value;
    }

    public void setStr_value(String str_value) {
        str_valueProperty().set(str_value);
    }

    @Override
    public String toString() {
        return str_keyProperty().get() + " - " + str_valueProperty().get();
    }
}
