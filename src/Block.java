import javax.swing.*;

public class Block {
    String name; // 名字,比如"雷"或数字
    int aroundMineNumber; // 周围雷的数目
    ImageIcon mineIcon; // 雷的图标
    boolean isMine = false; // 是否是雷
    boolean isMarked = false; // 是否被标记
    boolean isOpened = false; // 是否被挖开

    public Block() {
        super();
    }

    public Block(String name, int aroundMineNumber, ImageIcon mineIcon, boolean isMine, boolean isMarked, boolean isOpened) {
        super();
        this.name = name;
        this.aroundMineNumber = aroundMineNumber;
        this.mineIcon = mineIcon;
        this.isMine = isMine;
        this.isMarked = isMarked;
        this.isOpened = isOpened;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAroundMineNumber(int n) {
        aroundMineNumber = n;
    }

    public int getAroundMineNumber() {
        return aroundMineNumber;
    }

    public void setMineIcon(ImageIcon icon) {
        mineIcon = icon;
    }

    public ImageIcon getMineicon() {
        return mineIcon;
    }

    public void setIsMine(boolean b) {
        isMine = b;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public void setIsMarked(boolean m) {
        isMarked = m;
    }

    public boolean getIsMarked() {
        return isMarked;
    }

    public void setIsOpened(boolean o) {
        isOpened = o;
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public String toString() {
        return "Block [name=" + name + ", aroundMineNumber=" + aroundMineNumber + ", mineIcon=" + mineIcon + ", isMine="
                + isMine + ", isMark=" + isMarked + ", isOpen=" + isOpened + "]";
    }
}