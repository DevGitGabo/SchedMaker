package org.pe.edu.utp.test;

import org.pe.edu.utp.load.LoadHorarios;
import org.pe.edu.utp.result.SesionClaseResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LoadTest {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Fids\\Desktop\\Java\\rpte_mat.csv";

        List<SesionClaseResult> lista = LoadHorarios.loadData(path);
        String codigo = "U17211792";

        // Buscar todas las clases del Lunes
        List<SesionClaseResult> clases_lunes = lista.stream()
                .filter(c -> c.getDia().toUpperCase().equals("LUNES") )
                //.filter(c -> c.getHini().equals("08:00"))
                .filter(alu -> alu.getCod_alu().equals(codigo))
                .collect(Collectors.toList());

        if (!clases_lunes.isEmpty()){
            for (SesionClaseResult item : clases_lunes) {
                System.out.println(item);
            }
        }else{
            System.out.println("Sin clases a las 08:00 o código no existe");
        }

    }
}
