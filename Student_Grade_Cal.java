import java.util.*;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total no. of subjects");
        int n = sc.nextInt();
        int arr[] = new int[n];
        float sum = 0;
        System.out.println("enter the marks of student out of 100");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");// taking  marks by user into an array
            arr[i] = sc.nextInt();
            sum = sum + arr[i];
        }
        // Printing Sum of all subject
        System.out.println("Total marks of student out of (" + n * 100 + ") is:  " + sum);

        float Per = sum / n;
        // printing Percentage
        System.out.println("Percentage is : " + Per);
        if (Per > 90) {
            System.out.println("Grade obtained by student is : A+");
        } else if (Per > 80) {
            System.out.println("Grade obtained by student is : A");
        } else if (Per > 70) {
            System.out.println("Grade obtained by student is : B+");
        } else if (Per > 60) {
            System.out.println("Grade obtained by student is : B");
        } else if (Per > 50) {
            System.out.println("Grade obtained by student is : C+");
        } else if (Per > 40) {
            System.out.println("Grade obtained by student is : C");
        } else {
            System.out.println("Student failed in Exam");
        }

    }

}
