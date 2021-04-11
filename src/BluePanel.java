
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tienx
 */
public class BluePanel extends javax.swing.JPanel {

    /**
     * Creates new form BluePanel
     */
    // dat = 580px
    // khoang cach up va down = 120px
    // khoang cach 2 pipe = 500px
    // x =200 bat dau xuat hien
    BackGround bg1;
    Graphics g = this.getGraphics();
    Pipe[] pipes = new Pipe[6];
    boolean gameOver = false;
    boolean jump = false;
    boolean isFreeze = false;
    int highest, score =0;
    Bird bird;
    
    
    public BluePanel() {
        init(); 
        initComponents();
        lblGameOver.setVisible(false);
        lblGameOver.setBackground(new Color(0,0,0,0));
        lblScore.setBackground(new Color(0,0,0,0));
        movePipe.start();
        moveBird.start();
        
    }
    
    void init(){
        
        bg1 = new BackGround("background.png", 0, 0, this.getWidth(), this.getHeight());
        bird = new Bird("bird.png", 200, 300, 65, 65);
        pipes[0] = new Pipe("pipeDown.png");
        pipes[1] = new Pipe("pipeUp.png");
        pipes[2] = new Pipe("pipeDown.png");
        pipes[3] = new Pipe("pipeUp.png");
        pipes[4] = new Pipe("pipeDown.png");
        pipes[5] = new Pipe("pipeUp.png");
        createPipe(pipes[0], pipes[1]);
        
        

    }
    
    void draw(Graphics g){
        ImageIcon icon = new ImageIcon("background.png");
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
        for (Pipe pipe : pipes) {
            pipe.drawPipe(g);
        }
        bird.drawBird(g);
    }
    
    void createPipe(Pipe pipe1, Pipe pipe2){
        Random random = new Random();
        int height = random.nextInt((380-100)+1)+100;
        
        pipe1.x=800;
        pipe1.height = height;
        pipe1.y=580-height;
        pipe1.width = 110;
        
        pipe2.x=800;
        pipe2.y=0;
        pipe2.height=580-height-150;
        pipe2.width=110;
            
    }
    
    void freeze(){
        isFreeze = true;
        if(gameOver)
            lblGameOver.setVisible(true);
        try {
            movePipe.suspend();
            moveBird.suspend();        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    void continueGame(){
        try {
            
            moveBird.resume();
            movePipe.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        isFreeze = false;
        if(gameOver){
            gameOver = false;
            lblGameOver.setVisible(false);
            lblScore.setText("0");
            score = 0;
            for (Pipe pipe : pipes) {
                pipe.x = -500;
            }
            createPipe(pipes[0], pipes[1]);
            bird.y = 300;
        }
    }
    

    
    // tao thread de dich chuyen pipe ve ben trai
    Thread movePipe = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                // di chuyen qua trai
               for (Pipe pipe : pipes) {
                   pipe.x -= 1;                                
               }               
               repaint();
               
               // tao moi pipe
               if(pipes[0].x == 400){
                   createPipe(pipes[2], pipes[3]);
               }
               if(pipes[2].x==400){
                   createPipe(pipes[4], pipes[5]);
               }
               if(pipes[4].x==400){
                   createPipe(pipes[0], pipes[1]);
               }
               
               if(pipes[0].x==100){
                   score +=1;
                   lblScore.setText(""+score); 
               }
               if(pipes[2].x==100){
                   score +=1;
                   lblScore.setText(""+score); 
               }
               if(pipes[4].x==100){
                   score +=1;
                   lblScore.setText(""+score); 
               }
                              
                try {
                   Thread.sleep(5);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        }
    } );

    Thread moveBird = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                
                    if(bird.y < highest) jump = false;
                    if(jump == false){
                        bird.y +=1;
                    }
                    else{
                        bird.y-=3;
                    }
                
                for (Pipe pipe : pipes) {
                    if( pipe.intersects(bird) || bird.y <= 0|| bird.y >= 520)
                    {
                        gameOver = true;
                        freeze();                       
                    }                  
                }
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    });
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        draw(g);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblScore = new javax.swing.JLabel();
        lblGameOver = new javax.swing.JLabel();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lblScore.setFont(new java.awt.Font("Showcard Gothic", 0, 60)); // NOI18N
        lblScore.setForeground(new java.awt.Color(255, 255, 255));
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblScore.setText("0");

        lblGameOver.setFont(new java.awt.Font("Tahoma", 0, 75)); // NOI18N
        lblGameOver.setForeground(new java.awt.Color(255, 255, 255));
        lblGameOver.setText("Game Over");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblScore)
                .addGap(400, 400, 400))
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(lblGameOver)
                .addContainerGap(236, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblScore)
                .addGap(35, 35, 35)
                .addComponent(lblGameOver)
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblGameOver;
    private javax.swing.JLabel lblScore;
    // End of variables declaration//GEN-END:variables
}
