package hjg.qylab;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 导出‘项目合同书-清源创新实验室科研项目信息表’。
 * @Description
 * @Author hjg
 * @Date 2023-03-20 15:37
 */
public class ProjectContract {

    public static final String RESULT
            = "iText5-demo/target/project_contract_info.pdf";

    public static final float cellPadding = 5f;

    //行距
    public static final float relativeLeading = 1.3f;

    private static Font titleFont;
    private static Font boldFont;
    private static Font font;

    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        Document document = new Document();

        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));

        // step 3
        document.open();
        // step 4
        PdfPTable table = createProjectContractPdf();
        document.add(table);
        // step 5
        document.close();
    }

    private static PdfPTable createProjectContractPdf() throws DocumentException, IOException {
        return null;
    }
}
