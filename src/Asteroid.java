import java.awt.Graphics;
import java.util.*;

public abstract class Asteroid {
	
	/*
     * Current position of the object (in terms of graphics coordinates)
     *  
     * Coordinates are given by the upper-left hand corner of the object. This position should
     * always be within bounds.
     *  0 <= px <= maxX 
     *  0 <= py <= maxY 
     */
    private int px; 
    private int py;

    /* Size of object, in pixels. */
    private int width;
    private int height;

    /* Velocity: number of pixels to move every time move() is called. */
    private int vx;
    private int vy;

    /* 
     * Upper bounds of the area in which the object can be positioned. Maximum permissible x, y
     * positions for the upper-left hand corner of the object.
     */
    private int maxX;
    private int maxY;
    
    /*
     * Map of vocabulary words taken from CSV file. 
     */
    private static Map<String, String> vocab;
    private String word;
    
    /**
     * Constructor
     */
    public Asteroid(int vy, int vx, int px, int py, int width, int height, int courtWidth,
        int courtHeight) {
        this.vy = vy;
        this.vx = vx;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;

        // take the width and height into account when setting the bounds for the upper left corner
        // of the object.
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
        word = generateWord();
    }

    /* 
     * GETTERS 
     * */
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String getWord() {
    	return this.word;
    }
    
    /* 
     * SETTERS
     * */
    public void setPx(int px) {
        this.px = px;
        clip();
    }

    public void setPy(int py) {
        this.py = py;
        clip();
    }
    
    public void setVy(int vy) {
        this.vy = vy;
    }
    
    /*
     * VOCAB METHODS
     * */
    
    public static Map<String, String> getVocab() {
    	Map<String, String> copy = new TreeMap<>();
    	copy.putAll(vocab);
    	return copy;
    }
    
    public static void importVocab(String filePath) {
    	FileLineIterator fl = new FileLineIterator(filePath);
    	vocab = new TreeMap<>();
    	while (fl.hasNext()) {
    		String[] words = fl.next().split("\t");
    		vocab.put(words[0], words[1]);
    	}
    }
    
    public static String generateWord() {
    	List<String> keys = new ArrayList<>(vocab.keySet());
    	Random r = new Random();
    	return vocab.get(keys.get(r.nextInt(keys.size())));
    	//TODO: check this
    }

    /*
     * MOVEMENT and DRAWING METHODS
     * */

    /**
     * Prevents the object from going outside of the bounds of the area designated for the object.
     * (i.e. Object cannot go outside of the active area the user defines for it).
     */ 
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }

    /**
     * Moves the object by its velocity.  Ensures that the object does not go outside its bounds by
     * clipping.
     */
    public void move() {
        this.py += this.vy;
        this.px += this.vx;
        //clip();
    }

    /**
     * Default draw method that provides how the object should be drawn in the GUI. This method does
     * not draw anything. Subclass should override this method based on how their object should
     * appear.
     * 
     * @param g The <code>Graphics</code> context used for drawing the object.
     */
    public abstract void draw(Graphics g);
	
}
