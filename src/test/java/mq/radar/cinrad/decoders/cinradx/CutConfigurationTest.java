package mq.radar.cinrad.decoders.cinradx;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ucar.unidata.io.RandomAccessFile;

public class CutConfigurationTest {
	String dbz = "data/cinradx/ppi/dBZ/FSNH_20150830000024Z_PPI_01_dBZ";

	String cappi = "data/cinradx/cappi/FSNH_20150831000348Z_CAPPI_00_default";

	RandomAccessFile dbzFile, cappiFile;

	@Before
	public void setUp() throws Exception {
		dbzFile = new RandomAccessFile(dbz, "r");
		cappiFile = new RandomAccessFile(cappi, "r");
	}

	@After
	public void tearDown() throws Exception {
		dbzFile.flush();
		dbzFile.close();

		cappiFile.flush();
		cappiFile.close();
	}

	@Test
	public void testDBZ() throws IOException {
		TaskConfiguration taskConfiguration = new TaskConfiguration();
		taskConfiguration.builder(dbzFile, 160);
		System.out.println(dbzFile.getFilePointer());
		System.out.println(taskConfiguration);
		assertTrue(taskConfiguration.getCutNumber() == 8);
		for(int i=0;i<taskConfiguration.getCutNumber();i++){
			CutConfiguration cutConfiguration=new CutConfiguration();
			cutConfiguration.builder(dbzFile, -1);
			System.out.println(cutConfiguration);
			
			System.out.println(dbzFile.getFilePointer());
		}
	}

}
