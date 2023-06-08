package org.principles.openclose;

/**
 * @Author psikun
 * @Description 客户端测试类
 * @Date 2023/06/08/ 16:57
 */
public class Client {
    public static void main(String[] args) {
        // 1. 创建搜狗输入法对象
        SouGouInput input = new SouGouInput();

        // 2. 创建皮肤对象

        //  DefaultSkin skin = new DefaultSkin();
        TestSkin skin = new TestSkin();

        // 3. 设置皮肤
        input.setSkin(skin);

        // 进行展示
        input.display();
    }
}
