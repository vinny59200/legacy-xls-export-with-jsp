package com.vv.web;

import com.vv.domain.TdoVV;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportServlet extends HttpServlet {

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        processRequest( request, response );
    }

    private static void processRequest( final HttpServletRequest request, final HttpServletResponse response ) throws IOException {
        // Get data from the session (pageList)
        List<TdoVV> pageList = ( List<TdoVV> ) request.getSession()
                                                      .getAttribute( "pageList" );

        if ( pageList == null ) {
            response.sendError( HttpServletResponse.SC_BAD_REQUEST, "No data to export" );
            return;
        }

        // Prepare Excel document
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet( "Data" );

        // Create header row
        Row headerRow = sheet.createRow( 0 );
        headerRow.createCell( 0 )
                 .setCellValue( "Codification" );
        headerRow.createCell( 1 )
                 .setCellValue( "Model" );
        headerRow.createCell( 2 )
                 .setCellValue( "Quantity" );
        headerRow.createCell( 3 )
                 .setCellValue( "Measure Unit" );

        // Add data rows
        int rowNum = 1;
        for ( TdoVV vvData : pageList ) {
            Row row = sheet.createRow( rowNum++ );
            row.createCell( 0 )
               .setCellValue( vvData.getCodification() );
            row.createCell( 1 )
               .setCellValue( vvData.getModel() );
            row.createCell( 2 )
               .setCellValue( vvData.getQuantity() );
            row.createCell( 3 )
               .setCellValue( vvData.getMeasureUnit() );
        }

        // Set the response headers
        response.setContentType( "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
        response.setHeader( "Content-Disposition", "attachment; filename=export.xlsx" );

        // Write Excel file to response output stream
        try ( OutputStream out = response.getOutputStream() ) {
            workbook.write( out );
        }

        workbook.close();
    }
}
