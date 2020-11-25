package Game;





public class launcher {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final String TITLE = "PONG";

    public static void main(String[] args) {
        new Display(TITLE, WIDTH, HEIGHT);
    }
}
