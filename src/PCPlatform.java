public class PCPlatform implements GamePlatform {

    @Override
    public void startGame(String mode) {
        System.out.println("PC: starting " + mode);
    }

    @Override
    public void handleInput() {
        System.out.println("Controls: keyboard and mouse");
    }
}