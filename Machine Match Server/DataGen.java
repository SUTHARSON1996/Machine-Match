import java.io.*;
import java.util.Scanner;

/**
 * Created by home on 19/2/17.
 */
public class DataGen {
    private Scanner s;
    private File imgName;
    private File engName;
    private FileWriter imgWriter;
    private FileWriter engWriter;
    private String[] img_name;
    private String[] eng_name;

    public DataGen()throws Exception{
        s = new Scanner(System.in);
        imgName = new File("ImageName.txt");
        engName = new File("engName.txt");

        if(imgName==null){
            imgName.createNewFile();
        }
        if (engName == null){
            engName.createNewFile();
        }

    }

    public void writeToFile ()throws Exception{
        imgWriter = new FileWriter(imgName,true);
        engWriter = new FileWriter(engName,true);

        BufferedWriter buffImg = new BufferedWriter(imgWriter);
        BufferedWriter buffEng = new BufferedWriter(engWriter);

        for(int i=0;i<img_name.length;i++){
            buffImg.write(img_name[i]);
            buffImg.write("\n");
            buffEng.write(eng_name[i]);
            buffEng.write("\n");
        }

        buffEng.close();
        buffImg.close();
        imgWriter.close();
        engWriter.close();
    }


    private void readData(){

        System.out.print("Enter No Of Images For Training : ");
        int n = s.nextInt();
        img_name =new String[n];
        eng_name =new String[n];
        s.nextLine();
        for(int i=0;i<n;i++){
            System.out.print("Enter the Name Of image "+(i+1)+" : ");
            img_name[i] = s.nextLine();
            System.out.print("Enter the Name Of Engine "+(i+1)+" \n***Name should not contain white spaces; override with underscore***\n");
			eng_name[i] = s.nextLine();
			System.out.print("Enter the garrett part number of engine "+(i+1)+"\n ***It should not contain white spaces; override with underscore***\n");
            eng_name[i] = eng_name[i]+" "+s.nextLine();
			System.out.print("Enter the Turbo Model of engine "+(i+1)+"\n ***It should not contain white spaces; override with underscore***\n");
            eng_name[i] = eng_name[i]+" "+s.nextLine();

		
        }





    }



    public static void main(String[] args)throws Exception{
        DataGen dataGen = new DataGen();
        dataGen.readData();
        dataGen.writeToFile();

    }




}

