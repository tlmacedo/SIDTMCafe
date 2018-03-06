package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;

public class TabProdutoEstoqueVO extends RecursiveTreeObject<TabProdutoEstoqueVO> {

    Timestamp validade;
    IntegerProperty id, produto_id, qtd;
    StringProperty lote, documentoOrigem;

    public TabProdutoEstoqueVO() {
    }

    public Timestamp getValidade() {
        return validade;
    }

    public void setValidade(Timestamp validade) {
        this.validade = validade;
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

    public int getQtd() {
        return qtdProperty().get();
    }

    public IntegerProperty qtdProperty() {
        if (qtd == null) qtd = new SimpleIntegerProperty(0);
        return qtd;
    }

    public void setQtd(int qtd) {
        qtdProperty().set(qtd);
    }

    public String getLote() {
        return loteProperty().get();
    }

    public StringProperty loteProperty() {
        if (lote == null) lote = new SimpleStringProperty("");
        return lote;
    }

    public void setLote(String lote) {
        loteProperty().set(lote);
    }

    public String getDocumentoOrigem() {
        return documentoOrigemProperty().get();
    }

    public StringProperty documentoOrigemProperty() {
        if (documentoOrigem == null) documentoOrigem = new SimpleStringProperty("");
        return documentoOrigem;
    }

    public void setDocumentoOrigem(String documentoOrigem) {
        documentoOrigemProperty().set(documentoOrigem);
    }
}
