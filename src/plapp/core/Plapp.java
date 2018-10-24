package plapp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Plapp {

    private static final Logger LOGGER = Logger.getLogger(Plapp.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        new Plapp();
    }

    private Plapp() {
        String baseDir = "C:/Users/Joey/Google Drive/plapp/";
        File file = new File(baseDir + "docA.docx");

        try {
            printFileContent(file);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!");
        }
    }

    private void printFileContent(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);

        try (XWPFDocument doc = new XWPFDocument(fis)) {
            XWPFWordExtractor we = new XWPFWordExtractor(doc);
            LOGGER.info(we.getText());
            we.close();
        } catch (Exception e) {
            LOGGER.error("Error while printing content!");
        }
    }
}