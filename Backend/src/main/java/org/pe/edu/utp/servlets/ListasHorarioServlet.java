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
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.pe.edu.utp.App.lista;

public class ListasHorarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Capturar datos del Alumno
        String codigo = req.getParameter("txtCodigo");
        App.log.log("Query for: " + codigo + "\n", ErrorLog.Level.INFO);

        // Crear objeto Alumno
        try {

            List<SesionClaseResult> query = lista.stream().
                    filter(alu -> alu.getCod_alu().equals(codigo)).
                    toList();

            List<SesionClaseResult> clases_del_diaLunes =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("LUNES") ).toList();
            List<SesionClaseResult> clases_del_diaMartes =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("MARTES") ).toList();
            List<SesionClaseResult> clases_del_diaMiercoles =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("MIÉRCOLES") ).toList();
            List<SesionClaseResult> clases_del_diaJueves =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("JUEVES") ).toList();
            List<SesionClaseResult> clases_del_diaViernes =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("VIERNES") ).toList();
            List<SesionClaseResult> clases_del_diaSabado =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("SÁBADO") ).toList();
            List<SesionClaseResult> clases_del_diaDomingo =
                    lista.stream().filter(c -> c.getDia().toUpperCase().equals("DOMINGO") ).toList();

            clases_del_diaLunes = ordenarClasesPorHorario(clases_del_diaLunes);
            clases_del_diaMartes = ordenarClasesPorHorario(clases_del_diaMartes);
            clases_del_diaMiercoles = ordenarClasesPorHorario(clases_del_diaMiercoles);
            clases_del_diaJueves = ordenarClasesPorHorario(clases_del_diaJueves);
            clases_del_diaViernes = ordenarClasesPorHorario(clases_del_diaViernes);
            clases_del_diaSabado = ordenarClasesPorHorario(clases_del_diaSabado);
            clases_del_diaDomingo = ordenarClasesPorHorario(clases_del_diaDomingo);

            if (!query.isEmpty()){

                //String plantilla = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\template\\listado.html";
                String plantilla =  App.TEMPLATE_ROOT + App.SEPARADOR + "listado.html";
                String html = TextUTP.read(plantilla);

                //String detalle = "C:\\Users\\Fids\\Desktop\\Java\\Projects\\facility_finder\\src\\main\\resources\\template\\listado_item.html";
                String detalle = App.TEMPLATE_ROOT + App.SEPARADOR +  "listado_item.html";
                String html_detalle = TextUTP.read(detalle);

                StringBuilder[] cD = new StringBuilder[7];
                cD[0] = new StringBuilder(); // Lunes
                cD[1] = new StringBuilder(); // Martes
                cD[2] = new StringBuilder(); // Miércoles
                cD[3] = new StringBuilder(); // Jueves
                cD[4] = new StringBuilder(); // Viernes
                cD[5] = new StringBuilder(); // Sábado
                cD[6] = new StringBuilder(); // Domingo


                for (SesionClaseResult item : clases_del_diaLunes) {
                    cD[0].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                for (SesionClaseResult item : clases_del_diaMartes) {
                    cD[1].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                for (SesionClaseResult item : clases_del_diaMiercoles) {
                    cD[2].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                for (SesionClaseResult item : clases_del_diaJueves) {
                    cD[3].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                for (SesionClaseResult item : clases_del_diaViernes) {
                    cD[4].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                for (SesionClaseResult item : clases_del_diaSabado) {
                    cD[5].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                for (SesionClaseResult item : clases_del_diaDomingo) {
                    cD[6].append( html_detalle.replace("${curso}", item.getCurso() )
                            .replace("${horario}", item.getHini() + "-" + item.getHfin())
                            .replace("${ambiente}", item.getAmbiente())
                            .replace("${docente}", item.getDocente())
                    );
                }

                resp.getWriter().println(
                        html.replace("${detalleLunes}", cD[0].toString())
                                .replace("${detalleMartes}", cD[1].toString())
                                .replace("${detalleMiercoles}", cD[2].toString())
                                .replace("${detalleJueves}", cD[3].toString())
                                .replace("${detalleViernes}", cD[4].toString())
                                .replace("${detalleSabado}", cD[5].toString())
                                .replace("${detalleDomingo}", cD[6].toString())
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

    public static List<SesionClaseResult> ordenarClasesPorHorario(List<SesionClaseResult> clases) {
        List<SesionClaseResult> clasesOrdenadas = clases.stream()
                .sorted((c1, c2) -> LocalTime.parse(c1.getHini()).compareTo(LocalTime.parse(c2.getHini())))
                .collect(Collectors.toList());
        return clasesOrdenadas;
    }
}
