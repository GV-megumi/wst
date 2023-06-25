import java.io.File;

public class shi{
    static final int M=100;
    static final int N=32;
     ioo iooo=new ioo();
     File f=new File("a.txt");
     int[][] weishitu=new int[M][N];
    int num;//总磁盘数



shi(){
    for(int i=0;i<M;i++)
        for(int j=0;j<N;j++)
            weishitu[i][j]=-1;
    num=iooo.readd(f,weishitu);
    putwst();
}
//返回空磁盘数
public int putwst(){
    int i,j,n=0,k=num;
    for(i=0;i<8;i++){
        for(j=0;j<32;j++) {
            if(weishitu[i][j]==0)
                n++;
            System.out.print(weishitu[i][j] + " ");
            k--;
            if(k==0)
                break;
        }
        if(k==0)
            break;
        System.out.println();

    }
    System.out.println("\n");
    return n;
}

//判断序号是否越界  越界为true
    public boolean panduan(int p)
    {

        return !(p>=0&&p<num);
    }
    //obj 与 int 的同步  s=1  int -> obj
    public void obj_in(Object[][] pq){
        int i=0;
        int j=0;
        int k=num;

        while(k!=0) {
            pq[i][j] = weishitu[i][j];
            //weishitu[i][j]=Integer.parseInt(String.valueOf(pq[i][j]));
            j++;
            if(j==32){
                j=0;
                i++;
            }
            k--;
        }

    }



    //申请连续空间
    public boolean sqq(Object [][] pq,int k){
    int i,j=0,count=0,hang=(num-1)/32+1;//位示图
        boolean as=false;
        if(k>putwst()){
            System.out.println("空间不足");
            return false;
        }

for(i=0;i<hang&&count!=k;i++){
    for(j=0;j<32&&count!=k;j++){
        if(weishitu[i][j]==0)
            count++;
        else
            count=0;
        if(count==k)
        {
            as=true;
           // break;
        }
    }
    //if (count==k)
      //  break;

}


if(as) {
    i--;
    j--;
    for (; i >= 0; i--) {
        for (; j >= 0; j--) {
            weishitu[i][j] = 1;
            pq[i][j] = 1;
            count--;
            if (count == 0)
                break;

        }
        if (count == 0)
            break;
        j = 31;
    }
    iooo.writee(f,weishitu,num);
}

    return as;
    }


    //申请任意空间
    public boolean sq(Object [][] pq,int k){
    int i,j,hang=((num-1)/32+1);
    if(k>putwst()){
        System.out.println("空间不足");
        return false;
    }

    for(i=0;i<hang;i++){
        for(j=0;j<32;j++) {
            if (weishitu[i][j] == 0) {
                weishitu[i][j] = 1;
                pq[i][j] = 1;
                k--;
                if (k == 0)
                    break;
            }
        }
        if (k == 0)
            break;
        }
        iooo.writee(f,weishitu,num);

    return true;
    }

    //释放磁盘
    public boolean sf(Object [][] pq,int q,int h)
    {
        int i,j,min,n;

        if(panduan(q))
        {
            System.out.println("越界");
            return false;
        }

        if(h==-1){
            //释放1个

            i=q/32;
            j=q-32*i;
            weishitu[i][j]=0;
            pq[i][j]=0;


        }
        else{
            //释放连续
            if(panduan(h))
            {
                System.out.println("越界");
                return false;
            }


            min=Math.min(q,h);
            n=Math.abs(q-h)+1;
            i=min/32;
            j=min-32*i;

            while(n!=0){
                weishitu[i][j]=0;
                pq[i][j]=0;
                j++;
                if(j==32){
                    i++;
                    j=0;
                }
                n--;

            }

        }
        iooo.writee(f,weishitu,num);
    return true;
    }



}
