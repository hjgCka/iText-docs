package com.hjg;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Author hjg
 * @Date 2023-05-27 10:24
 */
public class LetterFont {

    public static final String dest = "D:/itext_output/yxfs/letter_font.pdf";

    public static void main(String[] args) throws IOException {
        createPdf(dest);
    }

    private static byte[] getFontByteArray() throws IOException {
        //font/wingding.ttf font/Cardo-Bold.ttf
        String fontPath = "font/wingding.ttf";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fontPath);
        byte[] tmp = new byte[1024];
        int index = 0;
        while((index = inputStream.read(tmp, 0, tmp.length)) > 0) {
            byteArrayOutputStream.write(tmp, 0, index);
        }

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return byteArray;
    }

    public static void createPdf(String dest) throws IOException {
        PdfFont pdfFont = PdfFontFactory.createFont(getFontByteArray(), PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest));
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        String s1 = "þ";
        System.out.println(string2Unicode(s1));
        document.add(new Paragraph(s1).setFont(pdfFont));

        String s2 = "¨";
        System.out.println(string2Unicode(s2));
        document.add(new Paragraph(s2).setFont(pdfFont));

        document.close();
    }

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
}
