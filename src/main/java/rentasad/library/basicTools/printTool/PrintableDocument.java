package rentasad.library.basicTools.printTool;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;

/**
 * 
 * Gustini GmbH (2018)
 * Creation: 01.10.2018
 * gustini.library.basicTools.printTool
 * rentasad.library.basicTools.printTool
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class PrintableDocument implements Printable
{

    public PrintableDocument() throws IOException
    {
//        PDDocument doc = PDDocument.load(pdfInputStream);
//        List<PDPage> pageList = doc.getDocumentCatalog().getAllPages();
//        PDPage page = pageList.get(0);
//        BufferedImage img = page.convertToImage();
//        Graphics2D g2d = img.createGraphics();
//        
    }

    @Override
    public int print(Graphics arg0, PageFormat arg1, int arg2) throws PrinterException
    {
        // TODO Auto-generated method stub
        return PAGE_EXISTS;
    }

}
