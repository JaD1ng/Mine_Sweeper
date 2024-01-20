import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class MineGame extends JFrame implements ActionListener {
    JMenuBar bar;
    JMenu fileMenu1, fileMenu2;
    JMenuItem junior, medium, senior, custom, record;
    JMenuItem introduction, gameplay;
    MineArea mineArea = null;
    File Record = new File("Record.txt");
    Hashtable hashtable = null;
    ShowRecord showHeroRecord = null;
    JDialog set = null;
    JPanel panel, panel1, panel2, panel3, panel4;
    JLabel label, label1, label2, label3;
    JTextField row = null, column = null, mine = null;
    JButton confirm, cancel;
    JDialog introduce = null, play = null;
    JLabel label4, label5;

    MineGame() {
        mineArea = new MineArea(16, 16, 40, 2);
        add(mineArea, BorderLayout.CENTER); // 边框布局
        bar = new JMenuBar();
        fileMenu1 = new JMenu("游戏");
        junior = new JMenuItem("junior");
        medium = new JMenuItem("medium");
        senior = new JMenuItem("senior");
        custom = new JMenuItem("custom");
        record = new JMenuItem("record");
        fileMenu1.add(junior);
        fileMenu1.add(medium);
        fileMenu1.add(senior);
        fileMenu1.add(custom);
        fileMenu1.add(record);
        fileMenu2 = new JMenu("帮助");
        introduction = new JMenuItem("introduction");
        gameplay = new JMenuItem("gameplay");
        fileMenu2.add(introduction);
        fileMenu2.add(gameplay);
        bar.add(fileMenu1);
        bar.add(fileMenu2);
        setJMenuBar(bar); // 设置窗体的菜单栏
        junior.addActionListener(this);
        medium.addActionListener(this);
        senior.addActionListener(this);
        custom.addActionListener(this);
        record.addActionListener(this);
        introduction.addActionListener(this);
        gameplay.addActionListener(this);
        hashtable = new Hashtable();
        hashtable.put("junior", "junior#" + 999 + "#匿名");
        hashtable.put("medium", "medium#" + 999 + "#匿名");
        hashtable.put("senior", "senior#" + 999 + "#匿名");
        if (!Record.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(Record);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(hashtable);
                objectOut.close();
                out.close();
            } catch (IOException e) {
            }
        }
        showHeroRecord = new ShowRecord(this, hashtable);
        setBounds(300, 100, 480, 560); // 移动组件并调整大小
        setVisible(true); // 使Window可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭Window的同时关闭资源
        validate(); // 再次布置子组件
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == junior) {
            mineArea.initMineArea(9, 9, 10, 1);
            setBounds(300, 100, 270, 350);
        }
        if (e.getSource() == medium) {
            mineArea.initMineArea(16, 16, 40, 2);
            setBounds(300, 100, 480, 560);
        }
        if (e.getSource() == senior) {
            mineArea.initMineArea(16, 30, 99, 3);
            setBounds(100, 100, 900, 560);
        }
        if (e.getSource() == custom) {
            set = new JDialog();
            set.setTitle("custom难度");
            set.setBounds(300, 100, 300, 130);
            set.setResizable(false);//设置大小不可变
            set.setModal(true);//设置为对话框模式
            panel = new JPanel();
            //panel.setLayout(new BorderLayout());
            panel1 = new JPanel();
            panel2 = new JPanel();
            panel3 = new JPanel();
            panel4 = new JPanel();
            label = new JLabel("请输入行列数与地雷数：", JLabel.CENTER);
            label1 = new JLabel("行：", JLabel.CENTER);
            label2 = new JLabel("列：", JLabel.CENTER);
            label3 = new JLabel("地雷数：", JLabel.CENTER);
            row = new JTextField();
            row.setText("16");
            row.setSize(1, 6);
            column = new JTextField();
            column.setText("16");
            mine = new JTextField();
            mine.setText("40");
            confirm = new JButton("confirm");
            confirm.addActionListener(this);
            cancel = new JButton("cancel");
            cancel.addActionListener(this);
            panel1.add(label1);
            panel1.add(row);
            panel2.add(label2);
            panel2.add(column);
            panel3.add(label3);
            panel3.add(mine);
            panel4.add(confirm);
            panel4.add(cancel);
            panel.add(panel1);
            panel.add(panel2);
            panel.add(panel3);
            set.add(label, BorderLayout.NORTH);
            set.add(panel, BorderLayout.CENTER);
            set.add(panel4, BorderLayout.SOUTH);
            set.setVisible(true);
        }
        if (e.getSource() == record) {
            if (showHeroRecord != null)
                showHeroRecord.setVisible(true);
        }
        if (e.getSource() == confirm) {
            int rowNum = Integer.parseInt(row.getText());
            int columnNum = Integer.parseInt(column.getText());
            int mineNum = Integer.parseInt(mine.getText());
            if (rowNum < 9)
                rowNum = 9;
            if (rowNum > 16)
                rowNum = 16;
            if (columnNum < 9)
                columnNum = 9;
            if (columnNum > 30)
                columnNum = 30;
            if (mineNum < 10)
                mineNum = 10;
            if (mineNum > 99)
                mineNum = 99;
            mineArea.initMineArea(rowNum,
                    columnNum, mineNum, 4);
            setBounds(100, 100, columnNum * 30, rowNum * 30 + 80);
            set.setVisible(false);
        }
        if (e.getSource() == cancel) {
            set.setVisible(false);
        }
        if (e.getSource() == introduction) {
            introduce = new JDialog();
            introduce.setTitle("扫雷introduction");
            introduce.setBounds(300, 100, 300, 300);
            introduce.setResizable(false);
            introduce.setModal(true);
            label4 = new JLabel();
            label4.setSize(280, 250);
            label4.setText("<html><body>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp扫雷最原始的版本可以追溯到1973年"
                    + "一款名为“方块”的游戏。不久，“方块”被改写成了游戏“Rlogic”。在“Rlogic”里，玩家的任务是作为美国"
                    + "海军陆战队队员，为指挥中心探出一条没有地雷的安全路线，如果路全被地雷堵死就算输。"
                    + "两年后，汤姆·安德森在“Rlogic”的基础上又编写出了游戏“地雷”，由此奠定了现代扫雷游戏的雏形。"
                    + "1981年，微软公司的罗伯特·杜尔和卡特·约翰逊两位工程师在Windows3.1系统上加载了该游戏，"
                    + "扫雷游戏才正式在全世界推广开来。</body></html>");
            introduce.add(label4);
            introduce.setVisible(true);
        }
        if (e.getSource() == gameplay) {
            play = new JDialog();
            play.setTitle("游戏gameplay");
            play.setBounds(300, 100, 300, 300);
            play.setResizable(false);
            play.setModal(true);
            label4 = new JLabel();
            label4.setSize(280, 250);
            label4.setText("<html><body>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp扫游戏目标是在最短的时间内"
                    + "根据点击格子出现的数字找出所有非雷格子，同时避免踩雷。<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                    + "&nbsp&nbsp当玩家点开一个格子时,会在该格子上显示周围8个格子的雷数"
                    + "（如果没有雷则自动点开周围的格子），玩家需要通过这些数字来判断雷的位置，"
                    + "将是雷的格子标记为小红旗。当所有地雷被标记且非雷格子都被点开时游戏胜利。</body></html>");
            play.add(label4);
            play.setVisible(true);
        }
        validate();
    }

    public static void main(String args[]) {
        new MineGame();
    }
}