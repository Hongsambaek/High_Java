package HomeWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeWork01 {
	public static void main(String[] args) {

		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("201502488", "김주엽", 80, 95, 75));
		stuList.add(new Student("201402188", "하준수", 90, 85, 85));
		stuList.add(new Student("201302675", "김소연", 80, 65, 95));
		stuList.add(new Student("201902488", "김민지", 80, 55, 90));
		stuList.add(new Student("202002488", "김대엽", 70, 75, 80));
		stuList.add(new Student("201302488", "김주형", 80, 90, 70));
		stuList.add(new Student("202102310", "김연주", 90, 80, 60));
		stuList.add(new Student("202302488", "김주현", 80, 85, 70));

		System.out.println("Student");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────");

		Collections.sort(stuList);
		System.out.println("학번의 오름차순");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("─────────────────────────────────────────────────────────────────────────────────────────");
		Collections.sort(stuList, new sumDesc());
		System.out.println("등수");
		int rank= 1;
		for (Student stu : stuList) {
			stu.setRank(rank++);
			System.out.println(stu);
		}
	}

}

class sumDesc implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		if (s1.getSum() > s2.getSum()) {
			return -1;
		} else if (s1.getSum() < s2.getSum()) {
			return 1;
		} else {
			if (s1.getNo().compareTo(s2.getNo()) == 1) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}

class Student implements Comparable<Student> {
	private String no;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;

	public Student(String no, String name, int kor, int eng, int math) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [학번 : " + no + ", 이름 : " + name + ", 국어 : " + kor + ", 영어 : " + eng + ", 수학 : " + math
				+ ", 총점 : " + sum + ", 등수 : " + rank + "]";
	}

	@Override
	public int compareTo(Student stu) {

		return this.getNo().compareTo(stu.no);
	}

}
