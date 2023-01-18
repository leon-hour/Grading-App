
class StudentGradeApp {
	private String studentGrade = "";

	public String grade(double points) {
		if (points <= 100 && points >= 90) {
			studentGrade = "A";
		} else if (points < 90 && points >= 80) {
			studentGrade = "B";
		} else if (points < 80 && points >= 70) {
			studentGrade = "C";
		} else if (points < 70 && points >= 60) {
			studentGrade = "D";
		} else if (points < 60 && points >= 0) {
			studentGrade = "F";
		} else {
			studentGrade = "";
			throw new IllegalArgumentException(
					"Error!!! The number you enter should be between 0 and 100. Run again out application. If this error appears....");
		}
		return studentGrade;
	}
}
