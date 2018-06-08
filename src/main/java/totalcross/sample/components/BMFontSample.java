package totalcross.sample.components;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import totalcross.Launcher;
import totalcross.io.File;
import totalcross.ui.Container;
import totalcross.ui.font.BMFont;
import totalcross.ui.gfx.Graphics;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.util.zip.ZipEntry;
import totalcross.util.zip.ZipStream;

public class BMFontSample extends Container {
   BMFont font;
   ArrayList<Image> pages;
   
   @Override
   public void initUI(){
      try {
         File is = new File("D:/TotalCross/projects/TCSample/src/main/resources/bmfont/Pacifico.zip", File.READ_ONLY);
         ZipStream zs = new ZipStream(is, ZipStream.INFLATE);
         pages = new ArrayList<Image>();
         
         ZipEntry entry = zs.getNextEntry();
         while (entry != null) {
            System.out.println(entry.getName() + " - " + entry.getSize());
            if (entry.getName().endsWith(".fnt")) {
               font = new BMFont(zs, null);
            } else if (entry.getName().endsWith(".png")) {
               pages.add(new Image(zs));
            }
            
            entry = zs.getNextEntry();
         }
         
         Image[] pagesArray = new Image[pages.size()];
         pages.toArray(pagesArray);
         
         font.setTexturePages(pagesArray);
      }  catch (IOException | ImageException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void onPaint(Graphics g) {
      super.onPaint(g);
      
      font.drawText("The quick brown fox jumps\nover the lazy dog", g, 30, 30, false);
   }
}
