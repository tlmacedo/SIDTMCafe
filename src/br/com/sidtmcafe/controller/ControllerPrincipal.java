package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.SisMenuPrincipalDAO;
import br.com.sidtmcafe.model.vo.SisMenuPrincipalVO;
import br.com.sidtmcafe.view.ViewPrincipal;
import com.jfoenix.controls.JFXToolbar;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrincipal implements Initializable, FormularioModelo, Constants {

    public BorderPane painelViewPrincipal;
    public JFXToolbar stbViewPrincipal;
    public Label btnRetraiMenuViewPrincipal;
    public Label btnExpandeMenuViewPrincipal;
    public TreeTableView treeMenuViewPrincipal;
    public Label viewPrincipal_StatusBarLeft;

    TreeTableColumn<SisMenuPrincipalVO, String> colunaItem;
    TreeTableColumn<SisMenuPrincipalVO, String> colunaAtalho;

    @Override
    public void fechar() {
        ViewPrincipal.getStage().close();
    }

    @Override
    public void preencherObjetos() {
        preencheMenuItem();
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {
        painelViewPrincipal.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.F12) {
                if (sairSistema())
                    fechar();
            }
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


        viewPrincipal_StatusBarLeft.setText(System.getProperty("USUARIO_LOGADO_NOME"));


    }

    boolean sairSistema() {

        if (new AlertMensagem("Sair do sistema",
                System.getProperty("USUARIO_LOGADO_NOME")
                        + ", deseja sair do sistema?", PATH_IMAGENS
                + "ic_sair_sistema_black_32dp.png").getRetornoAlert_YES_NO().get() == ButtonType.YES)
            return true;
        return false;
    }
}
