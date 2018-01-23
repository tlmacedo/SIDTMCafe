package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabTelefoneVO extends RecursiveTreeObject<TabTelefoneVO> {

    SisTelefoneOperadoraVO telefoneOperadoraVO;

    IntegerProperty id, telefoneOperadora_id;
    StringProperty descricao;

    public TabTelefoneVO() {
    }

    public SisTelefoneOperadoraVO getTelefoneOperadoraVO() {
        return telefoneOperadoraVO;
    }

    public void setTelefoneOperadoraVO(SisTelefoneOperadoraVO telefoneOperadoraVO) {
        this.telefoneOperadoraVO = telefoneOperadoraVO;
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

    public int getTelefoneOperadora_id() {
        return telefoneOperadora_idProperty().get();
    }

    public IntegerProperty telefoneOperadora_idProperty() {
        if (telefoneOperadora_id == null) telefoneOperadora_id = new SimpleIntegerProperty(-1);
        return telefoneOperadora_id;
    }

    public void setTelefoneOperadora_id(int telefoneOperadora_id) {
        telefoneOperadora_idProperty().set(telefoneOperadora_id);
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
        if (telefoneOperadoraVO.getTipo() == 0) {
            return descricaoProperty().get() + " fixo(" + telefoneOperadoraVO.getDescricao() + ")";
        } else {
            return descricaoProperty().get() + " celular(" + telefoneOperadoraVO.getDescricao() + ")";
        }
    }
}
