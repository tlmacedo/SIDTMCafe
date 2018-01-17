package br.com.sidtmcafe.controller;

import br.com.sidtmcafe.interfaces.FormularioModelo;
import br.com.sidtmcafe.model.dao.SisMenuPrincipalDAO;
import br.com.sidtmcafe.model.vo.SisMenuPrincipalVO;
import com.jfoenix.controls.JFXToolbar;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrincipal implements Initializable, FormularioModelo {


    public BorderPane pnlViewPrincipal;
    public JFXToolbar stbViewPrincipal;
    public Label btnRetraiMenuViewPrincipal;
    public Label btnExpandeMenuViewPrincipal;
    public TreeTableView treeMenuViewPrincipal;

    TreeTableColumn<SisMenuPrincipalVO, String> colunaItem;
    TreeTableColumn<SisMenuPrincipalVO, String> colunaAtalho;

    @Override
    public void fechar(Stage stage) {

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherObjetos();
    }

    void preencheMenuItem() {
        /*colunaRazao = new JFXTreeTableColumn<EmpresasVO, String>();
        colunaRazao.setPrefWidth(200);
        Label labelRazao = new Label("Razão / Nome");
        labelRazao.setPrefWidth(200);
        colunaRazao.setGraphic(labelRazao);
        colunaRazao.setCellValueFactory(param -> param.getValue().getValue().razaoProperty());*/

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
                treeItems[idTemp] = new TreeItem(principalVO, new ImageView("/images/material-design-icons/" + icoMenu));
            }
            treeItems[idTemp].setExpanded(true);
            treeItems[filhoTemp].getChildren().add(treeItems[idTemp]);
        }

        treeMenuViewPrincipal.getColumns().setAll(colunaItem, colunaAtalho);
        treeMenuViewPrincipal.setRoot(treeItems[0]);
        treeMenuViewPrincipal.setShowRoot(false);
    }
}
