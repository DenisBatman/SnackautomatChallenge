package ch.noseryoung.blj;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ProductSort {
    public BufferedImage image;
    public static final int MAX_AMOUNT_OF_PRODUCTS = 10;
    public ArrayList<Product> products = new ArrayList<Product>();
    private final String name;
    private int numberOfProducts;
    private double price;
    private final int SIZE = 80;
    public int x;
    public int y;

    public ProductSort(String name, double price){
        numberOfProducts = 0;
        this.name = name;
        this.price = price;
        this.numberOfProducts = 0;
        for(int i = 0; MAX_AMOUNT_OF_PRODUCTS > i; i++){
            assert products != null;
            products.add(new Product(name));
            numberOfProducts++;
        }
        this.price = price;
        this.image = getImage("/products/" + name);
    }

    public void fillStock(){
        if(products.size() < MAX_AMOUNT_OF_PRODUCTS * 0.3){
            for(int i = products.size(); i < MAX_AMOUNT_OF_PRODUCTS; i++){
                products.add(new Product(name));
            }
        }
    }
    public void draw(Graphics2D graphics2D, int x, int y) {
        this.x = x + (SIZE/2);
        this.y = y + (SIZE/2);
        if(image != null){
            graphics2D.drawImage(image, x, y, SIZE, SIZE ,null);
        } else {
            graphics2D.setFont(new Font("Century Gothic", Font.PLAIN, 45));
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(name, x, y);
        }

    }
    public BufferedImage getImage (String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        return image;
    }

    public BufferedImage getImage() { return image; }

    public int getNumberOfProducts(){return numberOfProducts;}
    public void setNumberOfProducts(int numberOfProducts){this.numberOfProducts = numberOfProducts;}
    public String getName(){return name;}
    public double getPrice() {return price;}
    public void setPrice(double price){this.price = price;}
}