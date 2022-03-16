package org.ampion.auspost;

public final class Constants {
    private Constants() {
    }

    public static final String ROOT = System.getProperty("user.dir");
    public static final String RESOURCES = ROOT + "/src/main/resources/";
    public static final String TEST_RESOURCES = ROOT + "/src/test/resources/";
    public static final String REPORTS = TEST_RESOURCES + "reports/";
    public static final String SNAPS = REPORTS + "snaps/";
    public static final String TEST_DATA = TEST_RESOURCES + "testdata/";

    public static final String LANGUAGE = "en-US";
    public static final String FILE_NOT_FOUND = "No such file,Please check the file path, name and format";
}