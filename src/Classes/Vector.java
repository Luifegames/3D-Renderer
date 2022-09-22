
package Classes;

public class Vector {
  private float x,y,z;

    public Vector(){
        x = 0;
        y = 0;
        z = 0;
    }
  
    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
  
    public float[][] vectToMatrix(){
        float[][] v = {
            {x},{y},{z},{1}};
        return v;
    }
    
    public Vector mul(int m){
        
        return new Vector(x * m, y * m, z  * m);
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
    
    
    
}
