public class Random {
    public int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("The minimum number must be less than the maximum number.");
        }
        java.util.Random random = new java.util.Random();
        return random.nextInt(max - min + 1) + min;
    }

}
