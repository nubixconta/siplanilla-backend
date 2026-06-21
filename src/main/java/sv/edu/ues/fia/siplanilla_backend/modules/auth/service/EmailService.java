package sv.edu.ues.fia.siplanilla_backend.modules.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailOrigen;

    @Value("${app.frontend.url:http://localhost:4200}")
    private String frontendUrl;

    /**
     * Enviar correo de desbloqueo de cuenta
     */
    public void enviarCorreoDesbloqueo(String emailDestino, String nombreUsuario, String token) {
        try {
            String linkDesbloqueo = frontendUrl + "/desbloquear?token=" + token;

            String contenido = "Hola " + nombreUsuario + ",\n\n" +
                    "Tu cuenta ha sido bloqueada por múltiples intentos fallidos de login.\n\n" +
                    "Para desbloquear tu cuenta, haz clic en el siguiente enlace (válido por 15 minutos):\n" +
                    linkDesbloqueo + "\n\n" +
                    "Si no solicitaste este desbloqueo, ignora este correo.\n\n" +
                    "Saludos,\n" +
                    "Sistema de Nómina";

            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom(emailOrigen);
            mensaje.setTo(emailDestino);
            mensaje.setSubject("Desbloquea tu cuenta - Sistema de Nómina");
            mensaje.setText(contenido);

            mailSender.send(mensaje);
            log.info("Correo de desbloqueo enviado a: {}", emailDestino);

        } catch (Exception e) {
            log.error("Error al enviar correo de desbloqueo a {}: {}", emailDestino, e.getMessage());
            throw new RuntimeException("Error al enviar correo de desbloqueo: " + e.getMessage());
        }
    }

    /**
     * Enviar correo de confirmación de desbloqueo exitoso
     */
    public void enviarCorreoDesbloqueoExitoso(String emailDestino, String nombreUsuario) {
        try {
            String contenido = "Hola " + nombreUsuario + ",\n\n" +
                    "¡Tu cuenta ha sido desbloqueada exitosamente!\n\n" +
                    "Ya puedes volver a acceder al sistema con tus credenciales.\n\n" +
                    "Si tienes problemas, contacta con soporte.\n\n" +
                    "Saludos,\n" +
                    "Sistema de Nómina";

            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom(emailOrigen);
            mensaje.setTo(emailDestino);
            mensaje.setSubject("Cuenta desbloqueada - Sistema de Nómina");
            mensaje.setText(contenido);

            mailSender.send(mensaje);
            log.info("Correo de confirmación de desbloqueo enviado a: {}", emailDestino);

        } catch (Exception e) {
            log.error("Error al enviar correo de confirmación a {}: {}", emailDestino, e.getMessage());
        }
    }
}
