package com.duts;

import javax.swing.JPanel;
import java.awt.Dimension;

public class GamePanel extends JPanel {
    // Configurações da tela
    final int originalTileSize = 16; // 16x16 tiles
    final int scale = 3; // Escala para monitores modernos
    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }
}