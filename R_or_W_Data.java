import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/** this file is mainly used to read and write customers data from .csv file */

public class R_or_W_Data {
    /**write in .csv file */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSuccess = false;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try{
            out = new FileOutputStream(file,true);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r\n");//.append("\r");
                }
            }
            isSuccess = true;
        } catch (Exception e){
            isSuccess = false;
        } finally{
            if(bw!=null){
                try{
                    bw.close();
                    bw = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try{
                    osw.close();
                    osw = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try{
                    out.close();
                    out = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    /**clear .csv file */
    public static boolean clearCsv(File file, List<String> dataList){
        boolean isSuccess = false;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try{
            out = new FileOutputStream(file,false);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
                for(String data : dataList){
                    bw.append(data).append("\r\n");//.append("\r");
                }
            isSuccess = true;
        } catch (Exception e){
            isSuccess = false;
        } finally{
            if(bw!=null){
                try{
                    bw.close();
                    bw = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try{
                    osw.close();
                    osw = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try{
                    out.close();
                    out = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    /**read .csv file */
    public static List<String> importCsv(File file){
        List<String> dataList = new ArrayList<String>();

        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null){
                dataList.add(line);
            }
        } catch (Exception e){
        }finally{
            if(br != null){
                try{
                    br.close();
                    br = null;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }
}
