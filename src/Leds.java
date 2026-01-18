public class Leds {
    public static void main(String[] args) {
        System.out.println(calculaTotalLeds(13, 15));
    }
    public static Integer calculaTotalLeds(Integer altura, Integer largura) {
        if (altura == 0 || largura == 0) return 0;
        return (altura + 1) * (largura + 1);
    }
}
