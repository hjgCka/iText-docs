package hjg;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Description
 * @Author hjg
 * @Date 2022-06-24 12:32
 */
public class CJKExample {
    /** The resulting PDF file. */
    public static final String RESULT
            = "iText5-demo/target/cjk_example.pdf";
    /** Movies, their director and original title */
    public static final String[][] MOVIES = {
            {
                    "STSong-Light", "UniGB-UCS2-H",
                    "Movie title: House of The Flying Daggers (China)",
                    "directed by Zhang Yimou",
                    "\u5341\u9762\u57cb\u4f0f"
            },
            {
                    "KozMinPro-Regular", "UniJIS-UCS2-H",
                    "Movie title: Nobody Knows (Japan)",
                    "directed by Hirokazu Koreeda",
                    "\u8ab0\u3082\u77e5\u3089\u306a\u3044"
            },
            {
                    "HYGoThic-Medium", "UniKS-UCS2-H",
                    "Movie title: '3-Iron' aka 'Bin-jip' (South-Korea)",
                    "directed by Kim Ki-Duk",
                    "\ube48\uc9d1"
            }
    };

    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException
     * @throws IOException
     * @throws    DocumentException
     * @throws    IOException
     */
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        OutputStream outputStream = Files.newOutputStream(Paths.get(filename));
        PdfWriter.getInstance(document, outputStream);
        // step 3
        document.open();
        // step 4
        BaseFont bf;
        Font font;

        for (int i = 0; i < 3; i++) {
            bf = BaseFont.createFont(MOVIES[i][0], MOVIES[i][1], BaseFont.EMBEDDED);
            font = new Font(bf, 12);
            document.add(new Paragraph(bf.getPostscriptFontName(), font));
            for (int j = 2; j < 5; j++) {
                document.add(new Paragraph(MOVIES[i][j], font));
                if(i == 0) {
                    document.add(new Phrase("你好", font));
                }
            }
            document.add(Chunk.NEWLINE);
        }


        // step 5
        document.close();
    }

    /**
     * Main method.
     *
     * @param    args    no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        new CJKExample().createPdf(RESULT);
    }
}
