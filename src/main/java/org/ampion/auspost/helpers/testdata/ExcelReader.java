package org.ampion.auspost.helpers.testdata;

import com.creditdatamw.zerocell.Reader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import static org.ampion.auspost.Constants.*;

public class ExcelReader {
    public <T> List<T> read(String fileName, String sheetName, Class<T> c) {
        return load(fileName, sheetName, c)
                .list();
    }

    private <T> Reader.@NotNull ReaderBuilder<T> load(String fileName, String sheetName, Class<T> c) {
        return Reader.of(c)
                .from(new File(TEST_DATA + fileName))
                .sheet(sheetName)
                .skipHeaderRow(true);
    }
}