
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
        this(other._image);
        
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
        return row < getHeight() && row >= 0 && col < getWidth() && col >= 0;
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
        if(_image.length != other._image.length || _image[0].length != other._image[0].length || other == null){
            return false;
        }
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                if(!_image[i][j].equals(other._image[i][j]))
                {
                    return false;
                }
            }           
        }
        return true;
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
       // RGBColor[][] invertImage = new RGBColor[_image.length][_image[0].length];

        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
              //  invertImage[i][j] = _image[i][j];
                _Image[i][j].invert();
            }
        }
        //_image = invertImage;
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
        int newLastCol = rotatedImage[0].length - 1; 
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                rotatedImage[newLastCol-j][i] = new RGBColor(_image[i][j]);
            }
        }     
        _image = rotatedImage;
    } // end of method rotateCounterClockwise()
    
    public void shiftCol(int offset)
    {    
        // validity
        if(Math.abs(offset) > _image[0].length || offset == 0)
            return;

        // black image
        RGBImage shift = new RGBImage(_image.length, _image[0].length);
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                shift._image[i][j] = new RGBColor();
            }
        }
        
        // shift right
        if(offset > 0) {    
            for(int i = _image[0].length - 1; i - offset >= 0; i--){ 
                for(int j = 0; j < _image.length; j++){
                    shift._image[j][i] = _image[j][i - offset];
                }
            }  
        }
        else { // shift left
            offset = -offset;   // make possitive because we are possitive.
            for(int i = 0; i + offset < _image[0].length; i++){ 
                for(int j = 0; j < _image.length; j++){
                    shift._image[j][i] = _image[j][i + offset];
                }
            }     
        }
        
        _image = shift._image;
    }
    
    public void shiftRow(int offset)
    {
        if(offset < _image.length || offset == 0) {
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
            for(int j = 0; j < _image[0].length-1; j++){ //columns 
                   strMatrix += _image[i][j].toString() + ' ';
            }
            strMatrix += _image[i][_image[0].length-1].toString() + '\n';  
        }
        return strMatrix;
    } // end of method toString()
    
    public RGBColor[][] toRGBColorArray()
    {        
        return new RGBImage(this)._image;    
    } // end of method toRGBColorArray()
    
} // end of class RGBImage
