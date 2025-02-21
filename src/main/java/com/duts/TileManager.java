package com.duts;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles; // Array de tiles disponíveis
    private int[][] mapTileNumbers; // Matriz que representa o mapa

    /**
     * Construtor do TileManager.
     * 
     * @param gamePanel Referência ao GamePanel para acessar configurações.
     */
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10]; // 10 tipos de tiles (pode aumentar conforme necessário)
        mapTileNumbers = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        loadTiles(); // Carrega os tiles
        loadMap("/maps/world01.txt"); // Carrega o mapa
    }

    /**
     * Carrega as imagens dos tiles e define suas propriedades.
     */
    private void loadTiles() {
        try {
            // Tile 0: Grama (sem colisão)
            tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")), false, gamePanel);

            // Tile 1: Parede (com colisão)
            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")), true, gamePanel);

            // Tile 2: Água (com colisão)
            tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")), true, gamePanel);

            // Adicione mais tiles conforme necessário
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega o mapa a partir de um arquivo de texto.
     * 
     * @param filePath Caminho do arquivo de mapa.
     */
    private void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = reader.readLine();
                while (col < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumbers[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Desenha o mapa na tela.
     * 
     * @param g2 Objeto Graphics2D usado para desenhar.
     */
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;

        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
            int tileNum = mapTileNumbers[col][row];
            int x = col * gamePanel.tileSize;
            int y = row * gamePanel.tileSize;

            tiles[tileNum].draw(g2, x, y);
            col++;

            if (col == gamePanel.maxScreenCol) {
                col = 0;
                row++;
            }
        }
    }
    
    public int getMaxScreenCol() {
        return gamePanel.maxScreenCol;
    }

    public int getMaxScreenRow() {
        return gamePanel.maxScreenRow;
    }

    public int getMapTileNumber(int col, int row) {
        return mapTileNumbers[col][row];
    }

    public Tile getTile(int index) {
        return tiles[index];
    }
}