
/**
 * Write a description of class RGBImage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RGBImage
{
    // declarations
    private RGBColor[][] _image;
    
    //constructors
    public RGBImage(int rows, int cols)
    {
        _image = new RGBColor[rows][cols];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
               _image[i][j] = new RGBColor();
            }
        }
    } // end of constructor RGBImage(int rows, int cols)
    
    public RGBImage(RGBColor[][] pixels)
    {
        _image = new RGBColor[pixels.length][pixels[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                _image[i][j] = new RGBColor(pixels[i][j]);
            }
        }
    } // end of constructor RGBImage(RGBColor[][] pixels)
    
    public RGBImage(RGBImage other)
    {
        _image = new RGBColor[other._image.length][other._image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                _image[i][j] = new RGBColor(other._image[i][j]);
            }
        }
        
    } // end of copy constructor RGBImage(RGBImage other)
    
    // methods
    public int getHeight()
    {
        return _image.length;
    } // end of method getHeight()
    
    public int getWidth()
    {
        return _image[0].length;
    } // end of method getWidth()
    
    public RGBColor getPixel(int row, int col)
    {
        if(!inImage(row, col))
        {
            return new RGBColor();
        }
        return new RGBColor(_image[row][col]);
    } // end of method RGBColor getPixel(int row, int col)
    
    private boolean inImage(int row, int col)
    {
        return row < getHeight() && col < getWidth();
    } // end of method inImage(int row, int col)
    
    public void setPixel(int row, int col, RGBColor pixel)
    {
        if(inImage(row,col))
        {
            _image[row][col] = new RGBColor(pixel);
        } // end of if statement 
    } // end of method setPixel(int row, int col, RGBColor pixel)
    
    public boolean equals(RGBImage other)
    {
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                if(_image[i][j] == other._image[i][j])
                {
                    return true;
                }
            }           
        }
        return false;
    } // end of method equals(RGBImage other)
    
    public void flipHorizontal()
    {
        RGBColor[][] horizontalImage = new RGBColor[_image.length][_image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                horizontalImage[i][_image[0].length-1-j] = new RGBColor(_image[i][j]);
            }
        }     
        _image = horizontalImage;    
    } // end of method flipHorizontal()
    
    public void flipVertical()
    {
        RGBColor[][] verticalImage = new RGBColor[_image.length][_image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                verticalImage[_image.length-1-i][j] = new RGBColor(_image[i][j]);
            }
        }     
        _image = verticalImage;   
    } // end of method flipVertical()
    
    public void invertColors()
    {
        RGBColor[][] invertImage = new RGBColor[_image.length][_image[0].length];

        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                invertImage[i][j].invert();
            }
        }
        _image = invertImage;
    } // end of method invertColors()
    
    public void rotateClockwise()
    {
        RGBColor[][] rotatedImage = new RGBColor[_image[0].length][_image.length];
        int newLastCol = rotatedImage[0].length - 1;
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                rotatedImage[j][newLastCol-i] = new RGBColor(_image[i][j]);
            }
        }     
        _image = rotatedImage;
    } // end of method rotateClockwise()
    
    public void rotateCounterClockwise()
    {
        RGBColor[][] rotatedImage = new RGBColor[_image[0].length][_image.length];
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                rotatedImage[_image[0].length-1-j][i] = new RGBColor(_image[i][j]);
            }
        }     
        _image = rotatedImage;
    } // end of method rotateCounterClockwise()
    
    public void shiftCol(int offset)
    {
        if(Math.abs(offset) < _image[0].length || offset == 0) {
            return;
        }
        
        RGBColor[][] shiftImage = new RGBColor[_image.length][_image[0].length];
        
        if(offset == _image[0].length) {
            for(int i = 0; i < _image.length; i++){ //rows
                for(int j = 0; j < _image[0].length; j++){ //columns 
                    shiftImage[i][j] = new RGBColor();
                }
            }
        }
        if(offset > 0) {
            for(int i = offset; i < _image[0].length; i++){ //rows
                for(int j = 0; j < _image.length; j++){
                    shiftImage[i][j] = _image[i][j-offset];
                }
            }
                
        }
        else { 
            for(int i = 0; i < _image[0].length + offset ; i++){ //rows
                for(int j = 0; j < _image.length; j++){
                    shiftImage[i][j] = _image[i][j-offset];
                }
            }
                 
        }
        _image = shiftImage;
    }
    
    public void shiftRow(int offset)
    {
        if(Math.abs(offset) < _image.length || offset == 0) {
            return;
        }
        
        RGBColor[][] shiftImage = new RGBColor[_image.length][_image[0].length];
        
        if(offset == _image.length) {
            for(int i = 0; i < _image.length; i++){ //rows
                for(int j = 0; j < _image[0].length; j++){ //columns 
                    shiftImage[i][j] = new RGBColor();
                }
            }
        }
        if(offset > 0) {
            for(int i = offset; i < _image.length; i++){ //rows
                for(int j = 0; j < _image[0].length; j++){
                    shiftImage[i][j] = _image[i-offset][j];
                }
            }
                
        }
        else { 
            for(int i = 0; i < _image.length + offset ; i++){ //rows
                for(int j = 0; j < _image[0].length; j++){
                    shiftImage[i][j] = _image[i-offset][j];
                }
            }
                 
        }
        _image = shiftImage;
    }
    
    public double[][] toGrayscaleArray()
    {
        double[][] grayImage = new double[_image.length][_image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ // columns
                grayImage[i][j] = _image[i][j].convertToGrayscale();
            }
        }
        return grayImage;    
    } // end of method toGrayscaleArray()
    
    public String toString()
    {
        String strMatrix = new String();
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
               if(j != _image[0].length-1)
                   strMatrix += _image[i][j].toString() + ' ';
            
            }
            strMatrix += _image[i][_image[0].length-1].toString() + '\n';  
        }
        return strMatrix;
    } // end of method toString()
    
    public RGBColor[][] toRGBColorArray()
    {
        RGBColor[][] copyImage = new RGBColor[_image.length][_image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ // columns
                copyImage[i][j] = _image[i][j];
            }
        }
        return copyImage;    
    } // end of method toRGBColorArray()
    
} // end of class RGBImage