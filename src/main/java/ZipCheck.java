import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by Sudipto Saha on 6/14/2017.
 */
public class ZipCheck {



/*
    public String matchRegex(String regex,String filename) throws IOException {
        File file = new File("C:\\Users\\Sudipto Saha\\Desktop\\check.zip");
        ZipFile zipFile = new ZipFile("C:\\Users\\Sudipto Saha\\Desktop\\check.zip");
        InputStream input;
        String value=null;

            input = new FileInputStream(file);
            ZipInputStream zip = new ZipInputStream(input);
            ZipEntry entry =null;// zip.getNextEntry();

            BodyContentHandler textHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();

            Parser parser = new AutoDetectParser();

                while ((entry=zip.getNextEntry())!=null) {

                    if (entry.getName() == filename) {
                        input = zipFile.getInputStream(entry);
                        BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                        String line;
                        Integer i = 0;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                            if (line.contains("PV_" + regex)) {
                                value = line.substring(3 + regex.length());

                            }
                        }

                        br.close();

                    }


                }


                    return value;
                    }

*/

    public static void main(String[] args) throws IOException {

        List<String> tempString = new ArrayList<String>();
        StringBuffer sbf = new StringBuffer();
        String value=null;
        MultiValueMap<String,String> map = new MultiValueMap<String, String>();

        File file = new File("C:\\Users\\Sudipto Saha\\Desktop\\check.zip");
        ZipFile zipFile = new ZipFile("C:\\Users\\Sudipto Saha\\Desktop\\check.zip");
        InputStream input;
        try {

            input = new FileInputStream(file);
            ZipInputStream zip = new ZipInputStream(input);
            ZipEntry entry =null;// zip.getNextEntry();

            BodyContentHandler textHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();

            Parser parser = new AutoDetectParser();

            try{
            while ((entry=zip.getNextEntry())!=null) {

                if (entry.getName().endsWith(".txt")) {
                    System.out.println("entry=" + entry.getName() + " " + entry.getSize());
                    //parser.parse(input, textHandler, metadata, new ParseContext());
                    //tempString.add(textHandler.toString());
                    input = zipFile.getInputStream(entry);
                        BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                        String line;
                        Integer i =0;
                        while((line = br.readLine()) != null) {
                            System.out.println(line);
                            if(line.contains("loc"))
                            {
                                i++;
                                value = line.substring(4);
                                tempString.add(value);
                                map.put(i.toString()+" "+entry.getName(),value);
                                String a = "PV_"+value;
                                System.out.println("******************"+a);
                            }



                        }

                        br.close();

                        /*while((line = br.readLine())!=null) {

                            for(int j =0;j<tempString.size();j++){

                                String regex = "PV_"+tempString.get(j);
                                if(line.contains(regex))
                                {
                                    String value1 = line.substring(regex.length());
                                    System.out.println("&&&&&&&&&&&"+value1);
                                }
                            }

                        }
                        tempString.removeAll(tempString);*/

                }
            }}catch(EOFException e){}
            zip.close();
            input.close();


            System.out.println(Collections.singletonList(map));
            //System.out.println("**************"+Collections.singletonList(tempString));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }/* catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }*/
    }



}