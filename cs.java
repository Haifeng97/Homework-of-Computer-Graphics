import java.util.*;

public class cs {
    cs(){
        System.out.println("请输入整数");
        System.out.println(
                "            +------wyt------+\n" +
                "            |               |\n" +
                "            |               |\n" +
                "            w               w\n" +
                "            x               x\n" +
                "            l               r\n" +
                "            |               |\n" +
                "            |               |\n" +
                "            +------wyb------+\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("wxl:");
        wxl = sc.nextInt();
        System.out.println("wyt:");
        wyt = sc.nextInt();
        System.out.println("wxr:");
        wxr = sc.nextInt();
        System.out.println("wyb:");
        wyb = sc.nextInt();
        System.out.println("x1:");
        x1 = sc.nextInt();
        System.out.println("y1");
        y1 = sc.nextInt();
        System.out.println("x2");
        x2 = sc.nextInt();
        System.out.println("y2");
        y2 = sc.nextInt();
        k = (y2 - y1)/(x2 - x1);
    }
    public float wxl ,wxr ,wyt ,wyb ,x1 ,x2 ,y1 ,y2,k;

    boolean code1[] = trans(x1, y1);//用bool数组存放点编码后的结果
    boolean code2[] = trans(x2, y2);
    public void codeupdt(){//更新code的值
        this.code1 = trans(this.x1, this.y1);
        this.code2 = trans(this.x2, this.y2);
    }
    public void kupdt(){//更新k的值
        this.k = (this.y2 - this.y1) / (this.x2 - this.x1);
    }
    public boolean[] trans(float x ,float y){//把点的坐标转换为code
        boolean[] arr = {false,false,false,false};
        if(x < wxl)
            arr[2] = true;
        if(x > wxr)
            arr[3] = true;
        if(y > wyt)
            arr[0] = true;
        if(y < wyb)
            arr[1] = true;
        return arr;
    }
    public void deal(){//运行Cohen-Sutherlan算法
        if(this.code1[2]){
            this.y1 = this.k*(this.wxl-this.x1)+this.y1;
            this.x1 = this.wxl;
        }
        if(this.code1[3]){
            this.y1 = this.k*(wxr-this.x1)+this.y1;
            this.x1 = this.wxr;
        }
        if(this.code1[0] && !this.code1[1] && !this.code1[2] && !this.code1[3]){
            this.x1 = (this.wyt - this.y1)/this.k+this.x1;
            this.y1 = this.wyt;
        }
        if(!this.code1[0] && this.code1[1] && !this.code1[2] && this.code1[3]){
            this.x1 = (this.wyb - this.y1)/this.k+this.x1;
            this.y1 = this.wyb;
        }
    }

    public static void main(String[] args) {
        cs abc = new cs();
        while (true){
              abc.codeupdt();
              if(!abc.code1[0]  && !abc.code1[1]  && !abc.code1[2]  && !abc.code1[3]  && !abc.code2[0]  && !abc.code2[1]  && !abc.code2[2]  && !abc.code2[3] ){
                  System.out.println("两点的坐标:x1=" + abc.x1 + " y1=" + abc.y1 + " x2=" + abc.x2 + " y2=" + abc.y2);
                  break;
              }
              else if((abc.code1[0] & abc.code2[0]) | (abc.code1[1] & abc.code2[1]) | (abc.code1[2] & abc.code2[2]) | (abc.code1[3] & abc.code2[3])){
                  System.out.println("不在图形内");
                  break;
              }
              else {
                  abc.kupdt();
                  if(!(abc.code1[0] | abc.code1[0] | abc.code1[2] | abc.code1[3])){//若点p1在图形内，交换两点
                      float t;
                      boolean te;
                      for (int i = 0; i < 4; i++) {
                          te = abc.code1[i];
                          abc.code1[i] = abc.code2[i];
                          abc.code2[i] = te;
                      }
                      t = abc.x1;
                      abc.x1 = abc.x2;
                      abc.x2 = t;
                      t = abc.y1;
                      abc.y1 = abc.y2;
                      abc.y2 = t;
                  }
                  abc.deal();
              }
        }
    }
}
