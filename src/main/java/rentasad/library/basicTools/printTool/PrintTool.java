package rentasad.library.basicTools.printTool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * 
 * Gustini GmbH (2018)
 * Creation: 07.11.2018
 * gustini.library.basicTools.printTool
 * rentasad.library.basicTools.printTool
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class PrintTool
{
    // The number of CMs per Inch
    public static final double CM_PER_INCH = 0.393700787d;
    // The number of Inches per CMs
    public static final double INCH_PER_CM = 2.545d;
    // The number of Inches per mm's
    public static final double INCH_PER_MM = 25.45d;
    private Map<String, PrintService> psMap;

    public PrintTool()
    {
        // TODO Auto-generated constructor stub
    }

//    @Test
//    public void testPrint()
//    {
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//        aset.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
//        aset.add(new MediaPrintableArea(0, 0, 150, 100, MediaPrintableArea.MM));
////        List<PDPage> pageList = doc.getDocumentCatalog().getAllPages();
////        PDPage page = pageList.get(0);
////        BufferedImage img = page.convertToImage();
////        Graphics2D g2d = img.createGraphics();
////        Printable printableDocument = new PrintableDocument();
////        
////        Paper paper = new Paper();
////        double width, height;
////        width = cmsToPixel(100, 203);
////        height = cmsToPixel(150, 203);
////        paper.setSize(width, height);
////        PageFormat pageFormat = new PageFormat();
////        pageFormat.setPaper(paper);
////        printJob.setPrintable(printableDocument, pageFormat);
//
//        
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        pj.setPrintable(new PrintTask());
//
//        if (pj.printDialog(aset))
//        {
//            try
//            {
//                pj.print(aset);
//            } catch (PrinterException ex)
//            {
//                ex.printStackTrace();
//            }
//        }
//    }

    /**
     * 
     * Description:
     * 
     * @param fileName
     * @return
     *         Creation: 12.11.2015 by mst
     * @throws IOException
     * @throws PrinterException
     * @throws GreetingCardPrintException
     */
    public boolean printPdfFile(String pdfFileName, final String printerName) throws IOException, PrinterException
    {
        boolean printsuccess = false;

        File fileToPrint = new File(pdfFileName);
        if (fileToPrint.exists())
        {
            PDDocument doc = null;
            // PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            // aset.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
            // aset.add(new MediaPrintableArea(0, 0, 150, 100, MediaPrintableArea.MM));

            PrinterJob printJob = PrinterJob.getPrinterJob();

            /*
             * Umwandeln der PrintServices in eine Map
             */
            if (existPrinter(printerName))
            {
                PrintService printService = psMap.get(printerName);
                printService.getAttributes();
                printJob.setPrintService(printService);

                try
                {
                    /*
                     * Drucken der PDF-Datei
                     */

                    doc = PDDocument.load(fileToPrint);
                    // PDPageContentStream contentStream = new PDPageContentStream(doc, , true, true, true);

                    doc.silentPrint(printJob);
                    printsuccess = true;

                } catch (Exception ex)
                {
                    throw ex;
                } finally
                {
                    if (doc != null)
                    {
                        try
                        {
                            doc.close();
                        } catch (IOException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            } else
            {
                /*
                 * Drucker existiert nicht.
                 */
                throw new PrinterException("Drucker " + printerName + " nicht gefunden!");
            }
        } else
        {
            throw new FileNotFoundException(fileToPrint.getAbsolutePath());
        }

        return printsuccess;

    }

    /**
     * Converts the given pixels to cm's based on the supplied DPI
     *
     * @param pixels
     * @param dpi
     * @return
     */
    public static double pixelsToCms(double pixels, double dpi)
    {
        return inchesToCms(pixels / dpi);
    }

    /**
     * Converts the given cm's to pixels based on the supplied DPI
     *
     * @param cms
     * @param dpi
     * @return
     */
    public static double cmsToPixel(double cms, double dpi)
    {
        return cmToInches(cms) * dpi;
    }

    /**
     * Converts the given cm's to inches
     *
     * @param cms
     * @return
     */
    public static double cmToInches(double cms)
    {
        return cms * CM_PER_INCH;
    }

    /**
     * Converts the given inches to cm's
     *
     * @param inch
     * @return
     */
    public static double inchesToCms(double inch)
    {
        return inch * INCH_PER_CM;
    }

    public static class PrintTask implements Printable
    {

        private BufferedImage img;

        public PrintTask()
        {
            double width = cmsToPixel(1, 72);
            double height = cmsToPixel(5, 72);

            img = new BufferedImage((int) Math.round(width), (int) Math.round(height), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.RED);
            g2d.draw(new Rectangle2D.Double(0, 0, width - 1, height - 1));
            g2d.draw(new Line2D.Double(0, 0, width, height));
            g2d.draw(new Line2D.Double(0, height, width, 0));
            g2d.dispose();
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
        {
            int result = NO_SUCH_PAGE;
            if (pageIndex < 2)
            {
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();

                System.out.println("Page width = " + width + " = " + pixelsToCms(width, 72));
                System.out.println("Page height = " + height + " = " + pixelsToCms(height, 72));

                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                double x = cmsToPixel(1, 72);
                double y = cmsToPixel(1, 72);
                System.out.println("Draw At " + x + "x" + y);
                g2d.drawRect(0, 0, (int) width - 1, (int) height - 1);
                g2d.drawImage(img, (int) x, (int) y, null);
                result = PAGE_EXISTS;
            }
            return result;
        }

    }

    /**
     * 
     * Description: Return availble Printerservices
     * 
     * @return
     *         Creation: 28.09.2018 by mst
     */
    public Map<String, PrintService> getPrinterMap()
    {
        /*
         * Only load one times
         */
        if (this.psMap == null)
        {
            this.psMap = Arrays.asList(PrinterJob.lookupPrintServices()).stream().collect(Collectors.toMap(PrintService::getName, Function.<PrintService> identity()));
        }
        return this.psMap;
    }

    /**
     * 
     * Description: return true if printer exist
     * 
     * @param printerName
     * @return
     *         Creation: 28.09.2018 by mst
     */
    public boolean existPrinter(String printerName)
    {
        return getPrinterMap().containsKey(printerName);
    }

    /**
     * 
     * Description: Print InputStream in Default Quality
     * 
     * @param pdfInputStream
     * @param printerName
     * @return
     * @throws IOException
     * @throws PrinterException
     *             Creation: 28.09.2018 by mst
     */
    public boolean printInputStream(InputStream pdfInputStream, final String printerName) throws IOException, PrinterException
    {
        
        boolean printsuccess = false;

        if (pdfInputStream != null)
        {
            PDDocument doc = null;

            PrinterJob printJob = PrinterJob.getPrinterJob();
            /*
             * Umwandeln der PrintServices in eine Map
             */
            Map<String, PrintService> psMap = getPrinterMap();
            if (psMap.containsKey(printerName))
            {
                
                printJob.setPrintService(psMap.get(printerName));

                try
                {
                    /*
                     * Drucken der PDF-Datei
                     */

                    doc = PDDocument.load(pdfInputStream);
                    
                    doc.silentPrint(printJob);
                    printsuccess = true;

                } catch (Exception ex)
                {
                    throw ex;
                } finally
                {
                    if (doc != null)
                    {
                        try
                        {
                            doc.close();
                        } catch (IOException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            } else
            {
                /*
                 * Drucker existiert nicht.
                 */
                throw new PrinterException("Drucker " + printerName + " nicht gefunden!");
            }
        } else
        {
            throw new PrinterException("Empty PDF InputStream!!");
        }

        return printsuccess;

    }

}
