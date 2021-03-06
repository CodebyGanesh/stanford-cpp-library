package stanford.spl;

import acm.graphics.*;
import acm.util.*;

/**
 * 
 * @author Marty Stepp
 * @version 2015/10/08
 */
public class GBufferedImage_load extends JBECommand {
	// gbufferedimage.load("foobar.png");
	public void execute(TokenScanner paramTokenScanner, JavaBackEnd paramJavaBackEnd) {
		paramTokenScanner.verifyToken("(");
		String id = nextString(paramTokenScanner);
		paramTokenScanner.verifyToken(",");
		String filename = nextString(paramTokenScanner);
		paramTokenScanner.verifyToken(")");
		
		GObject gobj = paramJavaBackEnd.getGObject(id);
		if (gobj != null && gobj instanceof GBufferedImage) {
			GBufferedImage img = (GBufferedImage) gobj;
			try {
				img.load(filename);
				String b64 = img.toStringBase64();
				SplPipeDecoder.writeResult(b64);   // this is a LONG string
			} catch (Exception ex) {
				SplPipeDecoder.writeResult("error:" + ex.getClass().getSimpleName() + ": " + ex.getMessage().replace('\n', ' '));
			}
		}
	}
}
