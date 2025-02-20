package com.duts;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    GamePanel gamePanel;
    int x, y;
    int speed;

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(double deltaTime) {
        if (gamePanel.upPressed)
            y -= speed * deltaTime;
        if (gamePanel.downPressed)
            y += speed * deltaTime;
        if (gamePanel.leftPressed)
            x -= speed * deltaTime;
        if (gamePanel.rightPressed)
            x += speed * deltaTime;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}