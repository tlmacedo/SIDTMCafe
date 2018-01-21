package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.SisMenuPrincipalDAO;
import br.com.sidtmcafe.model.vo.SisMenuPrincipalVO;
import br.com.sidtmcafe.view.ViewPrincipal;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrincipal implements Initializable, FormularioModelo, Constants {

    public BorderPane painelViewPrincipal;
    public JFXToolbar statusBar_ViewPrincipal;
    public TreeTableView<SisMenuPrincipalVO> treeMenuViewPrincipal;
    //    public Label viewPrincipal_StatusBarLeft;
//    public Label viewPrincipal_StatusBarCenter;
//    public Label viewPrincipal_StatusBarRight;
    public JFXTabPane tabPaneViewPrincipal;
    public Label lblImageLogoViewPrincipal;
    public Label lblBotaoExpandeMenuViewPrincipal;
    public Label lblBotaoRetraiMenuViewPrincipal;

    TreeTableColumn<SisMenuPrincipalVO, String> colunaItem;
    TreeTableColumn<SisMenuPrincipalVO, String> colunaAtalho;

    String horarioLog = DATAHORA_LOCAL.format(DTFORMAT_HORA).toString();
    Label stbUsuarioLogado, stbTeclasTela, stbDataBase, stbIcoRelogio, stbHora;

    Timeline timeline;
    int tabSelecionadaId = 0;

    @Override
    public void fechar() {
        ViewPrincipal.getStage().close();
    }

    @Override
    public void preencherObjetos() {
        preencheMenuItem();
        atualizarStatusBar();
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {
        painelViewPrincipal.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (CODE_KEY_SHIFT_CTRL_POSITIVO.match(event) || CHAR_KEY_SHIFT_CTRL_POSITIVO.match(event))
                lblBotaoExpandeMenuViewPrincipal.fireEvent(executarClickMouse(1));

            if (CODE_KEY_SHIFT_CTRL_NEGATIVO.match(event) || CHAR_KEY_SHIFT_CTRL_NEGATIVO.match(event))
                lblBotaoRetraiMenuViewPrincipal.fireEvent(executarClickMouse(1));

            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.F12) {
                if (sairSistema())
                    fechar();
            }
        });

        tabPaneViewPrincipal.getTabs().addListener(new ListChangeListener<Tab>() {
            @Override
            public void onChanged(Change<? extends Tab> c) {
                if (tabPaneViewPrincipal.getTabs().size() > 0) {
                    lblImageLogoViewPrincipal.setVisible(false);
                } else {
                    lblImageLogoViewPrincipal.setVisible(true);
                }
            }
        });

        lblBotaoExpandeMenuViewPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            for (int i = 0; i < treeMenuViewPrincipal.getExpandedItemCount(); i++) {
                treeMenuViewPrincipal.getTreeItem(i).setExpanded(true);
            }
        });

        lblBotaoRetraiMenuViewPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            for (int i = (treeMenuViewPrincipal.getExpandedItemCount() - 1); i > -1; i--) {
                treeMenuViewPrincipal.getTreeItem(i).setExpanded(false);
            }
        });

        treeMenuViewPrincipal.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            SisMenuPrincipalVO item = treeMenuViewPrincipal.getSelectionModel().getSelectedItem().getValue();
            if (item == null) return;
            if (event.getCode() == KeyCode.ENTER)
                adicionaNovaTab(item);
        });

        treeMenuViewPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            SisMenuPrincipalVO item = treeMenuViewPrincipal.getSelectionModel().getSelectedItem().getValue();
            if (item == null) return;
            if (item.getDescricao().equals("Sair") || event.getClickCount() == 2)
                treeMenuViewPrincipal.fireEvent(executarClickTelcado(KeyCode.ENTER));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
        fatorarObjetos();
        escutarTeclas();
    }

    void preencheMenuItem() {

        colunaItem = new TreeTableColumn<SisMenuPrincipalVO, String>();
        colunaItem.setPrefWidth(250);
        colunaItem.setCellValueFactory(param -> param.getValue().getValue().descricaoProperty());

        colunaAtalho = new TreeTableColumn<SisMenuPrincipalVO, String>();
        colunaAtalho.setPrefWidth(150);
        colunaAtalho.setCellValueFactory(param -> param.getValue().getValue().teclaAtalhoProperty());

        List<SisMenuPrincipalVO> menuPrincipalVOList = new SisMenuPrincipalDAO().getMenuPrincipalVOList();
        TreeItem[] treeItems = new TreeItem[menuPrincipalVOList.size() + 1];
        treeItems[0] = new TreeItem();

        for (SisMenuPrincipalVO principalVO : menuPrincipalVOList) {
            int idTemp = principalVO.getId();
            int filhoTemp = principalVO.getFilho_id();
            String icoMenu = principalVO.getIcoMenu();
            if (icoMenu.equals("")) {
                treeItems[idTemp] = new TreeItem(principalVO);
            } else {
                treeItems[idTemp] = new TreeItem(principalVO, new ImageView(PATH_IMAGENS + icoMenu));
            }
            treeItems[idTemp].setExpanded(true);
            treeItems[filhoTemp].getChildren().add(treeItems[idTemp]);
        }

        treeMenuViewPrincipal.getColumns().setAll(colunaItem, colunaAtalho);
        treeMenuViewPrincipal.setRoot(treeItems[0]);
        treeMenuViewPrincipal.setShowRoot(false);
    }

    void atualizarStatusBar() {
        stbUsuarioLogado = new Label("Usuário: " + System.getProperty("USUARIO_LOGADO_APELIDO") + " [" + System.getProperty("USUARIO_LOGADO_ID") + "]");
        stbUsuarioLogado.getStyleClass().setAll("status-bar-usuario-logado");

        stbTeclasTela = new Label("");
        stbTeclasTela.getStyleClass().setAll("status-bar-center");

        statusBar_ViewPrincipal.getLeftItems().setAll(stbUsuarioLogado, stbTeclasTela);

        stbDataBase = new Label("banco de dados: [" + BD_DATABASE_STB
                + "]   horario_log: " + horarioLog);

        Tooltip tooltipDetalhesLog = new Tooltip(stbDataBase.getText());
        tooltipDetalhesLog.getStyleClass().setAll("tool-tip-hora-view-principal");

        stbIcoRelogio = new Label("");
        stbIcoRelogio.getStyleClass().setAll("ico-relogio");

        stbHora = new Label(horarioLog);
        stbHora.getStyleClass().setAll("hora");
        stbHora.setTooltip(tooltipDetalhesLog);

        statusBar_ViewPrincipal.getRightItems().setAll(stbIcoRelogio, stbHora);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000), event -> {
            String hora = LocalTime.now().format(DTFORMAT_HORA);
            stbHora.setText(hora);
            statusBar_ViewPrincipal.getRightItems().set(1, stbHora);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    void atualizarTeclasStatusBar(String teclasStatusBar) {
        stbTeclasTela.setText(teclasStatusBar);
        statusBar_ViewPrincipal.getLeftItems().set(1, stbTeclasTela);
    }

    KeyEvent executarClickTelcado(KeyCode keyCode) {
        return new KeyEvent(KeyEvent.KEY_RELEASED, "", "", keyCode, true, true, true, true);
    }

    MouseEvent executarClickMouse(int qtdClicks) {
        return new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                0, 0, 0, MouseButton.PRIMARY, qtdClicks, true, true, true, true,
                true, true, true, true, true, true, null);
    }

    boolean existeTab(SisMenuPrincipalVO menuPrincipalVO) {
        tabSelecionadaId = 0;
        for (Tab tab : tabPaneViewPrincipal.getTabs()) {
            if (tab.getText().equals(menuPrincipalVO.getDescricao()))
                return true;
            tabSelecionadaId++;
        }
        return false;
    }

    void adicionaNovaTab(SisMenuPrincipalVO menuPrincipalVO) {
        if (existeTab(menuPrincipalVO)) {
            tabPaneViewPrincipal.getSelectionModel().select(tabSelecionadaId);
        } else {
            if (menuPrincipalVO.getTabPane() == 0) {

            } else {
                String menuprincipal = menuPrincipalVO.getDescricao();
                switch (menuprincipal) {
                    case "Sair":
                        fechar();
                        break;
                    default:
                        tabPaneViewPrincipal.getTabs().add(new Tab(menuprincipal));
                        break;
                }
                tabPaneViewPrincipal.getSelectionModel().select(tabSelecionadaId);
            }
        }
    }

    boolean sairSistema() {
        if (tabPaneViewPrincipal.getTabs().size() > 0) {
            tabPaneViewPrincipal.getTabs().remove(tabPaneViewPrincipal.getSelectionModel().getSelectedItem());
        } else {
            if (new AlertMensagem("Sair do sistema",
                    System.getProperty("USUARIO_LOGADO_APELIDO")
                            + ", deseja sair do sistema?", PATH_IMAGENS
                    + "ic_sair_sistema_white_32dp.png").getRetornoAlert_YES_NO().get() == ButtonType.YES)
                return true;
        }
        return false;
    }
}
