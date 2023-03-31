package org.pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pe.edu.utp.App;
import org.pe.edu.utp.result.SesionClaseResult;
import org.pe.edu.utp.utils.ErrorLog;
import org.pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListasHorarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Capturar datos del Alumno
        String codigo = req.getParameter("txtCodigo");
        App.log.log("Query for: " + codigo + "\n", ErrorLog.Level.INFO);

        // Crear objeto Alumno
        try {

            List<SesionClaseResult> query = App.lista.stream().
                    filter(alu -> alu.getCod_alu().equals(codigo)).
                    collect(Collectors.toList());

            // Hasta aquí se editó.

            if (!query.isEmpty()){

                //String plantilla = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\template\\listado.html";
                String plantilla =  App.TEMPLATE_ROOT + App.SEPARADOR + "listado.html";
                String html = TextUTP.read(plantilla);

                //String detalle = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\template\\listado_item.html";
                String detalle = App.TEMPLATE_ROOT + App.SEPARADOR +  "listado_item.html";
                String html_detalle = TextUTP.read(detalle);

                StringBuilder sb = new StringBuilder();
                for (SesionClaseResult item : query) {
                    sb.append( html_detalle.replace("${alumno}", item.getAlumno() )
                            .replace("${dia}", item.getDia())
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                            .replace("${curso}", item.getCurso())
                    );
                }
                resp.getWriter().println(
                        html.replace("${detalle}", sb.toString())
                                .replace("${codigo}", codigo)
                )
                ;

            }else{
                //String filename_error = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\template\\error.html";
                String filename_error = App.TEMPLATE_ROOT + App.SEPARADOR +  "error.html";
                String html_error = TextUTP.read(filename_error);
                App.log.log(codigo + " not found info\n", ErrorLog.Level.WARN);
                resp.getWriter().println(html_error);
            }

        }catch (IllegalArgumentException e){
            //String filename_error = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\template\\error.html";
            String filename_error = App.TEMPLATE_ROOT + App.SEPARADOR +  "error.html";
            String html_error = TextUTP.read(filename_error);
            App.log.log(e.getMessage(), ErrorLog.Level.ERROR);
            resp.getWriter().println(html_error.replace("${error}", e.getMessage()));
        }

    }
}
