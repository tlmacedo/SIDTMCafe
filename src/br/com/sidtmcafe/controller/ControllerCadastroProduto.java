package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.Tarefa;
import br.com.sidtmcafe.componentes.Variavel;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.service.ExecutaComandoTecladoMouse;
import br.com.sidtmcafe.service.PersonalizarCampo;
import br.com.sidtmcafe.view.ViewCadastroProduto;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCadastroProduto extends Variavel implements Initializable, FormularioModelo {
    public AnchorPane painelViewCadastroProduto;
    public TitledPane tpnCadastroProduto;
    public JFXTextField txtPesquisa;
    public JFXTreeTableView ttvProduto;
    public JFXComboBox cboFiltroPesquisa;
    public Label lblRegistrosLocalizados;
    public TitledPane tpnDadosCadastrais;

    @Override
    public void fechar() {

    }

    @Override
    public void preencherObjetos() {
        listaTarefas = new ArrayList<>();
//        criarTabelas();
//        carregaListas();
//        preencherCombos();
//        preencherTabelas();

        new Tarefa().tarefaAbreCadastroProduto(this, listaTarefas);

        PersonalizarCampo.fieldMaxLen(painelViewCadastroProduto);
        PersonalizarCampo.maskFields(painelViewCadastroProduto);

    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {
        String tituloTab = ViewCadastroProduto.getTituloJanela();

        ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            if (!(ControllerPrincipal.ctrlPrincipal.getTabAtual().equals(tituloTab)))
                return;
            if ((n != null) & (n != o))
                setStatusBarFormulario(getStatusFormulario());
        });


        eventCadastroProduto = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case F1:
//                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                            setStatusFormulario("Incluir");
//                            setTtvEmpresaVO(new TabEmpresaVO());
                        break;
                    case F2:
//                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                            guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
//                            if (!validarDadosCadastrais()) break;
//
//                            salvarEmpresa();
//                            setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
//                            empresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());
//
//                            setStatusFormulario("Pesquisa");
//                            carregarPesquisaEmpresas(txtPesquisa.getText());
                        break;
                    case F3:
//                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                            switch (getStatusFormulario().toLowerCase()) {
//                                case "incluir":
//                                    if (new AlertMensagem("Cancelar inclusão", USUARIO_LOGADO_APELIDO
//                                            + ", deseja cancelar inclusão no cadastro de empresa?",
//                                            "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
//                                        return;
//                                    PersonalizarCampo.clearField((AnchorPane) tpnDadosCadastrais.getContent());
//                                    break;
//                                case "editar":
//                                    if (new AlertMensagem("Cancelar edição", USUARIO_LOGADO_APELIDO
//                                            + ", deseja cancelar edição do cadastro de empresa?",
//                                            "ic_cadastro_empresas_white_24dp.png").getRetornoAlert_YES_NO().get() == ButtonType.NO)
//                                        return;
//                                    setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
//                                    empresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());
//                                    break;
//                            }
//                            setStatusFormulario("Pesquisa");
//                            carregarPesquisaEmpresas(txtPesquisa.getText());
//                            exibirDadosEmpresa();
                        break;
                    case F4:
//                            if ((!getStatusBarFormulario().contains(event.getCode().toString())) || (getTtvEmpresaVO() == null))
//                                break;
//                            indexObservableEmpresa = empresaVOObservableList.indexOf(getTtvEmpresaVO());
//                            setStatusFormulario("Editar");
                        break;
                    case F5:
//                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                            guardarEndereco(listEndereco.getSelectionModel().getSelectedIndex());
//                            if (!validarDadosCadastrais()) break;
//
//                            salvarEmpresa();
//                            setTtvEmpresaVO(new TabEmpresaDAO().getEmpresaVO(getTtvEmpresaVO().getId()));
//                            empresaVOObservableList.set(indexObservableEmpresa, getTtvEmpresaVO());
//
//                            setStatusFormulario("Pesquisa");
//                            carregarPesquisaEmpresas(txtPesquisa.getText());
//                            exibirDadosEmpresa();
//                            ttvEmpresa.requestFocus();
                        break;
                    case F6:
//                            if (getStatusFormulario().toLowerCase().equals("pesquisa") || (!event.isShiftDown())) break;
//                            keyShiftF6();
                        break;
                    case F7:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        txtPesquisa.requestFocus();
                        break;
                    case F8:
//                            if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
//                            cboFiltroPesquisa.requestFocus();
                        break;
                    case F12:
                        if (!getStatusBarFormulario().contains(event.getCode().toString())) break;
                        fecharTab(tituloTab);
                        break;
                    case HELP:
//                            if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
//                            keyInsert();
                        break;
                    case DELETE:
//                            if (getStatusFormulario().toLowerCase().equals("pesquisa")) return;
//                            keyDelete();
                        break;
                }
            }
        };

        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.addEventHandler(KeyEvent.KEY_RELEASED, eventCadastroProduto);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        setStatusFormulario("Pesquisa");
        escutarTeclas();
        Platform.runLater(() -> {
            painelViewCadastroProduto.fireEvent(ExecutaComandoTecladoMouse.pressTecla(KeyCode.F7));
        });

    }


    EventHandler<KeyEvent> eventCadastroProduto;


    List<Pair> listaTarefas;

    int qtdRegistrosLocalizados = 0;
    String statusFormulario, statusBarFormulario;

    static String STATUSBARPESQUISA = "[F1-Novo]  [F3-Excluir]  [F4-Editar]  [F7-Pesquisar]  [F8-Filtro pesquisa]  [F12-Sair]  ";
    static String STATUSBAREDITAR = "[F3-Cancelar edição]  [F5-Atualizar]  ";
    static String STATUSBARINCLUIR = "[F2-Incluir]  [F3-Cancelar inclusão]  ";

    public int getQtdRegistrosLocalizados() {
        return qtdRegistrosLocalizados;
    }

    public void setQtdRegistrosLocalizados(int qtdRegistrosLocalizados) {
        this.qtdRegistrosLocalizados = qtdRegistrosLocalizados;
        atualizaLblRegistrosLocalizados();
    }

    public String getStatusFormulario() {
        return statusFormulario;
    }

    public void setStatusFormulario(String statusFormulario) {
        this.statusFormulario = statusFormulario;
        atualizaLblRegistrosLocalizados();
        setStatusBarFormulario(statusFormulario);
    }

    public String getStatusBarFormulario() {
        return statusBarFormulario;
    }

    public void setStatusBarFormulario(String statusFormulario) {
        if (statusFormulario.toLowerCase().contains("incluir")) {
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), false);
//            PersonalizarCampo.clearField((AnchorPane) tpnDadosCadastrais.getContent());
//            cboClassificacaoJuridica.requestFocus();
//            cboClassificacaoJuridica.getSelectionModel().select(0);
//            cboClassificacaoJuridica.getSelectionModel().select(1);
            this.statusBarFormulario = STATUSBARINCLUIR;
        } else if (statusFormulario.toLowerCase().contains("editar")) {
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), true);
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), false);
//            txtCNPJ.requestFocus();
            this.statusBarFormulario = STATUSBAREDITAR;
        } else if (statusFormulario.toLowerCase().contains("pesquisa")) {
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnCadastroEmpresa.getContent(), false);
//            PersonalizarCampo.desabilitaCampos((AnchorPane) tpnDadosCadastrais.getContent(), true);
//            txtPesquisa.requestFocus();
            this.statusBarFormulario = STATUSBARPESQUISA;
        }
        ControllerPrincipal.ctrlPrincipal.atualizarTeclasStatusBar(statusBarFormulario);
    }

    void atualizaLblRegistrosLocalizados() {
        lblRegistrosLocalizados.setText("[" + getStatusFormulario() + "]  " + String.valueOf(getQtdRegistrosLocalizados()) + " registro(s) localizado(s).");
    }

    void fecharTab(String tituloTab) {
        ControllerPrincipal.ctrlPrincipal.painelViewPrincipal.removeEventHandler(KeyEvent.KEY_RELEASED, eventCadastroProduto);
        //System.out.println("qtdTabs: " + ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size());
        for (int i = 0; i < ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().size(); i++)
            if (ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().get(i).getText().toLowerCase().equals(tituloTab.toLowerCase())) {
                //System.out.println("tab: " + ControllerPrincipal.ctrlPrincipal.tabPaneViewPrincipal.getTabs().get(i).getText().toLowerCase());
                ControllerPrincipal.ctrlPrincipal.fecharTab(i);
            }
    }

}
