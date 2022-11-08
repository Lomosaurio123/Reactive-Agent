package agentes;

import java.util.HashMap;
import java.util.Random;

import javax.lang.model.util.ElementScanner14;
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
    ImageIcon migaja;
    HashMap < Integer, ImageIcon > cluster = new HashMap<>();
    int[][] matrix;
    JLabel tablero[][];
    int collected;
    int objective;
    int deliver;
    Agente partner;
    int xHouseIndex;
    int yHouseIndex;
    Boolean return_house = false;
    
    JLabel casillaAnterior;
    int previus_row;
    int previus_col;
    Random aleatorio = new Random(System.currentTimeMillis());
    
    public Agente(String nombre, ImageIcon icon, int[][] matrix, JLabel tablero[][], ImageIcon mother, ImageIcon migaja,  HashMap < Integer, ImageIcon > cluster){
        this.nombre = nombre;
        this.icon = icon;
        this.matrix = matrix;
        this.tablero = tablero;

        this.i = aleatorio.nextInt(matrix.length);
        this.j = aleatorio.nextInt(matrix.length);
        tablero[i][j].setIcon(icon);    
        this.mother = mother;    
        this.migaja = migaja;
        this.cluster = cluster;
    }

    //Funcion para retornar a casa
    public void returnToHouse() {
        
        return_house = true;

        while( true ) {

            int yDistance = yHouseIndex - i;
            int xDistance = xHouseIndex - j;
            
            //BaseCase
            if( yDistance == 0 && xDistance == 0 ) {
                matrix[i][j] = 2;
                break;
            }

            while( (yHouseIndex - i) > 0 ) {

                casillaAnterior = tablero[i][j];
                //Verificar que el siguiente movimiento no choque con otra pokebola u obstaculo
                if( matrix[i+1][j] == 0 || matrix[i+1][j] == 2 || matrix[i+1][j] == 4 ) {

                    previus_row = i;
                    previus_col = j;
                    i += 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                //Cuando se encuentra con un obstaculo o pokebola rompe
                else {
                    break;
                }

            }

            while( (xHouseIndex - j) > 0 ) {

                casillaAnterior = tablero[i][j];
                //Verificar que el siguiente movimiento no choque con otra pokebola u obstaculo
                if(  matrix[i][j+1] == 0 || matrix[i][j+1] == 2 || matrix[i][j+1] == 4 ) {
                    
                    previus_row = i;
                    previus_col = j;
                    j += 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                //Cuando se encuentra con un obstaculo o pokebola rompe
                else {
                    break;
                }

            }

            while( (yHouseIndex - i) < 0 ) {

                casillaAnterior = tablero[i][j];
                //Verificar que el siguiente movimiento no choque con otra pokebola u obstaculo
                if( matrix[i-1][j] == 0 || matrix[i-1][j] == 2 || matrix[i-1][j] == 4 ) {

                    previus_row = i;
                    previus_col = j;
                    i -= 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                //Cuando se encuentra con un obstaculo o pokebola rompe
                else {
                    break;
                }

            }

            while( (xHouseIndex - j) < 0 ) {

                casillaAnterior = tablero[i][j];
                //Verificar que el siguiente movimiento no choque con otra pokebola u obstaculo
                if( matrix[i][j-1] == 0 || matrix[i][j-1] == 2 || matrix[i][j-1] == 4 ) {
                    
                    previus_row = i;
                    previus_col = j;
                    j -= 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                //Cuando se encuentra con un obstaculo o pokebola rompe
                else {
                    break;
                }

            }

        }

        deliver ++;
        collected --;
        System.out.println("Se termino la entrega");

    }

    //Función para recoger migajas

    public void follow() {

        matrix[i][j] = 0;

        int last_movement = 0;

        while( true ) {

            casillaAnterior = tablero[i][j];
            previus_row = i;
            previus_col = j;

            //Arriba
            if( matrix[i - 1][j] == 4 ) {

                i--;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 1;

                try{
                    sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }
                
            }
            
            //Abajo
            else if( matrix[i + 1][j] == 4 ) {

                i++;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 2;

                try{
                    sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }

            }

            //Derecha
            else if( matrix[i][j + 1] == 4 ) {

                j++;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 3;

                try{
                    sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }

            }

            //Izquierda
            else if( matrix[i][j - 1] == 4 ) {

                j--;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 4;

                try{
                    sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }
                
            }

            else {

                if( last_movement == 1 ) {
                    i--;
                    break;
                }

                else if( last_movement == 2 ) {
                    i++;
                    break;
                }

                else if( last_movement == 3 ) {
                    j++;
                    break;
                }

                else if( last_movement == 4 ) {
                    j--;
                    break;
                }

            }

        }

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

            //Si encuentra migaja ejecutar el seguir migajas
            if( matrix[i][j] == 4 ) {
 
                actualizarPosicion();
                follow();

            }

            //Atrapar las pockebolas 
            if( matrix[i][j] % 3 == 0 && matrix[i][j] > 0 ) {
                System.out.println( matrix[i][j] );
                collected++;
                matrix[i][j] -= 3;
                actualizarPosicion();
                returnToHouse();
                return_house = false;
            } 

            else {
                actualizarPosicion();
            }


            //Si ambos terminan de entregar todas se termina
            if( (deliver  + partner.deliver )  == objective && collected == 0) {
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
        //Colocar las migajas 
        if( return_house ) {

            casillaAnterior.setIcon(migaja);

        }

        //Esto nos ayuda a que cuando este en estado de recoleccion vaya directo a la base mas cercana
        if( matrix[previus_row][previus_col] == 2 ) {

            casillaAnterior.setIcon( mother ); 

        }

        //No quitar el cluster hasta que se completen las 4 colecciones
        if( matrix[previus_row][previus_col] % 3 == 0 && matrix[previus_row][previus_col] > 0 ) {
            
            Integer matrix_value = matrix[previus_row][previus_col];
            casillaAnterior.setIcon( cluster.get( matrix_value ) ); 

        }

        tablero[i][j].setIcon(icon); // Pone su figura en la nueva casilla

    }
}