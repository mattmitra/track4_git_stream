package org.ssglobal.training.codes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Score {

	private List<ScoreInfo> profileList = new ArrayList<>();

	public Score() {
		profileList.add(new ScoreInfo("Smith", "John", 70));
		profileList.add(new ScoreInfo("Doe", "Mary", 85));
		profileList.add(new ScoreInfo("Page", "Alice", 82));
		profileList.add(new ScoreInfo("Cooper", "Jill", 97));
		profileList.add(new ScoreInfo("Flintstone", "Fred", 66));
		profileList.add(new ScoreInfo("Rubble", "Barney", 80));
		profileList.add(new ScoreInfo("Smith", "Judy", 48));
		profileList.add(new ScoreInfo("Dean", "James", 90));
		profileList.add(new ScoreInfo("Russ", "Joe", 55));
		profileList.add(new ScoreInfo("Wolfe", "Bill", 73));
		profileList.add(new ScoreInfo("Dart", "Mary", 54));
		profileList.add(new ScoreInfo("Rogers", "Chris", 78));
		profileList.add(new ScoreInfo("Toole", "Pat", 51));
		profileList.add(new ScoreInfo("Khan", "Omar", 93));
		profileList.add(new ScoreInfo("Smith", "Ann", 95));
	}

	public int getNumScores() {
		return (int) profileList.stream().count();
	}

	private Function<ScoreInfo, Integer> mapScoreInfoToScore = (s) -> {
		return s.getScore();
	};

	public double getAverage() {
		return profileList.stream().map(mapScoreInfoToScore).mapToInt(Integer::intValue).average().orElse(0);
	}

	// A listers
	private Predicate<ScoreInfo> filterAstudents = (s) -> {
		if (s.getScore() >= 90) {
			return true;
		} else {
			return false;
		}
	};

	public int getNumberAListers() {
		return (int) profileList.stream().filter(filterAstudents).count();
	}

	// F listers
	private Predicate<ScoreInfo> filterFstudents = (s) -> {
		if (s.getScore() < 70) {
			return true;
		} else {
			return false;
		}
	};

	public List<ScoreInfo> getFailingStudentList() {
		return profileList.stream().filter(filterFstudents).collect(Collectors.toList());
	}

	// Passers
	private Predicate<ScoreInfo> filterPassers = (s) -> {
		if (s.getScore() >= 70) {
			return true;
		} else {
			return false;
		}
	};

	public void printPassingStudents() {
		System.out.println(profileList.stream().filter(filterPassers).collect(Collectors.toList()).toString());
	}

	// Sort by last name
	private Comparator<ScoreInfo> sortedLastName = (sx, sy) -> {
		if (sx.getLastName().compareTo(sy.getLastName()) == 0) {
			return 0;
		} else if (sx.getLastName().compareTo(sy.getLastName()) < 0) {
			return -2;
		} else {
			return 2;
		}
	};

	public void displayAllStudents() {
		profileList.stream().sorted(sortedLastName).collect(Collectors.toList()).forEach(
				(s) -> System.out.println(String.join(" ", s.getFirstName(), s.getLastName(), String.valueOf(s.getScore())))
				);
	}

	// Sort by score
	private Comparator<ScoreInfo> sortedScore = (sx, sy) -> {
		if (sx.getScore() == sy.getScore()) {
			return 0;
		} else if (sx.getScore() < sy.getScore()) {
			return -2;
		} else {
			return 2;
		}
	};
	
	public List<String> getStudentRecords() {
		List<String> temp = new ArrayList<>();
		profileList.stream().sorted(sortedScore).collect(Collectors.toList()).forEach(
				(s) -> temp.add(String.join(" ", s.getFirstName(), s.getLastName(), String.valueOf(s.getScore())))
				);
		return temp;
	}

}
