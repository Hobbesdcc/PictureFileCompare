import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class PictureFileCompare {
	//Test
	private String pathMain;
	private String path1;
	private String path2;
	private String path3;
	private File logfile;

	private String versionNumber;

	static Scanner scan = new Scanner(System.in); 
	
	//Constructor For "PictureFileCompare" Class
	public PictureFileCompare() // Constructor
	{
		//Version Number!!
		versionNumber ="v1.10 (2015-10-26)";
		
		//Setup the MAIN folder path
		pathMain = "";
		
		//Setup the other folder path for ex: JPG, RAW, MOV folder
		path1 = "";
		path2 = "";
		path3 = "";
		
		//file = new File("");
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws IOException
    {
    	//Main directory HERE
    	PictureFileCompare fileObject = new PictureFileCompare();
    	
    	//Ask for Main directory input
    	fileObject.inputDirectory();
    	
    	
    	//Set Up Log File
    	//Check to see if log exists, if it uses that file, if it dose goes to the next number
    	for (int c = 1; c < 100; c++)
        {
    		//starts stepping though files until it finds one that dose not exist
    		fileObject.logfile = new File(fileObject.pathMain+"\\Log" + c + ".txt");
    		boolean exists = fileObject.logfile.exists();
    		
    		//check to see if file does not exist, if so breaks the loop
    		if (!exists){
    			c = 100;
    		}
        }
    	
    	//Set Up Log File, Format: output("your message!", System.out, printWriter)
    	fileObject.logfile.getParentFile().mkdirs(); //Makes new Directory for file
    	PrintWriter printWriter = new PrintWriter(fileObject.logfile); //Set steam to write to file
    	
    	//Sets up Log File header
    	fileObject.logFileSetup(printWriter);
    	
    	//*//remove 1st "/" to Turn off File maker and sorter <<<<
    	//Make New Folders in main directory if they are not there
    	output("\n--- createFolder() ---\n\n", System.out, printWriter); //DebugCode
    	createFolder(fileObject.path1,printWriter); // RAW folder
    	createFolder(fileObject.path2,printWriter); // JPG folder
    	createFolder(fileObject.path3,printWriter); // MOV folder
    	output("----------------------\n\n", System.out, printWriter); //DebugCode
    	
    	//Sort all files into folders based on extension
    	output("\n---- fileSorter() ----", System.out, printWriter); //DebugCode
    	fileObject.fileSorter(printWriter);
    	output("\n----------------------\n", System.out, printWriter); //DebugCode
    	//*/
    	
    	
    	//*//remove 1st "/" to Turn off matching tool <<<<
        //File class
        File folder1 = new File(fileObject.path1);
        File folder2 = new File(fileObject.path2);

        //it gets the list of files
        File[] listOfFiles1 = folder1.listFiles(); 
        File[] listOfFiles2 = folder2.listFiles(); 

        //Store the file names as Strings
        ArrayList<String> fileNames1 = new ArrayList<String>();
        ArrayList<String> fileNames2 = new ArrayList<String>();

        //get file names from first directory
        for (int i = 0; i < listOfFiles1.length; i++) 
        {

            if (listOfFiles1[i].isFile()) 
            {
                fileNames1.add(listOfFiles1[i].getName());
            }
        }

        //get file names from second directory
        for (int i = 0; i < listOfFiles2.length; i++) 
        {

            if (listOfFiles2[i].isFile()) 
            {
                fileNames2.add(listOfFiles2[i].getName());
            }
        }
       
        //Strings and boolean to temporarily store FileNames in order to compare them
        String tempFileName1 = ""; //temporarily FileName 1
        String tempFileName2 = ""; //temporarily FileName 2
        boolean foundFile = false; //If file is found, then True
        
        
        //MAIN LOGIC LOOP that goes though and see if there is a matching file
        output("\n\n----- Matching files -----\n", System.out, printWriter); //Debug
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	
        	//Store each file name at each location to "tempFileName1" every loop store to the next one 
        	tempFileName1 = fileNames1.get(i);
        	
        	//Remove the extension from the file name, put back in "tempFileName1"
        	tempFileName1 = tempFileName1.substring(0, tempFileName1.lastIndexOf('.'));
        	
        	//DebugCode
        	output("\n>> (i:" + i + ") 1st File Name: (" + tempFileName1 +")", System.out, printWriter); //Debug
        	
        	//make sure "foundFile" is set to false to start, only fining the matching file will set it true
        	foundFile = false;
        	
        	//nested loop to check all names against the one just stored in "tempFileName1"
        	for (int n = 0; n < fileNames2.size(); n++)
            {
        		tempFileName2 = ""; //clear tempFileName2
        		tempFileName2 = fileNames2.get(n); // get the next file name in the "fileNames2" array
        		tempFileName2 = tempFileName2.substring(0, tempFileName2.lastIndexOf('.')); //Remove the extension
        		
        		output("\n(n:" + n +") FileName1: [" + tempFileName1 + "], FileName2: ["+ tempFileName2 +"]", System.out, printWriter); //Debug
        		
        		
        		//if find a matching file then will set "foundFile = true" & end loop
        		if (tempFileName1.equals(tempFileName2) && !foundFile)
        		{
        			output(" (Found it!)\n", System.out, printWriter); //debug
        			foundFile = true; //foundFile true
        			n = fileNames2.size(); //ends the loop by max out "n" counter
        		}
             }
        	
        	
        	//If the file is not found when compared, then move to new folder
        	if (!foundFile)
        	{
        		output("\n\nFile Not Found: (" + fileNames1.get(i) + ")\n", System.out, printWriter); // debug
        		
        		//Name the Save File that will store files with no match
        		String saveFile = "NoMatch";

        		//Make New folder to move files that don't have a match
        		createFolder(fileObject.path1 + "\\" + saveFile,printWriter);
        		
    			//DebugCode
        		output("Original Location >>> " +  fileObject.path1 + "\\" + fileNames1.get(i), System.out, printWriter); //Debug
        		output("\nMove Location    >>> " +  fileObject.path1 + "\\" + saveFile + "\\" + fileNames1.get(i)+"\n", System.out, printWriter); //Debug
    			
    			//Move File from main directory to new folder that was just made
    			File fileToMove  = new File( fileObject.path1 + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(fileObject.path1 + "\\" + saveFile + "\\" + fileNames1.get(i)));
        	}
        }
        output("--------------------------\n", System.out, printWriter); //Debug
        //*/
        
        //LogFile Close
        printWriter.flush(); // Flush printWriter File
        printWriter.close(); // Close printWriter File
        
        System.out.print("\n\nFinished: Press enter to exit..");
        System.in.read();
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public static void output(final String msg, PrintStream out1, PrintWriter out2) {        
        //Output method, it prints message in console and to a log file
    	out1.print(msg);
        out2.print(msg);
    }
    
    
    public void logFileSetup(PrintWriter printWriter) {
    	//sets up log file with all info for the header
    	output("** PictureFile Tool: Matcher and Sorter: LOG ** (Author: Dave Chamot)\n", System.out, printWriter);
    	output("** Version Number: "+ this.versionNumber +"\n\n", System.out, printWriter);
    	
    	output("** LOG File: "+ this.logfile +"\n\n", System.out, printWriter);
    	output("----- logFileSetup() -----", System.out, printWriter);
    	output("\nDirectory used: " + this.pathMain, System.out, printWriter);
    	output("\nDate & Time of Log: " + LocalDateTime.now(), System.out, printWriter);
    	output("\n--------------------------\n\n", System.out, printWriter);
    }
    
    
    public void inputDirectory(){
		
    	System.out.print("Format e.g. ''C:\\Users\\Dave42\\Desktop\\Picturefolder'' \n");
    	System.out.print("Input/Drop Main Directory here: ");
		scan = new Scanner(System.in); // open a new Scanner name it "scan"
		this.pathMain = scan.nextLine(); // set [object being acted on] = [what was input into scanner]
		
		this.pathMain = this.pathMain.replaceAll("\"",""); //Removes all the "quote" marks from the string
		
		//Setup the other folder paths
		this.path1 = this.pathMain + "\\RAW";
		this.path2 = this.pathMain + "\\JPG";
		this.path3 = this.pathMain + "\\MOV";
		
		
		//*//DebugCode
		System.out.print("\n\n--- inputDirectory() ---\n\n");
		for (int i=0; i < this.pathMain.length(); i++) 
		{
			  char c = this.pathMain.charAt(i);
			  System.out.print("this.pathMain.charAt(" + c + "),\n");
			  
		}
		
		//DebugCode
		System.out.print("- - - - - - - - - - - -\n");
		System.out.print(this.pathMain);
		System.out.print("\n-----------------------\n\n");
		//*/
	}
    
    
    public static boolean createFolder(String theFilePath, PrintWriter printWriter)
    {
        boolean result = false;

        File directory = new File(theFilePath);
        
        if (directory.exists()) {
        	output("(Folder already exists: " + theFilePath +")\n", System.out, printWriter);
        } else {
            result = directory.mkdirs();
            output("(New Folder Made: " + theFilePath +")\n", System.out, printWriter);
        }

        return result;
        
    }
    
    
	public void fileSorter(PrintWriter printWriter){
		

        //File class
        File mainDirectory = new File(this.pathMain);
		//System.out.print("(this.Path: " + this.pathMain + ")");

        //it gets the list of files
        File[] listOfFiles1 = mainDirectory.listFiles(); 

        //Store the file names as Strings
        ArrayList<String> fileNames1 = new ArrayList<String>();

        //get file names from first directory
        for (int i = 0; i < listOfFiles1.length; i++) 
        {
            if (listOfFiles1[i].isFile()) 
            {
                fileNames1.add(listOfFiles1[i].getName());
            }
        }
        
        //Go though all the file names stored and sort them by extension in there folder
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	//Store temporary info for each file name at each location, every loop store next files info
        	String tempFileName1 = fileNames1.get(i);
    		String tempPath = "";
    		String tempFileType = "";
    		boolean foundFileType = false; //If file tpye is found, then True
    		
    		
    		//Figure out what type of file is being dealt with
    		if(tempFileName1.endsWith(".cr2")||tempFileName1.endsWith(".CR2")||tempFileName1.endsWith(".Cr2")||tempFileName1.endsWith(".dng")||tempFileName1.endsWith(".DNG")){
    			tempFileType = ".Cr2";
    			tempPath = this.path1;
    			foundFileType = true; 
    			
    		} else if(tempFileName1.endsWith(".jpg") || tempFileName1.endsWith(".JPG") || tempFileName1.endsWith(".Jpg")){
    			tempFileType = ".Jpg";
    			tempPath = this.path2;
    			foundFileType = true; 
    			
    		} else if(tempFileName1.endsWith(".mov") || tempFileName1.endsWith(".MOV") || tempFileName1.endsWith(".Mov")){
    			tempFileType = ".Mov";
    			tempPath = this.path3;
    			foundFileType = true; 
    			
    		}
    		
    		if(foundFileType==true){
    		
	    		//DebugCode	
	    		output("\n\n("+ tempFileType + ") File Found (i:"+i+"): " + fileNames1.get(i), System.out, printWriter); //Debug
	    		output("\nOriginal Location >>> " +  this.pathMain + "\\" + fileNames1.get(i), System.out, printWriter); //Debug
	    		output("\nMove Location     >>> " +  tempPath + "\\" + fileNames1.get(i), System.out, printWriter); //Debug
				
				//Move File from main directory to new folder
				File fileToMove  = new File(this.pathMain + "\\" + fileNames1.get(i));
				fileToMove.renameTo(new File(tempPath + "\\" + fileNames1.get(i)));
    		}	
        }
	}
}