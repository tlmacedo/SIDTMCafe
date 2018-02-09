package br.com.sidtmcafe.service;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ExecutaComandoTecladoMouse {

    public static KeyEvent pressTecla(KeyCode keyCode) {
        return new KeyEvent(KeyEvent.KEY_RELEASED, "", "", keyCode, true, true, true, true);
    }

    public static MouseEvent clickMouse(int qtdClicks) {
        return new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                0, 0, 0, MouseButton.PRIMARY, qtdClicks, true, true, true, true,
                true, true, true, true, true, true, null);
    }


}
