import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by home on 19/2/17.
 */
public class ExtractData {
    public Process process;
    public BufferedReader imgNameFile;
    public BufferedReader engNameFile;

    ArrayList<String> imgName;
    ArrayList<String> engName;

    ListIterator<String> l1 ;
    ListIterator<String> l2 ;


    public ExtractData()throws Exception{
        imgNameFile = new BufferedReader(new FileReader("ImageName.txt"));
        engNameFile = new BufferedReader(new FileReader("engName.txt"));
        imgName = new ArrayList<>();
        engName = new ArrayList<>();

    }

    public int extract()throws Exception{
        BufferedReader tempBuffer;
        l1 = imgName.listIterator();
        l2 = engName.listIterator();
        int index=0 , matches=0, prev=0, resIndex=0;
        while(l1.hasNext()){
            process = Runtime.getRuntime().exec("./feat "+l1.next());
            tempBuffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
			//System.out.println(tempBuffer.readLine());
			matches = Integer.parseInt(tempBuffer.readLine().toString());
          	if(matches > prev ){
                resIndex = index;
				prev=matches; 
            }
            index++;
            
        }

        return resIndex;
    }


    public void printData(int i){
        
            System.out.println(engName.get(i));
        

    }

    public static void main(String[] args)throws Exception{
        ExtractData extractData = new ExtractData();
        String t1 = extractData.imgNameFile.readLine();
        String t2 = extractData.engNameFile.readLine();
        while(t1!=null){
            extractData.imgName.add(t1);
            extractData.engName.add(t2);
            t1 = extractData.imgNameFile.readLine();
            t2 = extractData.engNameFile.readLine();


        }

        extractData.printData(extractData.extract());

    }



}

