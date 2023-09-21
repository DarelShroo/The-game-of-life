import java.io.IOException;

public class Main {
    public static void main(String[] args){
        World world = new World(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        world.fillWorld();
       generateRandomAliveCell(world, Integer.parseInt(args[2]));

        while (true){
            world.printWorld();
            if(Boolean.parseBoolean(args[3])){
                if (randomNumber(100) == 83) {
                    switch (randomNumber(20)) {
                        case 5: world.setBlockPattern(randomNumber(world.getWorld().length), randomNumber(world.getWorld()[0].length));
                        case 10:world.setGliderPattern(randomNumber(world.getWorld().length), randomNumber(world.getWorld()[0].length));
                        case 15:world.setBlinkerPattern(randomNumber(world.getWorld().length), randomNumber(world.getWorld()[0].length));
                        case 20:world.setToadPattern(randomNumber(world.getWorld().length), randomNumber(world.getWorld()[0].length));
                        case 0:world.setBeaconPattern(randomNumber(world.getWorld().length), randomNumber(world.getWorld()[0].length));
                        case 8:world.setPulsarPattern(randomNumber(world.getWorld().length), randomNumber(world.getWorld()[0].length));
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearConsole();
        }
    }

    public static void generateRandomAliveCell(World world, int nCell){
        int i = 0;
        int[] x_pos = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] y_pos = {0, 0, 1, -1, 1, -1, -1, 1};
        Random random = new Random();
        while(i<nCell){
            int continueStep = random.generateRandomNumber(0, 6);
            int x = random.generateRandomNumber(0, world.getWorld().length);
            int y = random.generateRandomNumber(0, world.getWorld()[0].length);
            for(int j = 0; j < continueStep; j++){
                int pos = random.generateRandomNumber(0, 8);
                try{
                    world.getWorld()[x+x_pos[pos]][y+y_pos[pos]].setState(true);
                }catch (ArrayIndexOutOfBoundsException exception){}
            }
            i++;
        }
    }

    private static int randomNumber(int max){
        Random random = new Random();
        return random.generateRandomNumber(0, max);
    }

    private static void clearConsole(){
        try {
            if(System.getProperty("os.name").contains("Windows")){
                ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                processBuilder.inheritIO().start().waitFor();
            }else {
                ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "clear");
                processBuilder.inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}