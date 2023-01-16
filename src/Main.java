import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;


public final class Main {
   private Main() {

   }

   /**
    * in main we read the input from args[0], and write the output in args[1]
    * we also call the thePopcorn function, which is basically the main handler
    * of the program
    */
   static int number = 1;
   public static void main(final String[] args) throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
      ArrayNode output = objectMapper.createArrayNode();
      Input inputData = objectMapper.readValue(new File(args[0]),
            Input.class);
      String last5 = args[0].substring(args[0].length() - 6);
      if (last5.equals("0.json"))
         last5 = "10.json";

      WhereIs.thePopcorn(inputData, objectMapper, output);
      objectWriter.writeValue(new File(CheckerFiles.RESULT_PATH + "out_basic_" + last5),
            output);
      objectWriter.writeValue(new File(args[1]), output);
   }
}

