import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

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
   public static void main(final String[] args) throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
      ArrayNode output = objectMapper.createArrayNode();
      Input inputData = objectMapper.readValue(new File(args[0]),
            Input.class);
      WhereIs.thePopcorn(inputData, objectMapper, output);
      objectWriter.writeValue(new File(args[1]), output);
   }
}

