package br.com.sidtmcafe.model.vo;

import br.com.sidtmcafe.model.dao.SisTipoEnderecoDAO;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TabEnderecoVO extends RecursiveTreeObject<TabEnderecoVO> {

    SisTipoEnderecoVO tipoEnderecoVO;
    SisMunicipioVO municipioVO;

    IntegerProperty id, sisTipoEndereco_id, sisMunicipio_id;
    StringProperty cep, logradouro, numero, complemento, bairro, pontoReferencia;

    public TabEnderecoVO() {
    }

    public TabEnderecoVO(int tipoEndereco_id, int uf_id, int municipio_id) {
        this.sisTipoEndereco_id = new SimpleIntegerProperty(tipoEndereco_id);
        this.sisMunicipio_id = new SimpleIntegerProperty(municipio_id);
    }

    public SisTipoEnderecoVO getTipoEnderecoVO() {
        return tipoEnderecoVO;
    }

    public void setTipoEnderecoVO(SisTipoEnderecoVO tipoEnderecoVO) {
        this.tipoEnderecoVO = tipoEnderecoVO;
    }

    public SisMunicipioVO getMunicipioVO() {
        return municipioVO;
    }

    public void setMunicipioVO(SisMunicipioVO municipioVO) {
        this.municipioVO = municipioVO;
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

    public int getSisTipoEndereco_id() {
        return sisTipoEndereco_idProperty().get();
    }

    public IntegerProperty sisTipoEndereco_idProperty() {
        if (sisTipoEndereco_id == null) sisTipoEndereco_id = new SimpleIntegerProperty(0);
        return sisTipoEndereco_id;
    }

    public void setSisTipoEndereco_id(int sisTipoEndereco_id) {
        sisTipoEndereco_idProperty().set(sisTipoEndereco_id);
    }

    public int getSisMunicipio_id() {
        return sisMunicipio_idProperty().get();
    }

    public IntegerProperty sisMunicipio_idProperty() {
        if (sisMunicipio_id == null) sisMunicipio_id = new SimpleIntegerProperty(0);
        return sisMunicipio_id;
    }

    public void setSisMunicipio_id(int sisMunicipio_id) {
        sisMunicipio_idProperty().set(sisMunicipio_id);
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

    public String getPontoReferencia() {
        return pontoReferenciaProperty().get();
    }

    public StringProperty pontoReferenciaProperty() {
        if (pontoReferencia == null) pontoReferencia = new SimpleStringProperty("");
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        pontoReferenciaProperty().set(pontoReferencia);
    }

    @Override
    public String toString() {
        try {
            return getTipoEnderecoVO().descricaoProperty().get();
        } catch (Exception ex) {
            return new SisTipoEnderecoDAO().getTipoEnderecoVO(1).descricaoProperty().get();
        }
    }
}
