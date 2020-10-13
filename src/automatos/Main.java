package automatos;

public class Main {

    public static void main(String[] args) {
        Automato automato = new Automato();
        if (automato.verificaSentenca("abb")) 
            System.out.println("Aceita");
        else
            System.out.println("Rejeita");
    }
}
