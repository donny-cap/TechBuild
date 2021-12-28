import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class s_list_mater implements Initializable {

    @FXML
    public Label pdf;

    @FXML
    public Label excel;

    @FXML
    private TableView<oop_materials> table_materials;

    @FXML
    private TableColumn<oop_materials, Integer> col_cost;

    @FXML
    private TableColumn<oop_materials, String> col_manuf;

    @FXML
    private TableColumn<oop_materials, String> col_name;

    @FXML
    private TableColumn<oop_materials, Integer> col_num;

    @FXML
    private TableColumn<oop_materials, Integer> col_quantity;

    @FXML
    private TableColumn<oop_materials, String> col_type;

    @FXML
    private TableColumn<oop_materials, Integer> col_weight;

    @FXML
    void import_pdf() throws SQLException, IOException, DocumentException {

        Connection conn = connectionsql.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        /* Define the SQL query */
        ResultSet query_set = stmt.executeQuery("SELECT * FROM `course_work`.materials");
        /* Step-2: Initialize PDF documents - logical objects */

        String home = System.getProperty("user.home");
        String file_name = home+"/Downloads/materials.pdf";
        Document document = new Document ();
        PdfWriter.getInstance (document, new FileOutputStream(file_name));

        document.open();

        document.add (Image.getInstance ("C:\\TechBuild\\src\\png\\Logo_PNG.png"));
        document.add(new Paragraph("            LIST OF MATERIALS"));
        document.add(new Paragraph("    "));
        //we have four columns in our table
        PdfPTable table = new PdfPTable(5);
        //create a cell object
        PdfPCell table_cell;
        PdfPCell c1 = new PdfPCell (new Phrase("Name"));
        table.addCell(c1);
        c1 = new PdfPCell (new Phrase ("Manufacturer"));
        table.addCell(c1);
        c1 = new PdfPCell (new Phrase ("Type")) ;
        table.addCell(c1);
        c1 = new PdfPCell (new Phrase ("Quantity")) ;
        table.addCell(c1);
        c1 = new PdfPCell (new Phrase ("Cost")) ;
        table.addCell(c1);

        while (query_set.next()) {
            String name = query_set.getString("name");
            table_cell=new PdfPCell(new Phrase(name));
            table.addCell(table_cell);
            String manufacturer=query_set.getString("manufacturer");
            table_cell=new PdfPCell(new Phrase(manufacturer));
            table.addCell(table_cell);
            String type=query_set.getString("type");
            table_cell=new PdfPCell(new Phrase(type));
            table.addCell(table_cell);
            String quantity=query_set.getString("quantity");
            table_cell=new PdfPCell(new Phrase(quantity));
            table.addCell(table_cell);
            String cost=query_set.getString("cost");
            table_cell=new PdfPCell(new Phrase(cost));
            table.addCell(table_cell);
        }
        /* Attach report table to PDF */
        document.add(table);
        document.close();

        /* Close all DB related objects */
        query_set.close();
        stmt.close();
        conn.close();

        pdf.setText("Success");

    }

    @FXML
    void import_xlsx() throws SQLException {

        Connection conn;
        conn = connectionsql.getConnection();
        assert conn != null;
        PreparedStatement ps=conn.prepareStatement("Select * from materials");
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        List<String> columns= new ArrayList<>() {{
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                add(rsmd.getColumnLabel(i));
            }
        }};

        try (Workbook book = new XSSFWorkbook()) {
            Sheet sheet = book.createSheet();
            Row header = sheet.createRow(0);

            for (int i=0;i<columns.size();i++){
                header.createCell(i).setCellValue(columns.get(i));
            }
            int rowIndex=0;
            while(rs.next()){
                Row row=sheet.createRow(++rowIndex);
                for(int i =0;i<columns.size();i++){
                    row.createCell(i).setCellValue(Objects.toString(rs.getObject(columns.get(i)),""));
                }
            }
            String home = System.getProperty("user.home");
            try (FileOutputStream fos=new FileOutputStream(home+"/Downloads/materials.xlsx")){
                book.write(fos);
                excel.setText("Success");
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (IOException ignored){
        }

    }

    public void exit() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/main_login.fxml");
    }

    public void list_mater() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_list_mater.fxml");
    }

    public void deliver_the_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_deliver_mater.fxml");
    }

    public void required_to_deliver() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_req_to_deliver.fxml");
    }

    public void delivered_materials() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_delivered_mater.fxml");
    }

    public void percentage() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_percentage.fxml");
    }

    public void info() throws IOException {
        Main m = new Main();
        m.changeScene("fxml/s_info.fxml");
    }

    ObservableList<oop_materials> listM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UpdateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable() throws Exception {
        col_num.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_manuf.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        listM = connectionsql.getDatamaterials();
        table_materials.setItems(listM);
    }
}