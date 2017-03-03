import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class testmail {

	public static void main(String[] args) throws URISyntaxException {
		Desktop desktop;
		if (Desktop.isDesktopSupported() 
		    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
		  //URI mailto = null;

			URI mailto = new URI("mailto:?subject=My%20Subject&body=The%20Body");

		  try {
			desktop.mail(mailto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
		  // TODO fallback to some Runtime.exec(..) voodoo?
		  throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
		}

	}

}
