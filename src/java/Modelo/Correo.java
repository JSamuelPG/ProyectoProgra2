package Modelo;

import ModeloDAO.SoliMuestraDAO;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author Samuel
 */
public class Correo {

    private String host;
    private String puerto;
    private String usuario;
    private String contrasena;

    public Correo(String host, String puerto, String usuario, String contrasena) {
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    SoliMuestraDAO sdao = new SoliMuestraDAO();

    public void enviarCorreo(SoliMuestra mu, String CorreoA) {
        String solicitante = mu.getCorreoSolicitante();
        String asuntosolicitante = "Muestra Generada";
        String mensajesolicitante = "<div style='border: 1px solid black; padding: 10px;'>" + "<p style='text-align: right;'>Fecha: " + mu.getFechaSolicitud() + "</p>" + "<p>Se le informa que la solicitud de Muestras para la gestión de <b>\"" + mu.getTipoSolicitud() + "\"</b>," + " Número de Muestra <b>\"" + mu.getNoMuestra() + "\"</b> fue registrada, Expediente del: <b>\"" + mu.getTipodeDocumento() + "\"</b>," + " Número: <b>\"" + mu.getNoDedocumento() + "\"</b>, se envía este aviso para seguimiento.</p>" + "<br><br>" + "<p><i>** Esta es una correspondencia autogenerada por el Sistema Muestras. Por favor <b>NO RESPONDA</b> a este correo.</i></p>" + "</div>";
        String asuntoanalista = "Muestra para Análisis";
        String mensajeanalista = "<div style='border: 1px solid black; padding: 10px;'>" + "<p style='text-align: right;'>Fecha: " + mu.getFechaSolicitud() + "</p>" + "<p>Se le informa que la solicitud de Muestras o porción de muestra para la gestión de <b>\"" + mu.getTipoSolicitud() + "\"</b>," + " Número de Muestra <b>\"" + mu.getNoMuestra() + "\"</b> le fue asignada con éxito, Expediente del: <b>\"" + mu.getTipodeDocumento() + "\"</b>," + " Número: <b>\"" + mu.getNoDedocumento() + "\"</b>, se envía este aviso para seguimiento desde su bandeja.</p>" + "<br><br>" + "<p><i>** Esta es una correspondencia autogenerada por el Sistema Muestras. Por favor <b>NO RESPONDA</b> a este correo.</i></p>" + "</div>";
        // Configuración de las propiedades del correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", puerto);
        props.put("mail.smtp.ssl.trust", host);

        // Crear la sesión
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contrasena);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CorreoA));
            message.setSubject(asuntoanalista);
            message.setContent(mensajeanalista, "text/html");
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(usuario));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(solicitante));
            message1.setSubject(asuntosolicitante);
            message1.setContent(mensajesolicitante, "text/html");
            
            Transport.send(message);
            Transport.send(message1);

            System.out.println("Correo enviado con éxito.");
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
