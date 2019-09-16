import java.util.ArrayList;


public class HandValue {
    public int value=0;
    int[] myfirstfour = new int[4];
    String[] myfirstfours = new String[4];
    int startcard;
    String startcards;


    public int gethandvalue(){
        return value;
    }
    public void init(String[] args){
        for(int i=0; i<=3; i++){
            if(args[i].substring(0,1).equals("A")){
                myfirstfour[i]=1;
            }
            else if(args[i].substring(0,1).equals("T")){
                myfirstfour[i]=10;
            }
            else if(args[i].substring(0,1).equals("J")){
                myfirstfour[i]=11;
            }
            else if(args[i].substring(0,1).equals("Q")){
                myfirstfour[i]=12;
            }
            else if(args[i].substring(0,1).equals("K")){
                myfirstfour[i]=13;
            }
            else {
                myfirstfour[i] = Integer.parseInt(args[i].substring(0, 1));
            }
            myfirstfours[i]=args[i].substring(1,2);
        }
        if(args[4].substring(0,1).equals("A")){
            startcard=1;
        }
        if(args[4].substring(0,1).equals("T")){
            startcard=10;
        }
        else if(args[4].substring(0,1).equals("J")){
            startcard=11;
        }
        else if(args[4].substring(0,1).equals("Q")){
            startcard=12;
        }
        else if(args[4].substring(0,1).equals("K")){
            startcard=13;
        }
        else {
           startcard = Integer.parseInt(args[4].substring(0, 1));
        }
        startcards=args[4].substring(1,2);
    }

    //FLUSH
    public void checkflush(){
        if(myfirstfours[0].equals(myfirstfours[1]) && myfirstfours[1].equals(myfirstfours[2]) && myfirstfours[2].equals(myfirstfours[3])){
            value +=4;
            if(startcards.equals(myfirstfours[0])){
                value +=1;
            }
        }
    }
    //Check one for nob
    public  void nob(){
        for(int i=0;i<=3;i++){
            if(myfirstfour[i]==11){
                if(myfirstfours[i].equals(startcards)){
                    value++;
                }
            }
        }
    }
    //Check pair
    public void pair(){
        for(int i=0;i<=3;i++){
            if(startcard==myfirstfour[i]){
                value+=2;
            }
        }
        for(int i=0;i<=2;i++){
            for(int j=i+1;j<=3;j++){
                if(myfirstfour[i]==myfirstfour[j]){
                    value+=2;
                }
            }
        }
    }

    //Check runs
    public  void runs(){
        ArrayList<Integer> myrun = new ArrayList<Integer>();
        int endnode;
        int[] myhandvalue=new int[5];
        for(int i=0;i<=3;i++){
            myhandvalue[i]=myfirstfour[i];
        }
        myhandvalue[4]=startcard;
                //sort myhandvalue
            for(int j=0;j<4;j++){
                int min=j;
                for(int i=j+1;i<5;i++){
                    if(myhandvalue[i]<myhandvalue[min]){
                        min=i;
                    }
                }
                if(min != j){
                    int tmp = myhandvalue[j];
                    myhandvalue[j]=myhandvalue[min];
                    myhandvalue[min]=tmp;
                }
            }
        for(int i=0;i<=2;i++){
           endnode=myhandvalue[i];
           myrun.add(myhandvalue[i]);
           for(int j=i+1;j<=4;j++){
               if(myhandvalue[j]==endnode)continue;
               if(myhandvalue[j]==endnode+1){
                   endnode=myhandvalue[j];
                   myrun.add(myhandvalue[j]);
               }
               if(myhandvalue[j]>endnode+1 && myrun.size()<3){
                   myrun.clear();
                   break;
               }
           }
           if(myrun.size()>=3)break;
       }
       int k=0;
       if(myrun.size()>=3){
           k=myrun.size();
           for(int i=0;i<myrun.size();i++){
               int d = 0;
               for(int j=0;j<=4;j++){
                   if(myrun.get(i)==myhandvalue[j]){
                       d++;
                   }
               }
               k=k*d;
           }
       }
       value+=k;
    }
    //check 15s
    public void check15s(){
        int[] myhandvalue=new int[5];
        for(int i=0;i<=3;i++){
            if(myfirstfour[i]==11 || myfirstfour[i]==12 || myfirstfour[i]==13 ){
                myhandvalue[i]=10;
            }
            else{
                myhandvalue[i]=myfirstfour[i];
            }
        }
        if(startcard==11 || startcard==12 || startcard==13){
            myhandvalue[4]=10;
        }
        else{
            myhandvalue[4]=startcard;
        }
        ArrayList<Integer> mysum = new ArrayList<Integer>();
        mysum.add(myhandvalue[0]);
        int k;
        for(int i=1;i<=4;i++){
            k=mysum.size();
            for(int j=0;j<k;j++){
                mysum.add(mysum.get(j)+myhandvalue[i]);
            }
            mysum.add(myhandvalue[i]);
        }
        for(int j=0;j<mysum.size();j++){
            if(mysum.get(j)==15){
                value+=2;
            }
        }
    }


    public static void main(String[] args){
        HandValue myhand= new HandValue();
        myhand.init(args);
        myhand.checkflush();
        myhand.nob();
        myhand.check15s();
        myhand.pair();
        myhand.runs();
        System.out.println(myhand.value);
    }

}
