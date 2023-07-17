// Vedant Kokane
// Roll no - 31430
// LP1 Assignment 1 - First Come First Serve
import java.util.*;
class Process{
    int n;
    int id[];
    int arr[]; // arrive
    int bt[]; // burst
    int ct[]; //compeletion
    int tat[]; // turn around time
    int wt[]; // waiting time
    Process(int x){
        n = x;
        id = new int[n];
        arr = new int[n];
        bt = new int[n];
        ct = new int[n];
        tat = new int[n];
        wt = new int[n];
    }

    void getData(){
        for(int i=0;i<n;i++){
            Scanner in = new Scanner(System.in);
            System.out.println("Enter Process Id , arrival time , burst time - ");
            id[i] = in.nextInt();
            arr[i] = in.nextInt();
            bt[i] = in.nextInt();
        }
        sortData();
    }

    void sortData(){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[j] < arr[i]){
                    int temp = id[i];
                    id[i] = id[j];
                    id[j] = temp;

                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }
    }

    void calculation(){
        for(int i=0;i<n;i++){
            if(i == 0){
                ct[i] = arr[i] + bt[i];
            }
            else{
                if(arr[i] > ct[i-1]){
                    ct[i] = arr[i] + bt[i];
                }
                else{
                    ct[i] = ct[i-1] + bt[i];
                }
            }
            tat[i] = ct[i] - arr[i]; // turn around time
            wt[i] = tat[i] - bt[i]; // waiting time
        }
    }

    void averageCalculation(){
        double waitSum=0,tatSum=0;
        for(int i=0;i<n;i++){
            waitSum = waitSum + wt[i];
            tatSum = tatSum + tat[i];
        }
        double avgWT = waitSum/n;
        double avgTAT = tatSum/n;
        System.out.println("Average Waiting time " + (avgWT));
        System.out.println("Average Turn around time " + (avgTAT));
    }
   
    void displayTable(){
        calculation();
        System.out.println("ID\t\tArrival\t\tBurst\t\tComplete\tTurn\tWaiting");
        for(int i=0;i<n;i++){
            System.out.println(id[i]+"\t\t  "+arr[i]+"\t\t  "+bt[i]+"\t\t  "+ct[i]+"\t\t  "+tat[i]+"\t  "+wt[i]);
        }
    }

}

class Main{
    public static void main(String[] args) {
     Scanner in = new Scanner(System.in);
     System.out.println("Enter total no of processes - ");
     int n = in.nextInt();
     Process p1 = new Process(n);
     char c = 'y';
     while(c != 'n'){
        System.out.println("------------------------------------------------------");
        System.out.println("1. Alot processes");
        System.out.println("2. Display Table");
        System.out.println("3. Average values of wait time and turn around time");
        System.out.println("------------------------------------------------------");
        int ch;
        ch = in.nextInt();
        if(ch == 1){
            p1.getData();
        }
        else if(ch == 2){
            p1.displayTable();
        }
        else if(ch == 3){
            p1.averageCalculation();
        }
        else{
            System.out.println("You have entered a wrong choice.");
        }

        System.out.println("Do you want to continue (y/n) - ");
        c = in.next().charAt(0);
     }

    }
}
