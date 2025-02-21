package com.duts;

public class CollisionChecker {
    private TileManager tileManager;
    GamePanel gamePanel;

    /**
     * Construtor do verificador de colisões.
     * @param tileManager Referência ao gerenciador de tiles para acesso ao mapa.
     */
    public CollisionChecker(TileManager tileManager, GamePanel gamePanel) {
        this.tileManager = tileManager;
        this.gamePanel = gamePanel;
    }

    /**
     * Verifica colisão em uma posição específica do mapa.
     * @param x Posição X em pixels para verificação.
     * @param y Posição Y em pixels para verificação.
     * @return true se houver colisão, false caso contrário.
     */
    public boolean checkTileCollision(int x, int y) {
        // Converte coordenadas de pixels para tiles
        int tileX = x / gamePanel.tileSize;
        int tileY = y / gamePanel.tileSize;
        
        // Verifica se está fora dos limites do mapa
        if (tileX < 0 || tileX >= tileManager.getMaxScreenCol() || 
            tileY < 0 || tileY >= tileManager.getMaxScreenRow()) {
            return true; // Colide com bordas do mapa
        }
        
        // Obtém o tile na posição calculada
        int tileNum = tileManager.getMapTileNumber(tileX, tileY);
        return tileManager.getTile(tileNum).isCollision();
    }
}