package io.spring.batch;

import io.spring.batch.configuration.JobConfiguration;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;


public class WritingXmlApplication {

    final static Logger logger = Logger.getLogger("WritingXmlApplication");


    public static void main(String[] args) throws Exception {

        String date_s = "2016-10-10 00:00:00.0";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date date = dt.parse(date_s);
        Long id= 1582L;
        JobParameters parameters = new JobParametersBuilder().addLong("identifiant",id).addDate("date",date).toJobParameters();
        ApplicationContext appContext = new AnnotationConfigApplicationContext(
                JobConfiguration.class);
        JobConfiguration jobConfiguration = appContext.getBean(JobConfiguration.class);
        JobLauncher launcher = jobConfiguration.jobLauncher();

       try {
                // launch the Job
                long startTime = System.currentTimeMillis();
                launcher.run(jobConfiguration.jobImport(), new JobParameters());
                long endTime = System.currentTimeMillis();
                double seconds = (endTime - startTime) / 1000.0;
                logger.info("Import duration : " + seconds + " seconds");
            } catch (JobExecutionAlreadyRunningException | JobRestartException
                    | JobInstanceAlreadyCompleteException
                    | JobParametersInvalidException e) {
                e.printStackTrace();
            }

    }
}
