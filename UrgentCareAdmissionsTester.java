
///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           UrgentCareAdmissionsTester Program
// Course:          CS 300, Summer, 2023
//
// Author:          Max Liss-'s-Gravemade
// Email:           lisssgravema@wisc.edu
// Lecturer's Name: Michelle Jensen
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
//
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p1/javadocs/UrgentCareAdmissions.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
/**
 * Designed to test the functionality of the UrgentCareAdmissions class.
 */
public class UrgentCareAdmissionsTester {
	/**
	 * Tests the getAdmissionIndex method of the UrgentCareAdmissions class.
	 * 
	 * @return true if the method works as expected, false otherwise.
	 */
	public static boolean testGetIndex() {
		int[][] samplePatientsList = new int[5][3];
		int size = 0;

		// Test empty list - expect 0 for any triage level
		if (UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, samplePatientsList, size) != 0) {
//			System.out.println("Failed at check 11");
			return false;
		}

		// Add a YELLOW patient and test again
		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		if (UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, samplePatientsList, size) != 0) {
//			System.out.println("Failed at check 2");
			return false;
		}

		if (UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, samplePatientsList, size) != 1) {
//			System.out.println("Failed at check 3");
			return false;
		}

		return true;
	}

	/**
	 * Tests the addPatient method of the UrgentCareAdmissions class.
	 * 
	 * @return true if the method works as expected, false otherwise.
	 */
	public static boolean testAddPatient() {
		int[][] samplePatientsList = new int[5][3];
		int size = 0;

		// Add a patient
		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		if (size != 1)
			return false;
		if (samplePatientsList[0][0] != 1)
			return false;

		return true;
	}

	/**
	 * Tests the removeNextPatient method of the UrgentCareAdmissions class.
	 * 
	 * @return true if the method works as expected, false otherwise.
	 */
	public static boolean testRemovePatient() {
		int[][] samplePatientsList = new int[5][3];
		int size = 0;

		// Remove from empty list
		size = UrgentCareAdmissions.removeNextPatient(samplePatientsList, size);
		if (size != 0)
			return false;

		// Add a patient and then remove
		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		size = UrgentCareAdmissions.removeNextPatient(samplePatientsList, size);
		if (size != 0)
			return false;

		return true;
	}

	/**
	 * Tests the getPatientIndex method of the UrgentCareAdmissions class.
	 * 
	 * @return true if the method works as expected, false otherwise.
	 */
	public static boolean testGetPatientIndex() {
		int[][] samplePatientsList = new int[5][3];
		int size = 0;

		// Find in empty list
		if (UrgentCareAdmissions.getPatientIndex(1, samplePatientsList, size) != -1)
			return false;

		// Add a patient and then search
		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		if (UrgentCareAdmissions.getPatientIndex(1, samplePatientsList, size) != 0)
			return false;

		return true;
	}

	/**
	 * Tests the getLongestWaitingPatientIndex method of the UrgentCareAdmissions
	 * class.
	 * 
	 * @return true if the method works as expected, false otherwise.
	 */
	public static boolean testLongestWaitingPatient() {
		int[][] samplePatientsList = new int[5][3];
		int size = 0;

		// Empty list
		if (UrgentCareAdmissions.getLongestWaitingPatientIndex(samplePatientsList, size) != -1)
			return false;

		// Add a patient and check
		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		if (UrgentCareAdmissions.getLongestWaitingPatientIndex(samplePatientsList, size) != 0)
			return false;

		return true;
	}

	/**
	 * Tests scenarios with empty and full patient arrays.
	 * 
	 * @return true if the related methods work as expected in these scenarios,
	 *         false otherwise.
	 */
	public static boolean testEmptyAndFullArrays() {
		int[][] samplePatientsList = new int[2][3];
		int size = 0;

		// Add to full array
		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		size = UrgentCareAdmissions.addPatient(new int[] { 2, 10, UrgentCareAdmissions.RED }, 0, samplePatientsList,
				size);
		size = UrgentCareAdmissions.addPatient(new int[] { 3, 15, UrgentCareAdmissions.GREEN }, 0, samplePatientsList,
				size);

		// Size should not increase after array is full
		if (size != 2)
			return false;

		return true;
	}

	/**
	 * Tests the getSummary method of the UrgentCareAdmissions class.
	 * 
	 * @return true if the method works as expected, false otherwise.
	 */
	public static boolean testGetSummary() {
		int[][] samplePatientsList = new int[5][3];
		int size = 0;

		size = UrgentCareAdmissions.addPatient(new int[] { 1, 5, UrgentCareAdmissions.YELLOW }, 0, samplePatientsList,
				size);
		size = UrgentCareAdmissions.addPatient(new int[] { 2, 10, UrgentCareAdmissions.RED }, 0, samplePatientsList,
				size);

		String expected = "Total number of patients: 2\nRED: 1\nYELLOW: 1\nGREEN: 0";
		String result = UrgentCareAdmissions.getSummary(samplePatientsList, size);
		if (!result.equals(expected))
			return false;

		return true;
	}

	/**
	 * Runs each of the tester methods and displays their result
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("get index: " + testGetIndex());
		System.out.println("add patient: " + testAddPatient());
		System.out.println("remove patient: " + testRemovePatient());
		System.out.println("get patient: " + testGetPatientIndex());
		System.out.println("longest wait: " + testLongestWaitingPatient());
		System.out.println("empty/full: " + testEmptyAndFullArrays());
		System.out.println("get summary: " + testGetSummary());
	}
}
