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
    JLabel[] label_junior, label_medium, label_senior;

    public ShowRecord(JFrame frame, Hashtable h) {
        setTitle("扫雷榜");
        hashtable = h;
        setBounds(300, 300, 320, 185);
        setResizable(false);
        setVisible(false);
        setModal(true);
        label_junior = new JLabel[3];
        label_medium = new JLabel[3];
        label_senior = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            label_junior[i] = new JLabel();
            label_junior[i].setBorder(null); // 设置无边框
            label_medium[i] = new JLabel();
            label_medium[i].setBorder(null);
            label_senior[i] = new JLabel();
            label_senior[i].setBorder(null);
        }
        label_junior[0].setText("初级");
        label_junior[1].setText("" + 999);
        label_junior[1].setText("匿名");
        label_medium[0].setText("中级");
        label_medium[1].setText("" + 999);
        label_medium[1].setText("匿名");
        label_senior[0].setText("高级");
        label_senior[1].setText("" + 999);
        label_senior[1].setText("匿名");
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++)
            pCenter.add(label_junior[i]);
        for (int i = 0; i < 3; i++)
            pCenter.add(label_medium[i]);
        for (int i = 0; i < 3; i++)
            pCenter.add(label_senior[i]);
        pCenter.setBorder(BorderFactory.createTitledBorder("扫雷榜"));
        show = new JButton("最佳成绩");
        recount = new JButton("重新记分");
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
            label_junior[0].setText(fenxi.nextToken());
            label_junior[1].setText(fenxi.nextToken());
            label_junior[2].setText(fenxi.nextToken());
            temp = (String) hashtable.get("中级");
            fenxi = new StringTokenizer(temp, "#");
            label_medium[0].setText(fenxi.nextToken());
            label_medium[1].setText(fenxi.nextToken());
            label_medium[2].setText(fenxi.nextToken());
            temp = (String) hashtable.get("高级");
            fenxi = new StringTokenizer(temp, "#");
            label_senior[0].setText(fenxi.nextToken());
            label_senior[1].setText(fenxi.nextToken());
            label_senior[2].setText(fenxi.nextToken());
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recount) {
            hashtable.put("初级", "初级#" + 999 + "#匿名");
            label_junior[0].setText("初级");
            label_junior[1].setText("" + 999);
            label_junior[2].setText("匿名");
            hashtable.put("中级", "中级#" + 999 + "#匿名");
            label_medium[0].setText("中级");
            label_medium[1].setText("" + 999);
            label_medium[2].setText("匿名");
            hashtable.put("高级", "高级#" + 999 + "#匿名");
            label_senior[0].setText("高级");
            label_senior[1].setText("" + 999);
            label_senior[2].setText("匿名");
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