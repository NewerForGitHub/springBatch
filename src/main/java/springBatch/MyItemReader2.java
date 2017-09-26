package springBatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyItemReader2 implements ItemReader<List<String>> {

    @Override
    public List<String> read() throws UnexpectedInputException, ParseException,
            NonTransientResourceException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("你是" + (100 + i + 1));
        }
        try {
            Thread.sleep(2000);
            System.out
                .println("====================================================睡一会儿");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}
