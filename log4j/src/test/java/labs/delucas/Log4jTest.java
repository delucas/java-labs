package labs.delucas;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

	private Logger logAplicacion = Logger.getLogger("aplicacionLogger");
	private Logger logCuentas = Logger.getLogger("cuentasLogger");

	// private Log logArchivo = LogFactory.getLog("ROLLING");

	@Test
	public void testTodosLosNivelesDeLog() {
		logCuentas.fatal("FATAL - Cuentas");
		logAplicacion.fatal("FATAL - Aplicacion");
		logCuentas.error("ERROR - Cuentas");
		logAplicacion.error("ERROR - Aplicacion");
		logCuentas.warn("WARN - Cuentas");
		logAplicacion.warn("WARN - Aplicacion");
		logCuentas.info("INFO - Cuentas");
		logAplicacion.info("INFO - Aplicacion");
		logCuentas.debug("DEBUG - Cuentas");
		logAplicacion.debug("DEBUG - Aplicacion");
		

	}

}
