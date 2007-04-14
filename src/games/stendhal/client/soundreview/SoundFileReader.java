package games.stendhal.client.soundreview;

import games.stendhal.client.SpriteStore;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import marauroa.common.Log4J;

public class SoundFileReader {

	/** expected location of the sound definition file (classloader). */
	public static final String STORE_PROPERTYFILE = "data/sounds/";



	static Properties soundprops;

	public SoundFileReader() {
		

	}

	public void init() {
		init(STORE_PROPERTYFILE+"stensounds.properties");

	}

	private void init(String propertyfile) {

		soundprops = loadSoundProperties(soundprops, propertyfile);

	}
	public void initWithXml(){
		
	}
	/**
	 * Obtains a resource input stream. Fetches currently from the main
	 * program's classloader.
	 * 
	 * @param name
	 * @return InputStream
	 * @throws IOException
	 */
	public static InputStream getResourceStream(String name) throws IOException {
		URL url = SpriteStore.get().getResourceURL(name);
		if (url == null) {
			throw new FileNotFoundException(name);
		}
		return url.openStream();
	}
	/**
	 * @param prop
	 *            the Property Object to load to
	 * @param url the Propertyfile
	 * @throws IOException
	 */
	public static Properties loadSoundProperties(Properties prop, String url) {
		InputStream in1;
		if (prop == null) {
			prop = new Properties();
		}
		try {
			in1 =  getResourceStream(url);

			prop.load(in1);
			in1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	byte[] getData(String soundname) {
		byte[] data;
		

		String url = SoundFileReader.soundprops.getProperty("soundbase") + "/" + soundname;
		InputStream in;
		ByteArrayOutputStream bout;
		bout = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(url);

			transferData(in, bout, 4096);
			in.close();
		} catch (IOException e) {
			Log4J.getLogger(SoundFileReader.class).error("could not open soundfile " + url);
			return null;
		}
		data = bout.toByteArray();
		
		return data;
	}

	/**
	 * Transfers the contents of the input stream to the output stream until the
	 * end of input stream is reached.
	 * 
	 * @param input
	 * @param output
	 * @param bufferSize
	 * @throws java.io.IOException
	 */
	static void transferData(InputStream input, OutputStream output, int bufferSize) throws java.io.IOException {
		byte[] buffer = new byte[bufferSize];
		int len;

		while ((len = input.read(buffer)) > 0) {
			output.write(buffer, 0, len);
		}
	} // transferData
}
