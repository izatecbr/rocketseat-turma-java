package com.example.demo.config;

import com.example.demo.config.listener.ArquivoProcessadoListener;
import com.example.demo.model.Venda;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.format.DateTimeFormatter;

@Configuration
public class BatchConfig {

    @Value("${input.folder}")
    private String inputFolder;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;

    public BatchConfig(JobRepository jobRepository,
                       PlatformTransactionManager transactionManager,
                       EntityManagerFactory entityManagerFactory) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public FlatFileItemReader<Venda> leitorVenda() {
        VendaFieldSetMapper vendaFieldSetMapper = new VendaFieldSetMapper();
        return new FlatFileItemReaderBuilder<Venda>()
                .name("leitorVenda")
                .resource(new FileSystemResource(inputFolder + "/vendas.csv"))
                .delimited()
                .delimiter(";")
                .names("idTerminal","numeroTransacao", "dataMovimento", "valorVenda", "formaPagamento")
                .fieldSetMapper(vendaFieldSetMapper)
                .build();
    }

    @Bean
    public JpaItemWriter<Venda> writerVenda() {
        JpaItemWriter<Venda> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Venda, Venda>chunk(10, transactionManager)
                .reader(leitorVenda())
                .writer(writerVenda())
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(1)
                .listener(new ArquivoProcessadoListener(inputFolder, inputFolder + "/processados"))
                .build();
    }

    @Bean
    public Job importVendasJob(Step step1) {
        return new JobBuilder("importVendasJob", jobRepository)
                .start(step1)
                .build();
    }
}

