package automatos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Automato {
    private String sentenca;
    private List<FuncaoTransicao> lista;
    private List<Integer> finais = new ArrayList<>();
    private int estadoInicial = 0;

    public Automato() {
        try {
            FileReader f = new FileReader("automato.txt");
            try (Scanner arquivoLido = new Scanner(f)) {
                this.lista = new ArrayList<>();
                
                while (arquivoLido.hasNext()) { //LEITURA LINHA POR LINHA
                    
                    String lido = arquivoLido.next()
                            .replaceAll(" ", "") //REMOVE EVENTUAIS ESPAÇOS EM BRANCO
                            .replaceAll("q", ""); //REMOVE A LETRA q
                    
                    String[] linha = lido.split(";"); //QUEBRA A LINHA EM ;
                    
                    //LINHA[0] REPRESENTA ESTADO DE ORIGEM E LINHA[2] REPRESENTA ESTADO DE DESTINO
                    if(linha[0].contains("*")){ //VERIFICAÇÃO SE ESTADO DE ORIGEM ESTA MARCADO COM *, INDICANDO QUE ESTE É ESTADO FINAL
                        int valorFinal = Integer.valueOf(linha[0].replace("*", "")); //É REMOVIDO O * DO ESTADO
                        if(!this.finais.contains(valorFinal)){ //SE ESTADO x NAO ESTIVER ADICIONADO, É ADICIONADO
                            this.finais.add(valorFinal);
                        }
                    }
                    if(linha[2].contains("*")){ //VERIFICAÇÃO SE ESTADO DE DESTINO ESTA MARCADO COM *, INDICANDO QUE ESTE É ESTADO FINAL
                        int valorFinal = Integer.valueOf(linha[2].replace("*", "")); //É REMOVIDO O * DO ESTADO
                        if(!this.finais.contains(valorFinal)){ //SE ESTADO x NAO ESTIVER ADICIONADO, É ADICIONADO
                            this.finais.add(valorFinal);
                        }
                    }
                    //TENDO OS ESTADOS MANIPULADOS E NO FORMATO ADEQUADO, É POSSIVEL ENTÃO CRIAR O OBJETO DE FUNÇÃO DE TRANSIÇÃO
                    FuncaoTransicao funcaoTransicao = new FuncaoTransicao();
                    funcaoTransicao.setEstadoOrigem(linha[0].replace("*", "")); 
                    funcaoTransicao.setAlfabeto(linha[1].charAt(0));
                    funcaoTransicao.setEstadoDestino(linha[2].replace("*", ""));
                    this.lista.add(funcaoTransicao);
                    
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(e.getCause());
        }

    }
    
    
    public boolean verificaSentenca(String entrada) {
        this.sentenca = entrada;
        int estadoAtual = this.estadoInicial;
        int i = 0;
        int origem = 0;
        
        while (i <= this.sentenca.length() - 1 && estadoAtual != -1) {
            for(FuncaoTransicao funcaoTransicao : this.lista){ //FOREACH PELA LISTA CONTENDO AS FUNCOES DE TRANSIÇÃO
                if(funcaoTransicao.getEstadoOrigem().equals(estadoAtual + "") && funcaoTransicao.getAlfabeto() == this.sentenca.charAt(i)){ //VERIFICA QUAL "CAMINHO" PERRCORRER DADO UMA LETRA DA SENTENÇA, SE COINCIDEIREM, O ESTADO ATUAL É ALTERADO
                    estadoAtual = Integer.valueOf(funcaoTransicao.getEstadoDestino());
                    break;
                }
            }
            i++;
        }

        if (estadoAtual == -1)
            return false;
        else if (this.isFinal(estadoAtual))
            return true;
        else 
            return false;
    }

    private boolean isFinal(int estadoAtual) {
        return finais.contains(estadoAtual);
    }

}
