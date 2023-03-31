package org.pe.edu.utp;

import org.apache.commons.cli.*;
import org.pe.edu.utp.load.LoadHorarios;
import org.pe.edu.utp.result.SesionClaseResult;
import org.pe.edu.utp.servlets.ListasHorarioServlet;
import org.pe.edu.utp.utils.ErrorLog;
import org.pe.edu.utp.utils.JettyUTP;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int APP_PORT = 8080;
    public static String DOCUMENT_ROOT = "";
    public static String WEB_ROOT = "";
    public static String TEMPLATE_ROOT = "";
    public static String LOG_ROOT = "";
    public static String SEPARADOR = "";
    public static List<SesionClaseResult> lista = new LinkedList<>();
    public static ErrorLog log;

    public static void main( String[] args ) throws Exception {

        SEPARADOR = System.getProperty("file.separator");  // Thanks Gn20018 !
        APP_PORT = getPort(args);
        DOCUMENT_ROOT = getDocumentRoot(args);
        WEB_ROOT = DOCUMENT_ROOT + SEPARADOR + "web";
        TEMPLATE_ROOT = DOCUMENT_ROOT + SEPARADOR + "template";
        LOG_ROOT = DOCUMENT_ROOT + SEPARADOR + "info.log";

        // Cargar data
        String path = DOCUMENT_ROOT + SEPARADOR + "rpte_mat.csv";
        System.out.println("path = " + path);
        System.out.println("log = " + LOG_ROOT);
        System.out.println("port = " + APP_PORT);
        log = new ErrorLog(LOG_ROOT);

        lista = LoadHorarios.loadData(path);

        // Buscar todas las clases del alumno.
        //clases_del_dia = lista.stream()
                //.filter(c -> c.getDia().toUpperCase().equals("LUNES") )
                //.filter(c -> c.getHini().equals("08:00"))
                //.filter(alu -> alu.getCod_alu().equals(codigo))
                //.collect(Collectors.toList());

        //String web_path = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\web\\";
        JettyUTP webserver = new JettyUTP(APP_PORT,WEB_ROOT);
        webserver.addServlet(ListasHorarioServlet.class, "/listar");
        webserver.start();
    }

    private static String getDocumentRoot(String[] args) throws ParseException {
        String dr = "";
        Options options = new Options();
        options.addOption("h", "help", false, "muestra esta ayuda");
        options.addOption("r", "doc_root", true, "ruta del document root");
        options.addOption("p", "port", true, "puerto de escucha");

        CommandLine cmd = new DefaultParser().parse(options,args);

        // Si no se envia nada, se utiliza la carpeta actual
        if ( cmd.getArgList().size() > 0 ){
            dr = cmd.getArgList().get(0);
        }else {
            dr = "./";
        }

        // Verificar si se ha solicitado la ayuda
        if ( cmd.hasOption("h") ){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FacilityFinder", options);
            System.exit(0);
        }

        if (cmd.hasOption("r")){
            dr = cmd.getOptionValue("r");
        }

        return dr;
    }

    private static int getPort(String[] args) throws ParseException {
        int port = 8080;
        Options options = new Options();
        options.addOption("h", "help", false, "muestra esta ayuda");
        options.addOption("r", "doc_root", true, "ruta del document root");
        options.addOption("p", "port", true, "puerto de escucha");
        CommandLine cmd = new DefaultParser().parse(options, args);

        // Verificar si se ha solicitado la ayuda
        if ( cmd.hasOption("h") ){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FacilityFinder", options);
            System.exit(0);
        }

        if (cmd.hasOption("p")){
            port = Integer.parseInt( cmd.getOptionValue("p") );
        }

        return port;
    }
}
