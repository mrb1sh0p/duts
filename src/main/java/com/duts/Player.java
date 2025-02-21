package com.duts;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
    private CollisionChecker collisionChecker;

    GamePanel gamePanel;
    int x, y;
    int speed;

    public Player(GamePanel gamePanel, CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
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
    public void update(boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {
        // Movimento horizontal
        if (leftPressed || rightPressed) {
            int newX = x;
            if (leftPressed)
                newX -= speed;
            if (rightPressed)
                newX += speed;

            // Verifica colisão apenas no eixo X
            if (!collisionChecker.checkTileCollision(newX, y)) {
                x = newX;
            }
        }

        // Movimento vertical
        if (upPressed || downPressed) {
            int newY = y;
            if (upPressed)
                newY -= speed;
            if (downPressed)
                newY += speed;

            // Verifica colisão apenas no eixo Y
            if (!collisionChecker.checkTileCollision(x, newY)) {
                y = newY;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}