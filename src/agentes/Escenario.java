package agentes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class Escenario extends JFrame{
    private JLabel[][] tablero;     
    private int[][] matrix;
    private final int dim = 12;
    private int number_pokeballs;

    private ImageIcon robot1;
    private ImageIcon robot2;
    private ImageIcon obstacleIcon;
    private ImageIcon sampleIcon;
    private ImageIcon actualIcon;
    private ImageIcon motherIcon;
    
    private Agente Bruno;
    private Agente Red;
   
    private final BackGroundPanel fondo = new BackGroundPanel(new ImageIcon("imagenes/surface.jpg"));
    
    private final JMenu settings = new JMenu("Objetos");
    private final JRadioButtonMenuItem obstacle = new JRadioButtonMenuItem("Obstaculo");
    private final JRadioButtonMenuItem sample = new JRadioButtonMenuItem("Pokeball");
    private final JRadioButtonMenuItem motherShip = new JRadioButtonMenuItem("Casa");
    
    public Escenario(){
        this.setContentPane(fondo);
        this.setTitle("Agentes Reactivos");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(50,50,dim*50+35,dim*50+85);
        initComponents();
    }
        
    private void initComponents(){
        ButtonGroup settingsOptions = new ButtonGroup();
        settingsOptions.add(sample);
        settingsOptions.add(obstacle);       
        settingsOptions.add(motherShip);
        
        JMenuBar barraMenus = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem run  = new JMenuItem("Iniciar");
        
        JMenuItem exit   = new JMenuItem("Salir");
              
        this.setJMenuBar(barraMenus);
        barraMenus.add(file);
        barraMenus.add(settings);
        file.add(run);
        file.add(exit);
        settings.add(motherShip);
        settings.add(obstacle);
        settings.add(sample);
            
        robot1 = new ImageIcon("imagenes/Bruno.png");
        robot1 = new ImageIcon(robot1.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        robot2 = new ImageIcon("imagenes/Red.png");
        robot2 = new ImageIcon(robot2.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        obstacleIcon = new ImageIcon("imagenes/rock.png");
        obstacleIcon = new ImageIcon(obstacleIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        sampleIcon = new ImageIcon("imagenes/pokeball.png");
        sampleIcon = new ImageIcon(sampleIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        motherIcon = new ImageIcon("imagenes/home.png");
        motherIcon = new ImageIcon(motherIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        this.setLayout(null);
        formaPlano();  
        
        exit.addActionListener(evt -> gestionaSalir(evt));
        run.addActionListener(evt -> gestionaRun(evt));
        obstacle.addItemListener(evt -> gestionaObstacle(evt));
        sample.addItemListener(evt -> gestionaSample(evt));
        motherShip.addItemListener(evt -> gestionaMotherShip(evt));

        class MyWindowAdapter extends WindowAdapter{
            public void windowClosing(WindowEvent eventObject){
		goodBye();
            }
        }
        addWindowListener(new MyWindowAdapter());
        
        // Crea 2 agentes
        Bruno = new Agente("Bruno",robot1, matrix, tablero, motherIcon); 
        Red = new Agente("Red",robot2, matrix, tablero, motherIcon); 
    }
        
    private void gestionaSalir(ActionEvent eventObject){
        goodBye();
    }
        
    private void goodBye(){
        int respuesta = JOptionPane.showConfirmDialog(rootPane, "Desea salir?","Aviso",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.YES_OPTION) System.exit(0);
    }
  
    private void formaPlano(){
        tablero = new JLabel[dim][dim];
        matrix = new int[dim][dim];

        for(int i=0;i<dim;i++)
            for(int j=0;j<dim;j++){
                int row = i;
                int col = j;
                matrix[i][j]=0;
                tablero[i][j]=new JLabel();
                tablero[i][j].setBounds(j*50+10,i*50+10,50,50);
                tablero[i][j].setBorder(BorderFactory.createDashedBorder(Color.black));
                tablero[i][j].setOpaque(false);
                this.add(tablero[i][j]);
            
                tablero[i][j].addMouseListener(new MouseAdapter(){ // Este listener nos ayuda a agregar poner objetos en la rejilla
                    @Override
                    public void mousePressed(MouseEvent e){
                        insertaObjeto(e);
                        if(actualIcon == obstacleIcon) matrix[row][col] = 1;
                        else if(actualIcon == sampleIcon) {
                            matrix[row][col] = 2;
                            number_pokeballs ++;
                            Bruno.objective = number_pokeballs;
                            Red.objective = number_pokeballs;
                        }
                        else if(actualIcon == motherIcon) matrix[row][col] = 3;
                    }   
                /* 
                    @Override
                    public void mouseReleased(MouseEvent e){
                        insertaObjeto(e);
                    }     */
                });           
            }
    }
        
    private void gestionaObstacle(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = obstacleIcon;
        else actualIcon = null;        
    }
    
    private void gestionaSample(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = sampleIcon;
        else actualIcon = null;   
    }
    
    private void gestionaMotherShip(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        if(opt.isSelected())
           actualIcon = motherIcon;
        else actualIcon = null;   
    }
    private void gestionaRun(ActionEvent eventObject){
        if(!Bruno.isAlive()) Bruno.start();
        if(!Red.isAlive()) Red.start();
        settings.setEnabled(false);
    }
       
    public void insertaObjeto(MouseEvent e){
        JLabel casilla = (JLabel) e.getSource();
        if(actualIcon!=null) casilla.setIcon(actualIcon); 
    }
}