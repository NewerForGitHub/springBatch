package springBatch;

import java.util.List;

import org.springframework.batch.core.step.item.DefaultItemFailureHandler;

public class ExceptionListener extends DefaultItemFailureHandler {

    @Override
    public void onWriteError(Exception ex, List<? extends Object> items) {
        System.out.println("写入失败");
    }

    @Override
    public void onReadError(Exception ex) {
        System.out.println("读取失败");
    }

}
