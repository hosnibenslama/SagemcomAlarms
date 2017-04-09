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
import io.spring.batch.reader.JobItemReader;
import io.spring.batch.reader.ModelFileItemReader;
import io.spring.batch.services.*;
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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Minella
 */

@Configuration
@EnableBatchProcessing
@EnableJpaRepositories("io.spring.repositories")
@ComponentScan("io.spring.batch")
@ImportResource("classpath:/config/config-beans.xml")
public class JobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;


	@Autowired
	public CustomerService customerService;

	@Autowired
	public BookService bookService;

	@Autowired
	public BookCategoryService bookCategoryService;

	@Autowired
	public AuteurService auteurService;

	@Autowired
	public ThemeService themeService;



	@Autowired
	public JobItemReader jobItemReader;


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
	public JobItemReader<Book> bookItemReader()  throws Exception {

    	//List<Customer> list = customerService.findAll();

		//Long iden = jobItemReader.getId();

		//List<Customer> list = customerService.findById(iden);

		/*System.out.println("__________________________________________________________________________ here hhhhhh");

		System.out.println(iden);

		Date date = jobItemReader.getDate();

		System.out.println(date);*/

		List<Book> list = bookService.findAll();

		for (Book item : list ) {


			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX" + item);

		}


		return new JobItemReader(list);
	}

	@Bean
	public ItemReader<ModelFile> modelFileItemReader()  throws Exception {

		//List<Customer> list = customerService.findAll();

		//Long iden = jobItemReader.getId();

		//List<Customer> list = customerService.findById(iden);

		/*System.out.println("__________________________________________________________________________ here hhhhhh");

		System.out.println(iden);

		Date date = jobItemReader.getDate();

		System.out.println(date);*/



		/*return new ItemReader<ModelFile>() {
			@Override
			public ModelFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				List<Book> listBook = bookService.findAll();
				List<BookCategory> listBookCategory= bookCategoryService.findAll();
				List<Theme> listTheme = themeService.findAll();
				List<Auteur> listAuteur = auteurService.findAll();

				ModelFile modelFile = new ModelFile();

				System.out.println("________________________________________________________________________________________");

				modelFile.setListAuteur(listAuteur);
				modelFile.setListBook(listBook);
				modelFile.setListBookCategory(listBookCategory);
				modelFile.setListTheme(listTheme);
				return modelFile;
			}
		};*/
		List<Book> listBook = bookService.findAll();
		List<BookCategory> listBookCategory= bookCategoryService.findAll();
		List<Theme> listTheme = themeService.findAll();
		List<Auteur> listAuteur = auteurService.findAll();

		ModelFile modelFile = new ModelFile();

		System.out.println("________________________________________________________________________________________");

		modelFile.setListAuteur(listAuteur);
		modelFile.setListBook(listBook);
		modelFile.setListBookCategory(listBookCategory);
		modelFile.setListTheme(listTheme);



	ModelFileItemReader modelFileItemReader= new ModelFileItemReader();

	modelFileItemReader.setModelFile(modelFile);

	modelFileItemReader.initialize();



	return  modelFileItemReader;



	}

	/*@Bean
	public ItemWriter<Customer> customerItemWriter() {
		return items -> {
			for (Customer item : items) {


				System.out.println(item.toString());

			}
		};
	}*/
	@Bean
	public StaxEventItemWriter<Book> bookItemWriter() throws Exception {

		//XStreamMarshaller marshaller = new XStreamMarshaller();

	/*	Map<String, Class> aliases = new HashMap<>();
		aliases.put("customer", Customer.class);

		marshaller.setAliases(aliases);
*/
		StaxEventItemWriter<Book> itemWriter = new StaxEventItemWriter<>();

		itemWriter.setRootTagName("books");
		itemWriter.setMarshaller(marshaller());
		//String customerOutputPath = File.createTempFile("customerOutput", ".xml").getAbsolutePath();
		File file = new File("C:\\Users\\Hosni\\Desktop\\Utilities\\new\\Writing Xml\\src\\main\\resources\\data\\customerOutput.xml");
		String bookOutputPath=file.getAbsolutePath();
		//System.out.println(">> Output Path: " + customerOutputPath);
		itemWriter.setResource(new FileSystemResource(bookOutputPath));
		//itemWriter.toString();

		itemWriter.afterPropertiesSet();

		return itemWriter;
	}

	@Bean
	public Marshaller marshaller() {
		XStreamMarshaller marshaller = new XStreamMarshaller();

		Map<String, Class> aliases = new HashMap<>();
		aliases.put("classeModele", ModelFile.class);

		marshaller.setAliases(aliases);

		return marshaller;
	}

	@Bean
	public StaxEventItemWriter<ModelFile> modelFileItemWriter() throws Exception {

		//XStreamMarshaller marshaller = new XStreamMarshaller();

	/*	Map<String, Class> aliases = new HashMap<>();
		aliases.put("customer", Customer.class);

		marshaller.setAliases(aliases);
*/
		StaxEventItemWriter<ModelFile> itemWriter = new StaxEventItemWriter<>();

		itemWriter.setRootTagName("books");
		itemWriter.setMarshaller(marshaller());
		//String customerOutputPath = File.createTempFile("customerOutput", ".xml").getAbsolutePath();
		File file = new File("C:\\Users\\Hosni\\Desktop\\Utilities\\new\\Writing Xml\\src\\main\\resources\\data\\customerOutput.xml");
		String bookOutputPath=file.getAbsolutePath();
		//System.out.println(">> Output Path: " + customerOutputPath);
		itemWriter.setResource(new FileSystemResource(bookOutputPath));
		//itemWriter.toString();

		itemWriter.afterPropertiesSet();

		return itemWriter;
	}




	@Bean
	public StaxEventItemReader<Book> bookItemReader2()  throws  Exception {

		XStreamMarshaller unmarshaller = new XStreamMarshaller();



		Map<String, Class> aliases = new HashMap<>();
		aliases.put("bookCategory", BookCategory.class);

		aliases.put("book", Book.class);

		aliases.put("theme", Theme.class);

		aliases.put("auteur", Auteur.class);

		unmarshaller.setAliases(aliases);

		StaxEventItemReader<Book> reader = new StaxEventItemReader<>();

		reader.setResource(new ClassPathResource("/data/customerOutput.xml"));
		reader.setFragmentRootElementName("book");
		reader.setUnmarshaller(unmarshaller);


		return reader;
	}

	@Bean
	public ItemWriter<Book> bookItemWriter2() throws  Exception {
		return items -> {
			for (Book item : items) {
				//System.out.println(item.toString());
				//bookService.save(item);
				System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY" + item.getBookCategory());
				//bookCategoryService.save(item.getBookCategory());
				auteurService.save(item.getAuteur());
				themeService.save(item.getBookCategory().getTheme());



			}
		};
	}

	@Bean
	public Step step2() throws Exception {
		return stepBuilderFactory.get("step1")
				.<Book, Book>chunk(20915)
				.reader(bookItemReader2())
				.writer(bookItemWriter2())
				.build();
	}

	@Bean
	public Job jobImport() throws Exception {
		return jobBuilderFactory.get("job")
				.start(step2())
				.incrementer(new RunIdIncrementer())
				.build();
	}

	@Bean
	public Step step1()  throws Exception {
		return stepBuilderFactory.get("step1")
				.<Book, Book>chunk(1)
				.reader(bookItemReader())
				.writer(bookItemWriter())
				.build();

	}

	@Bean
	public Step stepModelFile()  throws Exception {
		return stepBuilderFactory.get("step1")
				.<ModelFile, ModelFile>chunk(1)
				.reader(modelFileItemReader())
				.writer(modelFileItemWriter())
				.build();

	}



	@Bean
	public Job jobExport() throws Exception {
		return jobBuilderFactory.get("job")
				.start(step1())
				.incrementer(new RunIdIncrementer())
				.build();
	}

	@Bean
	public Job modelFileJobExport() throws Exception {
		return jobBuilderFactory.get("jobexportModelFile")
				.start(stepModelFile())
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
