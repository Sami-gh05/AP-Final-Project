package product;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public abstract class Product {
    protected String productCode;
    private String title;
    private double price;
    private int rate, ratersNum;
    protected String label;
    private ImageIcon imageIcon;
    private byte[] image;
    private JLabel imageLabel;

    public Product(String title, double price){
        this.title = title;
        this.price = price;
        this.rate = 1;
        this.productCode = "#";
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){ // if the user has access as the admin
        this.title = title;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(float price){ // if the user has access as the admin
        this.price = price;
    }
    public int getRate(){
        return this.rate;
    }

    public String getLabel() {
        return label;
    }

    public String getProductCode() {
        return productCode;
    }

    public void updateRate(int rate){ // users can rate each product
        this.rate = ((this.rate * ratersNum) + rate) / ++ratersNum;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    /*public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }*/

    public void setImageLabel(JLabel imageLabel){
        this.imageLabel = imageLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }


    public void fillIconWithByte(){
        this.imageIcon = new ImageIcon(byteArrayToImage(this.image));
    }
    public void fillByteWithIcon(){
        try {
            this.image = imageIconToByteArray(this.imageIcon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private BufferedImage byteArrayToImage(byte[] imageData) {
        try {
            InputStream is = new ByteArrayInputStream(imageData);
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private byte[] imageIconToByteArray(ImageIcon icon) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

        icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }
}