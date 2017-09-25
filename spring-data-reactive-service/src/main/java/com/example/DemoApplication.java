/*
 * Copyright 2017 the original author or authors.
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
package com.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(considerNestedRepositories = true)
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String[] names = { "Eddard", "Catelyn", "Jon", "Rob", "Sansa", "Aria", "Bran", "Rickon" };

		Random ramdom = new Random();
		Flux<Person> starks = Flux.fromStream(Stream.generate(() -> names[ramdom.nextInt(names.length)]).map(Person::new));

		// TODO: we actually need to to something with the people here !

		System.out.println("Winter is Coming!");
	}

	@Document
	@Data
	@RequiredArgsConstructor
	static class Person {

		@Id String id;
		final String name;
	}
}