import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }

  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  // for each loops are not supported but this code should run if it did

  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }

  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
      for (Pixel pixelObj : rowArray)
      {
        double average = ((pixelObj.getGreen()) + (pixelObj.getBlue()) + (pixelObj.getRed())) / 3.0;
        pixelObj.setRed((int) average);
        pixelObj.setGreen((int) average);
        pixelObj.setBlue((int) average);
      }
  }

  public void fixUnderwater()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (Pixel[] rowArray : pixels)
          for (Pixel pixelObj : rowArray)
          {
              pixelObj.setRed(pixelObj.getRed()*5);
          }
  }

  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }


    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - col - 1];
                leftPixel.setColor(rightPixel.getColor()); //take the left pixl and change it to color of corresponding right one
            }
        }
    }

    public void mirrorHorizontal() //top to bottom
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int length = pixels.length;
        for (int row = 0; row < length / 2; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[length-row-1][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int length = pixels.length;
        for (int row = 0; row < length / 2; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[length-row-1][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        }
    }

    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < row; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[height-row-1][width-col-1];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
    }
    System.out.println(count);
  }

    public void mirrorArms() {
        int mirrorPoint = 195;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        //Left arm!!
        for (int row = 152; row < 195; row++) {
            for (int col = 105; col < 170; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }

        int mirrorPoint2 = 198;
        Pixel topPixel2 = null;
        Pixel bottomPixel2 = null;

        // Right arm!!
        for (int row = 171; row < mirrorPoint2; row++)
        {
            for (int col = 239; col < 294; col++)
            {
                topPixel2 = pixels[row][col];
                bottomPixel2 = pixels[mirrorPoint2 - row + mirrorPoint2][col];
                bottomPixel2.setColor(topPixel2.getColor());
            }
        }
    }

    public void mirrorGull() {
        int mirrorPoint = 364;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 228; row < 340; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 228; col < mirrorPoint; col++) {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row]
                        [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

    public void copy2(Picture fromPic, int fromStartRow, int fromEndRow, int fromStartCol, int fromEndCol, int toStartRow, int toStartCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = fromStartRow, toRow = toStartRow;
             fromRow < fromEndRow &&
                     toRow < toPixels.length;
             fromRow++, toRow++)
        {
            for (int fromCol = fromStartCol, toCol = toStartCol;
                 fromCol < fromEndCol &&
                         toCol < toPixels[0].length;
                 fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  public void myCollage()
  {
      Picture gull = new Picture("seagull.jpg");
      Picture flower2 = new Picture("flower2.jpg");
      Picture temple = new Picture ("temple.jpg");
      this.copy2(gull,300,450,350,450,200,0);
      this.copy2(flower2, 0,100,0,100,0,0);
      this.copy2(temple, 200,350,300,400,300,200);
      this.fixUnderwater();
      this.negate();
      this.zeroBlue();
      this.mirrorDiagonal();

  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }

  public int getCountRedOverValue(int param)
  {
      int count = 0;
      Pixel[][] pixels = this.getPixels2D();
      for (Pixel[] rowArray : pixels)
      {
          for (Pixel pixelObj : rowArray)
          {
              int red = pixelObj.getRed();
              if (red>param)
              {
                  count++;
              }
          }
      }
      return count;
  }

  public void setRedToHalfValueInTopHalf()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 0; row< pixels.length/2; row++)
      {
          for (int col = 0 ; col<pixels[row].length; col++)
          {
              int newRed = (int)(pixels[row][col].getRed()*.5);
              pixels[row][col].setRed(newRed);

          }
      }
  }

  public void clearBlueOverValue(int param)
  {
      Pixel[][] pixels = this.getPixels2D();
      for (Pixel[] rowArray : pixels)
          for (Pixel pixelObj : rowArray)
          {
              if(pixelObj.getBlue()>param)
              {
                  pixelObj.setBlue(0);
              }
          }
  }

    public int[] getAverageForColumn(int col)
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel thisPixel = null;
        int[] averageArray = new int[pixels[col].length];
        for (int row = 0; row < pixels.length; row++)
        {
            thisPixel = pixels[row][col];
            averageArray[row] = thisPixel.getRed() + thisPixel.getGreen() + thisPixel.getBlue()/ 3;
        }
        return averageArray;
    }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
