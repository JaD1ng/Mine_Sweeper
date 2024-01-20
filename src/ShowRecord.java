import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class ShowRecord extends JDialog implements ActionListener {
    File file = new File("Record.txt");
    String name = null;
    Hashtable hashtable = null;
    JButton show, recount;
    JLabel[] juniorLabel, middleLabel, seniorLabel;

    public ShowRecord(JFrame frame, Hashtable h) {
        setTitle("扫雷榜");
        hashtable = h;
        setBounds(300, 300, 320, 185);
        setResizable(false);
        setVisible(false);
        setModal(true);
        juniorLabel = new JLabel[3];
        middleLabel = new JLabel[3];
        seniorLabel = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            juniorLabel[i] = new JLabel();
            juniorLabel[i].setBorder(null); // 设置无边框
            middleLabel[i] = new JLabel();
            middleLabel[i].setBorder(null);
            seniorLabel[i] = new JLabel();
            seniorLabel[i].setBorder(null);
        }
        juniorLabel[0].setText("初级");
        juniorLabel[1].setText("" + 999);
        juniorLabel[1].setText("匿名");
        middleLabel[0].setText("中级");
        middleLabel[1].setText("" + 999);
        middleLabel[1].setText("匿名");
        seniorLabel[0].setText("高级");
        seniorLabel[1].setText("" + 999);
        seniorLabel[1].setText("匿名");
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++)
            pCenter.add(juniorLabel[i]);
        for (int i = 0; i < 3; i++)
            pCenter.add(middleLabel[i]);
        for (int i = 0; i < 3; i++)
            pCenter.add(seniorLabel[i]);
        pCenter.setBorder(BorderFactory.createTitledBorder("扫雷榜"));
        show = new JButton("最佳成绩");
        recount = new JButton("重新计分");
        show.addActionListener(this);
        recount.addActionListener(this);
        JPanel pSouth = new JPanel();
        pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pSouth.add(recount);
        pSouth.add(show);
        add(pCenter, BorderLayout.CENTER);
        add(pSouth, BorderLayout.SOUTH);
    }

    public void readAndShow() {
        try {
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream object_in = new ObjectInputStream(in);
            hashtable = (Hashtable) object_in.readObject();
            object_in.close();
            in.close();
            String temp = (String) hashtable.get("初级");
            StringTokenizer fenxi = new StringTokenizer(temp, "#");
            juniorLabel[0].setText(fenxi.nextToken());
            juniorLabel[1].setText(fenxi.nextToken());
            juniorLabel[2].setText(fenxi.nextToken());
            temp = (String) hashtable.get("中级");
            fenxi = new StringTokenizer(temp, "#");
            middleLabel[0].setText(fenxi.nextToken());
            middleLabel[1].setText(fenxi.nextToken());
            middleLabel[2].setText(fenxi.nextToken());
            temp = (String) hashtable.get("高级");
            fenxi = new StringTokenizer(temp, "#");
            seniorLabel[0].setText(fenxi.nextToken());
            seniorLabel[1].setText(fenxi.nextToken());
            seniorLabel[2].setText(fenxi.nextToken());
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recount) {
            hashtable.put("初级", "初级#" + 999 + "#匿名");
            juniorLabel[0].setText("初级");
            juniorLabel[1].setText("" + 999);
            juniorLabel[2].setText("匿名");
            hashtable.put("中级", "中级#" + 999 + "#匿名");
            middleLabel[0].setText("中级");
            middleLabel[1].setText("" + 999);
            middleLabel[2].setText("匿名");
            hashtable.put("高级", "高级#" + 999 + "#匿名");
            seniorLabel[0].setText("高级");
            seniorLabel[1].setText("" + 999);
            seniorLabel[2].setText("匿名");
            try {
                FileOutputStream out = new FileOutputStream(file);
                ObjectOutputStream object_out = new ObjectOutputStream(out);
                object_out.writeObject(hashtable);
                object_out.close();
                out.close();
            } catch (IOException event) {
            }
            setVisible(false);
        }
        if (e.getSource() == show) {
            readAndShow();
        }
    }
}