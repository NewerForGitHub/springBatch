package springBatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class MyItemWriter2 implements ItemWriter<List<String>> {

    @Override
    public void write(List<? extends List<String>> items) {
        for (List<String> list : items) {
            for (String string : list) {
                System.out.println(string);
            }
        }
        System.out.println("================写入一次");
    }

}
