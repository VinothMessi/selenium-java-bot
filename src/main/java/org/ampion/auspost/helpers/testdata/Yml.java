package org.ampion.auspost.helpers.testdata;

import org.ampion.auspost.myexception.MyException;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;

import static org.ampion.auspost.Constants.*;

public class Yml {
    private Map<Object, Object> fileContents;
    private final List<Map<Object, Object>> arrayList = new ArrayList<>();

    // Read file
    public void load(final String fileName) throws MyException {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(TEST_DATA + fileName);
        } catch (FileNotFoundException e) {
            throw new MyException(FILE_NOT_FOUND);
        }
        Yaml yaml = new Yaml();
        this.fileContents = yaml.load(inputStream);
    }

    // Read as Properties
    public Object getValue(final @NotNull String key) {
        Map<?, ?> map = (Map<?, ?>) this.fileContents.get(key.split("\\.")[0]);
        return map.get(key.split("\\.")[1]);
    }

    // Read as DataProvider
    @SuppressWarnings("unchecked")
    public @NotNull Iterator<Object[]> getValues(final String key) {
        ArrayList<Map<?, ?>> arrList = (ArrayList<Map<?, ?>>) fileContents.get(key);
        Collection<Object[]> dp = new ArrayList<>();
        for (Map<?, ?> map : arrList) {
            dp.add(new Object[]{map});
        }
        return dp.iterator();
    }

    // // Read multiple documents
    @SuppressWarnings("unchecked")
    public List<Map<Object, Object>> loadAll(final String fileName) throws MyException {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(TEST_DATA + fileName);
        } catch (FileNotFoundException e) {
            throw new MyException(FILE_NOT_FOUND);
        }
        Yaml yaml = new Yaml();

        for (Object o : yaml.loadAll(inputStream)) {
            Map<Object, Object> map = (Map<Object, Object>) o;
            arrayList.add(map);
        }
        return arrayList;
    }

    // Read multiple documents
    public Iterator<Object[]> getValues() {
        Collection<Object[]> dp = new ArrayList<>();
        for (Map<?, ?> map : arrayList) {
            dp.add(new Object[]{map});
        }
        return dp.iterator();
    }

    public <T> T loadAs(final String fileName, Class<T> c) throws MyException {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(new FileReader(TEST_DATA + fileName), c);
        } catch (FileNotFoundException e) {
            throw new MyException(FILE_NOT_FOUND);
        }
    }
}