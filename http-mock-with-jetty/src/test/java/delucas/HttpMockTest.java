package delucas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 * Esta clase JUnit utiliza Jetty para simular (mock) el comportamiento de un
 * servidor HTTP. Cada método indica el comportamiento que espera del servidor
 * para poder comprobar distintos aspectos del componente bajo test.
 * Por motivos didácticos esta clase usa HttpClient para invocar al servidor,
 * pero en una implementación real este test JUnit ejecutaría métodos del
 * componente bajo test, como ser un DAO. 
 * 
 * Nota de Lucas:
 * El código y la idea original se encuentran en
 * http://www.dosideas.com/noticias/java/584-armando-un-mock-de-un-servidor-http.html
 * Mi mérito se reduce simplemente a mavenizar el proyecto, facilitando la
 * incorporación a proyectos de ese estilo (Maven 2)
 * 
 */
public class HttpMockTest {

    private static final int PUERTO_HTTP = 3210;
    private static final String URL_SERVICIO = "http://localhost:" + PUERTO_HTTP;

    /** El server http que servirá para hacer un mock del servicio real */
    private Server httpServer;


    @Before
    public void setUp() throws Exception {
        //iniciamos el servidor mock
        httpServer = new Server(PUERTO_HTTP);
        httpServer.start();

    }

    @After
    public void tearDown() throws Exception {
        httpServer.stop();
    }


    /** Esta prueba simula una respuesta OK (200) del servidor.
     *  Para esto se crea un handler que devuelve un codigo de estado 200
     *  junto con una respuesta.
     *
     *  Luego usando HttpClient se verifica que el servidor devuelva el valor
     *  deseado a nuestra petición. Esta invocación con HttpClient debería ser
     *  reemplazada por el componente de negocio que realiza la invocación.
     */
    @Test
    public void respuestaOk() throws IOException {
        Handler handler = new AbstractHandler() {

            public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
                    throws IOException, ServletException {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().print("<h1>Hola</h1>");
                ((Request) request).setHandled(true);
            }
        };
        httpServer.setHandler(handler);

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(URL_SERVICIO);
        int status = client.executeMethod(method);
        String respuesta = method.getResponseBodyAsString();

        assertEquals(HttpServletResponse.SC_OK, status);
        assertEquals("<h1>Hola</h1>", respuesta);
    }


    /**
     * Esta prueba simula una petición no encontrada (404) en el servidor.
     * Para esto creamos un handler que devuelve un código de estado 404.
     */
    @Test
    public void respuestaNoEncontrado() throws IOException {
        Handler handler = new AbstractHandler() {

            public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
                    throws IOException, ServletException {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                ((Request) request).setHandled(true);
            }
        };
        httpServer.setHandler(handler);

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(URL_SERVICIO);
        int status = client.executeMethod(method);

        assertEquals(HttpServletResponse.SC_NOT_FOUND, status);
    }


    /**
     * Esta prueba simula una petición que genera un error en el servidor (500).
     * Para esto creamos un handler que devuelve un código de estado 500.
     */
    @Test
    public void respuestoErrorEnServidor() throws IOException {
        Handler handler = new AbstractHandler() {

            public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
                    throws IOException, ServletException {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ((Request) request).setHandled(true);
            }
        };
        httpServer.setHandler(handler);

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(URL_SERVICIO);
        int status = client.executeMethod(method);

        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, status);
    }

}