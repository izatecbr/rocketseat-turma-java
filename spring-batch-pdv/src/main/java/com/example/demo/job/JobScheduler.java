package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final Job importVendasJob;

    public JobScheduler(JobLauncher jobLauncher, Job importVendasJob) {
        this.jobLauncher = jobLauncher;
        this.importVendasJob = importVendasJob;
    }

    //@Scheduled(cron = "0 0 1 * * *") // Executa diariamente às 01h00
    @Scheduled(cron = "0/15 * * * * *")
    public void executarJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("execucao", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(importVendasJob, params);
    }
}
