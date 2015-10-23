import java.io.File;
import java.util.*;

public class PictureFileCompare {

	private String pathMain;
	private String path1;
	private String path2;
	private String path3;

	//static Scanner scan = new Scanner(System.in); 
	
	//Constructor For "PictureFileCompare" Class
	public PictureFileCompare(String path) // Constructor
	{
		pathMain = path;
		path1 = path + "\\RAW";
		path2 = path + "\\JPG";
		path3 = path + "\\MOV";
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
    
    	//Main directory HERE
    	PictureFileCompare fileObject = new PictureFileCompare("C:\\Users\\Dave42\\Desktop\\2015-10-15");

    	//Make New Folders in main directory if they are not there
    	createFolder(fileObject.path1); // RAW folder
    	createFolder(fileObject.path2); // JPG folder
    	createFolder(fileObject.path3); // MOV folder
    	
    	//Sort all files into folders based on extension
    	fileObject.fileSorter();
    	
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
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	
        	//Store each file name at each location to "tempFileName1" every loop store to the next one 
        	tempFileName1 = fileNames1.get(i);
        	
        	//Remove the extension from the file name, put back in "tempFileName1"
        	tempFileName1 = tempFileName1.substring(0, tempFileName1.lastIndexOf('.'));
        	
        	//DebugCode
        	System.out.print("\n\n----------\n"); //Debug
        	System.out.print( "i:" + i + " NameF1: " + tempFileName1 + "\n"); //Debug
        	
        	//make sure "foundFile" is set to false to start, only fining the matching file will set it true
        	foundFile = false;
        	
        	//nested loop to check all names against the one just stored in "tempFileName1"
        	for (int n = 0; n < fileNames2.size(); n++)
            {
        		tempFileName2 = ""; //clear tempFileName2
        		tempFileName2 = fileNames2.get(n); // get the next file name in the "fileNames2" array
        		tempFileName2 = tempFileName2.substring(0, tempFileName2.lastIndexOf('.')); //Remove the extension
        		
        		System.out.print("\n\nn:" + n +" NameF1: " + tempFileName1 + ", NameF2: "+ tempFileName2); //Debug
        		
        		
        		//if find a matching file then will set "foundFile = true" & end loop
        		if (tempFileName1.equals(tempFileName2) && !foundFile)
        		{
        			System.out.print(" (Found it here!)"); //debug
        			foundFile = true; //foundFile ture
        			n = fileNames2.size(); //ends the loop by maxing out "n" counter
        		}
             }
        	
        	
        	//If the file is not found when compared, then move to new folder
        	if (!foundFile)
        	{
        		System.out.print("\n(File Not Found: " + fileNames1.get(i) + ")"); // debug
        		
        		//Name the Save File that will store files with no match
        		String saveFile = "NoJPG";

        		//Make New folder to move files that don't have a match
        		createFolder(fileObject.path1 + "\\" + saveFile);
        		
    			//DebugCode
    			System.out.print("\n 1 Orignal Location >>> " +  fileObject.path1 + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  fileObject.path1 + "\\" + saveFile + "\\" + fileNames1.get(i)); //Debug
    			
    			//Move File from main directory to new folder that was just made
    			File fileToMove  = new File( fileObject.path1 + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(fileObject.path1 + "\\" + saveFile + "\\" + fileNames1.get(i)));
        	}
        }
    }

    
    public static boolean createFolder(String theFilePath)
    {
        boolean result = false;

        File directory = new File(theFilePath);

        if (directory.exists()) {
            System.out.println("\n\n(Folder already exists: " + theFilePath +")");
        } else {
            result = directory.mkdirs();
        }

        return result;
    }
    
    
	public void fileSorter(){
		

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
        	//Store each file name at each location to "tempFileName1" every loop go to the next one 
        	String tempFileName1 = fileNames1.get(i);
        	
        	//MOVE RAW FILES (.dng, .cr2)
            if(tempFileName1.endsWith(".cr2")||tempFileName1.endsWith(".CR2")||tempFileName1.endsWith(".dng")||tempFileName1.endsWith(".DNG")){
            	
            	//DebugCode
            	System.out.print("\n\nTXT File Found ("+i+"): " + fileNames1.get(i) +"\n");
    			System.out.print("\n 1 Original Location >>> " +  this.pathMain + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  this.path1 + "\\" + fileNames1.get(i)); //Debug
    			
    			//Move File from main directory to new folder
    			File fileToMove  = new File(this.pathMain + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(this.path1 + "\\" + fileNames1.get(i)));
            }
            
            //MOVE .JPG FILES
            if(tempFileName1.endsWith(".jpg") || tempFileName1.endsWith(".JPG")){
            	
            	//DebugCode
            	System.out.print("\n\nTXT File Found ("+i+"): " + fileNames1.get(i) +"\n");
            	System.out.print("\n 1 Original Location >>> " +  this.pathMain + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  this.path2 + "\\" + fileNames1.get(i)); //Debug
            	
    			//Move File from main directory to new folder
    			File fileToMove  = new File(this.pathMain + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(this.path2 + "\\" + fileNames1.get(i)));
            }
            
            //MOVE .MOV FILES
            if(tempFileName1.endsWith(".mov") || tempFileName1.endsWith(".MOV")){
            	
            	//DebugCode
            	System.out.print("\n\nTXT File Found ("+i+"): " + fileNames1.get(i) +"\n");
            	System.out.print("\n 1 Original Location >>> " +  this.pathMain + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  this.path3 + "\\" + fileNames1.get(i)); //Debug
            	
    			//Move File from main directory to new folder
    			File fileToMove  = new File(this.pathMain + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(this.path3 + "\\" + fileNames1.get(i)));
            }
        }
	}
    
}