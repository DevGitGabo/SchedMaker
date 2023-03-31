package org.pe.edu.utp.load;

import org.pe.edu.utp.result.SesionClaseResult;
import org.pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LoadHorarios {

    public static List<SesionClaseResult> loadData(String path) throws IOException {
        List<SesionClaseResult> scr_list = new LinkedList<>();

        String[] content= TextUTP.readlinesAsArray(path,
                TextUTP.OS.WINDOWS,
                TextUTP.ENCONDING.ISO_8859_1,
                TextUTP.ENCONDING.UTF_8);

        // Por cada linea
        int row=0;

        for (String linea : content) {
            row++;
            /*
             * El CSV que proviene de PS, tiene el siguiente formato
             *
             *      "CAMPO1","CAMPO2", ...,"NOMBRE, APELLIDO", "CAMPO37"
             *
             * Se debe tener MUCHO cuidado al hacer el split, ya que la COMA (,)
             * es usada como parte del nombre del alumno, así que primero
             * debemos cambiar la COMA por ; y luego eliminar los envolvorios (")
             *
             * */
            linea = linea.replace("\",", ";");
            linea = linea.replace("\"", "");

            // Ignorar 1er registro
            if (row > 1) {
                String[] data = linea.split(";");
                String cod_alu = data[6];
                String alumno = data[4];
                String ambiente= data[33];
                String dia= data[43];
                String hini= data[44];
                String hfin= data[45];
                String docente= data[42];
                String curso= data[28];
                String tipo_aula= data[34];

                SesionClaseResult sc = new SesionClaseResult(cod_alu,
                        alumno,
                        ambiente,
                        dia,
                        hini,
                        hfin,
                        docente,
                        curso,
                        tipo_aula);

                scr_list.add(sc);

            }
        }

        return scr_list;
    }

}
