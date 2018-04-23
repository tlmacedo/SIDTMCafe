package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class TabProdutoVO extends RecursiveTreeObject<TabProdutoVO> {

    SisUnidadeComercialVO sisUnidadeComercialVO;
    SisSituacaoSistemaVO sisSituacaoSistemaVO;
    FiscalICMSVO fiscalICMSVO;
    FiscalPISCOFINSVO fiscalPISVO;
    FiscalPISCOFINSVO fiscalCOFINSVO;
    TabColaboradorVO usuarioCadastroVO;
    TabColaboradorVO usuarioAtualizacaoVO;

    Timestamp dataCadastro, dataAtualizacao;
    IntegerProperty id, sisUnidadeComercial_id, varejo, fiscalICMS_id, fiscalPIS_id, fiscalCOFINS_id, nfeOrigem, usuarioCadastro_id, usuarioAtualizacao_id;
    StringProperty codigo, descricao, nfeNcm, nfeCest, nfeGenero;
    DecimalFormat peso, precoFabrica, precoVenda, comissao;

    public TabProdutoVO() {
    }

    public SisUnidadeComercialVO getSisUnidadeComercialVO() {
        return sisUnidadeComercialVO;
    }

    public void setSisUnidadeComercialVO(SisUnidadeComercialVO sisUnidadeComercialVO) {
        this.sisUnidadeComercialVO = sisUnidadeComercialVO;
    }

    public SisSituacaoSistemaVO getSisSituacaoSistemaVO() {
        return sisSituacaoSistemaVO;
    }

    public void setSisSituacaoSistemaVO(SisSituacaoSistemaVO sisSituacaoSistemaVO) {
        this.sisSituacaoSistemaVO = sisSituacaoSistemaVO;
    }

    public FiscalICMSVO getFiscalICMSVO() {
        return fiscalICMSVO;
    }

    public void setFiscalICMSVO(FiscalICMSVO fiscalICMSVO) {
        this.fiscalICMSVO = fiscalICMSVO;
    }

    public FiscalPISCOFINSVO getFiscalPISVO() {
        return fiscalPISVO;
    }

    public void setFiscalPISVO(FiscalPISCOFINSVO fiscalPISVO) {
        this.fiscalPISVO = fiscalPISVO;
    }

    public FiscalPISCOFINSVO getFiscalCOFINSVO() {
        return fiscalCOFINSVO;
    }

    public void setFiscalCOFINSVO(FiscalPISCOFINSVO fiscalCOFINSVO) {
        this.fiscalCOFINSVO = fiscalCOFINSVO;
    }

    public TabColaboradorVO getUsuarioCadastroVO() {
        return usuarioCadastroVO;
    }

    public void setUsuarioCadastroVO(TabColaboradorVO usuarioCadastroVO) {
        this.usuarioCadastroVO = usuarioCadastroVO;
    }

    public TabColaboradorVO getUsuarioAtualizacaoVO() {
        return usuarioAtualizacaoVO;
    }

    public void setUsuarioAtualizacaoVO(TabColaboradorVO usuarioAtualizacaoVO) {
        this.usuarioAtualizacaoVO = usuarioAtualizacaoVO;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Timestamp getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Timestamp dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
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

    public int getSisUnidadeComercial_id() {
        return sisUnidadeComercial_idProperty().get();
    }

    public IntegerProperty sisUnidadeComercial_idProperty() {
        if (sisUnidadeComercial_id == null) sisUnidadeComercial_id = new SimpleIntegerProperty(0);
        return sisUnidadeComercial_id;
    }

    public void setSisUnidadeComercial_id(int sisUnidadeComercial_id) {
        sisUnidadeComercial_idProperty().set(sisUnidadeComercial_id);
    }

    public int getVarejo() {
        return varejoProperty().get();
    }

    public IntegerProperty varejoProperty() {
        if (varejo == null) varejo = new SimpleIntegerProperty(0);
        return varejo;
    }

    public void setVarejo(int varejo) {
        varejoProperty().set(varejo);
    }

    public int getFiscalICMS_id() {
        return fiscalICMS_idProperty().get();
    }

    public IntegerProperty fiscalICMS_idProperty() {
        if (fiscalICMS_id == null) fiscalICMS_id = new SimpleIntegerProperty(0);
        return fiscalICMS_id;
    }

    public void setFiscalICMS_id(int fiscalICMS_id) {
        fiscalICMS_idProperty().set(fiscalICMS_id);
    }

    public int getFiscalPIS_id() {
        return fiscalPIS_idProperty().get();
    }

    public IntegerProperty fiscalPIS_idProperty() {
        if (fiscalPIS_id == null) fiscalPIS_id = new SimpleIntegerProperty(0);
        return fiscalPIS_id;
    }

    public void setFiscalPIS_id(int fiscalPIS_id) {
        fiscalPIS_idProperty().set(fiscalPIS_id);
    }

    public int getFiscalCOFINS_id() {
        return fiscalCOFINS_idProperty().get();
    }

    public IntegerProperty fiscalCOFINS_idProperty() {
        if (fiscalCOFINS_id == null) fiscalCOFINS_id = new SimpleIntegerProperty(0);
        return fiscalCOFINS_id;
    }

    public void setFiscalCOFINS_id(int fiscalCOFINS_id) {
        fiscalCOFINS_idProperty().set(fiscalCOFINS_id);
    }

    public int getNfeOrigem() {
        return nfeOrigemProperty().get();
    }

    public IntegerProperty nfeOrigemProperty() {
        if (nfeOrigem == null) nfeOrigem = new SimpleIntegerProperty(0);
        return nfeOrigem;
    }

    public void setNfeOrigem(int nfeOrigem) {
        nfeOrigemProperty().set(nfeOrigem);
    }

    public int getUsuarioCadastro_id() {
        return usuarioCadastro_idProperty().get();
    }

    public IntegerProperty usuarioCadastro_idProperty() {
        if (usuarioCadastro_id == null) usuarioCadastro_id = new SimpleIntegerProperty(0);
        return usuarioCadastro_id;
    }

    public void setUsuarioCadastro_id(int usuarioCadastro_id) {
        usuarioCadastro_idProperty().set(usuarioCadastro_id);
    }

    public int getUsuarioAtualizacao_id() {
        return usuarioAtualizacao_idProperty().get();
    }

    public IntegerProperty usuarioAtualizacao_idProperty() {
        if (usuarioAtualizacao_id == null) usuarioAtualizacao_id = new SimpleIntegerProperty(0);
        return usuarioAtualizacao_id;
    }

    public void setUsuarioAtualizacao_id(int usuarioAtualizacao_id) {
        usuarioAtualizacao_idProperty().set(usuarioAtualizacao_id);
    }

    public String getCodigo() {
        return codigoProperty().get();
    }

    public StringProperty codigoProperty() {
        if (codigo == null) codigo = new SimpleStringProperty("");
        return codigo;
    }

    public void setCodigo(String codigo) {
        codigoProperty().set(codigo);
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

    public String getNfeNcm() {
        return nfeNcmProperty().get();
    }

    public StringProperty nfeNcmProperty() {
        if (nfeNcm == null) nfeNcm = new SimpleStringProperty("");
        return nfeNcm;
    }

    public void setNfeNcm(String nfeNcm) {
        nfeNcmProperty().set(nfeNcm);
    }

    public String getNfeCest() {
        return nfeCestProperty().get();
    }

    public StringProperty nfeCestProperty() {
        if (nfeCest == null) nfeCest = new SimpleStringProperty("");
        return nfeCest;
    }

    public void setNfeCest(String nfeCest) {
        nfeCestProperty().set(nfeCest);
    }

    public String getNfeGenero() {
        return nfeGeneroProperty().get();
    }

    public StringProperty nfeGeneroProperty() {
        if (nfeGenero == null) nfeGenero = new SimpleStringProperty("");
        return nfeGenero;
    }

    public void setNfeGenero(String nfeGenero) {
        nfeGeneroProperty().set(nfeGenero);
    }

    public DecimalFormat getPeso() {
        if (peso == null) peso = new DecimalFormat("0.00");
        return peso;
    }

    public void setPeso(DecimalFormat peso) {
        peso = peso;
    }

    public DecimalFormat getPrecoFabrica() {
        if (precoFabrica == null) precoFabrica = new DecimalFormat("0.00");
        return precoFabrica;
    }

    public void setPrecoFabrica(DecimalFormat precoFabrica) {
        precoFabrica = precoFabrica;
    }

    public DecimalFormat getPrecoVenda() {
        if (precoVenda == null) precoVenda = new DecimalFormat("0.00");
        return precoVenda;
    }

    public void setPrecoVenda(DecimalFormat precoVenda) {
        precoVenda = precoVenda;
    }

    public DecimalFormat getComissao() {
        if (comissao == null) comissao = new DecimalFormat("0.00");
        return comissao;
    }

    public void setComissao(DecimalFormat comissao) {
        comissao = comissao;
    }
}
