package com.hjg.itext.qylab;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.TrueTypeCollection;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.IOException;
import java.net.URL;

/**
 * @Description
 * @Author hjg
 * @Date 2023-03-20 16:54
 */
public class ProjectContract {

    private static float firstTitleSize = 16f;

    private static float defaultFontSize = 12f;

    private static String DEST = "d:/itext_output/qylab/project_contract.pdf";

    private static String toChinese(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        float[] columnWidthArray = new float[]{5, 10, 12, 12, 5, 15};
        URL url = ProjectContract.class.getClassLoader().getResource("font/songti-changgui.ttc");
        TrueTypeCollection trueTypeCollection = new TrueTypeCollection(url.getFile());
        //0-SimSun-宋体, 1-NSimSun-新宋
        PdfFont font = PdfFontFactory.createFont(trueTypeCollection.getFontByTccIndex(0),
                PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

        //float[] columnWidthArray = new float[]{5, 10, 13, 13, 5, 15};
        //PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdfDocument);

        document.add(createTable(font, columnWidthArray));
        document.close();
    }

    private static Table createTable(PdfFont font, float[] columnWidthArray) {
        Table table = new Table(UnitValue.createPercentArray(columnWidthArray))
                .setFont(font)
                //默认占据80%宽度，设为100%
                .setWidth(UnitValue.createPercentValue(85))
                //水平居中
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                //垂直居中
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(defaultFontSize)
                .setFixedLayout();

        //插入标题：清源创新实验室科研项目信息表
        table.addCell(
                new Cell(1, columnWidthArray.length)
                        .add(new Paragraph("清源创新实验室科研项目信息表").setBold().setFontSize(firstTitleSize))
                        //padding默认为2
                        .setPaddingBottom(10f)
                        .setBorder(Border.NO_BORDER)
        );

        //4行基本信息
        initBaseInfo(table);

        //承担单位
        initCddw(table);

        //合作单位
        int hzdwCount = 3;
        for (int i=0; i<hzdwCount; i++) {
            initHzdw(table, i);
        }

        //项目负责人
        initXmfzr(table);

        //初始化其它行
        initOther(table);

        return table;
    }

    private static void initBaseInfo(Table table) {
        table.addCell(
                new Cell(1, 2).add(new Paragraph("项目编号").setBold())
        ).addCell(
                new Cell(1, 4).add(new Paragraph("SQ00222001"))
        );
        table.addCell(
                new Cell(1, 2).add(new Paragraph("项目类型").setBold())
        ).addCell(
                new Cell(1, 4).add(new Paragraph("重点项目"))
        );
        table.addCell(
                new Cell(1, 2).add(new Paragraph("项目名称").setBold())
        ).addCell(
                new Cell(1, 4).add(new Paragraph("2022-11-25重点项目1"))
        );
        table.addCell(
                        new Cell(1, 2).add(new Paragraph("起始时间").setBold())
                ).addCell(new Cell(1, 1).add(new Paragraph("2022年1月")))
                .addCell(
                        new Cell(1, 1).add(new Paragraph("终止时间").setBold())
                ).addCell(new Cell(1, 2).add(new Paragraph("2024年12月")));
    }

    private static void initCddw(Table table) {
        table.addCell(new Cell(5, 1).add(new Paragraph("承担单位").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("名称").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("测试政府机关")))
                .addCell(new Cell(1, 1).add(new Paragraph("单位所在地").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("宜昌")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("代码").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("666667777788888333")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("通讯地址").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("0539-22223333")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("邮编").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("443000")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("开户银行").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("中国银行")))
                .addCell(new Cell(1, 1).add(new Paragraph("银行账号").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("333000")));
    }

    private static void initHzdw(Table table, int index) {
        String numStr = toChinese(String.valueOf(index + 1));
        String str = "项目合作单位(" + numStr + ")";
        table.addCell(new Cell(5, 1).add(new Paragraph(str).setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("名称").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("测试政府机关")))
                .addCell(new Cell(1, 1).add(new Paragraph("单位所在地").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("宜昌")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("代码").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("666667777788888333")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("通讯地址").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("0539-22223333")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("邮编").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("443000")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("开户银行").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("中国银行")))
                .addCell(new Cell(1, 1).add(new Paragraph("银行账号").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("333000")));
    }

    private static void initXmfzr(Table table) {
        table.addCell(new Cell(6, 1).add(new Paragraph("项目负责人").setBold())
                        .setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("姓名").setBold()))
                .addCell(new Cell(1, 1).add(new Paragraph("海盗")))
                .addCell(new Cell(1, 1).add(new Paragraph("性别").setBold()))
                .addCell(new Cell(1, 2).add(new Paragraph("男")))
                .addCell(new Cell(1, 1).add(new Paragraph("学位").setBold()))
                .addCell(new Cell(1, 1).add(new Paragraph("博士")))
                .addCell(new Cell(1, 1).add(new Paragraph("出生年月").setBold()))
                .addCell(new Cell(1, 2).add(new Paragraph("2022-10-23")))
                .addCell(new Cell(1, 1).add(new Paragraph("职称").setBold()))
                .addCell(new Cell(1, 1).add(new Paragraph("教授")))
                .addCell(new Cell(1, 1).add(new Paragraph("专业").setBold()))
                .addCell(new Cell(1, 2).add(new Paragraph("应用数学")))
                .addCell(new Cell(1, 1).add(new Paragraph("所在单位").setBold()))
                .addCell(new Cell(1, 4).add(new Paragraph("湖北IBM")))
                .addCell(new Cell(1, 1).add(new Paragraph("身份证件").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("身份证")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("身份证件号码").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("400900199910109999")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 1).add(new Paragraph("联系电话").setBold()))
                .addCell(new Cell(1, 1).add(new Paragraph("18900008888")))
                .addCell(new Cell(1, 1).add(new Paragraph("Email").setBold()))
                .addCell(new Cell(1, 2).add(new Paragraph("haidao@163.com")));
    }

    private static void initOther(Table table) {

        Table innerTable = new Table(UnitValue.createPercentArray(new float[]{1}))
                .setWidth(UnitValue.createPercentValue(100))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedLayout();

        //数字可以bold
        innerTable.addCell(new Cell().add(new Paragraph("高级10人，中级10人，初级10人，其他33人"))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
        ).addCell(new Cell().add(new Paragraph("博士10人，硕士10人，学士10人，其他33人"))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
        );

        table.addCell(
                    new Cell(1, 2)
                            .add(new Paragraph("参加项目人数").setBold())
                            .setVerticalAlignment(VerticalAlignment.MIDDLE)
                )
                .addCell(
                    new Cell(1, 1)
                            .add(new Paragraph("共有：10人").setTextAlignment(TextAlignment.LEFT))
                            .add(new Paragraph("其中：").setTextAlignment(TextAlignment.LEFT))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                )
                .addCell(new Cell(1, 3).add(innerTable)
                        .setPadding(-0.5f)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                )
                /*.addCell(
                    new Cell(1, 3)
                            .add(new Paragraph("高级10人，中级10人，初级10人，其他33人"))
                            .add(new Paragraph("博士10人，硕士10人，学士10人，其他33人"))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                )*/
                .addCell(new Cell(1, 2).add(new Paragraph("主要研究内容(200字以内)").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("研究石油提取优化技术。")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("预期成果").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("其他")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("预期知识产权").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("发表高水平科研论文 0 篇，获得国外发明专利 0 项，国内发明专利 0 项，其他 3 项。"))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("预期技术标准").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("企业标准3项")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("其他预期成果(200字以内)").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("创造就业岗位")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("预期产业化成果(200字以内)").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("壮大产业链")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("预期经济效益(200字以内)").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("创造税收1000万")).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).add(new Paragraph("经费预算").setBold()).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 4).add(new Paragraph("项目总预算 200 万元，其中申请实验室经费 100 万元，合作单位资助经费 100 万元。"))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE));
    }
}
