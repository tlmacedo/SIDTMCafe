package br.com.sidtmcafe.model.vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

import java.sql.Timestamp;
import java.util.List;

public class TabProdutoVO extends RecursiveTreeObject<TabProdutoVO> {

    SisUnidadeComercialVO unidadeComercialVO;
    SisSituacaoSistemaVO situacaoSistemaVO;
    SisFiscalCstIcmsVO fiscalCstIcmsVO;
    SisFiscalCstPisCofinsVO fiscalCstPisVO;
    SisFiscalCstPisCofinsVO fiscalCstCofinsVO;
    SisFiscalCstOrigemVO fiscalCstOrigemVO;
    TabColaboradorVO usuarioCadastroVO;
    TabColaboradorVO usuarioAtualizacaoVO;
    List<TabProdutoEstoqueVO> produtoEstoqueVOList;
    List<TabProdutoEanVO> produtoEanVOList;

    Timestamp dataCadastro, dataAtualizacao;
    IntegerProperty id, unidadeComercial_id, situacaoSistema_id, varejo, fiscalCstIcms_id, fiscalCstPis_id, fiscalCstCofins_id,
            fiscalOrigem_id, usuarioCadastro_id, usuarioAtualizacao_id, estoque;
    StringProperty codigo, descricao, fiscalNcm, fiscalCest, fiscalGenero;
    DoubleProperty peso, precoFabrica, precoConsumidor, comissao;

    public TabProdutoVO() {
    }

    public SisUnidadeComercialVO getUnidadeComercialVO() {
        return unidadeComercialVO;
    }

    public void setUnidadeComercialVO(SisUnidadeComercialVO unidadeComercialVO) {
        this.unidadeComercialVO = unidadeComercialVO;
    }

    public SisSituacaoSistemaVO getSituacaoSistemaVO() {
        return situacaoSistemaVO;
    }

    public void setSituacaoSistemaVO(SisSituacaoSistemaVO situacaoSistemaVO) {
        this.situacaoSistemaVO = situacaoSistemaVO;
    }

    public SisFiscalCstIcmsVO getFiscalCstIcmsVO() {
        return fiscalCstIcmsVO;
    }

    public void setFiscalCstIcmsVO(SisFiscalCstIcmsVO fiscalCstIcmsVO) {
        this.fiscalCstIcmsVO = fiscalCstIcmsVO;
    }

    public SisFiscalCstPisCofinsVO getFiscalCstPisVO() {
        return fiscalCstPisVO;
    }

    public void setFiscalCstPisVO(SisFiscalCstPisCofinsVO fiscalCstPisVO) {
        this.fiscalCstPisVO = fiscalCstPisVO;
    }

    public SisFiscalCstPisCofinsVO getFiscalCstCofinsVO() {
        return fiscalCstCofinsVO;
    }

    public void setFiscalCstCofinsVO(SisFiscalCstPisCofinsVO fiscalCstCofinsVO) {
        this.fiscalCstCofinsVO = fiscalCstCofinsVO;
    }

    public SisFiscalCstOrigemVO getFiscalCstOrigemVO() {
        return fiscalCstOrigemVO;
    }

    public void setFiscalCstOrigemVO(SisFiscalCstOrigemVO fiscalCstOrigemVO) {
        this.fiscalCstOrigemVO = fiscalCstOrigemVO;
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

    public List<TabProdutoEstoqueVO> getProdutoEstoqueVOList() {
        return produtoEstoqueVOList;
    }

    public void setProdutoEstoqueVOList(List<TabProdutoEstoqueVO> produtoEstoqueVOList) {
        this.produtoEstoqueVOList = produtoEstoqueVOList;
    }

    public List<TabProdutoEanVO> getProdutoEanVOList() {
        return produtoEanVOList;
    }

    public void setProdutoEanVOList(List<TabProdutoEanVO> produtoEanVOList) {
        this.produtoEanVOList = produtoEanVOList;
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

    public int getUnidadeComercial_id() {
        return unidadeComercial_idProperty().get();
    }

    public IntegerProperty unidadeComercial_idProperty() {
        if (unidadeComercial_id == null) unidadeComercial_id = new SimpleIntegerProperty(0);
        return unidadeComercial_id;
    }

    public void setUnidadeComercial_id(int unidadeComercial_id) {
        unidadeComercial_idProperty().set(unidadeComercial_id);
    }

    public int getSituacaoSistema_id() {
        return situacaoSistema_idProperty().get();
    }

    public IntegerProperty situacaoSistema_idProperty() {
        if (situacaoSistema_id == null) situacaoSistema_id = new SimpleIntegerProperty(0);
        return situacaoSistema_id;
    }

    public void setSituacaoSistema_id(int situacaoSistema_id) {
        situacaoSistema_idProperty().set(situacaoSistema_id);
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

    public int getFiscalCstIcms_id() {
        return fiscalCstIcms_idProperty().get();
    }

    public IntegerProperty fiscalCstIcms_idProperty() {
        if (fiscalCstIcms_id == null) fiscalCstIcms_id = new SimpleIntegerProperty(0);
        return fiscalCstIcms_id;
    }

    public void setFiscalCstIcms_id(int fiscalCstIcms_id) {
        fiscalCstIcms_idProperty().set(fiscalCstIcms_id);
    }

    public int getFiscalCstPis_id() {
        return fiscalCstPis_idProperty().get();
    }

    public IntegerProperty fiscalCstPis_idProperty() {
        if (fiscalCstPis_id == null) fiscalCstPis_id = new SimpleIntegerProperty(0);
        return fiscalCstPis_id;
    }

    public void setFiscalCstPis_id(int fiscalCstPis_id) {
        fiscalCstPis_idProperty().set(fiscalCstPis_id);
    }

    public int getFiscalCstCofins_id() {
        return fiscalCstCofins_idProperty().get();
    }

    public IntegerProperty fiscalCstCofins_idProperty() {
        if (fiscalCstCofins_id == null) fiscalCstCofins_id = new SimpleIntegerProperty(0);
        return fiscalCstCofins_id;
    }

    public void setFiscalCstCofins_id(int fiscalCstCofins_id) {
        fiscalCstCofins_idProperty().set(fiscalCstCofins_id);
    }

    public int getFiscalOrigem_id() {
        return fiscalOrigem_idProperty().get();
    }

    public IntegerProperty fiscalOrigem_idProperty() {
        if (fiscalOrigem_id == null) fiscalOrigem_id = new SimpleIntegerProperty(0);
        return fiscalOrigem_id;
    }

    public void setFiscalOrigem_id(int fiscalOrigem_id) {
        fiscalOrigem_idProperty().set(fiscalOrigem_id);
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

    public int getEstoque() {
        return estoqueProperty().get();
    }

    public IntegerProperty estoqueProperty() {
        if (estoque == null) estoque = new SimpleIntegerProperty(0);
        return estoque;
    }

    public void setEstoque(int estoque) {
        estoqueProperty().set(estoque);
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

    public String getFiscalNcm() {
        return fiscalNcmProperty().get();
    }

    public StringProperty fiscalNcmProperty() {
        if (fiscalNcm == null) fiscalNcm = new SimpleStringProperty("");
        return fiscalNcm;
    }

    public void setFiscalNcm(String fiscalNcm) {
        fiscalNcmProperty().set(fiscalNcm);
    }

    public String getFiscalCest() {
        return fiscalCestProperty().get();
    }

    public StringProperty fiscalCestProperty() {
        if (fiscalCest == null) fiscalCest = new SimpleStringProperty("");
        return fiscalCest;
    }

    public void setFiscalCest(String fiscalCest) {
        fiscalCestProperty().set(fiscalCest);
    }

    public String getFiscalGenero() {
        return fiscalGeneroProperty().get();
    }

    public StringProperty fiscalGeneroProperty() {
        if (fiscalGenero == null) fiscalGenero = new SimpleStringProperty("");
        return fiscalGenero;
    }

    public void setFiscalGenero(String fiscalGenero) {
        fiscalGeneroProperty().set(fiscalGenero);
    }

    public double getPeso() {
        return pesoProperty().get();
    }

    public DoubleProperty pesoProperty() {
        if (peso == null) peso = new SimpleDoubleProperty(0);
        return peso;
    }

    public void setPeso(double peso) {
        pesoProperty().set(peso);
    }

    public double getPrecoFabrica() {
        return precoFabricaProperty().get();
    }

    public DoubleProperty precoFabricaProperty() {
        if (precoFabrica == null) precoFabrica = new SimpleDoubleProperty(0);
        return precoFabrica;
    }

    public void setPrecoFabrica(double precoFabrica) {
        precoFabricaProperty().set(precoFabrica);
    }

    public double getPrecoConsumidor() {
        return precoConsumidorProperty().get();
    }

    public DoubleProperty precoConsumidorProperty() {
        if (precoConsumidor == null) precoConsumidor = new SimpleDoubleProperty(0);
        return precoConsumidor;
    }

    public void setPrecoConsumidor(double precoConsumidor) {
        precoConsumidorProperty().set(precoConsumidor);
    }

    public double getComissao() {
        return comissaoProperty().get();
    }

    public DoubleProperty comissaoProperty() {
        if (comissao == null) comissao = new SimpleDoubleProperty(0);
        return comissao;
    }

    public void setComissao(double comissao) {
        comissaoProperty().set(comissao);
    }

    @Override
    public String toString() {
        return descricaoProperty().get();
    }
}
