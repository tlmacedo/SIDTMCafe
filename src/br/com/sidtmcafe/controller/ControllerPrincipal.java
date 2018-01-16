package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.SisMenuPrincipalDAO;
import br.com.sidtmcafe.model.vo.SisMenuPrincipalVO;
import com.jfoenix.controls.JFXToolbar;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrincipal implements Initializable, FormularioModelo {

    public BorderPane pnlViewPrincipal;
    public JFXToolbar stbViewPrincipal;
    public ImageView btnRetraiMenuViewPrincipal;
    public TreeView treeMenuViewPrincipal;

    @Override
    public void fechar(Stage stage) {

    }

    @Override
    public void preencherObjetos() {
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
                treeItems[idTemp] = new TreeItem(principalVO, new ImageView("/images/material-design-icons" + icoMenu));
            }
            treeItems[idTemp].setExpanded(true);
            treeItems[filhoTemp].getChildren().add(treeItems[idTemp]);
        }

        treeMenuViewPrincipal.setRoot(treeItems[0]);
        treeMenuViewPrincipal.setShowRoot(false);
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTeclas() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
    }
}
