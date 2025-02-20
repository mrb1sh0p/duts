package com.duts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
    GamePanel gamePanel;
    public BufferedImage image; // Imagem do tile
    public boolean collision = false; // Se o tile tem colisão

    /**
     * Construtor padrão para criar um tile.
     * @param image A imagem que representa o tile.
     * @param collision Define se o tile tem colisão (true = bloqueado, false = passável).
     */
    public Tile(BufferedImage image, boolean collision, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.image = image;
        this.collision = collision;
    }

    /**
     * Desenha o tile na posição especificada.
     * @param g2 Objeto Graphics2D usado para desenhar.
     * @param x Posição X onde o tile será desenhado.
     * @param y Posição Y onde o tile será desenhado.
     */
    public void draw(Graphics2D g2, int x, int y) {
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}