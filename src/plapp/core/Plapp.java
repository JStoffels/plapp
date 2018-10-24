package plapp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        File firstFile = new File(baseDir + "docA.docx");
        File secondFile = new File(baseDir + "docB.docx");

        try {
            getFileContent(firstFile);
            LOGGER.info("Number of identical sentences: " + compareFiles(firstFile, secondFile));
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!");
        }
    }

    private int compareFiles(File firstFile, File secondFile) throws FileNotFoundException {

        int counter = 0;

        String[] sentencesFirstFile = getSentences(firstFile);
        String[] sentencesSecondFile = getSentences(secondFile);

        for (String sentenceFirstFile : sentencesFirstFile) {
            for (String sentenceSecondFile : sentencesSecondFile) {
                if (sentenceFirstFile.equals(sentenceSecondFile)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private String getFileContent(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        String content = "";

        try (XWPFDocument doc = new XWPFDocument(fis)) {
            XWPFWordExtractor we = new XWPFWordExtractor(doc);
            content = we.getText();
            we.close();
        } catch (Exception e) {
            LOGGER.error("Error while printing content!");
        }

        return content;
    }

    private String[] getSentences(File file) throws FileNotFoundException {
        return getFileContent(file).split("\\s*[.!?]+\\s*");
    }

}