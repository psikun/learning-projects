package org.principles.openclose;

/**
 * @Author psikun
 * @Description 搜狗输入法
 * @Date 2023/06/08/ 16:56
 */
public class SouGouInput {
    private AbstractSkin skin;

    public void setSkin(AbstractSkin skin) {
        this.skin = skin;
    }

    public void display() {
        skin.display();
    }
}
