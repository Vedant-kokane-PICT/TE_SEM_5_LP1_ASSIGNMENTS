// Vedant Kokane 
// 31430

#include<bits/stdc++.h>
using namespace std;

int main(){
	// mnemonic 
	unordered_map<string,string> me;
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

    // Assembly Directive
    me["START"] = "(AD,01)";
    me["END"] = "(AD,02)";
    me["EQU"] = "(AD,04)";
    me["LTORG"] = "(AD,05)";
    me["ORIGIN"] = "(AD,03)";

    // Declerative
    me["DS"] = "(DL,01)";
    me["DC"] = "(DL,02)";

    // Register
    unordered_map<string, string> reg;
    reg["AREG"] = "(RG,01)";
    reg["BREG"] = "(RG,02)";
    reg["CREG"] = "(RG,03)";
    reg["DREG"] = "(RG,04)";

    // Conditionals
    unordered_map<string, string> condition;
    condition["LT"] = "(CC,01)";
    condition["LE"] = "(CC,02)";
    condition["EQ"] = "(CC,03)";
    condition["GT"] = "(CC,04)";
    condition["GE"] = "(CC,05)";
    condition["ANY"] = "(CC,06)";

    unordered_map<string,pair<int,int>> literal ,symbol;
    vector<string> lit;
    int lc = 0;
    ifstream in;
    in.open("input.txt",ios::in);
    int sCnt = 0, lCnt = 0;
    int litStart = -1;
    ofstream out;
    out.open("intermediate.txt",ios::out);
    while(!in.eof()){
    	vector<string> decode;
    	lc++;
    	string line;
    	getline(in,line);
    	string word = "";
    	istringstream iss(line); // Use istringstream to extract words from the line
        while (iss >> word) {
            decode.push_back(word);
        }

    	for(int i=0;i<decode.size();i++){
    		//cout<<decode[i]<<endl;
    		if(decode[i] == "START"){
    			out<<me[decode[i]]<<" ";
    			lc = stoi(decode[i+1])-1;
    		}
    		else if(decode[i] == "LTORG"){
    			out<<me[decode[i]]<<" ";
    			litStart = lc++;
    			continue;
    		}
    		else if(decode[i] == "DS" || decode[i] == "DC"){
    			out<<me[decode[i]]<<" ";
    		}
    		else if(me.find(decode[i]) != me.end()){
    			out<<me[decode[i]]<<" ";
    		}
    		else if(reg.find(decode[i]) != reg.end()){
    			out<<reg[decode[i]]<<" ";
    			continue;
    		}
    		else if(condition.find(decode[i]) != condition.end()){
    			out<<condition[decode[i]]<<" ";
    		}
    		else if (decode[i][0] == '=') {
	        	string x = decode[i].substr(2,1);
	        
		        if (literal.find(x) == literal.end()) {
		            literal[x].first = 0;  
		            literal[x].second = lCnt++;
		            out<<"(L,"<<literal[x].second++<<") ";
		            lit.push_back(x);
	         	}
	         	else{
	         		auto it = literal.find(x);
	         		out<<"(L,"<<literal[x].second<<") ";
	         	}
	         	
			}
			else if (decode[i][0] >= '0' && decode[i][0] <= '9') {
			    out << "(C,01) ";
			}
    		else{
    			if(decode[i] == ","){
    				continue;
    			}
    			string x = decode[i];
    			int l = 0;
    			if(symbol.find(x) != symbol.end()){
	         		out<<"(S,"<<symbol[x].second <<") ";
    			}
    			else{
    				symbol[x].first = lc;
    				symbol[x].second = sCnt++;
    				out<<"(S,"<<symbol[x].second <<") ";
    			}	
    		}
    	}
    	out<<endl;
    }
	if(litStart == -1){
		litStart = lc;
	}
	out.close();
	out.open("literal.txt",ios::out);
	for(auto x: lit){
		out<<x<<" = " <<litStart++<<endl;
	}		
	out.close();
	out.open("symbol.txt",ios::out);
    for(auto x: symbol){
    	out<<x.first<<" "<<x.second.first<<endl;
    }
    cout<<"Pass 1 finished"<<endl;
	return 0;
} 



