package com.example.transparencia.poi;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.excel.AbstractExcelItemReader;
import org.springframework.batch.item.excel.Sheet;
import org.springframework.core.io.Resource;

public class PoiItemReaderTransparencia<T> extends AbstractExcelItemReader<T> {

    private Workbook workbook;
    
    private InputStream workbookStream;


    @Override
    protected Sheet getSheet(final int sheet) {
        return new PoiSheet(this.workbook.getSheetAt(sheet));
    }

    @Override
    protected int getNumberOfSheets() {
        return this.workbook.getNumberOfSheets();
    }

  
    
    @Override
    protected void openExcelFile( Resource resource) throws Exception {
        File archivo = resource.getFile();     
        workbook = new XSSFWorkbook(archivo);
    }

    @Override
    protected void doClose() throws Exception {
        if (workbook instanceof Closeable) {
            this.workbook.close();
        }

        if (workbookStream != null) {
            workbookStream.close();
        }
        this.workbook=null;
        this.workbookStream=null;
    }

}

