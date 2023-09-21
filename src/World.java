public class World {
    private Cell[][] world;

    public World(int x, int y) {
        this.world = new Cell[x][y];
    }

    public void printWorld() {
        for (int x = 0; x < this.world[0].length; x++) {
            System.out.print('-');
        }
        System.out.println();

        for (int x = 0; x < this.world.length; x++) {
            System.out.print('|');
            for (int y = 0; y < this.world[0].length; y++) {
                System.out.print(this.world[x][y].getBody());
                this.world[x][y].calculateNextState(this);
            }
            System.out.print('|');
            System.out.println();
        }

        for (int x = 0; x < this.world[0].length; x++) {
            System.out.print('-');
        }
        System.out.println();
        updateAllCells();
    }

    public Cell[][] getWorld() {
        return this.world;
    }

    public void fillWorld() {
        for (int x = 0; x < this.world.length; x++) {
            for (int y = 0; y < this.world[0].length; y++) {
                if (this.world[x][y] == null) {
                    setWorld(x, y, new Cell(x, y));
                }
            }
        }
    }

    public void setWorld(int x, int y, Cell cell) {
        this.world[x][y] = cell;
    }

    public void updateAllCells() {
        for (int x = 0; x < this.world.length; x++) {
            for (int y = 0; y < this.world[0].length; y++) {
                this.world[x][y].updateState();
            }
        }
    }

    public void setGliderPattern(int offsetX, int offsetY) {
        if (offsetX >= 0 && offsetX < this.world.length - 2 && offsetY >= 0 && offsetY < this.world[0].length - 2) {
            this.world[offsetX + 1][offsetY].setState(true);
            this.world[offsetX + 2][offsetY + 1].setState(true);
            this.world[offsetX][offsetY + 2].setState(true);
            this.world[offsetX + 1][offsetY + 2].setState(true);
            this.world[offsetX + 2][offsetY + 2].setState(true);
        }
    }

    public void setBlockPattern(int offsetX, int offsetY) {
        if (offsetX >= 0 && offsetX < this.world.length - 1 && offsetY >= 0 && offsetY < this.world[0].length - 1) {
            this.world[offsetX][offsetY].setState(true);
            this.world[offsetX][offsetY + 1].setState(true);
            this.world[offsetX + 1][offsetY].setState(true);
            this.world[offsetX + 1][offsetY + 1].setState(true);
        }
    }

    public void setBlinkerPattern(int offsetX, int offsetY) {
        if (offsetX >= 0 && offsetX < this.world.length - 2 && offsetY >= 0 && offsetY < this.world[0].length) {
            this.world[offsetX][offsetY].setState(true);
            this.world[offsetX + 1][offsetY].setState(true);
            this.world[offsetX + 2][offsetY].setState(true);
        }
    }

    public void setToadPattern(int offsetX, int offsetY) {
        if (offsetX >= 0 && offsetX < this.world.length - 2 && offsetY >= 0 && offsetY < this.world[0].length - 1) {
            this.world[offsetX + 1][offsetY].setState(true);
            this.world[offsetX + 1][offsetY + 1].setState(true);
            this.world[offsetX + 1][offsetY + 2].setState(true);
            this.world[offsetX + 2][offsetY - 1].setState(true);
            this.world[offsetX + 2][offsetY].setState(true);
            this.world[offsetX + 2][offsetY + 1].setState(true);
        }
    }

    public void setBeaconPattern(int offsetX, int offsetY) {
        if (offsetX >= 0 && offsetX < this.world.length - 3 && offsetY >= 0 && offsetY < this.world[0].length - 3) {
            this.world[offsetX][offsetY].setState(true);
            this.world[offsetX][offsetY + 1].setState(true);
            this.world[offsetX + 1][offsetY].setState(true);
            this.world[offsetX + 1][offsetY + 1].setState(true);
            this.world[offsetX + 2][offsetY + 2].setState(true);
            this.world[offsetX + 2][offsetY + 3].setState(true);
            this.world[offsetX + 3][offsetY + 2].setState(true);
            this.world[offsetX + 3][offsetY + 3].setState(true);
        }
    }

    public void setPulsarPattern(int offsetX, int offsetY) {
        if (offsetX >= 0 && offsetX < this.world.length - 12 && offsetY >= 0 && offsetY < this.world[0].length - 12) {
            int[][] pulsarPattern = {
                    {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                    {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                    {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0}
            };

            for (int row = 0; row < pulsarPattern.length; row++) {
                for (int col = 0; col < pulsarPattern[row].length; col++) {
                    this.world[offsetX + row][offsetY + col].setState(pulsarPattern[row][col] == 1);
                }
            }


        }
    }
}