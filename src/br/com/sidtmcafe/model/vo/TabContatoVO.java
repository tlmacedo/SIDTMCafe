package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class TabContatoVO extends RecursiveTreeObject<TabContatoVO> {

    TabCargoVO cargoVO;
    List<TabTelefoneVO> telefoneVOList;
    List<TabEmailHomePageVO> emailHomePageVOList;

    IntegerProperty id, cargo_id;
    StringProperty descricao, telefone_ids, emailHomePage_ids;

    public TabContatoVO() {
    }

    public TabCargoVO getCargoVO() {
        return cargoVO;
    }

    public void setCargoVO(TabCargoVO cargoVO) {
        this.cargoVO = cargoVO;
    }

    public List<TabTelefoneVO> getTelefoneVOList() {
        return telefoneVOList;
    }

    public void setTelefoneVOList(List<TabTelefoneVO> telefoneVOList) {
        this.telefoneVOList = telefoneVOList;
    }

    public List<TabEmailHomePageVO> getEmailHomePageVOList() {
        return emailHomePageVOList;
    }

    public void setEmailHomePageVOList(List<TabEmailHomePageVO> emailHomePageVOList) {
        this.emailHomePageVOList = emailHomePageVOList;
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

    public int getCargo_id() {
        return cargo_idProperty().get();
    }

    public IntegerProperty cargo_idProperty() {
        if (cargo_id == null) cargo_id = new SimpleIntegerProperty(0);
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        cargo_idProperty().set(cargo_id);
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
        if (descricaoProperty().get().equals(""))
            return "";
        return descricaoProperty().get() + " [" + cargoVO.descricaoProperty().get() + "]";
    }
}
