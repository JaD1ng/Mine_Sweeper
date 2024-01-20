import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;

public class LayMines {
    ImageIcon mineIcon;

    LayMines() {
        mineIcon = new ImageIcon("mine.gif");
    }

    public void layMinesForBlock(Block[][] block, int mineCount, int m, int n) {
        int row = block.length;
        int column = block[0].length;
        LinkedList<Block> list = new LinkedList<>();

        for (Block[] blocks : block) {
            list.addAll(Arrays.asList(blocks).subList(0, column));
        }

        while (mineCount > 0) {
            int size = list.size(); // list返回节点的个数
            int randomIndex = (int) (Math.random() * size);
            Block b = list.get(randomIndex);
            if (b != block[m][n]) {
                b.setIsMine(true); // 设雷
                b.setMineIcon(mineIcon);
                list.remove(randomIndex); // list删除索引值为randomIndex的节点
                mineCount--;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (block[i][j].getIsMine()) {
                    block[i][j].setIsOpened(false);
                    block[i][j].setIsMarked(false);
                } else {
                    // 不是雷的格计算雷的数目
                    int mineNumber = 0;
                    for (int k = Math.max(i - 1, 0); k <= Math.min(i + 1, row - 1); k++) {
                        for (int t = Math.max(j - 1, 0); t <= Math.min(j + 1, column - 1); t++) {
                            if (block[k][t].getIsMine())
                                mineNumber++;
                        }
                    }
                    block[i][j].setIsOpened(false);
                    block[i][j].setIsMarked(false);
                    block[i][j].setName("" + mineNumber);
                    block[i][j].setAroundMineNumber(mineNumber);
                }
            }
        }
    }
}