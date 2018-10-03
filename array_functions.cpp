/*
 *  Created on: Sep 29, 2018
 *      Author: Tre Brooks
 */


#include <string>
#include <iostream>
#include "constants.h"
#include "utilities.h"
#include "array_functions.h"
using namespace std;

struct aword{
    int count = 0;
    string value = " ";
};
 int size = 0;
 aword *wordArray = new aword[constants:: MAX_WORDS];

//zero out array that tracks words and their occurrences
void clearArray() {

	size = 0;
 }


//how many unique words are in array
int getArraySize(){
    return size;
}

//get data at a particular location
std::string getArrayWordAt(int i){

        return wordArray[i].value;
}
int getArrayWord_NumbOccur_At(int i){
    return wordArray[i].count;

}

/*loop through whole file, one line at a time
 * call processLine on each line
 * returns false: myfstream is not open
 *         true: otherwise*/
bool processFile(std::fstream &myfstream){
     if (myfstream.is_open())
      {
    	 std:: string line;
    	 while(!myfstream.eof()){
    		 getline(myfstream, line);
    		 processLine(line);
    	 }
        return true;
      }
    return false;

}

/*take 1 line and extract all the tokens from it
feed each token to processToken for recording*/
void processLine(std::string &myString){
     stringstream ss(myString);
    	    string tempToken;

    	    while(getline(ss,tempToken,constants::CHAR_TO_SEARCH_FOR)){
    	        processToken(tempToken);
    	    }
}

/*Keep track of how many times each token seen*/
void processToken(std::string &token){
	strip_unwanted_chars(token);
	if(token == "" ){
		return ;
	}
	bool exist = false;
	for(int i = 0; i < getArraySize(); i++){
		string temp = token;
		string temp2 = wordArray[i].value;
		toUpper(temp);
		toUpper(temp2);
		if(temp == temp2 ){
			exist = true;
			wordArray[i].count++;
		}
	}
	if(!exist){
	 wordArray[size].value = token;
	 wordArray[size].count = 1;
	 size++;
	}
// iterate size when a new token is added and not an old one.
}


/*if you are debugging the file must be in the project parent directory
  in this case Project2 with the .project and .cProject files*/
bool openFile(std::fstream& myfile, const std::string& myFileName, std::ios_base::openmode mode){
    myfile.open(myFileName.c_str(),mode);
    return myfile.is_open();
}

/*iff myfile is open then close it*/
void closeFile(std::fstream& myfile){
    if (myfile.is_open()){
        myfile.close();
    }
}

/* serializes all content in myEntryArray to file outputfilename
 * returns  FAIL_FILE_DID_NOT_OPEN if cannot open outputfilename
 *             FAIL_NO_ARRAY_DATA if there are 0 entries in myEntryArray
 *             SUCCESS if all data is written and outputfilename closes OK
 * */
int writeArraytoFile(const std::string &outputfilename){
	ofstream astream;
	    			astream.open(outputfilename.c_str());
	    			if(!astream.is_open()){
	    				return constants:: FAIL_FILE_DID_NOT_OPEN;
	    			}
    		if(getArraySize() == 0){
    				return constants::FAIL_NO_ARRAY_DATA;
    		}

    			for(int i = 0; i <getArraySize(); i++){

    				astream << wordArray[i].value << " " << wordArray[i].count << endl;
    }
        return constants:: SUCCESS;
}



/*
 * Sort myEntryArray based on so enum value.
 * You must provide a solution that handles alphabetic sorting (A-Z)
 * The presence of the enum implies a switch statement based on its value
 */
void sortArray(constants::sortOrder so){

	 switch(so){
	case constants:: NONE: break;
	case constants:: ASCENDING:
		for(int i = 0; i < size - 1; i++){
			for(int j = 0; j < size - i - 1; j++){
				string temp = wordArray[j].value;
				string temp2 = wordArray[j + 1].value;
				toUpper(temp);
				toUpper(temp2);
				if(temp > temp2){
					swap(wordArray[j], wordArray[j+1]);
				}

			}
		}
		break;
	case constants:: DESCENDING: break;


	}

}
