package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonHelper
{
   private JsonHelper()
   {
   }

   public static String toJson(Object from)
   {
      try
      {
         return new ObjectMapper().writeValueAsString(from);
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(e);
      }
   }

   public static String toJsonPretty(String dirty)
   {
      try
      {
         ObjectMapper mapper = new ObjectMapper();
         mapper.enable(SerializationFeature.INDENT_OUTPUT);

         JsonNode jsonNode = mapper.readTree(dirty);

         return mapper.writeValueAsString(jsonNode);
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(dirty, e);
      }
   }

   public static String toJsonPretty(Object from)
   {
      try
      {
         return new ObjectMapper()
               .writerWithDefaultPrettyPrinter()
               .writeValueAsString(from);
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(e);
      }
   }

   public static <T> T fromJson(String from, Class<T> t) throws Exception
   {
      try
      {
         return new ObjectMapper().readValue(from, t);
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(from, e);
      }
   }
}
