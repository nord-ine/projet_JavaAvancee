package org.nor.GameLogic;

public class Grid {

    public static Point[][] startingGrid(int lineSize,int gridHight,int gridWidth,GameVersion gameVersion) {
        Point[][] gameGrid = new Point[gridHight][gridWidth];
        for (int i = 0; i < gridHight ; i++) {
            for ( int j = 0 ; j < gridWidth ; j++ ) {
                gameGrid[i][j] = new Point(i, j, -1 ,gridHight,gridWidth);
            }
        }
        
        if(gameVersion instanceof D || gameVersion instanceof T) {
            int x = (int) gridHight/2 -1;
            int y = (int) gridWidth/2 -1;
            int a = lineSize-1;

            if (a == 4) {

                gameGrid[x - 4][y-1].setState(0);
                gameGrid[x - 4][y].setState(0);
                gameGrid[x - 4][y + 1].setState(0);
                gameGrid[x - 4][y + 2].setState(0);


                gameGrid[x - 1][y-1].setState(0);
                gameGrid[x - 1][y-2].setState(0);
                gameGrid[x - 1][y-3].setState(0);
                gameGrid[x - 1][y-4].setState(0);

                gameGrid[x - 1][y+2].setState(0);
                gameGrid[x - 1][y+3].setState(0);
                gameGrid[x - 1][y+4].setState(0);
                gameGrid[x - 1][y+5].setState(0);


                gameGrid[x + 2][y-1].setState(0);
                gameGrid[x + 2][y-2].setState(0);
                gameGrid[x + 2][y-3].setState(0);
                gameGrid[x + 2][y-4].setState(0);

                gameGrid[x + 2][y+2].setState(0);
                gameGrid[x + 2][y+3].setState(0);
                gameGrid[x + 2][y+4].setState(0);
                gameGrid[x + 2][y+5].setState(0);

                gameGrid[x + 5][y-1].setState(0);
                gameGrid[x + 5][y].setState(0);
                gameGrid[x + 5][y + 1].setState(0);
                gameGrid[x + 5][y + 2].setState(0);


                gameGrid[x][y - 4].setState(0);
                gameGrid[x + 1][y - 4].setState(0);

                gameGrid[x - 3][y - 1].setState(0);
                gameGrid[x - 2][y - 1].setState(0);
                gameGrid[x + 3][y - 1].setState(0);
                gameGrid[x + 4][y - 1].setState(0);

                gameGrid[x][y + 5].setState(0);
                gameGrid[x + 1][y + 5].setState(0);

                gameGrid[x - 3][y + 2].setState(0);
                gameGrid[x - 2][y + 2].setState(0);
                gameGrid[x + 3][y + 2].setState(0);
                gameGrid[x + 4][y + 2].setState(0);

            }

            if (a == 3) {

                gameGrid[x - 3][y - 1].setState(0);
                gameGrid[x - 3][y].setState(0);
                gameGrid[x - 3][y + 1].setState(0);

                gameGrid[x + 3][y - 1].setState(0);
                gameGrid[x + 3][y].setState(0);
                gameGrid[x + 3][y + 1].setState(0);

                gameGrid[x - 1][y - 3].setState(0);
                gameGrid[x - 1][y - 2].setState(0);
                gameGrid[x - 1][y - 1].setState(0);
                gameGrid[x - 1][y + 1].setState(0);
                gameGrid[x - 1][y + 2].setState(0);
                gameGrid[x - 1][y + 3].setState(0);


                gameGrid[x + 1][y - 3].setState(0);
                gameGrid[x + 1][y - 2].setState(0);
                gameGrid[x + 1][y - 1].setState(0);
                gameGrid[x + 1][y + 1].setState(0);
                gameGrid[x + 1][y + 2].setState(0);
                gameGrid[x + 1][y + 3].setState(0);

                gameGrid[x - 2][y - 1].setState(0);
                gameGrid[x - 2][y + 1].setState(0);
                gameGrid[x][y + 3].setState(0);
                gameGrid[x][y - 3].setState(0);
                gameGrid[x + 2][y - 1].setState(0);
                gameGrid[x + 2][y + 1].setState(0);
            }
        	
        }
        return gameGrid;
    }


}
