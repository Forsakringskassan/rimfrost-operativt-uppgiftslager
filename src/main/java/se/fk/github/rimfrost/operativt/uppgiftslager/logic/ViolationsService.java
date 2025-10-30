package se.fk.github.quarkustemplate.logic;

import java.util.Scanner;

public class ViolationsService
{
   /**
    * This is here to show what happens when static code analysis finds problems
    */
   public void a()
   {
      String a = null;
      try
      {
         Scanner scanner = new Scanner(System.in);
         String input = scanner.next();
         "".concat(input);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
