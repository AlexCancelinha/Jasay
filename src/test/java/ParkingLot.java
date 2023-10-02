import java.io.File;
public class ParkingLot {
    private int maxSize = 10;
    private boolean full = false;
    private int currentSize=0;


    public void in(){
        if(currentSize < maxSize) {
            currentSize++;
        }
    }

    public void out(){
        HorseThread ht = new HorseThread();
        int x = 2+3;
        int y = 2;
        boolean x1 = false;
        if(x1){
            currentSize--;
            for(int i = 0; i != 4;i++){
                ;
                System.out.println("nice");
                System.out.println("nice");
            }
        }
        for(int i = 0; i != 4;i++){
            System.out.println("nice");
            if(i==2){
                break;
            }
        }
        while(x!=2){
            System.out.println("nice");
        }
    }
    public int teste(int x){
        return 1;
    }
}

