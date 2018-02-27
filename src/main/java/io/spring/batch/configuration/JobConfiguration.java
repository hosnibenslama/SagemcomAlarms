/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.batch.configuration;

import io.spring.batch.domain.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hosni
 */

@Configuration
@EnableBatchProcessing
@ImportResource("classpath:/config/config-beans.xml")
public class JobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;



	@Bean
	public PlatformTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public JobRepository jobRepository() {
		try {
			return new MapJobRepositoryFactoryBean(transactionManager())
					.getJobRepository();
		} catch (Exception e) {
			return null;
		}
	}

	@Bean
	public JobLauncher jobLauncher() {
		final SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository());

		return launcher;
	}


	@Bean
	public StaxEventItemReader<Alarm> alarmItemReader()  throws  Exception {

		XStreamMarshaller unmarshaller = new XStreamMarshaller();

		Map<String, Class> aliases = new HashMap<>();
		aliases.put("alarm", Alarm.class);
		aliases.put("dc", DC.class);
		aliases.put("device", Device.class);
		aliases.put("m2M", M2M.class);
		unmarshaller.setAliases(aliases);

		StaxEventItemReader<Alarm> reader = new StaxEventItemReader<>();

		reader.setResource(new ClassPathResource("/data/alarms.xml"));
		reader.setFragmentRootElementName("alarm");
		reader.setUnmarshaller(unmarshaller);

		return reader;
	}

	@Bean
	public ItemWriter<Alarm> alarmItemWriter() throws  Exception {
		return new ItemWriter<Alarm>() {
			@Override
			public void write(List<? extends Alarm> list) throws Exception {

			}

		};


	}

	@Bean
	public Step alarmStep() throws Exception {
		return stepBuilderFactory.get("alarmStep")
				.<Alarm, Alarm>chunk(1000)
				.reader(alarmItemReader())
				.writer(alarmItemWriter())
				.build();
	}

	@Bean
	public Job jobImport() throws Exception {
		return jobBuilderFactory.get("job")
				.start(alarmStep())
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
