package com.duts;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    GamePanel gamePanel;
    int x, y;
    int speed;

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        x = 20;
        y = 20;
        speed = 4;
    }

    /**
     * Atualiza a posição do jogador baseado nas teclas pressionadas
     * 
     * @param deltaTime tempo desde o último frame para cálculo de movimento suave
     */
    public void update() {
        if (gamePanel.upPressed)
            y -= speed;
        if (gamePanel.downPressed)
            y += speed;
        if (gamePanel.leftPressed)
            x -= speed;
        if (gamePanel.rightPressed)
            x += speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}