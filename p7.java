import java.util.*;

class Process {
  int pid;
  int bt;
  int priority;

  Process(int pid, int bt, int priority) {
    this.pid = pid;
    this.bt = bt;
    this.priority = priority;
  }

  public int prior() {
    return priority;
  }
}

public class p7 {

  public void findWaitingTime(Process proc[], int n,
      int wt[]) {

    wt[0] = 0;

    for (int i = 1; i < n; i++)
      wt[i] = proc[i - 1].bt + wt[i - 1];
  }

  public void findTurnAroundTime(Process proc[], int n,
      int wt[], int tat[]) {
    for (int i = 0; i < n; i++)
      tat[i] = proc[i].bt + wt[i];
  }

  public void findavgTime(Process proc[], int n) {
    int wt[] = new int[n], tat[] = new int[n],
        total_wt = 0, total_tat = 0;

    findWaitingTime(proc, n, wt);

    findTurnAroundTime(proc, n, wt, tat);

    System.out.print(
        "\nProcesses   Burst time   Waiting time   Turn around time\n");

    for (int i = 0; i < n; i++) {
      total_wt = total_wt + wt[i];
      total_tat = total_tat + tat[i];
      System.out.print(" " + proc[i].pid + "\t\t"
          + proc[i].bt + "\t " + wt[i]
          + "\t\t " + tat[i] + "\n");
    }

    System.out.print("\nAverage waiting time = "
        + (float) total_wt / (float) n);
    System.out.print("\nAverage turn around time = "
        + (float) total_tat / (float) n);
  }

  public void priorityScheduling(Process proc[], int n) {

    Arrays.sort(proc, new Comparator<Process>() {
      @Override
      public int compare(Process a, Process b) {
        return b.prior() - a.prior();
      }
    });
    System.out.print(
        "Order in which processes gets executed \n");
    for (int i = 0; i < n; i++)
      System.out.print(proc[i].pid + " ");

    findavgTime(proc, n);
  }

  public static void main(String[] args) {
    p7 ob = new p7();
    int n = 3;
    Process proc[] = new Process[n];
    proc[0] = new Process(1, 10, 2);
    proc[1] = new Process(2, 5, 0);
    proc[2] = new Process(3, 8, 1);
    ob.priorityScheduling(proc, n);
  }
}