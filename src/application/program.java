package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter full file path");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}

			System.out.println("Enter salary: ");
			double salary = sc.nextDouble();

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> list2 = list.stream()
					.filter(x -> x.getSalary() > salary)
					.map(x -> x.getEmail()).sorted(comp)
					.collect(Collectors.toList());

			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			list2.forEach(System.out::println);

			Double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0,(x, y) -> x + y);

			System.out.println("Sum of salary of people whose name stars with 'M' : " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
