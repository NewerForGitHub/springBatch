package springBatch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// @WebAppConfiguration//会指定web运行
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJob {
    @Autowired
    private JobLauncher launcher;
    @Autowired
    private Job testJob;

    @Test
    public void runLaunch() {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("DATE", System.currentTimeMillis());
        JobParameters jobParameters = TestJob.getParameters(argMap);
        try {
            this.launcher.run(this.testJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    private static JobParameters getParameters(Map<String, ?> argMap) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

        for (Entry<String, ?> entry : argMap.entrySet()) {
            if (entry.getValue() instanceof Date) {
                jobParametersBuilder.addDate(entry.getKey(),
                    (Date) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                jobParametersBuilder.addDouble(entry.getKey(),
                    (Double) entry.getValue());
            } else if (entry.getValue() instanceof Long) {
                jobParametersBuilder.addLong(entry.getKey(),
                    (Long) entry.getValue());
            } else if (entry.getValue() instanceof String) {
                jobParametersBuilder.addString(entry.getKey(),
                    (String) entry.getValue());
            }
        }

        return jobParametersBuilder.toJobParameters();
    }
}