import java.sql.*;
import java.util.Scanner;

class DataBaseJava {

	static Scanner optionin = new Scanner(System.in);

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		try {
			int option = 0;
			final String FILE_PATH = "/StudentsInfo.txt";
			do {
				System.out.println("please enter the name of subject: ");
				String subject = optionin.next();

				Student employee = new Student(subject);
				GradeStudents gradeStudent = new GradeStudents(subject);
				do {
					System.out.println("Select an operation \n 1->Insert new Student \n 2->Update department "
							+ "\n 3->Delete a record \n 4->Search for a Student \n 5->Delete students with grade "
							+ "\n 6->Import students data from a txt file \n 7->Grade students "
							+ "\n 8-> Filter the data based in grade \n 9->Get grade average \n 0->Exit");

					option = optionin.nextInt();
					switch (validateInputOption(option)) {
					case INSERT:
						employee.insertStudent();
						break;
					case GRADE_STUDENTS:
						gradeStudent.insertGrade();
						break;
					case UPDATE:
						employee.updateStudentDepartment();
						break;
					case DELETE:
						employee.deleteStudentRecord();
						break;
					case SEARCH:
						employee.searchStudent();
						break;
					case FILTER_DATA_BASED_IN_GRADE:
						gradeStudent.filterDataBasedInGrade();
						break;
					case GET_AVERAGE_GRADE:
						gradeStudent.getAverageGrade();
						break;
					case IMPORT_EMPLOYEE_FROM_TXT_FILE:
						employee.importEmloyee(FILE_PATH);
						break;
					case DELETE_STUDENT_WITH_GRADE:
						gradeStudent.deleteStudentsWithGrade();
						break;
					case EXIT:
						checkForAnotherUse();
						break;
					default:
						throw new IllegalArgumentException("Invalid Input");
					}
				} while (option != 0);
			} while (option != 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void checkForAnotherUse()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("If you want to use our app for other subject please enter 10:");
		int option = optionin.nextInt();
		if (option == 10) {
			DataBaseJava.main(null);
		} else {
			System.out.println("Thanks for using our app.");
		}
	}

	public static Options validateInputOption(int optionId) {
		switch (optionId) {
		case 1:
			return Options.INSERT;
		case 2:
			return Options.UPDATE;
		case 3:
			return Options.DELETE;
		case 4:
			return Options.SEARCH;
		case 5:
			return Options.DELETE_STUDENT_WITH_GRADE;
		case 6:
			return Options.IMPORT_EMPLOYEE_FROM_TXT_FILE;
		case 7:
			return Options.GRADE_STUDENTS;
		case 8:
			return Options.FILTER_DATA_BASED_IN_GRADE;
		case 9:
			return Options.GET_AVERAGE_GRADE;
		case 0:
			return Options.EXIT;
		default:
			throw new IllegalArgumentException("Invalid Input");
		}
	}
}