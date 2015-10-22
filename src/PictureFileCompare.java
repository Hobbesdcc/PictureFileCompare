import java.io.File;
import java.util.*;


public class PictureFileCompare {

	private String path;
	private String path1;
	private String path2;

	//static Scanner scan = new Scanner(System.in); 
	
	//Constructor For "PictureFileCompare" Class
	public PictureFileCompare(String path) // Constructor
	{
		path = path;
		path1 = path + "\\RAW";
		path2 = path + "\\JPG";
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
    
    	//PictureFileCompare fileObject = new PictureFileCompare("E:\\2015\\2015-10-09\\RAW","E:\\2015\\2015-10-09\\JPG");
    	PictureFileCompare fileObject = new PictureFileCompare("C:\\Users\\dchamot\\Desktop\\CleanUp");

    	fileObject.fileStructureCreator();
    	//fileObject.fileSorter();
    	
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
                fileNames1.add(listOfFiles1[i].getName());//wow
            }
        }

        //get file names from second directory
        for (int i = 0; i < listOfFiles2.length; i++) 
        {

            if (listOfFiles2[i].isFile()) 
            {
                fileNames2.add(listOfFiles2[i].getName()); //seriously wow
            }
        }
       
        String tempFileName1 = "";
        String tempFileName2 = "";
        boolean foundFile = false;
        
        //*
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	tempFileName1 = fileNames1.get(i);
        	tempFileName1 = tempFileName1.substring(0, tempFileName1.lastIndexOf('.'));
        	System.out.print( "i:" + i + " NameF1: " + tempFileName1 + "\n");
              	
        	//*
        	
        	
        	foundFile = false;
        	for (int n = 0; n < fileNames2.size(); n++)
            {
        		tempFileName2 = "";
        		tempFileName2 = fileNames2.get(n);
        		tempFileName2 = tempFileName2.substring(0, tempFileName2.lastIndexOf('.'));
        		System.out.print("\n\nn:" + n);
        		
        		//*
        		
        		System.out.print(" NameF1: " + tempFileName1 + ", NameF2: "+ tempFileName2);
        		
        		if (tempFileName1.equals(tempFileName2) && !foundFile)
        		{
        			System.out.print(" (Found it here!)");
        			foundFile = true;
        			n = fileNames2.size(); 
        		
        		}
        		//*/
        		
             }
        	 //*/
        	
        	if (!foundFile)
        	{
        		System.out.print(" (File Not Found: " + fileNames1.get(i) + ")");
        		String path = fileObject.path1 + "\\" + "ToRemove";
    			createFolder(path);
    			
    			
    			System.out.print("\n 1 Orignal Location >>> " +  fileObject.path1 + "\\" + fileNames1.get(i)); //Debug
    			File fileToMove  = new File( fileObject.path1 + "\\" + fileNames1.get(i));
    			
    			
    			System.out.print("\n 2 Move Location >>> " +  fileObject.path1 + "\\" + "ToRemove" + "\\" + fileNames1.get(i)); //Debug
    			fileToMove.renameTo(new File(fileObject.path1 + "\\" + "ToRemove" + "\\" + fileNames1.get(i)));
        	}
        	
        	System.out.print("\n\n\n----------\n");
        }
        //*/

    }

    
    public static boolean createFolder(String theFilePath)
    {
        boolean result = false;

        File directory = new File(theFilePath);

        if (directory.exists()) {
            System.out.println("\n(Folder already exists)");
        } else {
            result = directory.mkdirs();
        }

        return result;
    }

    
	public void fileStructureCreator(){

		File directory1 = new File(this.path1);
		File directory2 = new File(this.path2);
		
		if (directory1.exists()) {
	            System.out.println("\n(Folder already exists)");
	     
		 	} else {
	            directory1.mkdirs();
	        }
		
		if (directory2.exists()) {
            System.out.println("\n(Folder already exists)");
     
		 	} else {
	            directory2.mkdirs();
	        }
	}
	
	public void fileSorter(){
		

        //File class
        //File folder1 = new File(this.path);

        //it gets the list of files
        //File[] listOfFiles1 = folder1.listFiles(); 

        //Store the file names as Strings
        //ArrayList<String> fileNames1 = new ArrayList<String>();

        /*//get file names from first directory
        
        for (int i = 0; i < listOfFiles1.length; i++) 
        {

            if (listOfFiles1[i].isFile()) 
            {
                fileNames1.add(listOfFiles1[i].getName());//wow
            }
        }
        
        /*
        for (int i = 0; i < fileNames1.size(); i++)
        {
        	String tempFileName1 = fileNames1.get(i);
        	
            if(tempFileName1.endsWith(".txt")){
            	
            	System.out.print(" (txt File: " + fileNames1.get(i) + ")");
            	//File fileToMove  = new File( fileObject.path1 + "\\" + fileNames1.get(i));
            }
         //*/

        }
  
	}
    
}