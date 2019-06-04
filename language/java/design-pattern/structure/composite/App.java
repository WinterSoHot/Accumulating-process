package gu.java.pattern.structure.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gudongxian
 * @create 2018-04-26 下午9:11
 **/
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * 组合模式是一个分区设计模式。组合模式描述了一组对象的处理方式与对象的单个实例相同。
     * 组合的意图是将对象“组合”到树结构中，以表示部分整个层次结构。
     * 实现组合模式可以让客户机对单个对象和组合进行统一处理。
     * <p>
     * 在这个例子中，我们有由字母组成的句子。所有对象都可以通过相同的接口({@link LetterComposite})处理。。
     */
    public static void main(String[] args) {
        LOGGER.info("Message from the orcs: ");

        LetterComposite orcMessage = new Messenger().messageFromOrcs();
        orcMessage.print();

        LOGGER.info("\nMessage from the elves: ");

        LetterComposite elfMessage = new Messenger().messageFromElves();
        elfMessage.print();
    }
}
