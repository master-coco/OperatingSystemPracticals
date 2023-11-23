import java.util.*;

public class p6 {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int n;
		int[][] A = new int[100][4];
		int total = 0;
		float avg_wt, avg_tat;
		System.out.println("Enter number of process:");
		n = input.nextInt();
		System.out.println("Enter Burst Time:");
		for (int i = 0; i < n; i++) {
			System.out.print("P" + (i + 1) + ": ");
			A[i][1] = input.nextInt();
			A[i][0] = i + 1;
		}
		for (int i = 0; i < n; i++) {
			int index = i;
			for (int j = i + 1; j < n; j++) {
				if (A[j][1] < A[index][1]) {
					index = j;
				}
			}
			int temp = A[i][1];
			A[i][1] = A[index][1];
			A[index][1] = temp;
			temp = A[i][0];
			A[i][0] = A[index][0];
			A[index][0] = temp;
		}
		A[0][2] = 0;
		for (int i = 1; i < n; i++) {
			A[i][2] = 0;
			for (int j = 0; j < i; j++) {
				A[i][2] += A[j][1];
			}
			total += A[i][2];
		}
		avg_wt = (float)total / n;
		total = 0;
		System.out.println("P\tBT\tWT\tTAT");
		for (int i = 0; i < n; i++) {
			A[i][3] = A[i][1] + A[i][2];
			total += A[i][3];
			System.out.println("P" + A[i][0] + "\t"
							+ A[i][1] + "\t" + A[i][2]
							+ "\t" + A[i][3]);
		}
		avg_tat = (float)total / n;
		System.out.println("Average Waiting Time= "
						+ avg_wt);
		System.out.println("Average Turnaround Time= "
						+ avg_tat);
        input.close();
	}
}
