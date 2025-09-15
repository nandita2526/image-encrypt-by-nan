import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageEncryptDecrypt {

    // Encrypt the image by adding key to each pixel value
    public static void encrypt(String inputPath, String outputPath, int key) {
        try {
            File inputFile = new File(inputPath);
            BufferedImage img = ImageIO.read(inputFile);

            int width = img.getWidth();
            int height = img.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int p = img.getRGB(x, y);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    // Simple encryption by adding key
                    r = (r + key) % 256;
                    g = (g + key) % 256;
                    b = (b + key) % 256;

                    // Set new RGB
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    img.setRGB(x, y, p);
                }
            }

            File outputFile = new File(outputPath);
            ImageIO.write(img, "png", outputFile);
            System.out.println("Image encrypted and saved at: " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Decrypt the image by subtracting key from each pixel value
    public static void decrypt(String inputPath, String outputPath, int key) {
        try {
            File inputFile = new File(inputPath);
            BufferedImage img = ImageIO.read(inputFile);

            int width = img.getWidth();
            int height = img.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int p = img.getRGB(x, y);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    // Simple decryption by subtracting key
                    r = (r - key + 256) % 256;
                    g = (g - key + 256) % 256;
                    b = (b - key + 256) % 256;

                    // Set new RGB
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    img.setRGB(x, y, p);
                }
            }

            File outputFile = new File(outputPath);
            ImageIO.write(img, "png", outputFile);
            System.out.println("Image decrypted and saved at: " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        int key = 50; // encryption key

        // Run with your own image paths
        encrypt("input.jpg", "encrypted.png", key);
        decrypt("encrypted.png", "decrypted.png", key);
    }
}
