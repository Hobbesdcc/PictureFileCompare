import java.io.File;
import java.util.*;


public class PictureFileCompare {

	private String pathMain;
	private String path1;
	private String path2;

	//static Scanner scan = new Scanner(System.in); 
	
	//Constructor For "PictureFileCompare" Class
	public PictureFileCompare(String path) // Constructor
	{
		pathMain = path;
		path1 = path + "\\RAW";
		path2 = path + "\\JPG";
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
    
    	//Main directory HERE
    	PictureFileCompare fileObject = new PictureFileCompare("C:\\Users\\dchamot\\Desktop\\CleanUp");

    	createFolder(fileObject.path1);
    	createFolder(fileObject.path2);
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
        
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	System.out.print("\n\n----------\n");
        	tempFileName1 = fileNames1.get(i);
        	tempFileName1 = tempFileName1.substring(0, tempFileName1.lastIndexOf('.'));
        	System.out.print( "i:" + i + " NameF1: " + tempFileName1 + "\n");
        	
        	foundFile = false;
        	for (int n = 0; n < fileNames2.size(); n++)
            {
        		tempFileName2 = "";
        		tempFileName2 = fileNames2.get(n);
        		tempFileName2 = tempFileName2.substring(0, tempFileName2.lastIndexOf('.'));
        		
        		System.out.print("\n\nn:" + n +" NameF1: " + tempFileName1 + ", NameF2: "+ tempFileName2); //Debug
        		
        		if (tempFileName1.equals(tempFileName2) && !foundFile)
        		{
        			System.out.print(" (Found it here!)");
        			foundFile = true;
        			n = fileNames2.size(); 
        		
        		}
             }
        	
        	if (!foundFile)
        	{
        		System.out.print("\n(File Not Found: " + fileNames1.get(i) + ")");
        		
        		String path = fileObject.path1 + "\\" + "NoJPG";
    			createFolder(path);
    			
    			System.out.print("\n 1 Orignal Location >>> " +  fileObject.path1 + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  fileObject.path1 + "\\" + "NoJPG" + "\\" + fileNames1.get(i)); //Debug
    			
    			File fileToMove  = new File( fileObject.path1 + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(fileObject.path1 + "\\" + "NoJPG" + "\\" + fileNames1.get(i)));
        	}
        	
        }
        //*/

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

        //*get file names from first directory
        for (int i = 0; i < listOfFiles1.length; i++) 
        {

            if (listOfFiles1[i].isFile()) 
            {
                fileNames1.add(listOfFiles1[i].getName());
            }
        }
        
        //*
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	String tempFileName1 = fileNames1.get(i);
        	
            if(tempFileName1.endsWith(".cr2")){
            	
            	System.out.print("\n\nTXT File Found ("+i+"): " + fileNames1.get(i) +"\n");
    			System.out.print("\n 1 Original Location >>> " +  this.pathMain + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  this.path1 + "\\" + fileNames1.get(i)); //Debug
    			
    			File fileToMove  = new File(this.pathMain + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(this.path1 + "\\" + fileNames1.get(i)));
            }
            
            if(tempFileName1.endsWith(".jpg")){
            	
            	System.out.print("\n\nTXT File Found ("+i+"): " + fileNames1.get(i) +"\n");
            	System.out.print("\n 1 Original Location >>> " +  this.pathMain + "\\" + fileNames1.get(i)); //Debug
    			System.out.print("\n 2 Move Location >>> " +  this.path1 + "\\" + fileNames1.get(i)); //Debug
            	
    			File fileToMove  = new File(this.pathMain + "\\" + fileNames1.get(i));
    			fileToMove.renameTo(new File(this.path2 + "\\" + fileNames1.get(i)));
            }
        }
        
        //*/
	}
    
}