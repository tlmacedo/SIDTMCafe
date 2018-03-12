package br.com.sidtmcafe.model.model;

import br.com.sidtmcafe.model.vo.TabEmpresaDetalheReceitaFederalVO;
import br.com.sidtmcafe.model.vo.TabEmpresaVO;
import br.com.sidtmcafe.model.vo.TabProdutoEanVO;
import br.com.sidtmcafe.model.vo.TabProdutoVO;
import br.com.sidtmcafe.service.FormatarDado;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

import static br.com.sidtmcafe.interfaces.Constants.DECIMAL_FORMAT;

public class TabModel {

    static JFXTreeTableColumn<TabProdutoVO, Integer> colunaIdProduto;
    static JFXTreeTableColumn<TabProdutoVO, String> colunaCodigo;
    static JFXTreeTableColumn<TabProdutoVO, String> colunaDescricao;
    static JFXTreeTableColumn<TabProdutoVO, String> colunaUndCom;
    static JFXTreeTableColumn<TabProdutoVO, String> colunaPrecoFabrica;
    static JFXTreeTableColumn<TabProdutoVO, String> colunaPrecoConsumidor;
    static JFXTreeTableColumn<TabProdutoVO, Integer> colunaQtdEstoque;
    static JFXTreeTableColumn<TabProdutoVO, String> colunaSituacaoSistema;
    static JFXTreeTableColumn<TabProdutoVO, Integer> colunaVarejo;

    static JFXTreeTableColumn<TabEmpresaVO, Integer> colunaIdEmpresa;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaCnpj;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaIe;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaRazao;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaFantasia;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaEndereco;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaEndLogradouro;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaEndNumero;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaEndComplemento;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaEndBairro;
    static JFXTreeTableColumn<TabEmpresaVO, String> colunaEndUFMunicipio;
    static JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsCliente;
    static JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsFornecedor;
    static JFXTreeTableColumn<TabEmpresaVO, Boolean> colunaIsTransportadora;
    static JFXTreeTableColumn<TabEmpresaDetalheReceitaFederalVO, String> colunaQsaKey;
    static JFXTreeTableColumn<TabEmpresaDetalheReceitaFederalVO, String> colunaQsaValue;


    public static JFXTreeTableColumn<TabProdutoVO, Integer> getColunaIdProduto() {
        return colunaIdProduto;
    }

    public static JFXTreeTableColumn<TabProdutoVO, String> getColunaCodigo() {
        return colunaCodigo;
    }

    public static JFXTreeTableColumn<TabProdutoVO, String> getColunaDescricao() {
        return colunaDescricao;
    }

    public static JFXTreeTableColumn<TabProdutoVO, String> getColunaUndCom() {
        return colunaUndCom;
    }

    public static JFXTreeTableColumn<TabProdutoVO, String> getColunaPrecoFabrica() {
        return colunaPrecoFabrica;
    }

    public static JFXTreeTableColumn<TabProdutoVO, String> getColunaPrecoConsumidor() {
        return colunaPrecoConsumidor;
    }

    public static JFXTreeTableColumn<TabProdutoVO, Integer> getColunaQtdEstoque() {
        return colunaQtdEstoque;
    }

    public static JFXTreeTableColumn<TabProdutoVO, String> getColunaSituacaoSistema() {
        return colunaSituacaoSistema;
    }

    public static JFXTreeTableColumn<TabProdutoVO, Integer> getColunaVarejo() {
        return colunaVarejo;
    }


    public static JFXTreeTableColumn<TabEmpresaVO, Integer> getColunaIdEmpresa() {
        return colunaIdEmpresa;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaCnpj() {
        return colunaCnpj;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaIe() {
        return colunaIe;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaRazao() {
        return colunaRazao;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaFantasia() {
        return colunaFantasia;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaEndereco() {
        return colunaEndereco;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaEndLogradouro() {
        return colunaEndLogradouro;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaEndNumero() {
        return colunaEndNumero;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaEndComplemento() {
        return colunaEndComplemento;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaEndBairro() {
        return colunaEndBairro;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, String> getColunaEndUFMunicipio() {
        return colunaEndUFMunicipio;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, Boolean> getColunaIsCliente() {
        return colunaIsCliente;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, Boolean> getColunaIsFornecedor() {
        return colunaIsFornecedor;
    }

    public static JFXTreeTableColumn<TabEmpresaVO, Boolean> getColunaIsTransportadora() {
        return colunaIsTransportadora;
    }

    public static JFXTreeTableColumn<TabEmpresaDetalheReceitaFederalVO, String> getColunaQsaKey() {
        return colunaQsaKey;
    }

    public static JFXTreeTableColumn<TabEmpresaDetalheReceitaFederalVO, String> getColunaQsaValue() {
        return colunaQsaValue;
    }

    public static void tabelaProduto() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(28);
            colunaIdProduto = new JFXTreeTableColumn<TabProdutoVO, Integer>();
            colunaIdProduto.setGraphic(lblId);
            colunaIdProduto.setPrefWidth(28);
            colunaIdProduto.setStyle("-fx-alignment: center-right;");
            colunaIdProduto.setCellValueFactory(param -> param.getValue().getValue().idProperty().asObject());

            Label lblCodigo = new Label("Código");
            lblCodigo.setPrefWidth(60);
            colunaCodigo = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaCodigo.setGraphic(lblCodigo);
            colunaCodigo.setPrefWidth(60);
            colunaCodigo.setStyle("-fx-alignment: center-right;");
            colunaCodigo.setCellValueFactory(param -> param.getValue().getValue().codigoProperty());

            Label lblDescricao = new Label("Descrição");
            lblDescricao.setPrefWidth(350);
            colunaDescricao = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaDescricao.setGraphic(lblDescricao);
            colunaDescricao.setPrefWidth(350);
            colunaDescricao.setCellValueFactory(param -> param.getValue().getValue().descricaoProperty());

            Label lblUndComercial = new Label("Und Com");
            lblUndComercial.setPrefWidth(70);
            colunaUndCom = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaUndCom.setGraphic(lblUndComercial);
            colunaUndCom.setPrefWidth(70);
            colunaUndCom.setCellValueFactory(param -> param.getValue().getValue().getUnidadeComercialVO().siglaProperty());

            Label lblVarejo = new Label("Varejo");
            lblVarejo.setPrefWidth(50);
            colunaVarejo = new JFXTreeTableColumn<TabProdutoVO, Integer>();
            colunaVarejo.setGraphic(lblVarejo);
            colunaVarejo.setPrefWidth(50);
            colunaVarejo.setStyle("-fx-alignment: center-right;");
            colunaVarejo.setCellValueFactory(param -> param.getValue().getValue().varejoProperty().asObject());

            Label lblPrecoFab = new Label("Preço Fab.");
            lblPrecoFab.setPrefWidth(90);
            colunaPrecoFabrica = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaPrecoFabrica.setGraphic(lblPrecoFab);
            colunaPrecoFabrica.setPrefWidth(90);
            colunaPrecoFabrica.setStyle("-fx-alignment: center-right;");
            colunaPrecoFabrica.setCellValueFactory(param -> {
                try {
                    return new SimpleStringProperty(DECIMAL_FORMAT.format(param.getValue().getValue().precoFabricaProperty().getValue()).replace(".", ","));
                } catch (Exception ex) {
                    return new SimpleStringProperty("0");
                }
            });

            Label lblPrecoCons = new Label("Preço Cons.");
            lblPrecoCons.setPrefWidth(90);
            colunaPrecoConsumidor = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaPrecoConsumidor.setGraphic(lblPrecoCons);
            colunaPrecoConsumidor.setPrefWidth(90);
            colunaPrecoConsumidor.setStyle("-fx-alignment: center-right;");
            colunaPrecoConsumidor.setCellValueFactory(param -> {
                try {
                    return new SimpleStringProperty(DECIMAL_FORMAT.format(param.getValue().getValue().precoConsumidorProperty().getValue()).replace(".", ","));
                } catch (Exception ex) {
                    return new SimpleStringProperty("0");
                }
            });

            Label lblSituacaoSistema = new Label("Situação");
            lblSituacaoSistema.setPrefWidth(100);
            colunaSituacaoSistema = new JFXTreeTableColumn<TabProdutoVO, String>();
            colunaSituacaoSistema.setGraphic(lblSituacaoSistema);
            colunaSituacaoSistema.setPrefWidth(100);
            colunaSituacaoSistema.setCellValueFactory(param -> param.getValue().getValue().getSituacaoSistemaVO().descricaoProperty());

            Label lblEstoque = new Label("Estoque");
            lblEstoque.setPrefWidth(65);
            colunaQtdEstoque = new JFXTreeTableColumn<TabProdutoVO, Integer>();
            colunaQtdEstoque.setGraphic(lblEstoque);
            colunaQtdEstoque.setPrefWidth(65);
            colunaQtdEstoque.setStyle("-fx-alignment: center-right;");
            colunaQtdEstoque.setCellValueFactory(param -> param.getValue().getValue().estoqueProperty().asObject());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void tabelaEmpresa() {
        try {
            Label lblId = new Label("id");
            lblId.setPrefWidth(28);
            colunaIdEmpresa = new JFXTreeTableColumn<TabEmpresaVO, Integer>();
            colunaIdEmpresa.setGraphic(lblId);
            colunaIdEmpresa.setPrefWidth(28);
            colunaIdEmpresa.setStyle("-fx-alignment: center-right;");
            colunaIdEmpresa.setCellValueFactory(param -> param.getValue().getValue().idProperty().asObject());

            Label lblCnpj = new Label("C.N.P.J / C.P.F.");
            lblCnpj.setPrefWidth(110);
            colunaCnpj = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaCnpj.setGraphic(lblCnpj);
            colunaCnpj.setPrefWidth(110);
            colunaCnpj.setStyle("-fx-alignment: center-right;");
            colunaCnpj.setCellValueFactory(param -> {
                if (param.getValue().getValue().isPessoaJuridicaProperty().get() == 0)
                    return new SimpleStringProperty(FormatarDado.getCampoFormatado(param.getValue().getValue().cnpjProperty().getValue(), "cpf"));
                return new SimpleStringProperty(FormatarDado.getCampoFormatado(param.getValue().getValue().cnpjProperty().getValue(), "cnpj"));
            });

            Label lblIe = new Label(("IE / RG"));
            lblIe.setPrefWidth(90);
            colunaIe = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaIe.setGraphic(lblIe);
            colunaIe.setPrefWidth(90);
            colunaIe.setStyle("-fx-alignment: center-right;");
            colunaIe.setCellValueFactory(param -> {
                try {
                    if (param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty().get() != "")
                        return new SimpleStringProperty(FormatarDado.getCampoFormatado(param.getValue().getValue().ieProperty().getValue(), "ie" + param.getValue().getValue().getEnderecoVOList().get(0).getUfVO().getSigla()));
                    return param.getValue().getValue().ieProperty();
                } catch (Exception ex) {
                    return param.getValue().getValue().ieProperty();
                }
            });

            Label lblRazao = new Label("Razão / Nome");
            lblRazao.setPrefWidth(250);
            colunaRazao = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaRazao.setGraphic(lblRazao);
            colunaRazao.setPrefWidth(250);
            colunaRazao.setCellValueFactory(param -> param.getValue().getValue().razaoProperty());

            Label lblFantasia = new Label("Fantasia / Apelido");
            lblFantasia.setPrefWidth(150);
            colunaFantasia = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaFantasia.setGraphic(lblFantasia);
            colunaFantasia.setPrefWidth(150);
            colunaFantasia.setCellValueFactory(param -> param.getValue().getValue().fantasiaProperty());

            colunaEndereco = new JFXTreeTableColumn<TabEmpresaVO, String>("Endereço");
            colunaEndereco.setStyle("-fx-alignment: center;");

            Label lblEndLogradouro = new Label("Logradouro");
            lblEndLogradouro.setPrefWidth(170);
            colunaEndLogradouro = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndLogradouro.setGraphic(lblEndLogradouro);
            colunaEndLogradouro.setPrefWidth(170);
            colunaEndLogradouro.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).logradouroProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndNumero = new Label("Número");
            lblEndNumero.setPrefWidth(40);
            colunaEndNumero = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndNumero.setGraphic(lblEndNumero);
            colunaEndNumero.setPrefWidth(40);
            colunaEndNumero.setStyle("-fx-alignment: center-right;");
            colunaEndNumero.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).numeroProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).numeroProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndComplemento = new Label("Complemento");
            lblEndComplemento.setPrefWidth(150);
            colunaEndComplemento = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndComplemento.setGraphic(lblEndComplemento);
            colunaEndComplemento.setPrefWidth(150);
            colunaEndComplemento.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).complementoProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).complementoProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndBairro = new Label("Bairro");
            lblEndBairro.setPrefWidth(95);
            colunaEndBairro = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndBairro.setGraphic(lblEndBairro);
            colunaEndBairro.setPrefWidth(95);
            colunaEndBairro.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0).bairroProperty() != null)
                        return param.getValue().getValue().getEnderecoVOList().get(0).bairroProperty();
                return new SimpleStringProperty("");
            });

            Label lblEndUFMunicipio = new Label("UF - Cidade");
            lblEndUFMunicipio.setPrefWidth(75);
            colunaEndUFMunicipio = new JFXTreeTableColumn<TabEmpresaVO, String>();
            colunaEndUFMunicipio.setGraphic(lblEndUFMunicipio);
            colunaEndUFMunicipio.setPrefWidth(75);
            colunaEndUFMunicipio.setCellValueFactory(param -> {
                if (param.getValue().getValue().getEnderecoVOList().size() > 0)
                    if (param.getValue().getValue().getEnderecoVOList().get(0) != null)
                        return new SimpleStringProperty(
                                param.getValue().getValue().getEnderecoVOList().get(0).getUfVO().siglaProperty() + " - " +
                                        param.getValue().getValue().getEnderecoVOList().get(0).getMunicipioVO().descricaoProperty());
                return new SimpleStringProperty("");
            });

            colunaEndereco.getColumns().addAll(colunaEndLogradouro, colunaEndNumero,
                    colunaEndComplemento, colunaEndBairro);

            VBox vBoxIsCliente = new VBox();
            Label lblImgIsCliente = new Label();
            lblImgIsCliente.getStyleClass().add("lbl_ico_cliente");
            lblImgIsCliente.setPrefSize(24, 24);
            Label lblIsCliente = new Label("Cliente");
            vBoxIsCliente.setAlignment(Pos.CENTER);
            vBoxIsCliente.getChildren().addAll(lblImgIsCliente, lblIsCliente);
            colunaIsCliente = new JFXTreeTableColumn<TabEmpresaVO, Boolean>();
            colunaIsCliente.setPrefWidth(55);
            colunaIsCliente.setGraphic(vBoxIsCliente);
            colunaIsCliente.setCellValueFactory(param -> {
                if (param.getValue().getValue().getIsCliente() == 0) return new SimpleBooleanProperty(false);
                else return new SimpleBooleanProperty(true);
            });

            VBox vBoxIsFornecedor = new VBox();
            Label lblImgIsFornecedor = new Label();
            lblImgIsFornecedor.getStyleClass().add("lbl_ico_fornecedor");
            lblImgIsFornecedor.setPrefSize(24, 24);
            Label lblIsFornecedor = new Label("Forn.");
            vBoxIsFornecedor.setAlignment(Pos.CENTER);
            vBoxIsFornecedor.getChildren().addAll(lblImgIsFornecedor, lblIsFornecedor);
            colunaIsFornecedor = new JFXTreeTableColumn<TabEmpresaVO, Boolean>();
            colunaIsFornecedor.setPrefWidth(55);
            colunaIsFornecedor.setGraphic(vBoxIsFornecedor);
            colunaIsFornecedor.setCellValueFactory(param -> {
                if (param.getValue().getValue().getIsFornecedor() == 0) return new SimpleBooleanProperty(false);
                else return new SimpleBooleanProperty(true);
            });

            VBox vBoxIsTransportadora = new VBox();
            Label lblImgIsTransportadora = new Label();
            lblImgIsTransportadora.getStyleClass().add("lbl_ico_transportadora");
            lblImgIsTransportadora.setPrefSize(24, 24);
            Label lblIsTransportadora = new Label("Transp.");
            vBoxIsTransportadora.setAlignment(Pos.CENTER);
            vBoxIsTransportadora.getChildren().addAll(lblImgIsTransportadora, lblIsTransportadora);
            colunaIsTransportadora = new JFXTreeTableColumn<TabEmpresaVO, Boolean>();
            colunaIsTransportadora.setPrefWidth(55);
            colunaIsTransportadora.setGraphic(vBoxIsTransportadora);
            colunaIsTransportadora.setCellValueFactory(param -> {
                if (param.getValue().getValue().getIsTransportadora() == 0) return new SimpleBooleanProperty(false);
                else return new SimpleBooleanProperty(true);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void tabelaQsaReceita() {
        try {
            Label lblQsaKey = new Label("Item");
            lblQsaKey.setPrefWidth(100);
            colunaQsaKey = new JFXTreeTableColumn<TabEmpresaDetalheReceitaFederalVO, String>();
            colunaQsaKey.setGraphic(lblQsaKey);
            colunaQsaKey.setPrefWidth(100);
            colunaQsaKey.setCellValueFactory(param -> {
                return param.getValue().getValue().str_keyProperty();
            });

            Label lblQsaValue = new Label("Detalhe");
            lblQsaValue.setPrefWidth(250);
            colunaQsaValue = new JFXTreeTableColumn<TabEmpresaDetalheReceitaFederalVO, String>();
            colunaQsaValue.setGraphic(lblQsaValue);
            colunaQsaValue.setPrefWidth(250);
            colunaQsaValue.setCellValueFactory(param -> {
                return param.getValue().getValue().str_valueProperty();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
