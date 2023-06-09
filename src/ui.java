import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.Double.parseDouble;


public class ui extends JFrame implements ActionListener{

    shi shii=new shi();
    private Container c;  ////容器
    Object[] biaotou=new Object[] //表头
            {"0","1","2","3","4","5","6","7","8",
                    "9","10","11","12","13","14","15","16",
                    "17","18","19","20","21","22","23","24",
                    "25","26","27","28","29","30","31"};
    Object[][] pq; //表格内容

    private JTable table;
    private JScrollPane sp;


    private JButton a1,a2,a3;//按钮
    private JTextField b1,b2,b3,b4,b5,b6;//文本框
    private JCheckBox c1; //复选框
    private Font font =new Font("宋体",Font.PLAIN,12);


    ui(){
        c = this.getContentPane();
        setBounds(100,100,1100,700);
        c.setLayout(null);
        setTitle("位示图");

//int -》 object
        pq=new Object[shii.num/32+1][32];
        shii.obj_in(pq);

        table=new JTable(pq,biaotou){
            //设置表格不可编辑
            public boolean isCellEditable(int row,int column){return false;}
        };
        sp=new JScrollPane(table);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器
        tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示
        table.setDefaultRenderer(Object.class, tcr);//设置渲染器

        // table.setBounds(50,100,1400,500);
        sp.setBounds(50,100,1000,200);
       // table.getColumnModel().getColumn(1).setPreferredWidth(2);
      //  table.getColumnModel().getColumn(2).setPreferredWidth(2);
       // c.add(table);
        c.add(sp);


        a1=new JButton("申请盘块");
        a1.setBounds(600,500,100,30);
        a1.addActionListener(this);
        a1.setFont(font);

        a2=new JButton("释放盘块");
        a2.setBounds(600,550,100,30);
        a2.addActionListener(this);
        a2.setFont(font);

        a3=new JButton("退出");
        a3.setBounds(600,600,100,30);
        a3.addActionListener(this);
        a3.setFont(font);

        c.add(a1);
        c.add(a2);
        c.add(a3);

        c1=new JCheckBox("申请连续盘块");
        c1.setBounds(710,500,120,30);
        c1.setFont(font);

        c.add(c1);


//申请盘快
        b1=new JTextField(20);
        b1.setBounds(400,500,120,30);
        b1.setFont(font);

//释放盘快
        b2=new JTextField(20);
        b2.setBounds(400,550,120,30);
        b2.setFont(font);


        b3=new JTextField(50);
        b3.setEditable(false);
        b3.setText("请输入释放的盘块编号（0~"+(shii.num-1)+")");
        b3.setHorizontalAlignment(JTextField.LEFT);
        b3.setBackground(null);
        b3.setBorder(null);
        b3.setBounds(190,550,200,30);
        b3.setFont(font);


        b4=new JTextField(35);
        b4.setText("请输入申请磁盘块数：");
        b4.setHorizontalAlignment(JTextField.LEFT);
        b4.setBackground(null);
        b4.setEditable(false);
        b4.setBorder(null);
        b4.setBounds(190,500,120,30);
        b4.setFont(font);


        b5=new JTextField(60);
        b5.setText("若需要释放连续的磁盘，请以\" - \" 隔开");
        b5.setBackground(null);
        b5.setBorder(null);
        b5.setHorizontalAlignment(JTextField.LEFT);
        b5.setEditable(false);
        b5.setBounds(720,550,220,30);
        b5.setFont(font);


        b6=new JTextField(60);
        b6.setText("状态：");
        b6.setBackground(null);
        b6.setBorder(null);
        b6.setHorizontalAlignment(JTextField.LEFT);
        b6.setEditable(false);
        b6.setBounds(190,600,220,30);
        b6.setFont(font);

        c.add(b4);
        c.add(b1);
        c.add(b5);
        c.add(b2);
        c.add(b6);
        c.add(b3);



        setVisible(true);//是否可见
        setLocationRelativeTo(null);//位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//是否有退出按钮（右上角）
        this.setDefaultCloseOperation(3);
    }


    public void actionPerformed(ActionEvent e){

        int q=0,qq=0,qqq=-1,i;
        boolean lx=false;//判断是否释放连续磁盘
        String s;

//申请盘快
        if(e.getSource()==a1){
            //q = Integer.parseInt(b1.getText());
            try {
                q = Integer.parseInt(b1.getText());
            }
            catch (NumberFormatException ee){
                System.out.println("不能为空/非法字符");
                b6.setText("不能为空/非法字符");
                return;
            }


            if(c1.isSelected()){
                //连续
                if(shii.sqq(pq,q)) {
                    System.out.println("申请成功");
                    b6.setText("状态：磁盘申请成功");
                }
                else {
                    System.out.println("申请失败");
                    b6.setText("状态：磁盘申请失败");
                }

            }
            else{

                if(shii.sq(pq,q)) {
                    System.out.println("申请成功");
                    b6.setText("状态：磁盘申请成功");

                }
                else {
                    System.out.println("申请失败");
                    b6.setText("状态：磁盘申请失败");
                }

            }
            table.repaint();
        }



//释放盘快
        else if(e.getSource()==a2){
    s = b2.getText();
    qq=0;
    qqq=-1;
            for(i=0;i<s.length();i++){
                if(s.charAt(i)=='-')
                {
                    //输入了负的序号
                    if(i==0||qqq!=-1){//qqq不为-1则已出现过一个 -
                        System.out.println("序号不为负/只能修改一个连续的盘块");
                        b6.setText("序号不能为负/只能修改一个连续的盘块");
                        return;
                    }
                    qqq=0;
                    lx=true;
                    continue;
                }
                if(s.charAt(i)==' ')
                    continue;
                if(s.charAt(i)<48||s.charAt(i)>58){
                    System.out.println("非法字符");
                    b6.setText("非法字符");
                    return;
                }


                if(!lx)
                    qq=qq*10+Integer.parseInt(String.valueOf(s.charAt(i)));
                else
                    qqq=qqq*10+Integer.parseInt(String.valueOf(s.charAt(i)));

                
            }

            if(i==0){
                System.out.println("不能为空");
                b6.setText("不能为空");
                return;

            }
            System.out.println(qq+"  "+qqq);

            if(shii.sf(pq,qq,qqq)){
                System.out.println("释放成功");
                b6.setText("状态：磁盘释放成功");
            }
else {
                System.out.println("释放失败");
                b6.setText("状态：磁盘释放失败");
            }
            table.repaint();
        }
        else if(e.getSource()==a3) {
            System.exit(0);

        }


    }


}

