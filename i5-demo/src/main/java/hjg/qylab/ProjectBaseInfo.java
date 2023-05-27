package hjg.qylab;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author hjg
 * @Date 2023-01-03 18:22
 */
public class ProjectBaseInfo {

    public static final String RESULT
            = "iText5-demo/target/base_info.pdf";

    public static final float cellPadding = 5f;

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
        PdfPTable table = createBaeInfoTable();
        document.add(table);
        // step 5
        document.close();
    }

    private static PdfPTable createBaeInfoTable() throws DocumentException, IOException {
        int numColumns = 7;
        PdfPTable table = new PdfPTable(numColumns);
        //A4的宽为595，减去2个外边距，595 - 72 = 523。523*0.9 = 470
        table.setWidthPercentage(470/5.23f);
        table.setWidths(new int[]{1, 1, 1, 1, 1, 1, 1});

        //这几个方法，只在table.addCell(phrase | string)时，才会有用
        //table.setHorizontalAlignment(Element.ALIGN_CENTER);

        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        if (titleFont == null) {
            titleFont = new Font(base, 16, Font.BOLD, BaseColor.BLACK);
        }
        if (boldFont == null) {
            boldFont = new Font(base, 12, Font.BOLD);
        }
        if (font == null) {
            font = new Font(base, 12);
        }

        //设置标题
        setUpTitle(table);
        //初始化表头前四项
        fillHeaderInfo(table);
        //项目负责人信息
        fillFzrInfo(table);
        //项目联系人信息
        fillLxrInfo(table);
        //项目推荐人信息
        fillTjrInfo(table);
        //主要参加单位
        fillMemberUnitInfo(table);
        //项目简介
        String synopsis = "测试项目";
        fillDescInfo(synopsis, table);

        return table;
    }

    private static void setUpTitle(PdfPTable table) {
        PdfPCell titleCell = new PdfPCell(new Phrase("清源创新实验室项目申报基本信息表", titleFont));
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setColspan(7);
        titleCell.setBorder(PdfPCell.NO_BORDER);
        titleCell.setPaddingBottom(cellPadding * 2);
        table.addCell(titleCell);
    }

    private static void fillHeaderInfo(PdfPTable table) {
        DecimalFormat df = new DecimalFormat(",##0.00####");
        for(int i=0; i<4; i++) {
            PdfPCell keyCell;
            PdfPCell valueCell;
            if (i == 0) {
                keyCell = new PdfPCell(new Phrase("项目名称", boldFont));
                valueCell = new PdfPCell(new Phrase("测试", font));
            } else if (i == 1) {
                keyCell = new PdfPCell(new Phrase("项目类型", boldFont));
                valueCell = new PdfPCell(new Phrase("重点项目", font));
            } else if (i == 3) {
                keyCell = new PdfPCell(new Phrase("经费概算", boldFont));
                BigDecimal outlayBudget = BigDecimal.valueOf(500);
                BigDecimal labAppliedBudget = BigDecimal.valueOf(360);
                StringBuilder sb = new StringBuilder();
                sb.append("总概算").append(" ").append(df.format(outlayBudget)).append(" ")
                        .append("万元，其中申请实验室经费").append(" ").append(df.format(labAppliedBudget)).append(" ").append("万元");
                valueCell = new PdfPCell(new Phrase(sb.toString(), font));
            } else {
                keyCell = new PdfPCell(new Phrase("执行时间", boldFont));
                StringBuilder sb = new StringBuilder();

                Calendar ca = Calendar.getInstance();
                ca.setTime(new Date());

                sb.append(ca.get(Calendar.YEAR)).append(" ").append("年").append(" ")
                        .append(ca.get(Calendar.MONTH) + 1).append(" ").append("月").append(" ")
                        .append(ca.get(Calendar.DAY_OF_MONTH)).append(" ").append("日").append(" ")
                        .append("至").append(" ");
                ca.setTime(new Date());
                sb.append(ca.get(Calendar.YEAR)).append(" ").append("年").append(" ")
                        .append(ca.get(Calendar.MONTH) + 1).append(" ").append("月").append(" ")
                        .append(ca.get(Calendar.DAY_OF_MONTH)).append(" ").append("日");
                valueCell = new PdfPCell(new Phrase(sb.toString(), font));
            }
            keyCell.setColspan(2);
            keyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            keyCell.setPadding(cellPadding);

            valueCell.setColspan(5);
            valueCell.setVerticalAlignment(Element.ALIGN_CENTER);
            valueCell.setPadding(cellPadding);

            table.addCell(keyCell);
            table.addCell(valueCell);
        }
    }

    private static void fillFzrInfo(PdfPTable table) {
        PdfPCell fzrCell = new PdfPCell(new Phrase("项目负责人信息", boldFont));
        fzrCell.setRowspan(7);
        fzrCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        fzrCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        fzrCell.setPadding(cellPadding);
        fzrCell.setLeading(0, relativeLeading);
        table.addCell(fzrCell);
        for(int i=0; i<7; i++) {
            if (i == 0) {
                PdfPCell k1Cell = new PdfPCell(new Phrase("姓名", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("李白", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setPadding(cellPadding);
                v1Cell.setPadding(cellPadding);

                PdfPCell k2Cell = new PdfPCell(new Phrase("性别", boldFont));
                PdfPCell v2Cell = new PdfPCell(new Phrase("男", font));
                k2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k2Cell.setPadding(cellPadding);
                v2Cell.setPadding(cellPadding);

                PdfPCell k3Cell = new PdfPCell(new Phrase("出生日期", boldFont));
                PdfPCell v3Cell;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String birthdayStr = sdf.format(new Date());
                v3Cell = new PdfPCell(new Phrase(birthdayStr, font));

                k3Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k3Cell.setPadding(cellPadding);
                v3Cell.setPadding(cellPadding);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
                table.addCell(k2Cell);
                table.addCell(v2Cell);
                table.addCell(k3Cell);
                table.addCell(v3Cell);
            } else if (i == 1) {
                PdfPCell k1Cell = new PdfPCell(new Phrase("证件类型", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("身份证", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setPadding(cellPadding);
                v1Cell.setPadding(cellPadding);

                PdfPCell k2Cell = new PdfPCell(new Phrase("证件号码", boldFont));
                PdfPCell v2Cell = new PdfPCell(new Phrase("400900199910109999", font));
                k2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k2Cell.setPadding(cellPadding);
                v2Cell.setColspan(3);
                v2Cell.setPadding(cellPadding);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
                table.addCell(k2Cell);
                table.addCell(v2Cell);
            } else if (i == 2) {
                PdfPCell k1Cell = new PdfPCell(new Phrase("所在单位", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("湖北IBM", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setPadding(cellPadding);
                v1Cell.setColspan(5);
                v1Cell.setPadding(cellPadding);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
            } else if (i == 3) {
                PdfPCell k1Cell = new PdfPCell(new Phrase("最高学位", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("硕士", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setPadding(cellPadding);
                v1Cell.setColspan(5);
                v1Cell.setPadding(cellPadding);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
            } else if (i == 4) {
                PdfPCell k1Cell = new PdfPCell(new Phrase("职称", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("架构师", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setPadding(cellPadding);
                v1Cell.setColspan(2);
                v1Cell.setPadding(cellPadding);

                PdfPCell k2Cell = new PdfPCell(new Phrase("职务", boldFont));
                PdfPCell v2Cell = new PdfPCell(new Phrase("开发部经理", font));
                k2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k2Cell.setPadding(cellPadding);
                v2Cell.setColspan(2);
                v2Cell.setPadding(cellPadding);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
                table.addCell(k2Cell);
                table.addCell(v2Cell);
            } else if ( i == 5) {
                PdfPCell k1Cell = new PdfPCell(new Phrase("电子邮箱", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("2234232S@163.com", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                k1Cell.setPadding(cellPadding);
                v1Cell.setColspan(2);
                v1Cell.setPadding(cellPadding);
                v1Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell k2Cell = new PdfPCell(new Phrase("移动电话", boldFont));
                PdfPCell v2Cell = new PdfPCell(new Phrase("13900003333", font));
                k2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                k2Cell.setPadding(cellPadding);
                v2Cell.setColspan(2);
                v2Cell.setPadding(cellPadding);
                v2Cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
                table.addCell(k2Cell);
                table.addCell(v2Cell);
            } else {
                PdfPCell k1Cell = new PdfPCell(new Phrase("研究领域", boldFont));
                PdfPCell v1Cell = new PdfPCell(new Phrase("经济领域", font));
                k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                k1Cell.setPadding(cellPadding);
                v1Cell.setColspan(5);
                v1Cell.setPadding(cellPadding);

                table.addCell(k1Cell);
                table.addCell(v1Cell);
            }
        }
    }

    private static void fillLxrInfo(PdfPTable table) {
        PdfPCell lxrCell = new PdfPCell(new Phrase("项目联系人", boldFont));
        lxrCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        lxrCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        lxrCell.setPadding(cellPadding);
        lxrCell.setLeading(0, relativeLeading);
        table.addCell(lxrCell);
        PdfPCell nameKCell = new PdfPCell(new Phrase("姓名", boldFont));
        nameKCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nameKCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        nameKCell.setPadding(cellPadding);
        table.addCell(nameKCell);

        PdfPCell nameCell = new PdfPCell(new Phrase("李白", font));
        nameCell.setColspan(2);
        nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        nameCell.setPadding(cellPadding);
        table.addCell(nameCell);

        PdfPCell phoneKCell = new PdfPCell(new Phrase("移动电话", boldFont));
        phoneKCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        phoneKCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        phoneKCell.setPadding(cellPadding);
        table.addCell(phoneKCell);

        PdfPCell phoneCell = new PdfPCell(new Phrase("13500002222", font));
        phoneCell.setColspan(2);
        phoneCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        phoneCell.setPadding(cellPadding);
        table.addCell(phoneCell);
    }

    private static void fillTjrInfo(PdfPTable table) {
        //推荐人信息
        List<String> referrerDetails = new ArrayList<String>(){
            {
                add("TEST");
                add("TEST");
                add("TEST");
            }
        };

        if (referrerDetails == null || referrerDetails.size() == 0) {
            referrerDetails = new ArrayList<>();
            referrerDetails.add("");
        }
        PdfPCell tjrCell = new PdfPCell(new Phrase("项目推荐人", boldFont));
        tjrCell.setRowspan(referrerDetails.size() * 2);
        tjrCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tjrCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tjrCell.setPadding(cellPadding);
        tjrCell.setLeading(0, relativeLeading);
        table.addCell(tjrCell);
        for(String details : referrerDetails) {
            PdfPCell k1Cell = new PdfPCell(new Phrase("姓名", boldFont));
            PdfPCell v1Cell = new PdfPCell(new Phrase("李白", font));
            k1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            k1Cell.setPadding(cellPadding);
            v1Cell.setColspan(2);
            v1Cell.setPadding(cellPadding);

            PdfPCell k2Cell = new PdfPCell(new Phrase("联系方式", boldFont));
            PdfPCell v2Cell = new PdfPCell(new Phrase("13500008888", font));
            k2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            k2Cell.setPadding(cellPadding);
            v2Cell.setColspan(2);
            v2Cell.setPadding(cellPadding);

            PdfPCell k3Cell = new PdfPCell(new Phrase("职务职称", boldFont));
            PdfPCell v3Cell = new PdfPCell(new Phrase("高级工程师", font));
            k3Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            k3Cell.setPadding(cellPadding);
            v3Cell.setColspan(2);
            v3Cell.setPadding(cellPadding);

            PdfPCell k4Cell = new PdfPCell(new Phrase("所在单位", boldFont));
            PdfPCell v4Cell = new PdfPCell(new Phrase("Apple", font));
            k4Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            k4Cell.setPadding(cellPadding);
            v4Cell.setColspan(2);
            v4Cell.setPadding(cellPadding);

            table.addCell(k1Cell);
            table.addCell(v1Cell);
            table.addCell(k2Cell);
            table.addCell(v2Cell);
            table.addCell(k3Cell);
            table.addCell(v3Cell);
            table.addCell(k4Cell);
            table.addCell(v4Cell);
        }
    }

    private static void fillMemberUnitInfo(PdfPTable table) {
        List<String> list = new ArrayList<String>(){
            {
                add("TEST");
                add("TEST");
                add("TEST");
            }
        };

        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
            list.add("IBM");
            list.add("Apple");
            list.add("Microsoft");
        }

        PdfPCell cjdwCell = new PdfPCell(new Phrase("主要参加单位", boldFont));
        cjdwCell.setRowspan(1 + list.size());
        cjdwCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cjdwCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cjdwCell.setPadding(cellPadding);
        cjdwCell.setLeading(0, relativeLeading);
        table.addCell(cjdwCell);

        String[] array = {"单位名称", "单位性质", "组织机构代码"};
        for (int i=0; i<3; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(array[i], boldFont));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(cellPadding);
            table.addCell(cell);
        }

        for (String unit : list) {
            PdfPCell cell1 = new PdfPCell(new Phrase("湖北IBM", font));
            cell1.setColspan(2);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setPadding(cellPadding);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase("民营企业", font));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setPadding(cellPadding);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("666666888888999999", font));
            cell3.setColspan(2);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setPadding(cellPadding);
            table.addCell(cell3);
        }
    }

    private static void fillDescInfo(String desc, PdfPTable table) {
        PdfPCell cell = new PdfPCell(new Phrase("项目简介", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(cellPadding);
        cell.setMinimumHeight(100f);
        table.addCell(cell);

        Paragraph paragraph = new Paragraph(desc, font);
        PdfPCell descCcell = new PdfPCell(paragraph);
        descCcell.setColspan(6);
        descCcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        descCcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        descCcell.setPadding(cellPadding);
        descCcell.setLeading(0, relativeLeading);
        table.addCell(descCcell);
    }
}
