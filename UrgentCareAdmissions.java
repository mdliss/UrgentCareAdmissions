///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           UrgentCareAdmissions Program
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
 * Represents the system for managing the admissions in an urgent care facility.
 * This system tracks patients based on their urgency level and assists in
 * managing their order and summary details.
 * 
 * The constants RED, YELLOW, and GREEN are used to represent the urgency levels
 * with RED being the highest urgency and GREEN the lowest.
 * 
 * 
 * @author Max Liss-'s-Gravemade
 * @version 1.0
 */
public class UrgentCareAdmissions {
	/**
	 * Represents the highest urgency level.
	 */
	public static final int RED = 0;
	/**
	 * Represents the medium urgency level.
	 */
	public static final int YELLOW = 1;
	/**
	 * Represents the lowest urgency level.
	 */
	public static final int GREEN = 2;

	/**
	 * Determines the appropriate index in the patients list to insert a patient
	 * based on their urgency level.
	 * 
	 * @param triage       The urgency level of the patient.
	 * @param patientsList The current list of patients.
	 * @param size         The number of patients in the list.
	 * @return The index to insert the patient, or -1 if list is full or invalid
	 *         triage.
	 */
	public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
		if (size >= patientsList.length || triage < RED || triage > GREEN) {
			return -1;
		}

		int index = 0;
		while (index < size && triage >= patientsList[index][2]) {
			index++;
		}

		return index;
	}

	/**
	 * Inserts a patient record at a specified position in the patient list.
	 * 
	 * @param patientRecord The record of the patient to add.
	 * @param index         The desired position to insert the patient.
	 * @param patientsList  The current list of patients.
	 * @param size          The number of patients in the list.
	 * @return The updated size of the list after insertion.
	 */

	public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
		if (patientRecord == null || patientRecord.length != 3 || size >= patientsList.length || index < 0
				|| index > size) {
			return size;
		}

		for (int i = size - 1; i >= index; i--) {
			patientsList[i + 1] = patientsList[i];
		}

		patientsList[index] = patientRecord;
		return size + 1;
	}

	/**
	 * Removes the next patient from the list.
	 * 
	 * @param patientsList The current list of patients.
	 * @param size         The number of patients in the list.
	 * @return The updated size of the list after removal.
	 */
	public static int removeNextPatient(int[][] patientsList, int size) {
		if (size <= 0) {
			return size;
		}

		for (int i = 0; i < size - 1; i++) {
			patientsList[i] = patientsList[i + 1];
		}
		return size - 1;
	}

	/**
	 * Searches for a patient based on their case ID and returns their index.
	 * 
	 * @param caseID       The case ID of the desired patient.
	 * @param patientsList The current list of patients.
	 * @param size         The number of patients in the list.
	 * @return The index of the patient, or -1 if not found.
	 */
	public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
		for (int i = 0; i < size; i++) {
			if (patientsList[i][0] == caseID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finds the patient who has been waiting the longest.
	 * 
	 * @param patientsList The current list of patients.
	 * @param size         The number of patients in the list.
	 * @return The index of the longest waiting patient, or -1 if list is empty.
	 */
	public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
		if (size <= 0) {
			return -1;
		}
		int longestWaitingIndex = 0;
		for (int i = 1; i < size; i++) {
			if (patientsList[i][1] < patientsList[longestWaitingIndex][1]) {
				longestWaitingIndex = i;
			}
		}
		return longestWaitingIndex;
	}

	/**
	 * Generates a summary of the patient list.
	 * 
	 * @param patientsList The current list of patients.
	 * @param size         The number of patients in the list.
	 * @return A formatted summary string.
	 */
	public static String getSummary(int[][] patientsList, int size) {
		int[] triageCounts = new int[3];

		for (int i = 0; i < size; i++) {
			int triage = patientsList[i][2];
			if (triage >= RED && triage <= GREEN) {
				triageCounts[triage]++;
			}
		}

		return String.format("Total number of patients: %d\nRED: %d\nYELLOW: %d\nGREEN: %d", size, triageCounts[RED],
				triageCounts[YELLOW], triageCounts[GREEN]);
	}
}
