import java.io.*;

public class ioo {

public int readd(File f,int[][] p){
    int i=0,j=0,ss,k=0;
    //f=new File("a.txt");
    try {
        FileReader r = new FileReader(f);
        while((ss=r.read())!=-1){
            if(ss==32||ss==13||ss==10)// 32 空格  13 10 \n
                continue;
            p[i][j]=ss-48;
            k++;
            j++;
            if(j==32){
                i++;
                j=0;
            }

        }
        r.close();
    }
    catch (IOException e){}
   // System.out.println(sd);

    return k;
};

public void writee(File f,int[][] p,int n){

    String s;
    int k=n;
    try{
        FileWriter e=new FileWriter(f);
        for(int i=0;i<((n-1)/32+1);i++) {
            for(int j=0;j<32;j++) {
                s= String.valueOf(p[i][j]);
                e.write(s + " ");
                k--;
                if(k==0)
                    break;

            }
            if(k==0)
                break;
            e.write("\n");
        }

        e.close();
        System.out.println("写入完成");
    }
    catch (IOException e){

    }
    return ;
}

}
