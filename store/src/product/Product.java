package product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

public abstract class Product implements Serializable {
    protected String productCode;
    private String title;
    private double price;
    private int rate, ratersNum;
    protected String label;
    private byte[] image;
    private ImageIcon imageIcon;

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
        return this.getRate();
    }
    public void updateRate(int rate){ // users can rate each product
        this.rate = (this.rate * ratersNum) + rate / ++ratersNum;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ImageIcon getImageIcon() {
        if(this.getImage() != null){
            this.imageIcon = new ImageIcon(byteArrayToImage(getImage()));
        }

        return imageIcon;
    }

    private BufferedImage byteArrayToImage(byte[] imageData) {
        try {
            return ImageIO.read(new ByteArrayInputStream(imageData));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
