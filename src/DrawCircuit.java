import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class UpAndDownElementsPanel extends JPanel {
    private BufferedImage image;
    int length;
    String type;
    public UpAndDownElementsPanel(int l,String t) {
        type = t;
        length = l;
        try {
            image = ImageIO.read(new File(type+".jpeg"));
        }
        catch (IOException ex) {
            // handle exception...
        }
    }
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D graphics2D = (Graphics2D) g;
        Stroke stroke = new BasicStroke(2);
        graphics2D.setStroke(stroke);
        int xForLine=18;
        int heigthOfImage = 55;
        int widthOfImage=40;
        if (type.equals("up and down resistance")) {
            xForLine = 14;
            widthOfImage = 30;
        }
        if(type.equals("up and down capacitance"))
            xForLine=19;
        if (type.equals("up and down inductance")) {
            xForLine = 4;
            widthOfImage = 25;
        }
        if (type.equals("up voltage dc source")||type.equals("down voltage dc source")
                ||type.equals("up current dc source")||type.equals("down current dc source")
                ||type.equals("up controlled voltage source")||type.equals("down controlled voltage source")
                ||type.equals("up controlled current source")||type.equals("down controlled current source")
                ||type.equals("up and down ac source")) {
            xForLine = 20;
            heigthOfImage = 55;
        }
        if (type.equals("up diode")||type.equals("down diode")) {
            xForLine = 15;
            widthOfImage = 30;
        }

        graphics2D.drawLine(xForLine,0,xForLine,length);
        g.drawImage(image,0,length/2-heigthOfImage/2,widthOfImage,heigthOfImage,this);
    }
    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform tx = AffineTransform.getRotateInstance(0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(image, null), 0, 0, 100, 50, null);
        //////////////////////////////////////////////////////////
        //g.drawImage(image, 0, 0,150,100, this); // see javadoc for more info on the parameters
    }*/
}

class RightAndLeftElementsPanel extends JPanel {
    private BufferedImage image;
    int length;
    String type;
    public RightAndLeftElementsPanel(int l,String t) {
        type = t;
        length = l;
        try {
            image = ImageIO.read(new File(type+".jpg"));
        }
        catch (IOException ex) {
            // handle exception...
        }
    }
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D graphics2D = (Graphics2D) g;
        Stroke stroke = new BasicStroke(2);
        graphics2D.setStroke(stroke);
        int yForLine=18;
        int widthOfImage = 55;
        int heightOgImage = 40;
        if (type.equals("right and left resistance")) {
            yForLine = 14;
            heightOgImage = 30;
        }
        if(type.equals("right and left capacitance"))
            yForLine=19;
        if (type.equals("right and left inductance")) {
            yForLine = 4;
            heightOgImage =25;
        }
        if (type.equals("right voltage dc source")||type.equals("left voltage dc source")
                ||type.equals("right current dc source")||type.equals("left current dc source")
                ||type.equals("right controlled voltage source")||type.equals("left controlled voltage source")
                ||type.equals("right controlled current source")||type.equals("left controlled current source")
                ||type.equals("right and left ac source")) {
            yForLine = 20;
            widthOfImage = 55;
        }
        if (type.equals("right diode")||type.equals("left diode")) {
            yForLine = 15;
            heightOgImage = 30;
        }

        graphics2D.drawLine(0,yForLine,length,yForLine);
        g.drawImage(image,length/2-widthOfImage/2,0,widthOfImage,heightOgImage,this);
    }
}


class MyJPanel extends JPanel{
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D graphics2D = (Graphics2D) g;
        Stroke stroke = new BasicStroke(2);
        graphics2D.setStroke(stroke);
        g.drawRect(0,0,1060,985);
        g.setColor(Color.gray);
        for(int i=0;i<=5;i++)
            for(int j=0;j<=5;j++)
                graphics2D.fillOval(105+170*i,105+170*j,5,5);
    }
}

public class DrawCircuit {
    //ArrayList<Element> elements = new ArrayList<Element>(Circuit.allElements.values());
    static JFrame frame = new JFrame();
    public static void main(){
        frame.setTitle("Graphic drawing of the circuit");
        frame.setSize(1520,1033);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        Container container = frame.getContentPane();
        Component glassPane = frame.getGlassPane();
        JRootPane rootPane = frame.getRootPane();
        MyJPanel myJPanel = new MyJPanel();
        myJPanel.setBounds(0,0,1070,1000);
        container.setBackground(new Color(247, 247, 247));
        container.add(myJPanel);

        /*JPanel labelJPanel =new JPanel();
        JLabel label = new JLabel("the schematic of circuit");
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,30);
        label.setFont(font);
        labelJPanel.add(label);
        labelJPanel.setBounds(0,0,1060,50);
        labelJPanel.setBackground(Color.gray);
        container.add(labelJPanel);*/

        paintUpAndDownElements("up diode",105,105,170,"D1","30m");
        paintRightAndLeftElements("right current dc source",105,105,170*2,"D2","50m");
        paintUpAndDownElements("up and down inductance",105,105+170,170,"D2","50m");
        paintRightAndLeftElements("right and left resistance",105,105+170,170,"D2","50m");
        paintRightAndLeftElements("right and left capacitance",105+170,105+170,170,"D2","50m");
        paintRightAndLeftElements("right and left ac source",105,105+170*2,170,"D2","50m");
        paintRightAndLeftElements("right controlled voltage source",105+170,105+170*2,170,"D2","50m");
        paintUpAndDownElements("up and down capacitance",105+170,105+170,170,"D1","30m");
        paintUpAndDownElements("up voltage dc source",105+170*2,105,170,"D1","30m");
        paintUpAndDownElements("up controlled current source",105+170*2,105+170,170,"D1","30m");




        frame.setLayout(null);
        frame.setVisible(true);
    }
    static void paintUpAndDownElements(String typeOfElementInGraphics,int x,int y,int ertefa,String name,String value){
        UpAndDownElementsPanel r1 = new UpAndDownElementsPanel(ertefa,typeOfElementInGraphics);
        int xForText = x+40-19;
        if (typeOfElementInGraphics.equals("up and down resistance")) {
            x -= 14;
            xForText -= 5;
        }
        if(typeOfElementInGraphics.equals("up and down capacitance"))
            x-=19;
        if (typeOfElementInGraphics.equals("up and down inductance")) {
            x -= 4;
        }
            if (typeOfElementInGraphics.equals("up voltage dc source")||typeOfElementInGraphics.equals("down voltage dc source")
                ||typeOfElementInGraphics.equals("up current dc source")||typeOfElementInGraphics.equals("down current dc source")
                ||typeOfElementInGraphics.equals("up controlled voltage source")||typeOfElementInGraphics.equals("down controlled voltage source")
                ||typeOfElementInGraphics.equals("up controlled current source")||typeOfElementInGraphics.equals("down controlled current source")
                ||typeOfElementInGraphics.equals("up and down ac source"))
            x-= 20;
        if (typeOfElementInGraphics.equals("up diode")||typeOfElementInGraphics.equals("down diode")) {
            x -= 15;
            xForText -=6;
        }
            r1.setBounds(x,y,50,ertefa);
        frame.getContentPane().add(r1);

        JLabel nameInGraphic = new JLabel(name);
        nameInGraphic.setBackground(new Color(247, 247, 247));
        JPanel forName  = new JPanel();
        forName.setBounds(xForText,ertefa/2-20+y,30,30);
        forName.add(nameInGraphic);
        forName.setBackground(new Color(247, 247, 247));
        frame.getContentPane().add(forName);

        JLabel valueInGraphic = new JLabel(value);
        valueInGraphic.setBackground(new Color(247, 247, 247));
        JPanel forValue  = new JPanel();
        forValue.setBounds(xForText,ertefa/2+10+y,30,30);
        forValue.add(valueInGraphic);
        forValue.setBackground(new Color(247, 247, 247));
        frame.getContentPane().add(forValue);
    }
    static void paintRightAndLeftElements(String typeOfElementInGraphics,int x,int y,int tool,String name,String value){
        RightAndLeftElementsPanel r1 = new RightAndLeftElementsPanel(tool,typeOfElementInGraphics);
        int yForText=y+40-19;
        if (typeOfElementInGraphics.equals("right and left resistance")) {
            y -= 14;
            yForText-=5;
        }
        if(typeOfElementInGraphics.equals("right and left capacitance"))
            y-=19;
        if (typeOfElementInGraphics.equals("right and left inductance")) {
            y -= 4;
        }
        if (typeOfElementInGraphics.equals("right voltage dc source")||typeOfElementInGraphics.equals("left voltage dc source")
                ||typeOfElementInGraphics.equals("right current dc source")||typeOfElementInGraphics.equals("left current dc source")
                ||typeOfElementInGraphics.equals("right controlled voltage source")||typeOfElementInGraphics.equals("left controlled voltage source")
                ||typeOfElementInGraphics.equals("right controlled current source")||typeOfElementInGraphics.equals("left controlled current source")
                ||typeOfElementInGraphics.equals("right and left ac source"))
            y-= 20;
        if (typeOfElementInGraphics.equals("right diode")||typeOfElementInGraphics.equals("left diode")){
            y-=15;
            yForText-=6;
        }
        r1.setBounds(x,y,tool,40);
        frame.getContentPane().add(r1);

        JLabel nameInGraphic = new JLabel(name);
        nameInGraphic.setBackground(new Color(247, 247, 247));
        JPanel forName  = new JPanel();
        forName.setBounds(tool/2-20+x,yForText,30,30);
        forName.add(nameInGraphic);
        forName.setBackground(new Color(247, 247, 247));
        frame.getContentPane().add(forName);

        JLabel valueInGraphic = new JLabel(value);
        valueInGraphic.setBackground(new Color(247, 247, 247));
        JPanel forValue  = new JPanel();
        forValue.setBounds(tool/2+10+x,yForText,30,30);
        forValue.add(valueInGraphic);
        forValue.setBackground(new Color(247, 247, 247));
        frame.getContentPane().add(forValue);
    }
}
