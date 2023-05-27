package com.hjg.itext.font;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: hjg
 * @createdOn: 2021/2/19
 */
public class Chinese_text {

    private static final Logger logger = LoggerFactory.getLogger(Chinese_text.class);

    private static final String DEST = "d:/itext_output/cn_text.pdf";

    /**
     * 使用ttc的字体文件时，需要在末尾加上',1'，可查看
     * {@link com.itextpdf.io.font.FontProgramFactory} 的233行。
     *
     * ttc全称是TrueType Collection。TTC字体是TrueType字体集成文件(. TTC文件)，是在一单独文件结构中包含多种字体,以便更有效地共享轮廓数据。
     * 通过,1 来选择具体的字体。
     */
    public static final String FONT = "font/songti-changgui.ttc,1";

    public static void main(String[] args) {
        String str = "你好, Spring";

        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DEST));
            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
            Text text = new Text(str).setFont(font);
            Paragraph paragraph = new Paragraph();
            paragraph.add(text);

            document.add(paragraph);

            document.add(new Paragraph().setFont(font).add("你好, Java").setBold());

            document.close();

        } catch (Exception e) {
            logger.error("生成pdf时异常", e);
        }
    }
}
