public class Cell {
    private static final boolean  IS_ALIVE = true;
    private static final boolean  IS_DEAD = false;
    private boolean state = IS_DEAD;
    private boolean nextState = IS_DEAD;

    private int x, y;

    public Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX (){
        return this.x;
    }

    public int getY () {
        return this.y;
    }

    public boolean isAlive(){
        return this.state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public Character getBody(){
        return this.state ? '\u25A0': ' ';
    }

    public void calculateNextState(World world) {
        int[] x_pos = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] y_pos = {0, 0, 1, -1, 1, -1, -1, 1};

        boolean[] neighbors = new boolean[8];

        for (int i = 0; i < x_pos.length; i++) {
            int x = this.getX() + x_pos[i];
            int y = this.getY() + y_pos[i];
            if (x >= 0 && x < world.getWorld().length && y >= 0 && y < world.getWorld()[0].length) {
                neighbors[i] = world.getWorld()[x][y].isAlive();
            } else {
                neighbors[i] = false;
            }
        }

        int liveNeighbors = 0;
        for (boolean neighbor : neighbors) {
            if (neighbor) {
                liveNeighbors++;
            }
        }

        if (this.isAlive()) {
            if (liveNeighbors < 2 || liveNeighbors > 3) {
                this.nextState = IS_DEAD;
            } else {
                this.nextState = this.state;
            }
        } else {
            if (liveNeighbors == 3) {
                this.nextState = IS_ALIVE;
            } else {
                this.nextState = this.state;
            }
        }
    }

    public void updateState() {
        this.state = this.nextState;
    }
}
