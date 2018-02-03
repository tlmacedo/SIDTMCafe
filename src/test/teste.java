import static javafx.application.Application.launch;

public class teste {
    public teste() {
        String teste = " 14 DE JULHO ";
        System.out.println(teste + " - " + teste.length());
        teste = teste.trim();
        System.out.println(teste + " - " + teste.length());
    }

    public static void main(String[] args) {
        //launch(args);
        new teste();
    }
}
