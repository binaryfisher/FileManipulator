import java.io.*;

/**
 * @Author: Wilson
 * @Date: 10/7/2019
 * @Time: 3:27 PM
 * @Description:
 */
public class FileManipulator {


    public static void main(String[] args) {

        FileManipulator fileManipulator = new FileManipulator();
        File fileFrom = new File("C:\\Users\\Wilson\\IdeaProjects\\FileMani\\Imageslibrary");
        File fileTo = new File("C:\\Users\\Wilson\\Desktop\\copyfile");

        fileManipulator.copyFolder(fileFrom,fileTo);


    }


    public  void copyFile(File fileFrom,File fileTo){

        BufferedInputStream  bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(fileFrom));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileTo));
            byte[] bytes = new byte[1024];
            while (bufferedInputStream.read(bytes) != -1){
                bufferedOutputStream.write(bytes);
            }
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void copyFolder(File fileFrom,File fileTo){
        if(fileFrom.isDirectory()){
            File newFolder = new File(fileTo,fileFrom.getName());
            newFolder.mkdirs();
            File[] fileArray = fileFrom.listFiles();
            for (File file: fileArray) {
                copyFolder(file,newFolder);
            }
        }else {
            File newFile = new File(fileTo,fileFrom.getName());
            copyFile(fileFrom,newFile);
        }
    }
    

}
