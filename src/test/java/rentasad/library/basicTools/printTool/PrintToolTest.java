package rentasad.library.basicTools.printTool;

import org.junit.After;
import org.junit.Before;

/**
 * 
 * Gustini GmbH (2018)
 * Creation: 28.09.2018
 * gustini.library.basicTools
 * org.gustini.library.tools.printTool
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class PrintToolTest
{
    @SuppressWarnings("unused")
	private PrintTool printTool;
    
    
    @Before
    public void setUp() throws Exception
    {
        printTool = new PrintTool();
        
    }

    @After
    public void tearDown() throws Exception
    {
    }



//    @Test
//    public void testPrintPdfFile() throws Exception
//    {
//        String filePathToPrint = "c:\\Development\\Workspace New\\gustini.application\\gustini.application.GreetingCardPrinter\\ressources\\unittests\\greetingcards\\todoPath\\430243436.pdf";
//        String printerName = "Godex RT700"; 
//        printTool.printPdfFile(filePathToPrint, printerName);
//    }


//    @Test
//    public void testPrintInputStream() throws Exception
//    {
//        Map<String, String> mySqlConfigMap = null;
//        PrintTool printTool = new PrintTool();
//        mySqlConfigMap = new HashMap<>();
//        mySqlConfigMap.put("MYSQL_HOST", "gstvmdbs3.gustini.local");
//        mySqlConfigMap.put("MYSQL_DATABASE", "Endkontrolle_DEV1");
//        mySqlConfigMap.put("MYSQL_USER", "greetingcardDev");
//        mySqlConfigMap.put("MYSQL_PASSWORD", "greetingcardDev");
//        mySqlConfigMap.put("MYSQL_ENCODING", "utf8");
//        String printerName = "Godex RT700"; 
//        int cardID = 2639;
//        InputStream is = null;
//        Connection con = MYSQLConnection.dbConnect(mySqlConfigMap);
//        String selectQuery = "SELECT pdfFileInputStreamContent FROM greetingCards g WHERE g.cardID=?";
//        PreparedStatement ps = con.prepareStatement(selectQuery);
//        ps.setInt(1, cardID);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next())
//        {
//            is = rs.getBinaryStream("pdfFileInputStreamContent");
//        }
//        printTool.printInputStream(is, printerName);
//        con.close();
//    }
}
