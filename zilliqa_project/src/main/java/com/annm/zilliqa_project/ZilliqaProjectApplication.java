package com.annm.zilliqa_project;

import com.annm.zilliqa_project.service.BigQueryService;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ZilliqaProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ZilliqaProjectApplication.class, args);

		boolean exit = false;
		Job job = context.getBean("runJob", Job.class);
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);

		String VMArgumentsList[] = ManagementFactory.getRuntimeMXBean().getInputArguments().toArray(new String[0]);
		List<String> JobList = new ArrayList<String>();
		for (String s : VMArgumentsList) {
			if (s.contains("-Djob")){
				int equalIndex = s.indexOf("=");
				s = s.substring(2, equalIndex);
				JobList.add(s);
			}
		}
		for (String s : JobList) {
			String choice = System.getProperty(s);
			switch (choice) {
				case "BQToDB":
					runJob(jobLauncher, job);
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
					break;
			}
		}

//		context.close();


	}

	public static void runJob(JobLauncher jobLauncher, Job job) {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis()).toJobParameters();
		try {
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	static String fullVMArguments() {
//		String name = javaVmName();
//		return (contains(name, "Server") ? "-server "
//				: contains(name, "Client") ? "-client " : "")
//				+ joinWithSpace(vmArguments());
//	}
//
//	static List<String> vmArguments() {
//		return ManagementFactory.getRuntimeMXBean().getInputArguments();
//	}
//
//	static boolean contains(String s, String b) {
//		return s != null && s.indexOf(b) >= 0;
//	}
//
//	static String javaVmName() {
//		return System.getProperty("java.vm.name");
//	}
//
//	static String joinWithSpace(Collection<String> c) {
//		return join(" ", c);
//	}
//
//	public static String join(String glue, Iterable<String> strings) {
//		if (strings == null) return "";
//		StringBuilder buf = new StringBuilder();
//		Iterator<String> i = strings.iterator();
//		if (i.hasNext()) {
//			buf.append(i.next());
//			while (i.hasNext())
//				buf.append(glue).append(i.next());
//		}
//		return buf.toString();
//	}




}

