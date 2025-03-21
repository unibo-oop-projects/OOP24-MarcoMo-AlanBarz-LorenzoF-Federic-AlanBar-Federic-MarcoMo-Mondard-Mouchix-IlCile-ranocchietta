package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Button;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Position;
import frogger.controller.ControllerImpl;

public class InputTest {

    private ControllerImpl controller;
    
    @BeforeEach
    void setUp(){
        controller = new ControllerImpl();
        controller.gameInit();
    }

    @Test
    void testInput(){
        assertEquals(new Position(0, -6), this.controller.getGame().getPlayer().getPos());

        System.out.println(KeyEvent.getKeyText(KeyEvent.VK_UP).toString());
        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'S'));
        //this.controller.getInputController().notifyCommand(new MoveUp());
        this.controller.getInputController().processInput(this.controller.getGame());
        assertEquals(new Position(0, -5), this.controller.getGame().getPlayer().getPos());

        //this.controller.getInputController().notifyCommand(new MoveDown());
        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(0, -6), this.controller.getGame().getPlayer().getPos());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(-1, -6), this.controller.getGame().getPlayer().getPos());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(0, -6), this.controller.getGame().getPlayer().getPos());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(0, -6), this.controller.getGame().getPlayer().getPos());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());
        
        assertEquals(new Position(-7, -6), this.controller.getGame().getPlayer().getPos());

        this.controller.getScenePanel().keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(-7, -6), this.controller.getGame().getPlayer().getPos());
    }
}
