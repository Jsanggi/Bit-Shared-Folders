package com.adacho.main;

import com.adacho.module.JobFactory;

public class ProjectMain {

	public static void main(String[] args) {

		DoJdbcWork toDo = new DoJdbcWork(new JobFactory());
		toDo.doWork();
	}

}
