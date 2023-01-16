import java.io.File;

public class CheckerFiles {
   public static final String IN_FOLDER = "in/";
   public static final String REF_FOLDER = "ref/";
   public static final String RESULT_PATH = "result/";
   public static final String OUT_NAME = "out_";
   public static final String CHECKER_RESOURCES_FOLDER = "checker/resources/";
   public static final File TEST_INPUTS_FILE = new File(CHECKER_RESOURCES_FOLDER + IN_FOLDER);

   public static final String OUT_FILE = "results.out";
   public static final File TEST_OUT_FILE = new File(RESULT_PATH + OUT_FILE);
}
