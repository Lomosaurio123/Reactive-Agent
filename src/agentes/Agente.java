package agentes;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Agente extends Thread{
    String nombre;
    int i;
    int j;
    ImageIcon icon;
    int[][] matrix;
    JLabel tablero[][];
    
    JLabel casillaAnterior;
    Random aleatorio = new Random(System.currentTimeMillis());
    
    public Agente(String nombre, ImageIcon icon, int[][] matrix, JLabel tablero[][]){
        this.nombre = nombre;
        this.icon = icon;
        this.matrix = matrix;
        this.tablero = tablero;

        this.i = aleatorio.nextInt(matrix.length);
        this.j = aleatorio.nextInt(matrix.length);
        tablero[i][j].setIcon(icon);        
    }

    
    public void run(){

        int next_move_row = 0;
        int next_move_col = 0;

        while(true){

            casillaAnterior = tablero[i][j];
            //Obtener el movimiento aleatorio evitando las diagonales 
            int flag = 1;
            while(flag == 1) {
                next_move_row = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                next_move_col = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                if( ( next_move_col == 0 && ( next_move_row == 1 || next_move_row == -1 ) ) || ( next_move_row == 0 && ( next_move_col == 1 || next_move_col == -1 ) )) {
                    flag = 0;
                }
            }

            //Evitamos que se salga de los boredes  
            if( i > matrix.length-2 && next_move_row == 1) {
                next_move_row = -1;
                next_move_col = 0;
            }
            if( i < 1 && next_move_row == -1 ){
                next_move_row = 1;
                next_move_col = 0;
            }
            if( j > matrix.length-2 && next_move_col == 1) {
                next_move_col = -1;
                next_move_row = 0;
            }

            if( j < 1 && next_move_col == -1 ) {
                next_move_col = 1;
                next_move_row = 0;
            }
    
            //Realizamos los movimientos
            i += next_move_row;
            j += next_move_col;

            actualizarPosicion();
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
        tablero[i][j].setIcon(icon); // Pone su figura en la nueva casilla
        System.out.println(nombre + " fila:" + i + " Columna:"+ j);       
    }
}