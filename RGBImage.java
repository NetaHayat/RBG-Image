
/**
 * RGBImage.java represents an image made of RGB pixels (red,green,blue)
 * This class uses RGBColor class 
 *
 * @author Neta Hayat
 * @version 21/04/21 v-1
 */
public class RGBImage
{
    // declarations
    private RGBColor[][] _image; // 2D array
    
    //constructors
     /**
     * Construct an all black RGBImage based on the dimensions given
     * @param rows number of rows, represents the height's image
     * @param cols number of columns, represents the width's image
     */    
    public RGBImage(int rows, int cols)
    {
        _image = new RGBColor[rows][cols];
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
               _image[i][j] = new RGBColor();
            } // end of columns loop 
        } // end of rows loop 
    } // end of constructor RGBImage(int rows, int cols)
    
    /**
     * Construct a new RGBImage from given 2D pixels array.
     * @param pixels 2D array of RGB pixels in image
     */    
    public RGBImage(RGBColor[][] pixels)
    {
        _image = new RGBColor[pixels.length][pixels[0].length];
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                _image[i][j] = new RGBColor(pixels[i][j]);
            } // end of columns loop 
        } // end of rows loop
    } // end of constructor RGBImage(RGBColor[][] pixels)

    /**
     * Copy Construct creates a new RGBImage from an other given RGBImage.
     * @param other an image to copy
     */   
    public RGBImage(RGBImage other)
    {
        this(other._image);
        
    } // end of copy constructor RGBImage(RGBImage other)
    
    // methods
    /**
     * This method returns the height of the image
     * @return height's image (number of rows)
     */       
    public int getHeight()
    {
        return _image.length;
    } // end of method getHeight()

    /**
     * This method returns the width of the image
     * @return width's image (number of cols)
     */       
    public int getWidth()
    {
        return _image[0].length;
    } // end of method getWidth()
    
    /**
     * This method returns the pixel's value based on its position (row and col paramaters) 
     * If the position is out of the image's dimensions it will return a black pixel
     * @param row the row in which the pixel is positioned (height)
     * @param col the column in which the pixel is positioned (width)
     * @return return pixel values if position is valid. If not - return a black pixel
     */      
    public RGBColor getPixel(int row, int col)
    {
        if(!inImage(row, col)) // if the pixel position is out of the image's dimensions
        {
            return new RGBColor();
        } // end of if statement
        return new RGBColor(_image[row][col]);
    } // end of method RGBColor getPixel(int row, int col)
    
    // This method returns true if the given position (row and col) is in the image. 
    private boolean inImage(int row, int col)
    {
        return row < getHeight() && row >= 0 && col < getWidth() && col >= 0;
    } // end of method inImage(int row, int col)
    
    /**
     * This method sets the pixel's value based on its position (row and col paramaters) 
     * If the position is out of the image's dimensions it will not changed 
     * @param row the row in which the pixel is positioned (height)
     * @param col the column in which the pixel is positioned (width)
     * @param pixel value to set
     */          
    public void setPixel(int row, int col, RGBColor pixel)
    {
        if(inImage(row,col)) // if the pixel position is in the image dimensions
        {
            _image[row][col] = new RGBColor(pixel);
        } // end of if statement 
    } // end of method setPixel(int row, int col, RGBColor pixel)
    
    /**
     * Check if other given image is equal to this image.
     * @param other the image we are checking with
     * @return true if the other image is equal to this image
     */     
    public boolean equals(RGBImage other)
    {
        // if the dimensions aren't equal or other is null
        if(other == null || _image.length != other._image.length || _image[0].length != other._image[0].length){
            return false; 
        } // end of if statement
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                if(!_image[i][j].equals(other._image[i][j])) // if one pair of pixels is not equal
                {
                    return false;
                } // end of if statement
            } // end of columns loop          
        } // end of rows loop
        return true;
    } // end of method equals(RGBImage other)

    /**
     * This method flips the image horizontally.
     */
    public void flipHorizontal()
    {
        // creating a new 2D array based on this image dimensions
        RGBColor[][] horizontalImage = new RGBColor[_image.length][_image[0].length]; 
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                horizontalImage[i][_image[0].length-1-j] = new RGBColor(_image[i][j]);
            } // end of columns loop
        } // end of rows loop 
        _image = horizontalImage;    
    } // end of method flipHorizontal()
    
    /**
     * This method flips the image vertically.
     */    
    public void flipVertical()
    {
        // creating a new 2D array based on this image dimensions        
        RGBColor[][] verticalImage = new RGBColor[_image.length][_image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                verticalImage[_image.length-1-i][j] = new RGBColor(_image[i][j]);
            }
        }     
        _image = verticalImage;   
    } // end of method flipVertical()

    /**
     * This method inverts the image colors.
     * based on the method invert from RGBColor class
     */
    public void invertColors()
    {
        // run through the entire image and invert each pixel
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ //columns 
                _image[i][j].invert();
            } // end of columns loop
        } // end of rows loop
    } // end of method invertColors()
    
    /**
     * This method rotates the image 90 degrees clockwise
     */
    public void rotateClockwise()
    {
        // creating a new 2D array based on this image rotated dimensions      
        RGBColor[][] rotatedImage = new RGBColor[_image[0].length][_image.length];
        int newLastCol = rotatedImage[0].length - 1; // new last column after rotation
        
        for(int i = 0; i < _image.length; i++){ //rows , rotated columns
            for(int j = 0; j < _image[0].length; j++){ //columns , rotated rows 
                rotatedImage[j][newLastCol-i] = new RGBColor(_image[i][j]);
            } // end of columns loop
        } // end of rows loop
        _image = rotatedImage;
    } // end of method rotateClockwise()

    /**
     * This method rotates the image 90 degrees counter clockwise
     */
    public void rotateCounterClockwise()
    {
        // creating a new 2D array based on this image rotated dimensions 
        RGBColor[][] rotatedImage = new RGBColor[_image[0].length][_image.length];
        int newLastRow = rotatedImage.length - 1; // new last row after rotation
        
        for(int i = 0; i < _image[0].length; i++){ //columns, rotated rows
            for(int j = 0; j < _image.length; j++){ //rows, rotated columns 
                rotatedImage[newLastRow-i][j] = new RGBColor(_image[j][i]);
            } // end of rows loop
        } // end of columns loop    
        _image = rotatedImage;
    } // end of method rotateCounterClockwise()
    
    /**
     * This method shifts the columns of the image right or left based on the given offset value
     * The pixels that have been shifted to different position change to black .
     * @param offset the number that declares the shifting 
     * negative value shifts to the left and positive shifts to the right
     */    
    public void shiftCol(int offset)
    {    
        // offset validity
        if(Math.abs(offset) > _image[0].length || offset == 0){
            return;
        } // end of if statement

        // creating a black image based on the dimensions image
        RGBImage shift = new RGBImage(_image.length, _image[0].length);
        
        if(offset > 0) { // shift right  
            for(int i = _image[0].length - 1; i - offset >= 0; i--){ // starts from the last column
                for(int j = 0; j < _image.length; j++){ // rows
                    shift._image[j][i] = _image[j][i - offset]; // moves each pixel to the right based on the offset value
                } // end of rows loop
            } // end of columns loop
        } // end of if statement
        
        else { // shift left (if offset < 0)
            offset = -offset;  // make positive offset
            for(int i = 0; i + offset < _image[0].length; i++){ // starts from the first column
                for(int j = 0; j < _image.length; j++){ // rows
                    shift._image[j][i] = _image[j][i + offset]; // moves each pixel to the left based on the offset value
                } // end of rows loop
            } // end of columns loop
        } // end of if statement
        _image = shift._image;
    } // end of method shiftCol(int offset) 

    /**
     * This method shifts the rows of the image up and down based on the given offset value
     * The pixels that have been shifted to different position change to black .
     * @param offset the number that declares the shifting 
     * negative value shifts up and positive shifts down
     */    
    public void shiftRow(int offset)
    {
        // offset validity
        if(Math.abs(offset) > _image[0].length || offset == 0){
            return;
        } // end of if statement
        
        // creating a black image based on the dimensions image
        RGBImage shift = new RGBImage(_image.length, _image[0].length);
        
        if(offset > 0) { // shift down
            for(int i = _image.length - 1; i - offset >= 0; i--){  // starts from the last row
                for(int j = 0; j < _image[0].length; j++){ // columns
                    shift._image[i][j] = _image[i - offset][j]; // moves each pixel down based on the offset value
                } // end of columns loop
            } // end of rows loop
        } // end of if statement
        
        else { // shift up (if offset < 0)
            offset = -offset;  // make positive offset
            for(int i = 0; i + offset < _image.length; i++){ // starts from the first row
                for(int j = 0; j < _image[0].length; j++){ // columns
                    shift._image[i][j] = _image[i + offset][j]; // // moves each pixel up based on the offset value
                } // end of columns loop
            } // end of rows loop    
        } // end of if statement  
        _image = shift._image;
    } // end of method shiftRow(int offset)

    /**
     * This method returns a gray scale array of the image.
     * based on the method convertToGrayscale from RGBColor class
     * @return a gray scale array of the image
     */
    public double[][] toGrayscaleArray()
    {
        double[][] grayImage = new double[_image.length][_image[0].length];
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length; j++){ // columns
                grayImage[i][j] = _image[i][j].convertToGrayscale();
            } // end of columns loop
        } // end of rows loop
        return grayImage;    
    } // end of method toGrayscaleArray()

    /**
     * This method returns a String representation of the image
     * between each pixel there's a single sapce 
     * every line represents a row and the pixel is represented with the RGB values: (red,green,blue)
     * @return a String representation of the image
     */
    public String toString()
    {
        String strMatrix = new String();  
        
        for(int i = 0; i < _image.length; i++){ //rows
            for(int j = 0; j < _image[0].length-1; j++){ //columns 
                   strMatrix += _image[i][j].toString() + ' '; // single space between each pixel of a row
            } // end of columns loop
            strMatrix += _image[i][_image[0].length-1].toString() + '\n';  // new line after the last pixel of each row
        } // end of rows loop
        return strMatrix;
    } // end of method toString()
    
    /**
     * This method returns a RGBColor array of the image.
     * @return a RGBColor array of the image
     */
    public RGBColor[][] toRGBColorArray()
    {        
        return new RGBImage(this)._image;    
    } // end of method toRGBColorArray()
    
} // end of class RGBImage