package plapp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Plapp {

    //private static final Logger LOGGER = Logger.getLogger(Plapp.class);
    private String baseDir = "C:/Users/Joey/Google Drive/plapp/";

    public static void main(String[] args) {
        new Plapp();
    }

    private Plapp() {
        File file = new File(baseDir + "docA.docx");

        try {
            printFileContent(file);
        } catch (FileNotFoundException e) {
            //
        }
    }

    private void printFileContent(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);

        try (XWPFDocument doc = new XWPFDocument(fis)) {
            XWPFWordExtractor we = new XWPFWordExtractor(doc);
            System.out.println(we.getText());
            //LOGGER.info(we.getText());
            we.close();
        } catch (Exception e) {
            //LOGGER.info("error while printing content");
        }
    }
}