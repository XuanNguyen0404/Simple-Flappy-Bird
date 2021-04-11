
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
public class BackGround extends Rectangle{
    String image;

    public BackGround(String image, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.image = image;
    }
    
    public void drawBackGround(Graphics g){
        ImageIcon icon = new ImageIcon(image);
        Image img = icon.getImage();
        g.drawImage(img, x, y, width, height, Mainframe.p);
    }
}
