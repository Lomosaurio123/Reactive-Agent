package agentes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Agente extends Thread{
    String nombre;
    int i;
    int j;
    ImageIcon icon;
    ImageIcon mother;
    int[][] matrix;
    JLabel tablero[][];
    int collected;
    int objective;
    int deliver;
    Agente partner;
    Map<Integer,int[]> houses = new HashMap<>();
    
    JLabel casillaAnterior;
    int previus_row;
    int previus_col;
    Random aleatorio = new Random(System.currentTimeMillis());
    
    public Agente(String nombre, ImageIcon icon, int[][] matrix, JLabel tablero[][], ImageIcon mother){
        this.nombre = nombre;
        this.icon = icon;
        this.matrix = matrix;
        this.tablero = tablero;

        this.i = aleatorio.nextInt(matrix.length);
        this.j = aleatorio.nextInt(matrix.length);
        tablero[i][j].setIcon(icon);    
        this.mother = mother;    
    }

    //Funcion para retornar a casa
    public void returnToHouse() {
        //ver cual es la casa mas cercana al punto donde estamos 
        System.out.println("Entre aqui");
        
        int next_move_row = 0;
        int next_move_col = 0;

        while(true){

            casillaAnterior = tablero[i][j];
            previus_row = i;
            previus_col = j;

            //Obtener el movimiento aleatorio evitando las diagonales 
            int flag = 1;
            while(flag == 1) {
                next_move_row = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                next_move_col = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                if( ( next_move_col == 0 && ( next_move_row == 1 || next_move_row == -1 ) ) || ( next_move_row == 0 && ( next_move_col == 1 || next_move_col == -1 ) )) {
                    flag = 0;
                }
            }

            //Evitamos que se salga de los boredes o intente cruzar obstaculos y pokebolas
            if( ( i > matrix.length-2 || matrix[i+1][j] == 1 || matrix[i+1][j] == 2 ) && next_move_row == 1 ) {
                next_move_row = -1;
                next_move_col = 0;
            }

            if( ( i < 1 || matrix[i-1][j] == 1 || matrix[i-1][j] == 2 ) && next_move_row == -1 ){
                next_move_row = 1;
                next_move_col = 0;
            }
            
            if( ( j > matrix.length-2 || matrix[i][j+1] == 1 || matrix[i][j+1] == 2 ) && next_move_col == 1 ) {
                next_move_col = -1;
                next_move_row = 0;
            }

            if( ( j < 1 || matrix[i][j-1] == 1 || matrix[i][j-1] == 2 ) && next_move_col == -1 ) {
                next_move_col = 1;
                next_move_row = 0;
            }

            //Caso donde este entre dos obstaculos verticalmente lejos de la orilla 
            if( ( i < matrix.length - 1 && i > 1 ) && ( (matrix[i+1][j] == 1 && matrix[i-1][j] == 1) || (matrix[i+1][j] == 2 && matrix[i-1][j] == 2) ) ) next_move_row = 0;
            
            //Caso donde este entre dos obstaculos horizontalmente o cerca de la orilla 
            if( ( j < matrix.length - 1 && j > 1 ) && ( (matrix[i][j+1] == 1 && matrix[i][j-1] == 1) || (matrix[i][j+1] == 2 && matrix[i][j-1] == 2) ) ) next_move_col = 0;

            //Caso en el que es obstaculo frontera
            if( ( i == matrix.length -1 && ( matrix[i-1][j] == 1 || matrix[i-1][j] == 2 ) ) || ( i == 0 && ( matrix[i+1][j] == 1 || matrix[i+1][j] == 2 ) ) ) next_move_row = 0;
            if( ( j == matrix.length -1 && ( matrix[i][j-1] == 1 || matrix[i][j-1] == 2 ) ) || ( j == 0 && ( matrix[i][j+1] == 1 || matrix[i][j+1] == 2 ) ) ) next_move_col = 0;

            //Realizamos los movimientos
            i += next_move_row;
            j += next_move_col;

            //Encontrar casa
            if( matrix[i][j] == 3 ) {
                actualizarPosicion();
                break;
            } else {
                actualizarPosicion();
            }
    
            try{
               sleep(50+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }            
        
        //actualizamos el delivery 
        deliver ++;
        collected --;
        System.out.println("Termine de entregar");
        return;

    }

    //Funcion principal
    public void run(){

        int next_move_row = 0;
        int next_move_col = 0;

        while(true){

            casillaAnterior = tablero[i][j];
            previus_row = i;
            previus_col = j;

            //Obtener el movimiento aleatorio evitando las diagonales 
            int flag = 1;
            while(flag == 1) {
                next_move_row = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                next_move_col = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                if( ( next_move_col == 0 && ( next_move_row == 1 || next_move_row == -1 ) ) || ( next_move_row == 0 && ( next_move_col == 1 || next_move_col == -1 ) )) {
                    flag = 0;
                }
            }

            //Evitamos que se salga de los boredes o intente cruzar obstaculos
            if( ( i > matrix.length-2 || matrix[i+1][j] == 1 ) && next_move_row == 1 ) {
                next_move_row = -1;
                next_move_col = 0;
            }

            if( ( i < 1 || matrix[i-1][j] == 1 ) && next_move_row == -1 ){
                next_move_row = 1;
                next_move_col = 0;
            }
            
            if( ( j > matrix.length-2 || matrix[i][j+1] == 1 ) && next_move_col == 1 ) {
                next_move_col = -1;
                next_move_row = 0;
            }

            if( ( j < 1 || matrix[i][j-1] == 1 ) && next_move_col == -1 ) {
                next_move_col = 1;
                next_move_row = 0;
            }

            //Caso donde este entre dos obstaculos verticalmente lejos de la orilla 
            if( ( i < matrix.length - 1 && i > 1 ) && (matrix[i+1][j] == 1 && matrix[i-1][j] == 1) ) next_move_row = 0;
            
            //Caso donde este entre dos obstaculos horizontalmente o cerca de la orilla 
            if( ( j < matrix.length - 1 && j > 1 ) && (matrix[i][j+1] == 1 && matrix[i][j-1] == 1)  ) next_move_col = 0;

            //Caso en el que es obstaculo frontera
            if( ( i == matrix.length -1 && matrix[i-1][j] == 1 ) || ( i == 0 && matrix[i+1][j] == 1 ) ) next_move_row = 0;
            if( ( j == matrix.length -1 && matrix[i][j-1] == 1 ) || ( j == 0 && matrix[i][j+1] == 1 ) ) next_move_col = 0;

            //Realizamos los movimientos
            i += next_move_row;
            j += next_move_col;

            //Atrapar las pockebolas 
            if( matrix[i][j] == 2 ) {
                collected++;
                matrix[i][j] = 0;
                actualizarPosicion();
                returnToHouse();
            } else {
                actualizarPosicion();
            }


            //Si ambos terminan de entregar todas se termina
            if( (deliver + partner.deliver) == objective && collected == 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Se han juntado todas las pokebolas", "Finalizo el juego", JOptionPane.WARNING_MESSAGE);
                break;
            } 
    
            try{
               sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }            
    }

    public synchronized void actualizarPosicion(){
        casillaAnterior.setIcon(null); // Elimina su figura de la casilla anterior
        //Esto nos ayuda a que cuando este en estado de recoleccion vaya directo a la base mas cercana
        if( matrix[previus_row][previus_col] == 3 ) {
            casillaAnterior.setIcon(mother); 
        }
        tablero[i][j].setIcon(icon); // Pone su figura en la nueva casilla
        // System.out.println(nombre + " fila:" + i + " Columna:"+ j);
    }
}