import javax.swing.*;
import java.awt.*;

public class BlockView extends JPanel {
    JLabel blockNameOrIcon; // 用来显示Block对象的name、number和mineIcon属性
    JButton blockCover; // 用来遮挡blockNameOrIcon.
    CardLayout card; // 卡片式布局，显示第一次添加的组件
    ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8;

    BlockView() {
        card = new CardLayout();
        setLayout(card);
        blockNameOrIcon = new JLabel("", JLabel.CENTER);
        blockNameOrIcon.setHorizontalTextPosition(AbstractButton.CENTER);
        blockNameOrIcon.setVerticalTextPosition(AbstractButton.CENTER);
        blockCover = new JButton();
        add("cover", blockCover);
        add("view", blockNameOrIcon);
    }

    public void giveView(Block block) {
        if (block.isMine) {
            blockNameOrIcon.setIcon(block.getMineicon());
        } else {
            int n = block.getAroundMineNumber();
            if (n == 1) {
                icon1 = new ImageIcon("img/1.jpg");
                blockNameOrIcon.setIcon(icon1);
            }
            if (n == 2) {
                icon2 = new ImageIcon("img/2.jpg");
                blockNameOrIcon.setIcon(icon2);
            }
            if (n == 3) {
                icon3 = new ImageIcon("img/3.jpg");
                blockNameOrIcon.setIcon(icon3);
            }
            if (n == 4) {
                icon4 = new ImageIcon("img/4.jpg");
                blockNameOrIcon.setIcon(icon4);
            }
            if (n == 5) {
                icon5 = new ImageIcon("img/5.jpg");
                blockNameOrIcon.setIcon(icon5);
            }
            if (n == 6) {
                icon6 = new ImageIcon("img/6.jpg");
                blockNameOrIcon.setIcon(icon6);
            }
            if (n == 7) {
                icon7 = new ImageIcon("img/7.jpg");
                blockNameOrIcon.setIcon(icon7);
            }
            if (n == 8) {
                icon8 = new ImageIcon("img/8.jpg");
                blockNameOrIcon.setIcon(icon8);
            } else
                blockNameOrIcon.setText(" ");
        }
    }

    public void seeBlockNameOrIcon() {
        card.show(this, "view"); // 翻转到指定的组件
        validate();
    }

    public void seeBlockCover() {
        card.show(this, "cover");
        validate();
    }

    public JButton getBlockCover() {
        return blockCover;
    }
}