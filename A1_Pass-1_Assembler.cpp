// Vedant Kokane 
// 31430
#include<iostream>
#include<map>
#include<vector>
#include<iterator>
#include<fstream>
#include<string>
#include <sstream>
using namespace std;


int main(){
	map<string,string> me;
	me["STOP"] = "(IS,00)";
	me["ADD"] = "(IS,01)";
	me["SUB"] = "(IS,02)";
	me["MULT"] = "(IS,03)";
	me["MOVER"] = "(IS,04)";
	me["MOVEM"] = "(IS,05)";
	me["COMP"] = "(IS,06)";
	me["BC"] = "(IS,07)";
	me["DIV"] = "(IS,08)";
	me["READ"] = "(IS,09)";
	me["PRINT"] = "(IS,10)";

	me["DS"] = "(DL,01)";
	me["DC"] = "(DL,02)";

	me["START"] = "(AD,01)";
	me["END"] = "(AD,02)";
	me["EQU"] = "(AD,03)";
	me["ORIGIN"] = "(AD,04)";
	me["LTORG"] = "(AD,05)";

	me["AREG"] = "(RG,01)";
	me["BREG"] = "(RG,02)";
	me["CREG"] = "(RG,03)";
	me["DREG"] = "(RG,04)";

	me["LT"] = "(CC,01)";
	me["LE"] = "(CC,02)";
	me["EQ"] = "(CC,03)";
	me["GT"] = "(CC,04)";
	me["GE"] = "(CC,05)";
	me["GE"] = "(CC,06)";

	map<string,pair<int,int>> symbol,literal;
	vector<string> lit;

	int lc = 0;
	ifstream in;
	in.open("input2.txt",ios::in);
	ofstream out;
	out.open("intermediate.txt",ios::out);
	int sCnt = 0,lCnt = 0, litStart = -1;
	while(!in.eof()){
		lc++;
		vector<string> decode;
		string line, word="";
		getline(in,line);
		istringstream iss(line);
		while(iss >> word){
			decode.push_back(word);
		}
		for(int i=0;i<decode.size();i++){
			if(decode[i] == "START"){
				cout<<me[decode[i]]<<" (C,01) ";
				lc = stoi(decode[i+1]) - 1;
				lc++;
				i++;
			}
			else if(decode[i] == "DS" || decode[i] == "DC"){
				cout<<me[decode[i]]<<" ";
			}
			else if(decode[i] == "LTORG"){
				cout<<me[decode[i]]<<" ";
				litStart = lc;
				continue;
			}
			else if(me.find(decode[i]) != me.end()){
				cout<<me[decode[i]]<<" ";
			}
			else if(decode[i][0] == '='){
				string x = decode[i].substr(2,1);
				if(literal.find(x) == literal.end()){
					literal[x].first = 0;
					literal[x].second = lCnt++;
					cout<<"(L,"<<literal[x].second<<") ";
					lit.push_back(x);
				}
				else{
					cout<<"(L,"<<literal[x].second<<") ";
				}
			}
			else if(decode[i][0] <= '0' && decode[i][0] <= '9'){
				cout<<"(C,01) ";
			}
			else if(decode[i] == "LTORG"){
				string x = decode[i];
				int n = x.length();
				int num = stoi(x.substr(n - 1, 1));
				string sym = x.substr(0,n-1);
				lc = symbol[sym].first;
				cout<<me[decode[i]]<<" ";
				i++;
			}
			else{
				if(decode[i] == ","){
					continue;
				}
				string x = decode[i];;
				if(symbol.find(x) == symbol.end()){
					symbol[x].first = lc++;
					symbol[x].second = sCnt++;
				}
				cout<<"(S,"<<symbol[x].second<<") ";
			}
		}
		cout<<endl;
	}
	cout<<endl;
	if(litStart == -1){
		litStart = lc;
	}

	for(auto x: lit){
		cout<<x<<" = "<<litStart++<<endl;
	}

	for(auto x:symbol){
		cout<<x.first<<" = "<<x.second.first<<endl;
	}

	return 0;
}


