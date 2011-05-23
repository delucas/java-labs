package delucas.labs.opencsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class OpenCSVTest {

	@Test
	public void funcionamientoBasicoLectura() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("csvbasico.csv"));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			System.out.println(nextLine[0] + " <-> " + nextLine[1] + " <-> "
					+ "etc...");
		}
	}

	@Test
	public void funcionamientoBasicoEscritura() throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("csvbasico-out.csv"),
				';');
		// feed in your array (or convert your data to an array)

		for (int i = 0; i < 10; i++) {
			String[] entries = ("first" + i + "#second" + i + "#third" + i).split("#");
			writer.writeNext(entries);
			
		}
		writer.close();
	}

}
