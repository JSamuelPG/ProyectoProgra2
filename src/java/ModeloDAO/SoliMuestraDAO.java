package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUDSM;
import Modelo.Entidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.SoliMuestra;
import java.util.ArrayList;
import java.util.List;
import Modelo.Solicitantes;
import Modelo.Users;
import java.sql.*;
import java.util.Calendar;

public class SoliMuestraDAO implements CRUDSM {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    SoliMuestra s = new SoliMuestra();

    private Conexion conexion;

    public SoliMuestraDAO() {
        conexion = new Conexion();
    }

    public Solicitantes obtenerSolicitante(String nitSolicitante) {
        Solicitantes soli = null;
        String sql = "SELECT * FROM solicitantes WHERE s_Nit = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection(); // Obtener la conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, nitSolicitante);
            System.out.println("NIT buscado: " + nitSolicitante);

            rs = ps.executeQuery(); // Ejecutar la consulta

            if (rs.next()) {
                soli = new Solicitantes();
                soli.setSolId(rs.getInt("s_Id"));
                soli.setSolNit(rs.getString("s_Nit"));
                soli.setSolNombre(rs.getString("s_Nombre"));
                soli.setSolCorreo(rs.getString("s_Correo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return soli;
    }

    public String obtenCorreoAnalista(String idUsuario) {
        String correo = "";
        String sql = "select correo from usuarios where id_usuario = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                correo = rs.getString("correo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return correo;
    }

    public List<Users> obtenAnalista() {
        List<Users> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE id_rol = 2 AND estado = 'Activo'"; // id_rol 2 para Analista
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users usuario = new Users();
                usuario.setIdusuario(rs.getInt("id_usuario"));
                usuario.setPrimerNombre(rs.getString("primer_nombre"));
                usuario.setCorreo(rs.getString("correo"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaUsuarios;
    }

    public boolean existeNoMuestra(String noMuestra) {
        boolean existe = false;
        String query = "SELECT COUNT(*) FROM reg_solmuestra WHERE no_Muestra = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, noMuestra); // Establece el parámetro en la consulta
            rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                existe = true; // El número de muestra ya está registrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe; // retorna el resultado de la verificación
    }

    @Override
    public List listarR() {
        ArrayList<SoliMuestra> listR2 = new ArrayList<>();
        String sql = "select * from reg_solmuestra";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SoliMuestra sm = new SoliMuestra();
                sm.setIdSolicitud(rs.getInt("id_Solicitud"));
                sm.setTipoSolicitud(rs.getString("tipo_Solicitud"));
                sm.setTipoEntidad(rs.getString("tipo_Entidad"));
                sm.setFechaSolicitud(rs.getDate("fecha_Solicitud"));
                sm.setTipodeDocumento(rs.getString("tipode_Documento"));
                sm.setNoDedocumento(rs.getString("no_Dedocumento"));
                sm.setNitProveedor(rs.getString("nit_Proveedor"));
                sm.setNombreProveedor(rs.getString("nombre_Proveedor"));
                sm.setCorreoProveedor(rs.getString("correo_Proveedor"));
                sm.setCorreoSolicitante(rs.getString("correo_Solicitante"));
                sm.setDireccionProveedor(rs.getString("direccion_Proveedor"));
                sm.setTelefonoProveedor(rs.getString("telefono_Proveedor"));
                sm.setNitSolicitante(rs.getString("nit_Solicitante"));
                sm.setNombreSolicitante(rs.getString("nombre_Solicitante"));
                sm.setNoMuestra(rs.getString("no_Muestra"));
                sm.setDescripcionProducto(rs.getString("descrip_Producto"));
                sm.setIdUsuario(rs.getString("id_Usuario"));
                sm.setRegUsuario(rs.getString("Reg_Usuario"));

                listR2.add(sm);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listR2;
    }

    @Override
    public SoliMuestra listR(int idSolicitud) {
        String sql = "SELECT r.*, u.primer_nombre, u.primer_apellido "
                + "FROM reg_solmuestra r "
                + "JOIN usuarios u ON r.id_Usuario = u.id_usuario "
                + "WHERE r.id_Solicitud = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SoliMuestra s = null; // Asegúrate de inicializar el objeto

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSolicitud); // Establece el valor de idSolicitud
            rs = ps.executeQuery();

            if (rs.next()) { // Cambia a if para obtener solo un resultado
                s = new SoliMuestra(); // Instancia el objeto aquí
                s.setIdSolicitud(rs.getInt("id_Solicitud"));
                s.setTipoSolicitud(rs.getString("tipo_Solicitud"));
                s.setTipoEntidad(rs.getString("tipo_Entidad"));
                s.setFechaSolicitud(rs.getDate("fecha_Solicitud"));
                s.setTipodeDocumento(rs.getString("tipode_Documento"));
                s.setNoDedocumento(rs.getString("no_Dedocumento"));
                s.setNitProveedor(rs.getString("nit_Proveedor"));
                s.setNombreProveedor(rs.getString("nombre_Proveedor"));
                s.setCorreoProveedor(rs.getString("correo_Proveedor"));
                s.setCorreoSolicitante(rs.getString("correo_Solicitante"));
                s.setDireccionProveedor(rs.getString("direccion_Proveedor"));
                s.setTelefonoProveedor(rs.getString("telefono_Proveedor"));
                s.setNitSolicitante(rs.getString("nit_Solicitante"));
                s.setNombreSolicitante(rs.getString("nombre_Solicitante"));
                s.setNoMuestra(rs.getString("no_Muestra"));
                s.setDescripcionProducto(rs.getString("descrip_Producto"));
                String analistaAsignado = rs.getString("primer_nombre") + " " + rs.getString("primer_apellido");
                s.setIdUsuario(analistaAsignado);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s; // Puede ser null si no se encuentra el registro
    }

    @Override
    public boolean addR(SoliMuestra smu) {
        String sql = "INSERT INTO reg_SolMuestra(id_Solicitud, tipo_Solicitud, tipo_Entidad, fecha_Solicitud, tipode_Documento, no_Dedocumento, nit_Proveedor, nombre_Proveedor, correo_Proveedor, correo_Solicitante, direccion_Proveedor, telefono_Proveedor, nit_Solicitante, nombre_Solicitante, no_Muestra, descrip_Producto, id_Usuario, Reg_Usuario, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, smu.getIdSolicitud());
            ps.setString(2, smu.getTipoSolicitud());
            ps.setString(3, smu.getTipoEntidad());
            ps.setDate(4, smu.getFechaSolicitud());
            ps.setString(5, smu.getTipodeDocumento());
            ps.setString(6, smu.getNoDedocumento());
            ps.setString(7, smu.getNitProveedor());
            ps.setString(8, smu.getNombreProveedor());
            ps.setString(9, smu.getCorreoProveedor());
            ps.setString(10, smu.getCorreoSolicitante());
            ps.setString(11, smu.getDireccionProveedor());
            ps.setString(12, smu.getTelefonoProveedor());
            ps.setString(13, smu.getNitSolicitante());
            ps.setString(14, smu.getNombreSolicitante());
            ps.setString(15, smu.getNoMuestra());
            ps.setString(16, smu.getDescripcionProducto());
            ps.setString(17, smu.getIdUsuario());
            ps.setString(18, smu.getRegUsuario());
            ps.setString(19, smu.getEstado());

            // Ejecutamos la consulta
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar la solicitud: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean editR(SoliMuestra smu) {
        String sql = "UPDATE reg_solmuestra SET id_Usuario = ? WHERE id_Solicitud = ?"; 
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();

            // Preparar la consulta
            ps = con.prepareStatement(sql);
            ps.setString(1, smu.getIdUsuario());
            ps.setInt(2, smu.getIdSolicitud());

            // Ejecutar la actualización
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0; // Retornar true si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Retornar false si no se actualizó ninguna fila
    }

     
    //MODIFICADO
    @Override
    public boolean eliminarR(int idSolicitud) {
        String sql = "delete from reg_solmuestra where id_Solicitud=" + idSolicitud;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private String numeroCorrelativoCache = null; // Almacena el último número correlativo obtenido

// Método para obtener el número correlativo de la base de datos
    private String obtenerNumeroCorrelativoGeneral(String tipoSolicitud) {
        String numeroCorrelativo = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            // Obtener el año actual
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            // Consultar el último número correlativo para el tipo de solicitud y el año actual
            String sqlSelect = "SELECT MAX(no_Muestra) AS ultimoNumero FROM reg_solmuestra "
                    + "WHERE tipo_solicitud = ? AND no_Muestra LIKE ?";
            ps = con.prepareStatement(sqlSelect);
            ps.setString(1, tipoSolicitud);
            // Establecer el patrón para la búsqueda
            String patron = tipoSolicitud.equals("MuestraParaAnalisis") ? "AR-%-" + year : "OTM-%-" + year;
            ps.setString(2, patron);
            rs = ps.executeQuery();
            if (rs.next()) {
                String ultimoNumero = rs.getString("ultimoNumero");
                // Verificar si hay un último número
                if (ultimoNumero != null && !ultimoNumero.isEmpty()) {
                    // Extraer el número del último registro
                    String[] partes = ultimoNumero.split("-");
                    try {
                        int correlativo = Integer.parseInt(partes[1]); // Obtener el número correlativo
                        // Formatear el nuevo número como string de 5 dígitos
                        numeroCorrelativo = String.format("%05d", correlativo + 1);
                    } catch (NumberFormatException e) {
                        // Si hay algún problema al convertir el correlativo
                        numeroCorrelativo = "00001"; // Empezar de nuevo si hay algún problema
                    }
                } else {
                    // Si no hay número, comenzar en 1
                    numeroCorrelativo = "00001"; // Formato como un número de 5 dígitos
                }
            }
            // Crear el nuevo número de muestra en función del tipo de solicitud
            if ("MuestraParaAnalisis".equals(tipoSolicitud)) {
                numeroCorrelativo = "AR-" + numeroCorrelativo + "-" + year; // Formato para "AR"
            } else if ("SolicitudSinMuestra".equals(tipoSolicitud)) {
                numeroCorrelativo = "OTM-" + numeroCorrelativo + "-" + year; // Formato para "OTM"
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Almacena en caché el número correlativo
        numeroCorrelativoCache = numeroCorrelativo;
        return numeroCorrelativo;
    }

// Método específico para obtener el número correlativo de solicitud
    public String obtenerNumeroCorrelativo(String tipoSolicitud) {
        // Retorna el número correlativo almacenado en caché si ya fue generado
        if (numeroCorrelativoCache != null) {
            return numeroCorrelativoCache;
        }
        // Si no fue generado, llama al método general
        return obtenerNumeroCorrelativoGeneral(tipoSolicitud);
    }

// Método específico para obtener el número correlativo de correo
    public String obtenerNumeroCorrelativoCorreo(String tipoSolicitud2) {
        // Retorna el número correlativo almacenado en caché si ya fue generado
        if (numeroCorrelativoCache != null) {
            return numeroCorrelativoCache;
        }
        // Si no fue generado, llama al método general
        return obtenerNumeroCorrelativoGeneral(tipoSolicitud2);
    }

    // Método para obtener el número correlativo de la base de datos sin caché
    public String obtenerNoCorrelativo(String tipoSolicitud) {
        String numeroCorrelativo = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            // Obtener el año actual
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            // Consultar el último número correlativo para el tipo de solicitud y el año actual
            String sqlSelect = "SELECT MAX(no_Muestra) AS ultimoNumero FROM reg_SolMuestra "
                    + "WHERE tipo_Solicitud = ? AND no_Muestra LIKE ?";
            ps = con.prepareStatement(sqlSelect);
            ps.setString(1, tipoSolicitud);
            // Establecer el patrón para la búsqueda
            String patron = tipoSolicitud.equals("MuestraParaAnalisis") ? "AR-%-" + year : "OTM-%-" + year;
            ps.setString(2, patron);
            rs = ps.executeQuery();
            if (rs.next()) {
                String ultimoNumero = rs.getString("ultimoNumero");
                // Verificar si hay un último número
                if (ultimoNumero != null && !ultimoNumero.isEmpty()) {
                    // Extraer el número del último registro
                    String[] partes = ultimoNumero.split("-");
                    try {
                        int correlativo = Integer.parseInt(partes[1]); // Obtener el número correlativo
                        // Formatear el nuevo número como string de 5 dígitos
                        numeroCorrelativo = String.format("%05d", correlativo + 1);
                    } catch (NumberFormatException e) {
                        // Si hay algún problema al convertir el correlativo
                        numeroCorrelativo = "00001"; // Empezar de nuevo si hay algún problema
                    }
                } else {
                    // Si no hay número, comenzar en 1
                    numeroCorrelativo = "00001"; // Formato como un número de 5 dígitos
                }
            }
            // Crear el nuevo número de muestra en función del tipo de solicitud
            if ("MuestraParaAnalisis".equals(tipoSolicitud)) {
                numeroCorrelativo = "AR-" + numeroCorrelativo + "-" + year; // Formato para "AR"
            } else if ("SolicitudSinMuestra".equals(tipoSolicitud)) {
                numeroCorrelativo = "OTM-" + numeroCorrelativo + "-" + year; // Formato para "OTM"
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return numeroCorrelativo; // Devuelve el número correlativo generado
    }

    public Entidad obtenerPorNit(String nitEntidad) {
        Entidad entidad = null;
        String sql = "SELECT * FROM entidades_registrado WHERE er_Nit = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nitEntidad);
            System.out.println("NIT buscado: " + nitEntidad);

            rs = ps.executeQuery(); // Ejecutar la consulta

            if (rs.next()) {
                entidad = new Entidad();
                entidad.setEntidadId(rs.getInt("er_Id"));
                entidad.setEntidadNit(rs.getString("er_Nit"));
                entidad.setEntidadNombre(rs.getString("er_Nombre"));
                entidad.setEntidadTipo(rs.getString("er_Tipo"));
                entidad.setEntidadCorreo(rs.getString("er_Correo"));
                entidad.setEntidadDireccion(rs.getString("er_Direccion"));
                entidad.setEntidadTelefono(rs.getString("er_Telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entidad;
    }

    public String generarNumeroMuestra(String tipoSolicitud, String numeroMuestra) {
        // Obtener el número correlativo basado en el tipo de solicitud
        String numeroCorrelativo = obtenerNumeroCorrelativo(tipoSolicitud);

        // Generar el número de muestra o solicitud
        String numeroGenerado;
        if ("ConNumerodeMuestra".equals(tipoSolicitud)) {
            numeroGenerado = numeroMuestra + "-" + Calendar.getInstance().get(Calendar.YEAR);
        } else {
            numeroGenerado = numeroCorrelativo; // Usar el número correlativo si no tiene número de muestra
        }

        return numeroGenerado;
    }

    public int obtenerCorrelativo() {
        int siguienteId = 1;
        String sql = "SELECT MAX(id_Solicitud) FROM reg_solmuestra";
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                siguienteId = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return siguienteId;
    }

    public List<SoliMuestra> obtenSolicitudesUsuario(int idUsuario) {
        List<SoliMuestra> listaSolicitudes = new ArrayList<>();
        String sql = "SELECT * FROM reg_SolMuestra WHERE id_Usuario = ?";

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                SoliMuestra solicitud = new SoliMuestra();
                solicitud.setIdSolicitud(rs.getInt("id_Solicitud"));
                solicitud.setNoDedocumento(rs.getString("no_Dedocumento"));
                solicitud.setFechaSolicitud(rs.getDate("fecha_Solicitud"));
                solicitud.setEstado(rs.getString("estado"));
                listaSolicitudes.add(solicitud);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaSolicitudes;
    } 

    
    
    
    
    
        public List<SoliMuestra> buscarSolicitudes(String noMuestra, String nitProveedor) {
            List<SoliMuestra> solicitudes = new ArrayList<>();
            String sql = "SELECT * FROM reg_SolMuestra WHERE (no_Muestra = ? OR ? = '') AND (nit_Proveedor = ? OR ? = '')";

            try {
                Conexion cn = new Conexion();
                con = cn.getConnection(); // Obtiene la conexión
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, noMuestra != null ? noMuestra : "");
                    ps.setString(2, noMuestra != null ? noMuestra : "");
                    ps.setString(3, nitProveedor != null ? nitProveedor : "");
                    ps.setString(4, nitProveedor != null ? nitProveedor : "");

                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            SoliMuestra solicitud = new SoliMuestra();
                            solicitud.setIdSolicitud(rs.getInt("id_Solicitud"));
                            solicitud.setTipoSolicitud(rs.getString("tipo_Solicitud"));
                            solicitud.setTipoEntidad(rs.getString("tipo_Entidad"));
                            solicitud.setFechaSolicitud(rs.getDate("fecha_Solicitud"));
                            solicitud.setTipodeDocumento(rs.getString("tipode_Documento"));
                            solicitud.setNoDedocumento(rs.getString("no_Dedocumento"));
                            solicitud.setNitProveedor(rs.getString("nit_Proveedor"));
                            solicitud.setNombreProveedor(rs.getString("nombre_Proveedor"));
                            solicitud.setCorreoProveedor(rs.getString("correo_Proveedor"));
                            solicitud.setCorreoSolicitante(rs.getString("correo_Solicitante"));
                            solicitud.setDireccionProveedor(rs.getString("direccion_Proveedor"));
                            solicitud.setTelefonoProveedor(rs.getString("telefono_Proveedor"));
                            solicitud.setNitSolicitante(rs.getString("nit_Solicitante"));
                            solicitud.setNombreSolicitante(rs.getString("nombre_Solicitante"));
                            solicitud.setNoMuestra(rs.getString("no_Muestra"));
                            solicitud.setDescripcionProducto(rs.getString("descrip_Producto"));
                            solicitud.setIdUsuario(rs.getString("id_Usuario")); // Cambia a int
                            solicitud.setRegUsuario(rs.getString("Reg_Usuario"));
                            solicitud.setEstado(rs.getString("estado"));

                            solicitudes.add(solicitud);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepciones según lo necesites
            } finally {
                // Cierra la conexión en el bloque finally
                try {
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Manejo de excepciones al cerrar la conexión
                }
            }
            return solicitudes;
        }







}
