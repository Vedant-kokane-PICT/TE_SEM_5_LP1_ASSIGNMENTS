#include<bits/stdc++.h>
using namespace std;

class Memory{
public:
	vector<int> process,blocks;
	int blockNo , processNo;

	Memory(int m,int n){
		blockNo = n;
		processNo = m;
		blocks = vector<int> (blockNo,0);
		process = vector<int> (processNo,0);
	}

	void readData(){
		cout<<"Enter sizes of Blocks"<<endl;
		for(int i=0;i<blockNo;i++){
			cout<<"Enter size of "<<i+1<<" block"<<endl;
			cin>>blocks[i];
		}
		cout<<"Enter sizes of Processes"<<endl;
		for(int i=0;i<processNo;i++){
			cout<<"Enter size of "<<i+1<<" process"<<endl;
			cin>>process[i];
		}
	}

	void firstFit(){
		vector<bool> visited(blockNo,0);
		vector<int> allocated(processNo,-1),internal(processNo,-1);
		int ex = 0 ,in = 0;

		for(int i=0;i<processNo;i++){
			bool flag = 0;
			for(int j=0;j<blockNo;j++){
				if(process[i] <= blocks[j]){
					allocated[i] = j+1;
					internal[i] = blocks[j] - process[i];
					in += internal[i];
					blocks[j] = blocks[j] - process[i];
					flag = 1;
					visited[j] = 1;
					break;	
				}
			}
			if(!flag){
				ex += process[i];
			}
		}

		cout<<"Processes No\t"<<"Process \t"<<"Block No\t"<<"Internal\t"<<endl;
		for(int i=0;i<processNo;i++){
			cout<<i+1<<"\t\t\t\t"<<process[i]<<"\t\t\t\t"<<allocated[i]<<"\t\t\t"<<internal[i]<<endl;
		}	
		cout << "Internal fragmentation :- " << in << endl;
        cout << "External fragmentation :- " << ex << endl;
	}

	void nextFit(){
		vector<bool> visited(blockNo,0);
		vector<int> allocated(processNo,-1),internal(processNo,-1);
		int ex = 0 ,in = 0;
		int start = 0;

		for(int i=0;i<processNo;i++){
			bool flag = 0;
			for(int j=0;j<blockNo;j++){
				if(process[i] <= blocks[j] && !visited[j]){
					allocated[i] = j+1;
					internal[i] = blocks[j] - process[i];
					in += internal[i];
					blocks[j] = blocks[j] - process[i];
					flag = 1;
					visited[j] = 1;
					break;
				}
			}
			if(!flag){
				ex += process[i];
			}
		} 
		cout<<"Processes No\t"<<"Process \t"<<"Block No\t"<<"Internal\t"<<endl;
		for(int i=0;i<processNo;i++){
			cout<<i+1<<"\t\t\t\t"<<process[i]<<"\t\t\t\t"<<allocated[i]<<"\t\t\t"<<internal[i]<<endl;
		}	
		cout << "Internal fragmentation :- " << in << endl;
        cout << "External fragmentation :- " << ex << endl;
	}

	void bestFit(){
		map<int,int> mp;
		for(int i=0;i<blockNo;i++){
			mp[blocks[i]] = i; 
		}
		sortAscending(blocks);
		vector<bool> visited(blockNo,0);
		vector<int> internal(processNo,-1) ,allocated(processNo,-1);
		int ex = 0, in = 0;
		for(int i=0;i<processNo;i++){
			bool flag = 0;
			for(int j=0;j<blockNo;j++){
				if(process[i] <= blocks[j] && !visited[j]){
					allocated[i] =  mp[blocks[j]] + 1;
					internal[i] = blocks[j] - process[i];
					in += internal[i];
					blocks[j] = blocks[j] - process[i];
					flag = 1;
					visited[j] = 1;
					break;
				}
			}
			if(!flag){
				ex += process[i];
			}
		} 
		cout<<"Processes No\t"<<"Process \t"<<"Block No\t"<<"Internal\t"<<endl;
		for(int i=0;i<processNo;i++){
			cout<<i+1<<"\t\t\t\t"<<process[i]<<"\t\t\t\t"<<allocated[i]<<"\t\t\t"<<internal[i]<<endl;
		}	
		cout << "Internal fragmentation :- " << in << endl;
        cout << "External fragmentation :- " << ex << endl;
	}
	
	void worstFit(){
		map<int,int> mp;
		for(int i=0;i<blockNo;i++){
			mp[blocks[i]] = i; 
		}
		sortDescending(blocks);
		vector<bool> visited(blockNo,0);
		vector<int> internal(processNo,-1) ,allocated(processNo,-1);
		int ex = 0, in = 0;
		for(int i=0;i<processNo;i++){
			bool flag = 0;
			for(int j=0;j<blockNo;j++){
				if(process[i] <= blocks[j] && !visited[j]){
					allocated[i] =  mp[blocks[j]] + 1;
					internal[i] = blocks[j] - process[i];
					in += internal[i];
					blocks[j] = blocks[j] - process[i];
					flag = 1;
					visited[j] = 1;
					break;
				}
			}
			if(!flag){
				ex += process[i];
			}
		} 
		cout<<"Processes No\t"<<"Process \t"<<"Block No\t"<<"Internal\t"<<endl;
		for(int i=0;i<processNo;i++){
			cout<<i+1<<"\t\t\t\t"<<process[i]<<"\t\t\t\t"<<allocated[i]<<"\t\t\t"<<internal[i]<<endl;
		}	
		cout << "Internal fragmentation :- " << in << endl;
        cout << "External fragmentation :- " << ex << endl;
    }


	void sortAscending(vector<int> &arr){
		int n = arr.size();
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				if(arr[i] > arr[j]){
					swap(arr[i],arr[j]);
				}
			}
		}
	}

	void sortDescending(vector<int> &arr){
		int n = arr.size();
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				if(arr[i] < arr[j]){
					swap(arr[i],arr[j]);
				}
			}
		}
	}
};

int main(){
	int n1,n2;
	cout<<"Enter total no of Blocks "<<endl;
	cin>> n1;
	cout<<"Enter total no of Processes "<<endl;
	cin>> n2;
	int choice;
	Memory obj(n1,n2);
	obj.readData();
	do {
            cout << "\nMemory Allocation Strategies Menu:\n";
            cout << "1. First Fit\n";
            cout << "2. Next Fit\n";
            cout << "3. Best Fit\n";
            cout << "4. Worst Fit\n";
            cout << "5. Exit\n";
            cout << "Enter your choice: ";
            cin >> choice;

            switch (choice) {
                case 1:
                    obj.firstFit();
                    break;

                case 2:
                    obj.nextFit();
                    break;

                case 3:
                    obj.bestFit();
                    break;

                case 4:
                    obj.worstFit();
                    break;

                case 5:
                    cout << "Exiting the program.\n";
                    break;

                default:
                    cout << "Invalid choice. Please enter a valid option.\n";
            }
        } while (choice != 5);
	return 0;
}
