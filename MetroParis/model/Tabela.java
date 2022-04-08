package model;

import java.util.ArrayList;

public class Tabela {

    private int tam = 14;

    private ArrayList<Integer> caminho;
    private int tempoTotalMin = 0;

    private int[][] distanciasLinhaReta =
    {
        {0,11,20,27,40,43,39,28,18,10,18,30,30,32},{11,0,9,16,29,32,28,19,11,4,17,23,21,24},{20,9,0,7,20,22,19,15,10,11,21,21,13,18},
        {27,16,7,0,13,16,12,13,13,18,26,21,11,17},{40,29,20,13,0,3,2,21,25,31,38,27,16,20},{43,32,22,16,3,0,4,23,28,33,41,30,17,20},
        {39,28,19,12,2,4,0,22,25,29,38,28,13,17},{28,19,15,13,21,23,22,0,9,22,18,7,25,30},{18,11,10,13,25,28,25,9, 0,13,12,12,23,28},
        {10,4,11,18,31,33,29,22,13,0,20,27,20,23},{18,17,21,26,38,41,38,18,12,2,0,15,35,39},{30,23,21,21,27,30,28,7,12,27,15,0,31,37},
        {30,21,13,11,16,17,13,25,23,20,35,31,0,5},{32,24,18,17,20,20,17,30,28,23,39,37,5,0}
    };

    private int[][] distanciasReais = 
    {
        {-1,11,0,0,0,0,0,0,0,0,0,0,0,0},{11,0,9,0,0,0,0,0,11,4,0,0,0,0},{0,9,0,7,0,0,0,0,10,0,0,0,19,0},
        {0,0,7,0,15,0,0,16,0,0,0,0,13,0},{0,0,0,15,0,3,2,28,0,0,0,0,0,0},{-1,0,0,0,3,0,0,0,0,0,0,0,0,0},
        {-1,0,0,0,2,0,0,0,0,0,0,0,0,0},{0,0,0,16,28,0,0,0,11,0,0,7,0,0},{0,11,10,0,0,0,0,11,0,0,14,0,0,0},
        {-1,4,0,0,0,0,0,0,0,0,0,0,0,0},{-1,0,0,0,0,0,0,0,14,0,0,0,0,0},{-1,0,0,0,0,0,0,7,0,0,0,0,0,0},
        {0,0,19,13,0,0,0,0,0,0,0,0,0,5},{-1,0,0,0,0,0,0,0,0,0,0,0,5,0}
    };
    // 1 - Azul / 2 - Amarelo / 3 - Vermelho / 4 - Verde
    private int[][] cores = 
    {
        {0,1,0,0,0,0,0,0,0,0,0,0,0,0},{1,0,1,0,0,0,0,0,2,2,0,0,0,0},{0,1,0,1,0,0,0,0,3,0,0,0,3,0},
        {0,0,1,0,1,0,0,4,0,0,0,0,4,0},{0,0,0,1,0,1,2,2,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,2,0,0,0,0,0,0,0,0,0},{0,0,0,4,2,0,0,0,2,0,0,4,0,0},{0,2,3,0,0,0,0,2,0,0,3,0,0,0},
        {0,2,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,3,0,0,0,0,0},{0,0,0,0,0,0,0,4,0,0,0,0,0,0},
        {0,0,3,4,0,0,0,0,0,0,0,0,0,4},{0,0,0,0,0,0,0,0,0,0,0,0,4,0}
    };

    public int getTempoTotalMin(){
        return this.tempoTotalMin;
    }


    public ArrayList<Integer> fazBusca(int origem, int destino){

        origem = origem - 1;
        destino = destino - 1;
        int origemAnterior = origem;
        
        this.caminho = new ArrayList<>();
        caminho.add(origem + 1);
 
        int somaTempoLinhaAtual = 0;
        int menorSomaDistancia = 0;
        int tempoTotalAtual = 0;
        int tempoTotal = 0;
        int corLinhaAnterior = 0;
        int corLinhaAtual = 0;
        int indice = -1;
        int cont = 0;
        
        while(true){
            cont++;
            
            for(int i=0;i<tam;i++){

                if(distanciasReais[i][0] == -1 && i != destino){
                    continue; // Verifica se é um caminho sem saída
                }

                if(distanciasReais[origem][i] > 0){ // 0 é um caminho inesistente 
                    
                    int linhaRetaTempo = convertMin(distanciasLinhaReta[i][destino]); // conversão para segundos (dist em linha reta)
                    int distRealTempo = convertMin(distanciasReais[origem][i]); // conversão para segundos (dist real)

                    if(tempoTotal != 0 && cores[origem][i] != corLinhaAnterior){
                        // Caso as cores das linhas sejam diferentes significa que ouve uma troca de linha 
                        distRealTempo = distRealTempo + 4;
                    }
                    
                    somaTempoLinhaAtual = (linhaRetaTempo + distRealTempo);

                    if(indice == -1){ // como foi o primeiro a ser analisado então de cara é o menor tempo
                        //Aqui eu guardo a cor da linha com menor tempo para poder comparar com a cor da próxima linha
                        corLinhaAtual = cores[origem][i];
                        indice = i; // Guardei o indice para a próxima análise
                        menorSomaDistancia = somaTempoLinhaAtual;
                        tempoTotalAtual = distRealTempo;
                    }
                    else{
                        if(somaTempoLinhaAtual < menorSomaDistancia){

                            menorSomaDistancia = somaTempoLinhaAtual;
                            tempoTotalAtual = distRealTempo;
                            corLinhaAtual = cores[origem][i];
                            indice = i;   
                        }
                    }
                }
            }
            
            corLinhaAnterior = corLinhaAtual;
            tempoTotal = tempoTotal + tempoTotalAtual;
            origemAnterior = origem;
            caminho.add(indice + 1);
            origem = indice;
            indice = -1;

            if(origem == destino || cont == 50){
                this.tempoTotalMin = tempoTotal;
                break;
            }
        } 
        return caminho; 
    } 

    public int convertMin(int distancia){

        int distConv = 0;
        distConv = (60 * distancia) / 30;
        return distConv;
    }

    public void imprimeDistancias(){

        for(int i=0;i<tam;i++){
            for(int k=0;k<tam;k++){
                System.out.print(distanciasLinhaReta[i][k] + " ");
            }
            System.out.println();
        }
    }

    public int simularCaminho(int[] caminhoS){

        int tempoGasto = 0;

        for(int i=0;i<caminhoS.length-1;i++){
            int a = caminhoS[i];
            int b = caminhoS[i+1];
            
            tempoGasto = tempoGasto + distanciasReais[a-1][b-1]; 
        }
        
        return convertMin(tempoGasto);
    }

    public void imprimeDistanciasReais(){

        for(int i=0;i<tam;i++){
            for(int k=0;k<tam;k++){
                System.out.print(distanciasReais[i][k] + " ");
            }
            System.out.println();
        }
    }

    public void imprimeCores(){
        for(int i=0;i<tam;i++){
            for(int k=0;k<tam;k++){
                System.out.print(cores[i][k] + " ");
            }
            System.out.println();
        }
    }
}
