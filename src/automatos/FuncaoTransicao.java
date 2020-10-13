package automatos;

public class FuncaoTransicao {
    private String estadoOrigem, estadoDestino;
    private char alfabeto;
    
    public FuncaoTransicao() {
        this.estadoOrigem = "";       
        this.alfabeto = '-';      
        this.estadoDestino = "";   
    } 

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public char getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(char alfabeto) {
        this.alfabeto = alfabeto;
    }
}
