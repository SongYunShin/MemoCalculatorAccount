package software_최종;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.JUnit4;

import software_최종.MemoCalculatorAccountbook.CalculatorPanel;

public class MemoCalculatorAccountbookTest {

   private JFrame frm;
   private JTabbedPane tabbedPane;
   public CalculatorPanel Panel1;
   
   Calculator cal;

   @Test
   public void testMemoCalculatorAccountbook(){
      Calculator cal = new Calculator();
      if(cal.symbol == 'c'&& cal.calNum == 50)
            assertTrue(cal.result == 122);
      if(cal.symbol == 'f'&& cal.calNum == 122)
            assertTrue(cal.result == 50);
      if(cal.symbol == 'i'&& cal.calNum == 1)
            assertTrue(cal.result == 2.54);
      
      
   }

   @Test
   public void testMain() {
      Calculator cal = new Calculator();
      if(cal.symbol == '+'&& cal.calNum == 15 && cal.calNum == 10)
            assertTrue(cal.result == 25);
      if(cal.symbol == '-'&& cal.calNum == 15 && cal.calNum == 10)
            assertTrue(cal.result == 5);
   }

}