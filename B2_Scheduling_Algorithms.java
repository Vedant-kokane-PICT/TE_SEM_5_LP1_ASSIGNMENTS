// Vedant Kokane
// Roll no - 31430
// LP1 Scheduling Assignment  - 
/*
Algorithms -
1. First Come First Serve (FCFS)
2. SJF (Preemptive)
3. Priority (Non-Preemptive)
4. Round Robin (Preemptive)
*/

package Program;
import java.util.*;


class FCFS{
    int n;
    int arr[]; // arrive
    int bt[]; // burst
    int ct[]; //compeletion
    int tat[]; // turn around time
    int wt[]; // waiting time
    String id[];
    
    FCFS(int x){
        n = x;
        id = new String[n];
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
            id[i] = in.nextLine();
            arr[i] = in.nextInt();
            bt[i] = in.nextInt();
        }
        sortData();
    }

    void sortData(){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[j] < arr[i]){
                    String temp1 = id[i];
                    id[i] = id[j];
                    id[j] = temp1;

                    int temp;	
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
        System.out.println(" ");
    }

}



class SJF{
	// Preemptive
	int n;
    String id[];
    int arr[]; // arrive
    int bt[]; // burst
    int ct[]; //compeletion
    int tat[]; // turn around time
    int wt[]; // waiting time
    String gt[]; // grantt chart
	SJF(int x){
		n = x;
        id = new String[n];
        arr = new int[n];
        bt = new int[n];
        ct = new int[n];
        tat = new int[n];
        wt = new int[n];
        gt = new String[n];
	}
	
	void getData(){
        for(int i=0;i<n;i++){
            Scanner in = new Scanner(System.in);
            System.out.println("Enter Process Id , arrival time , burst time - ");
            id[i] = in.nextLine();
            arr[i] = in.nextInt();
            bt[i] = in.nextInt();
        }
        sortData();
    }
	void sortData(){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(bt[j] < bt[i]){
                	String temp1 = id[i];
                    id[i] = id[j];
                    id[j] = temp1;

                    int temp = arr[i];
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
	            gt[i] = id[i];
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
	        System.out.println("ID\t\tArrival\t\tBurst\t\tComplete\tTurn\tWaiting/Response");
	        for(int i=0;i<n;i++){
	            System.out.println(id[i]+"\t\t  "+arr[i]+"\t\t  "+bt[i]+"\t\t  "+ct[i]+"\t\t  "+tat[i]+"\t  "+wt[i]);
	        }
	        System.out.print("Grant Chart - ");
	        for(int i=0;i<n;i++) {
	        	System.out.print(gt[i] + " ");
	        }
	        System.out.println(" ");
	    }
	
	
}

class RoundRobin{
    int n;
    String id[];
    int arr[]; // arrive
    int bt[]; // burst
    int quantum;
    int ct[]; //compeletion
    int tat[]; // turn around time
    int wt[]; // waiting time

    RoundRobin(int x, int q){
        n = x;
        id = new String[n];
        arr = new int[n];
        bt = new int[n];
        quantum = q;
        ct = new int[n];
        tat = new int[n];
        wt = new int[n];
    }

    void getData(){
        for(int i=0;i<n;i++){
            Scanner in = new Scanner(System.in);
            System.out.println("Enter Process Id , arrival time , burst time - ");
            id[i] = in.nextLine();
            arr[i] = in.nextInt();
            bt[i] = in.nextInt();
        }
        sortData();
    }

    void sortData(){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[j] < arr[i]){
                    String temp1 = id[i];
                    id[i] = id[j];
                    id[j] = temp1;

                    int temp = arr[i];
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
        int current_time = 0;
        int running_process = -1;
        int finished_processes = 0;
        Queue<Integer> ready_queue = new LinkedList<>();

        for(int i=0;i<n;i++){
            ready_queue.add(i);
        }

        while(finished_processes != n){
            if(running_process == -1 && !ready_queue.isEmpty()){
                running_process = ready_queue.poll();
            }

            if(running_process != -1){
                if(bt[running_process] > quantum){
                    bt[running_process] -= quantum;
                    current_time += quantum;
                }
                else{
                    current_time += bt[running_process];
                    ct[running_process] = current_time;
                    tat[running_process] = ct[running_process] - arr[running_process];
                    wt[running_process] = tat[running_process] - bt[running_process];
                    finished_processes++;
                    running_process = -1;
                }
            }

            if(running_process == -1 && !ready_queue.isEmpty()){
                running_process = ready_queue.poll();
            }
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
        System.out.println(" ");
    }
}



class Priority{
    int n;
    String id[];
    int arr[]; // arrive
    int bt[]; // burst
    int priority[];
    int ct[]; //compeletion
    int tat[]; // turn around time
    int wt[]; // waiting time

    Priority(int x){
        n = x;
        id = new String[n];
        arr = new int[n];
        bt = new int[n];
        priority = new int[n];
        ct = new int[n];
        tat = new int[n];
        wt = new int[n];
    }

    void getData(){
        for(int i=0;i<n;i++){
            Scanner in = new Scanner(System.in);
            System.out.println("Enter Process Id , arrival time , burst time , priority - ");
            id[i] = in.nextLine();
            arr[i] = in.nextInt();
            bt[i] = in.nextInt();
            priority[i] = in.nextInt();
        }
        sortData();
    }

    void sortData(){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(priority[j] < priority[i]){
                    String temp1 = id[i];
                    id[i] = id[j];
                    id[j] = temp1;

                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;
                }
            }
        }
    }

    void calculation() {
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i];
            } else {
                if (ar[i] > ct[i - 1]) {
                    ct[i] = ar[i] + bt[i];
                } else {
                    ct[i] = ct[i - 1] + bt[i];
                }
            }
            tat[i] = ct[i] - ar[i];
            wt[i] = tat[i] - bt[i];
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
        System.out.println(" ");
    }
}	





class Main{
    public static void main(String[] args) {
     Scanner in = new Scanner(System.in);
     System.out.println("Enter total no of processes - ");
     int n = in.nextInt();
     SJF p1 = new SJF(n);
     // FCFS p1 = new FCFS(n);
     // RoundRobin p1 = new RoundRobin(n);
     // Priority p1 = new Priority(n);
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
