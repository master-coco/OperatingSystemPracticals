import java.util.*;
 
public class p6second {
public static void main (String args[])
{
try (Scanner sc = new Scanner(System.in)) {
    System.out.println ("enter no of process:");
    int n= sc.nextInt();
    int pid[] = new int[n]; 
    int at[] = new int[n]; 
    int bt[] = new int[n]; 
    int ct[] = new int[n]; 
    int ta[] = new int[n];
    int wt[] = new int[n];  
    int f[] = new int[n];  
    int k[]= new int[n];  
        int i, st=0, tot=0;
        float avgwt=0, avgta=0;
     
        for (i=0;i<n;i++)
        {
         pid[i]= i+1;
         System.out.println ("enter process " +(i+1)+ " arrival time:");
         at[i]= sc.nextInt();
         System.out.println("enter process " +(i+1)+ " burst time:");
         bt[i]= sc.nextInt();
         k[i]= bt[i];
         f[i]= 0;
        }
        
        while(true){
         int min=99,c=n;
         if (tot==n)
         break;
        
         for ( i=0;i<n;i++)
         {
         if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
         {
         min=bt[i];
         c=i;
         }
         }
        
         if (c==n)
         st++;
         else
         {
         bt[c]--;
         st++;
         if (bt[c]==0)
         {
         ct[c]= st;
         f[c]=1;
         tot++;
         }
         }
        }
        
        for(i=0;i<n;i++)
        {
         ta[i] = ct[i] - at[i];
         wt[i] = ta[i] - k[i];
         avgwt+= wt[i];
         avgta+= ta[i];
        }
        
        System.out.println("pid  arrival  burst  complete turn waiting");
        for(i=0;i<n;i++)
        {
         System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ ta[i] +"\t"+ wt[i]);
        }
        
        System.out.println("\naverage tat is "+ (float)(avgta/n));
        System.out.println("average wt is "+ (float)(avgwt/n));
        sc.close();
}
}
}