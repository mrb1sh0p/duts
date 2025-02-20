package com.duts;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private TileManager tileManager;

    Player player;
    Thread gameThread;
    boolean gameRunning = true;

    boolean upPressed, downPressed, leftPressed, rightPressed;

    // FPS configurável
    int FPS = 60;

    // Configurações da tela
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 4;
    final int tileSize = originalTileSize * scale; // 64x64
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 1024 pixels
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels

    public GamePanel() {
        this.player = new Player(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        tileManager = new TileManager(this);
    }

    // iniciar o thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            update();
            repaint();
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
                if (remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

                if (delta >= 1) {
                    update(); // Atualiza a lógica do jogo
                    repaint(); // Redesenha a tela
                    delta--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void update() {
        player.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
            upPressed = true;
        if (code == KeyEvent.VK_S)
            downPressed = true;
        if (code == KeyEvent.VK_A)
            leftPressed = true;
        if (code == KeyEvent.VK_D)
            rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
            upPressed = false;
        if (code == KeyEvent.VK_S)
            downPressed = false;
        if (code == KeyEvent.VK_A)
            leftPressed = false;
        if (code == KeyEvent.VK_D)
            rightPressed = false;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, screenWidth, screenHeight);

        tileManager.draw(g2);
        player.draw(g2);
    }
}