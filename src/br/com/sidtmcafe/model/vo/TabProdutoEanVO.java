package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabProdutoEanVO extends RecursiveTreeObject<TabProdutoEanVO> {

    IntegerProperty id, produto_id;
    StringProperty descricao;

    public TabProdutoEanVO() {
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

    public int getProduto_id() {
        return produto_idProperty().get();
    }

    public IntegerProperty produto_idProperty() {
        if (produto_id == null) produto_id = new SimpleIntegerProperty(0);
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        produto_idProperty().set(produto_id);
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
