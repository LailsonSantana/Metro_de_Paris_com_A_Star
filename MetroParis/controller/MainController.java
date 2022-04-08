package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import model.Tabela;

public class MainController implements Initializable {

    private ArrayList<Integer> caminho;

    private int[] caminhoSimulado = { 6,5,8,12};

    @FXML
    private ImageView fundo, umdois, doistres, doisnove, doisdez, tresquatro, tresnove, trestreze, quatrocinco,
            quatrooito, quatrotreze, cincoseis, cincosete, cincooito, oitonove, oitodoze, noveonze, trezequatorze;

    @FXML
    private TextField fieldPartida, fieldDestino;

    @FXML
    private TextFlow flowTempo;

    @FXML
    private Label tempPerc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.loadScene();

    }

    public void search(ActionEvent event) {

        caminho = new ArrayList<>();
        Tabela tabela = new Tabela();

        int p = Integer.parseInt(fieldPartida.getText().toString());
        int d = Integer.parseInt(fieldDestino.getText().toString());

        if(p == d){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                    
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Escolha uma estação diferente do estação atual !"); 
            alert.show();
        }
        else{

        caminho = tabela.fazBusca(p, d);

        for (int i = 0; i < caminho.size() - 1; i++) {

            int a = caminho.get(i);
            int b = caminho.get(i + 1);

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == 1 && b == 2) {

                umdois.setVisible(true);
            } else if (a == 5 && b == 6) {
                cincoseis.setVisible(true);
            } else if (a == 2 && b == 3) {
                doistres.setVisible(true);
            } else if (a == 2 && b == 9) {
                doisnove.setVisible(true);
            } else if (a == 2 && b == 10) {
                doisdez.setVisible(true);
            } else if (a == 3 && b == 4) {
                tresquatro.setVisible(true);
            } else if (a == 3 && b == 9) {
                tresnove.setVisible(true);
            } else if (a == 3 && b == 13) {
                trestreze.setVisible(true);
            } else if (a == 4 && b == 5) {
                quatrocinco.setVisible(true);
            } else if (a == 4 && b == 8) {
                quatrooito.setVisible(true);
            } else if (a == 4 && b == 13) {
                quatrotreze.setVisible(true);
            } else if (a == 5 && b == 7) {
                cincosete.setVisible(true);
            } else if (a == 5 && b == 8) {
                cincooito.setVisible(true);
            } else if (a == 8 && b == 9) {
                oitonove.setVisible(true);
            } else if (a == 9 && b == 11) {
                noveonze.setVisible(true);
            } else if (a == 8 && b == 12) {
                oitodoze.setVisible(true);
            } else {
                trezequatorze.setVisible(true);
            }
            }
        }
        tempPerc.setText(Integer.toString(tabela.getTempoTotalMin()) + " Min");
         int res = tabela.simularCaminho(caminhoSimulado);
         System.out.println("CAMINHO SIMULADO : " + res);

        for (int i = 0; i < caminho.size(); i++) {
            System.out.println(caminho.get(i) + " / ");
        }

    }

    public void searchAgain(ActionEvent event) {
        System.out.println("---------------------------");
        tempPerc.setText("0");
        umdois.setVisible(false);
        doistres.setVisible(false);
        doisnove.setVisible(false);
        oitodoze.setVisible(false);
        noveonze.setVisible(false);
        oitonove.setVisible(false);
        cincooito.setVisible(false);
        quatrotreze.setVisible(false);
        quatrooito.setVisible(false);
        quatrocinco.setVisible(false);
        trestreze.setVisible(false);
        tresnove.setVisible(false);
        tresquatro.setVisible(false);
        doisdez.setVisible(false);
        trezequatorze.setVisible(false);
        cincosete.setVisible(false);
        cincoseis.setVisible(false);
        fieldPartida.setText("");
        fieldDestino.setText("");

    }

    public void loadScene() {

        Image image = new Image("/images/FUNDO.jpg");
        fundo.setImage(image);
        Image image2 = new Image("/images/1-2.png");// 1
        umdois.setImage(image2);
        Image image3 = new Image("/images/2-3.png");// 2
        doistres.setImage(image3);
        Image image4 = new Image("/images/2-9.png");// 3
        doisnove.setImage(image4);
        Image image5 = new Image("/images/2-10.png");// 4
        doisdez.setImage(image5);
        Image image6 = new Image("/images/3-4.png");// 5
        tresquatro.setImage(image6);
        Image image7 = new Image("/images/3-9.png");// 6
        tresnove.setImage(image7);
        Image image8 = new Image("/images/3-13.png");// 7
        trestreze.setImage(image8);
        Image image9 = new Image("/images/4-5.png");// 8
        quatrocinco.setImage(image9);
        Image image11 = new Image("/images/4-8.png");// 9
        quatrooito.setImage(image11);
        Image image12 = new Image("/images/4-13.png");// 10
        quatrotreze.setImage(image12);
        Image image13 = new Image("/images/5-6.png");// 11
        cincoseis.setImage(image13);
        Image image14 = new Image("/images/5-7.png");// 12
        cincosete.setImage(image14);
        Image image15 = new Image("/images/5-8.png");// 13
        cincooito.setImage(image15);
        Image image16 = new Image("/images/8-9.png");// 14
        oitonove.setImage(image16);
        Image image17 = new Image("/images/8-12.png");// 15
        oitodoze.setImage(image17);
        Image image18 = new Image("/images/9-11.png");// 16
        noveonze.setImage(image18);
        Image image19 = new Image("/images/13-14.png");// 17
        trezequatorze.setImage(image19);
    }
}