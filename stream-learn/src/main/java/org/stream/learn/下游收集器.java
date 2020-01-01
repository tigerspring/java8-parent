package org.stream.learn;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class 下游收集器 {

	public static void main(String[] args) throws Exception {
		Stream<City> cities = readCity();
		
		Map<String,Integer> sumMap = cities.collect(Collectors.groupingBy(City::getState,Collectors.summingInt(City::getPopulation)));
		System.out.println(sumMap);
		
		//取名字最長的城市
		cities = readCity();
		Map<String,Optional<String>> optionMap = cities.collect(
				Collectors.groupingBy(
						City::getState,
						Collectors.mapping(
								City::getName,
								Collectors.maxBy(Comparator.comparing(String::length)
								)
						)
				)
		);
		System.out.println(optionMap);
		/*optionMap.put("河北省", Optional.empty());
		for (String key : optionMap.keySet()) {
			System.err.println(key+"===>"+optionMap.get(key).orElse("沒有信息"));
		}*/
		
		//统计城市人口
		cities = readCity();
		Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(
				Collectors.groupingBy(City::getState,Collectors.summarizingInt(City::getPopulation)));
		System.out.println(stateToCityPopulationSummary.toString());

		//按照省份连接所有城市的名称
		cities = readCity();
		Map<String,String> stateToCityNames = cities.collect(
				Collectors.groupingBy(
						City::getState,
						Collectors.reducing(
								"",
								City::getName,
								(s,t)->s.length()==0? t : t +"," +s))
		);
		System.out.println(stateToCityNames);
		cities = readCity();
		Map<String,String> aaaMap = cities.collect(
				Collectors.groupingBy(
						City::getState,
						Collectors.mapping(
								City::getName,
								Collectors.joining(",")
						)
				)
		);
		System.out.println(aaaMap);
	}
	
	public static Stream<City> readCity() throws Exception{
		return Files.lines(Paths.get("d:/city.txt"))
				.map(l->l.replaceAll("，", ",").split(","))
				.map(a-> new City(a[0],a[1],Integer.parseInt(a[2])));
	}
}

class City{
	
	public City() {
		super();
	}
	public City(String name, String state, int population) {
		super();
		this.name = name;
		this.state = state;
		this.population = population;
	}
	String name;
	String state;
	int population;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
}
