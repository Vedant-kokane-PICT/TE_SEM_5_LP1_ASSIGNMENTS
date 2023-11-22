#include<iostream>
#include<utility>
#include<vector>
#include<algorithm>
#include<cmath>
#include<unistd.h>
using namespace std;

class node{
public:
	int id;
	node(){
		id = 0;
	}
	node(int x){
		id = x;
	}
};

const static bool cmp(node& a, node& b){
	return a.id < b.id;
}

class Ring{
	vector<node> arr;
	int no;
public:
	Ring(int n){
		no = n;
		arr = vector<node>(no);
	}

	void getData(){
		cout<<"Enter IDs"<<endl;
		for(int i=0;i<no;i++){
			cout<<"Enter ID for node "<<i+1<<endl;
			cin>>arr[i].id;
		}
		sort(arr.begin(), arr.end(),cmp);
	}

	void detect(){
		sort(arr.begin(),arr.end(),cmp);
		cout<<"Co-ordinator "<<arr.back().id<<" has failed"<<endl;
		arr.pop_back();
		no--;
		election();
	}
	void election(){
		int newCo = rand() % no;
		cout<<arr[newCo].id << " has detected that the Co-ordinator has failed "<<endl;
		cout<<arr[newCo].id << " has initiated the election "<<endl;
		int i = 0;
		while(i < no-2){
			if(newCo != (newCo % no)+1){
				cout<<arr[newCo].id <<" send message to "<<arr[(newCo % no)+1].id<<endl;
			}
			i++;
			newCo = newCo % no + 1;
		}

		cout<<"new Co-ordinator is - "<<arr.back().id<<endl;
	}
};



class Bully{
	vector<node> arr;
	int no;
public:
	Bully(int n){
		no = n;
		arr = vector<node>(no);
	}

	void getData(){
		cout<<"Enter IDs"<<endl;
		for(int i=0;i<no;i++){
			cout<<"Enter ID for node "<<i+1<<endl;
			cin>>arr[i].id;
		}
		sort(arr.begin(), arr.end(),cmp);
	}

	void detect(){
		cout<<"Co-ordinator "<<arr.back().id<<" has failed"<<endl;
		arr.pop_back();
		no--;
		election();
	}

	void election(){
		int newCo = rand() % no;
		cout<<arr[newCo].id << " has detected that the Co-ordinator has failed "<<endl;
		cout<<arr[newCo].id << " has initiated the election "<<endl;
		
		for(int i=0;i<no;i++){
			if(i != newCo){
				if(arr[newCo].id >= arr[i].id){
					cout<<arr[newCo].id <<" send message to "<<arr[i].id<<endl;
					sleep(2);
					cout<<arr[i].id <<" responds with OK to "<<arr[newCo].id<<endl;
					sleep(1);
				}
				else{
					cout<<arr[newCo].id <<" send message to "<<arr[i].id<<endl;
					sleep(2);
					cout<<arr[i].id <<" does not responds with OK "<<endl;
					sleep(1);
					cout<<arr[i].id <<" must be having higher id"<<endl;
					newCo = i;
				}
			}
		}

		cout<<"new Co-ordinator is - "<<arr.back().id<<endl;
	}
};

int main(){
	Ring obj(4);
	obj.getData();
	obj.detect();
 //  Bully obj(4);
	// obj.getData();
	// obj.detect();
	return 0;
}
