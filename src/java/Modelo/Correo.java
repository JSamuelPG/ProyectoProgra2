
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
 
  public void enviarCorreo(String correoDestinatario, String fecha, String tipoSolicitud, String numeroMuestra, String expediente, String tipoDocumento) {
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
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestinatario));
        message.setSubject("Confirmación de Solicitud de Muestras");

        // Validar los parámetros
        if (correoDestinatario == null || correoDestinatario.isEmpty()) {
            throw new MessagingException("El destinatario no puede estar vacío");
        }

        // Generar el número de muestra usando el nuevo método
        String numeroGenerado = sdao.generarNumeroMuestra(tipoSolicitud, numeroMuestra);

        // Construir el contenido del mensaje
        String contenido = "Fecha: " + fecha + "\n\n" +
                           "Se le informa que la solicitud de Muestras para la gestión de “" + tipoSolicitud + "”," +
                           " Número de Muestra “" + numeroGenerado + "” fue registrada, " +
                           " Expediente: " + expediente + " Hoja de Trámite: " + tipoDocumento + ", se envía este aviso para seguimiento.\n\n" +
                           "** Esta es una correspondencia autogenerada por el Sistema Muestras. Por favor NO RESPONDA a este correo.";

        message.setText(contenido);

        // Enviar el mensaje
        Transport.send(message);

        System.out.println("Correo enviado con éxito.");
    } catch (MessagingException e) {
        System.err.println("Error al enviar el correo: " + e.getMessage());
        e.printStackTrace();
    }
}

}
